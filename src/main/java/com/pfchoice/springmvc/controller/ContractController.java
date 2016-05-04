package com.pfchoice.springmvc.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.Message;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.Contract;
import com.pfchoice.core.entity.FilesUpload;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.entity.ReferenceContract;
import com.pfchoice.core.service.ContractService;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.ProviderService;

import ml.rugal.sshcommon.page.Pagination;

@Controller
@SessionAttributes({ "username", "userpath" })
public class ContractController {

	private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

	@Autowired
	private ContractService contractService;

	@Autowired
	private InsuranceService insuranceService;

	@Autowired
	private ProviderService providerService;

	@Autowired
	@Qualifier("contractValidator")
	private Validator validator;

	/**
	 * @param binder
	 */
	@InitBinder("contract")
	public void initBinder(final WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.setValidator(validator);
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
	

	/**
	 * @return
	 */
	@ModelAttribute("contract")
	public Contract createContractModel() {
		return new Contract();
	}

	/**
	 * @return
	 */
	@ModelAttribute("refContract")
	public ReferenceContract createRefContractModel() {
		return new ReferenceContract();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/provider/{id}/contract/new")
	public String addProviderContractPage(@PathVariable Integer id, Model model) {
		logger.info("provider id is"+id);
		Contract contract = createContractModel();
		model.addAttribute("contract", contract);
		model.addAttribute("contractType", "Standard Contract");
		model.addAttribute("pmpmRequired", false);

		return TileDefinitions.PROVIDERCONTRACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/provider/{id}/contract", method = RequestMethod.GET)
	public String updateProviderContractPage(@PathVariable Integer id, Model model) {
		Contract dbContract = contractService.findActiveContractByRefId("provider", id);
		if (dbContract == null) {
			dbContract = createContractModel();
		}
		model.addAttribute("insuranceRequired", true);
		model.addAttribute("contract", dbContract);
		model.addAttribute("pmpmRequired", false);
		model.addAttribute("contractType", "Standard Contract");

		logger.info("Returning contractEdit.jsp page");
		return TileDefinitions.PROVIDERCONTRACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}/contractList", "/user/provider/{id}/contractList" })
	public String handleRequest(@PathVariable Integer id, Model model) {

		List<Contract> listBean = contractService.findAllContractsByRefId("provider", id);
		model.addAttribute("contractList", listBean);
		model.addAttribute("insuranceRequired", false);
		model.addAttribute("pmpmRequired", false);
		model.addAttribute("contractType", "Standard Contract");
		return TileDefinitions.PROVIDERCONTRACTLIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/provider/{id}/contractDisplay" }, method = RequestMethod.GET)
	public String displayProviderContractPage(@PathVariable Integer id, Model model) {
		Contract dbContract = contractService.findActiveContractByRefId("provider", id);
		logger.info("Returning contract.getId()" + dbContract.getId());

		model.addAttribute("contract", dbContract);
		model.addAttribute("pmpmRequired", false);
		logger.info("Returning contractDisplay.jsp page");
		return TileDefinitions.PROVIDERCONTRACTDISPLAY.toString();
	}

	/**
	 * @param id
	 * @param cntId
	 * @param model
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/admin/provider/{id}/contract/{cntId}" }, method = RequestMethod.GET)
	public String updateProviderContractPage(@PathVariable Integer id, @PathVariable Integer cntId, Model model,
			HttpServletResponse response) throws IOException {
		logger.info("provider id is"+id);

		Contract dbContract = contractService.findById(cntId);
		if (dbContract == null) {
			dbContract = createContractModel();
		}
		model.addAttribute("contract", dbContract);
		model.addAttribute("pmpmRequired", false);
		model.addAttribute("contractType", "Standard Contract");

		logger.info("Returning contractEdit.jsp page");
		return TileDefinitions.PROVIDERCONTRACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param contract
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @param fileUpload
	 * @return
	 */
	@RequestMapping(value = "/admin/provider/{id}/contract/save.do", method = RequestMethod.POST, params = { "add" })
	public String newproviderContractAction(@PathVariable Integer id, @Validated Contract contract,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username,
			@RequestParam(required = false, value = "fileUpload") CommonsMultipartFile fileUpload) {
		model.addAttribute("contractType", "Standard Contract");
		logger.info("provider id is"+id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning contractEdit.jsp page");
			return TileDefinitions.PROVIDERCONTRACTEDIT.toString();
		} else {
			Provider dbProvider = providerService.findById(id);
			model.addAttribute("contract", contract);
			contract.setCreatedBy(username);
			contract.setUpdatedBy(username);
			ReferenceContract refContract = createRefContractModel();
			refContract.setPrvdr(dbProvider);
			refContract.setCreatedBy(username);
			refContract.setUpdatedBy(username);
			contract.setReferenceContract(refContract);

			if (fileUpload != null && !"".equals(fileUpload.getOriginalFilename())) {
				FilesUpload uploadFile = new FilesUpload();
				uploadFile.setFileName(fileUpload.getOriginalFilename());
				uploadFile.setContentType(fileUpload.getContentType());
				uploadFile.setData(fileUpload.getBytes());
				uploadFile.setCreatedBy(username);
				uploadFile.setUpdatedBy(username);
				contract.setFilesUpload(uploadFile);
			}

			logger.info("Returning contractEditSuccess.jsp page after create");
			contractService.save(contract);
			model.addAttribute("Message", "Provider Contract Added Successfully");
			List<Contract> listBean = contractService.findAllContractsByRefId("provider", id);
			model.addAttribute("contractList", listBean);
			model.addAttribute("insuranceRequired", false);
			model.addAttribute("pmpmRequired", false);
			return TileDefinitions.PROVIDERCONTRACTLIST.toString();
		}
	}

	/**
	 * @param id
	 * @param contract
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @param fileUpload
	 * @return
	 */
	@RequestMapping(value = "/admin/provider/{id}/contract/save.do", method = RequestMethod.POST, params = { "update" })
	public String saveproviderContractAction(@PathVariable Integer id, @Validated Contract contract,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username,
			@RequestParam(required = false, value = "fileUpload") CommonsMultipartFile fileUpload) {
		logger.info("provider id is"+id);
		model.addAttribute("contractType", "Standard Contract");
		contract.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			logger.info("Returning contractEdit.jsp page");
			contract.setActiveInd('Y');
			return TileDefinitions.PROVIDERCONTRACTEDIT.toString();
		} else {
			final Integer cntId = contract.getId();
			if (cntId != null) {
				contract.setUpdatedBy(username);
				contract.setCreatedBy(username);
				contract.getReferenceContract().setUpdatedBy(username);
				contract.getReferenceContract().setCreatedBy(username);
				contract.getReferenceContract().setActiveInd('Y');
				contract.setActiveInd('Y');
				if (fileUpload != null && !"".equals(fileUpload.getOriginalFilename())) {
					FilesUpload uploadFile = new FilesUpload();
					uploadFile.setFileName(fileUpload.getOriginalFilename());
					uploadFile.setContentType(fileUpload.getContentType());
					uploadFile.setData(fileUpload.getBytes());
					uploadFile.setCreatedBy(username);
					uploadFile.setUpdatedBy(username);
					contract.setFilesUpload(uploadFile);
				}
				logger.info("Returning ContractEditSuccess.jsp page after update");
				contractService.update(contract);
			}
			model.addAttribute("Message", "Provider Contract Updated Successfully");
			List<Contract> listBean = contractService.findAllContractsByRefId("provider", id);
			model.addAttribute("contractList", listBean);
			model.addAttribute("insuranceRequired", false);
			model.addAttribute("pmpmRequired", false);
			return TileDefinitions.PROVIDERCONTRACTLIST.toString();
		}
	}

	/**
	 * @param id
	 * @param contract
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/provider/{id}/contract/save.do", method = RequestMethod.POST, params = { "delete" })
	public String deleteProviderContractAction(@PathVariable Integer id, @Validated Contract contract,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		model.addAttribute("contractType", "Standard Contract");
		Provider dbProvider = providerService.findById(id);
		dbProvider.getRefContracts().forEach(refContract -> {
			refContract.getContract().setActiveInd('N');
			refContract.getContract().setUpdatedBy(username);
			refContract.setActiveInd('N');
			refContract.setUpdatedBy(username);
		});
		providerService.update(dbProvider);
		model.addAttribute("Message", "Provider contract deleted successfully");
		List<Contract> listBean = contractService.findAllContractsByRefId("provider", id);
		model.addAttribute("contractList", listBean);
		model.addAttribute("insuranceRequired", false);
		model.addAttribute("pmpmRequired", false);
		return TileDefinitions.PROVIDERCONTRACTLIST.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/insurance/{id}/contractList", "/user/insurance/{id}/contractList" })
	public String handleInsuranceRequest(@PathVariable Integer id, Model model) {

		List<Contract> listBean = contractService.findAllContractsByRefId("insurance", id);
		model.addAttribute("contractList", listBean);
		model.addAttribute("insuranceRequired", false);
		model.addAttribute("pmpmRequired", true);
		model.addAttribute("contractType", "Contract");
		logger.info("Returning insuranceContractList.jsp page");
		return TileDefinitions.INSURANCECONTRACTLIST.toString();
	}

	/** contract Display where activeInd =N **/

	/**
	 * @param id
	 * @param cntId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/provider/{id}/contract/{cntId}/display", method = RequestMethod.GET)
	public String displayProviderContractPage(@PathVariable Integer id, @PathVariable Integer cntId, Model model) {
		Contract dbContract = contractService.findById(cntId);
		logger.info("Returning contract.getId()" + dbContract.getId());
		logger.info("provider id is"+id);
		model.addAttribute("contract", dbContract);
		model.addAttribute("pmpmRequired", true);
		logger.info("Returning contractDisplay.jsp page");
		return TileDefinitions.PROVIDERCONTRACTEDIT.toString();
	}

	/** End of contract display **/

	/* -- Insurance Contract */

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/insurance/{id}/contract/new")
	public String addInsuranceContractPage(@PathVariable Integer id, Model model) {
		logger.info("insurance id is"+id);
		Contract contract = createContractModel();
		model.addAttribute("contract", contract);
		model.addAttribute("pmpmRequired", true);
		model.addAttribute("contractType", "Contract");
		return TileDefinitions.INSURANCECONTRACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param contract
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @param fileUpload
	 * @return
	 */
	@RequestMapping(value = "/admin/insurance/{id}/contract/save.do", method = RequestMethod.POST, params = { "add" })
	public String addMembershipContractAction(@PathVariable Integer id, @Validated Contract contract,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username,
			@RequestParam(required = false, value = "fileUpload") CommonsMultipartFile fileUpload) {
		logger.info("insurance id is"+id);
		model.addAttribute("contractType", "Contract");
		if (bindingResult.hasErrors()) {
			model.addAttribute("pmpmRequired", true);
			logger.info("Returning insuranceContractEdit.jsp page");
			return TileDefinitions.INSURANCECONTRACTEDIT.toString();
		}
		Insurance dbInsurance = insuranceService.findById(id);
		logger.info("Returning insurance.getId()" + dbInsurance.getId());
		model.addAttribute("contract", contract);
		contract.setCreatedBy(username);
		contract.setUpdatedBy(username);
		ReferenceContract refCnt = createRefContractModel();
		refCnt.setCreatedBy(username);
		refCnt.setUpdatedBy(username);
		refCnt.setIns(dbInsurance);
		contract.setReferenceContract(refCnt);

		if (fileUpload != null && !"".equals(fileUpload.getOriginalFilename())) {
			FilesUpload uploadFile = new FilesUpload();
			uploadFile.setFileName(fileUpload.getOriginalFilename());
			uploadFile.setContentType(fileUpload.getContentType());
			uploadFile.setData(fileUpload.getBytes());
			uploadFile.setCreatedBy(username);
			uploadFile.setUpdatedBy(username);
			contract.setFilesUpload(uploadFile);
		}

		contractService.save(contract);
		logger.info("Returning insuranceContractEditSuccess.jsp page after create");
		model.addAttribute("pmpmRequired", true);
		model.addAttribute("Message", "Insurance Contract Added Successfully");
		List<Contract> listBean = contractService.findAllContractsByRefId("insurance", id);
		model.addAttribute("contractList", listBean);
		model.addAttribute("insuranceRequired", false);
		model.addAttribute("pmpmRequired", true);
		return TileDefinitions.INSURANCECONTRACTLIST.toString();
	}

	/**
	 * @param id
	 * @param contract
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @param fileUpload
	 * @return
	 */
	@RequestMapping(value = "/admin/insurance/{id}/contract/save.do", method = RequestMethod.POST, params = {
			"update" })
	public String saveMembershipContractAction(@PathVariable Integer id, @Validated Contract contract,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username,
			@RequestParam(required = false, value = "fileUpload") CommonsMultipartFile fileUpload) {
		logger.info("insurance id is"+id);
		model.addAttribute("contractType", "Contract");
		if (bindingResult.hasErrors()) {
			logger.info("Returning insuranceContractEdit.jsp page");
			model.addAttribute("pmpmRequired", true);
			contract.setActiveInd('Y');
			return TileDefinitions.INSURANCECONTRACTEDIT.toString();
		}

		if (null != contract.getId()) {
			logger.info("Returning ContractEditSuccess.jsp page after update");
			contract.setUpdatedBy(username);
			contract.setCreatedBy(username);
			contract.getReferenceContract().setUpdatedBy(username);
			contract.getReferenceContract().setCreatedBy(username);
			contract.getReferenceContract().setActiveInd('Y');
			contract.setActiveInd('Y');
			if (fileUpload != null && !"".equals(fileUpload.getOriginalFilename())) {
				FilesUpload uploadFile = new FilesUpload();
				uploadFile.setFileName(fileUpload.getOriginalFilename());
				uploadFile.setContentType(fileUpload.getContentType());
				uploadFile.setData(fileUpload.getBytes());
				uploadFile.setCreatedBy(username);
				uploadFile.setUpdatedBy(username);
				contract.setFilesUpload(uploadFile);
			}
			contractService.update(contract);
			model.addAttribute("Message", "Insurance Contract Updated Successfully");
			List<Contract> listBean = contractService.findAllContractsByRefId("insurance", id);
			model.addAttribute("contractList", listBean);
			model.addAttribute("insuranceRequired", false);
			model.addAttribute("pmpmRequired", true);
			return TileDefinitions.INSURANCECONTRACTLIST.toString();
		}
		return TileDefinitions.INSURANCECONTRACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param contract
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/insurance/{id}/contract/save.do", method = RequestMethod.POST, params = {
			"delete" })
	public String deleteInsuranceContractAction(@PathVariable Integer id, @Validated Contract contract,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("insurance id is"+id);
		model.addAttribute("contractType", "Contract");
		if (bindingResult.hasErrors()) {
			model.addAttribute("pmpmRequired", true);
			model.addAttribute("contractType", "Contract");
			logger.info("Returning insuranceContractEdit.jsp page");
			contract.setActiveInd('Y');
			return TileDefinitions.INSURANCECONTRACTEDIT.toString();
		}

		if (null != contract.getId()) {
			logger.info("Returning ContractEditSuccess.jsp page before delete");
			contract.setUpdatedBy(username);
			contract.setCreatedBy(username);
			contract.setActiveInd('N');
			contract.getReferenceContract().setUpdatedBy(username);
			contract.getReferenceContract().setCreatedBy(username);
			contract.getReferenceContract().setActiveInd('N');

			contractService.update(contract);
			model.addAttribute("Message", "Insurance contract deleted successfully");
			List<Contract> listBean = contractService.findAllContractsByRefId("insurance", id);
			model.addAttribute("contractList", listBean);
			model.addAttribute("insuranceRequired", false);
			model.addAttribute("pmpmRequired", true);
			return TileDefinitions.INSURANCECONTRACTLIST.toString();
		}
		return TileDefinitions.INSURANCECONTRACTEDIT.toString();

	}

	/**
	 * @param id
	 * @param cntId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/insurance/{id}/contract/{cntId}", method = RequestMethod.GET)
	public String updateInsuranceContractPage(@PathVariable Integer id, @PathVariable Integer cntId, Model model) {
		logger.info("insurance id is"+id);
		Contract dbContract = contractService.findById(cntId);
		model.addAttribute("contractType", "Contract");
		if (dbContract == null) {
			dbContract = createContractModel();
		}
		model.addAttribute("contract", dbContract);
		model.addAttribute("pmpmRequired", true);

		logger.info("Returning insuranceContractEdit.jsp page");
		return TileDefinitions.INSURANCECONTRACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param cntId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/insurance/{id}/contract/{cntId}/display", method = RequestMethod.GET)
	public String displayInsuranceContractPage(@PathVariable Integer id, @PathVariable Integer cntId, Model model) {
		Contract dbContract = contractService.findActiveContractByRefId("insurance", id);
		logger.info("Returning contract.getId()" + dbContract.getId());
		model.addAttribute("pmpmRequired", true);
		model.addAttribute("contract", dbContract);
		logger.info("Returning contractDisplay.jsp page");
		return TileDefinitions.INSURANCECONTRACTEDIT.toString();
	}

	/* -- End of Insurance Contract */

	/**
	 * @param cntId
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/contract/{cntId}/file", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public String downloadContractFile(@PathVariable Integer cntId, HttpServletResponse response)
			throws IOException {
		Contract dbContract = contractService.findById(cntId);
		logger.info("fetching contractFile for contract id" + cntId);
		byte[] bytes = dbContract.getFilesUpload().getData();
		response.setContentType(dbContract.getFilesUpload().getContentType());
		response.setHeader("Content-Disposition", "attachment;filename=" + dbContract.getFilesUpload().getFileName());
		OutputStream os = response.getOutputStream();
		os.write(bytes); // newHtml is a String.
		os.flush();
		// response.getOutputStream().write(bytes);
		// response.getWriter().write(fileContent);

		logger.info("Returning contractFile page");
		return TileDefinitions.PROVIDERCONTRACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/provider/{id}/prvdrInsContract/new")
	public String addprvdrinsContractPage(@PathVariable Integer id, Model model) {

		Set<Insurance> insList = getInsuranceList(id, true);

		Contract contract = createContractModel();
		model.addAttribute("contract", contract);
		model.addAttribute("insuranceRequired", true);
		model.addAttribute("pmpmRequired", true);
		model.addAttribute("contractType", "Third Party Agreement");
		model.addAttribute("insuranceList", insList);
		return TileDefinitions.PROVIDERCONTRACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}/prvdrInsContractList", "/user/provider/{id}/prvdrInsContractList" })
	public String getInsuranceContractList(@PathVariable Integer id, Model model)  {

		List<Contract> listBean = contractService.findAllContractsByRefId("providerInsurance", id);
		model.addAttribute("contractList", listBean);
		model.addAttribute("insuranceRequired", true);
		model.addAttribute("contractType", "Third Party Agreement");
		model.addAttribute("pmpmRequired", true);
		return TileDefinitions.PROVIDERCONTRACTLIST.toString();
	}

	/**
	 * @param id
	 * @param cntId
	 * @param model
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/provider/{id}/prvdrInsContract/{cntId}", method = RequestMethod.GET)
	public String updateInsuranceProviderContractPage(@PathVariable Integer id, @PathVariable Integer cntId,
			Model model, HttpServletResponse response) throws IOException {
		logger.info("insurance id is"+id);
		Contract dbContract = contractService.findById(cntId);
		model.addAttribute("contractType", "Third Party Agreement");
		if (dbContract == null) {
			dbContract = createContractModel();
		}

		dbContract.setInsId(dbContract.getReferenceContract().getIns().getId());
		Pagination page = contractService.getPage(0, 20000);
		List<Contract> contracts = (List<Contract>) page.getList();
		List<Insurance> insList = new ArrayList<>();
		insList.clear();
		contracts.forEach(contract1 -> {
			if (contract1.getActiveInd() == 'Y' && contract1.getReferenceContract().getActiveInd() == 'Y'
					&& contract1.getReferenceContract().getIns() != null
					&& contract1.getReferenceContract().getPrvdr() == null) {
				insList.add(contract1.getReferenceContract().getIns());
			}
		});
		model.addAttribute("contract", dbContract);
		model.addAttribute("insuranceList", insList);
		model.addAttribute("insuranceRequired", true);
		model.addAttribute("pmpmRequired", true);

		logger.info("Returning contractEdit.jsp page");
		return TileDefinitions.PROVIDERCONTRACTEDIT.toString();
	}

	/**
	 * @param id
	 * @param contract
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @param fileUpload
	 * @return
	 */
	@RequestMapping(value = "/admin/provider/{id}/prvdrInsContract/save.do", method = RequestMethod.POST, params = {
			"add" })
	public String newInsuranceproviderContractAction(@PathVariable Integer id, @Validated Contract contract,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username,
			@RequestParam(required = false, value = "fileUpload") CommonsMultipartFile fileUpload) {

		model.addAttribute("contractType", "Third Party Agreement");
		if (bindingResult.hasErrors()) {
			Set<Insurance> insList = getInsuranceList(id, true);
			model.addAttribute("insuranceList", insList);
			model.addAttribute("pmpmRequired", true);
			model.addAttribute("insuranceRequired", true);

			logger.info("Returning contractEdit.jsp page");
			return TileDefinitions.PROVIDERCONTRACTEDIT.toString();
		} else {
			final Integer insId = contract.getInsId();
			Insurance dbInsurance = insuranceService.findById(insId);
			Provider dbProvider = providerService.findById(id);
			model.addAttribute("contract", contract);
			contract.setCreatedBy(username);
			contract.setUpdatedBy(username);
			ReferenceContract refContract = createRefContractModel();
			refContract.setIns(dbInsurance);
			refContract.setPrvdr(dbProvider);
			refContract.setCreatedBy(username);
			refContract.setUpdatedBy(username);
			contract.setReferenceContract(refContract);

			if (fileUpload != null && !"".equals(fileUpload.getOriginalFilename())) {
				FilesUpload uploadFile = new FilesUpload();
				uploadFile.setFileName(fileUpload.getOriginalFilename());
				uploadFile.setContentType(fileUpload.getContentType());
				uploadFile.setData(fileUpload.getBytes());
				uploadFile.setCreatedBy(username);
				uploadFile.setUpdatedBy(username);
				contract.setFilesUpload(uploadFile);
			}

			logger.info("Returning contractEditSuccess.jsp page after create");
			contractService.save(contract);
			List<Contract> listBean = contractService.findAllContractsByRefId("providerInsurance", id);
			model.addAttribute("contractList", listBean);
			model.addAttribute("insuranceRequired", true);
			model.addAttribute("pmpmRequired", true);
			return TileDefinitions.PROVIDERCONTRACTLIST.toString();
		}
	}

	/**
	 * @param id
	 * @param contract
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @param fileUpload
	 * @return
	 */
	@RequestMapping(value = "/admin/provider/{id}/prvdrInsContract/save.do", method = RequestMethod.POST, params = {
			"update" })
	public String saveInsuranceProviderContractAction(@PathVariable Integer id, @Validated Contract contract,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username,
			@RequestParam(required = false, value = "fileUpload") CommonsMultipartFile fileUpload) {

		model.addAttribute("contractType", "Third Party Agreement");
		contract.setActiveInd('Y');

		if (bindingResult.hasErrors()) {
			Set<Insurance> insList = getInsuranceList(id, false);
			model.addAttribute("contract", contract);
			model.addAttribute("insuranceList", insList);
			model.addAttribute("pmpmRequired", true);
			model.addAttribute("insuranceRequired", true);
			logger.info("Returning contractEdit.jsp page");
			contract.setActiveInd('Y');
			return TileDefinitions.PROVIDERCONTRACTEDIT.toString();
		} else {
			final Integer cntId = contract.getId();
			if (cntId != null) {
				final Integer insId = contract.getInsId();
				Insurance dbInsurance = insuranceService.findById(insId);
				contract.getReferenceContract().setIns(dbInsurance);
				contract.setUpdatedBy(username);
				contract.setCreatedBy(username);
				contract.getReferenceContract().setUpdatedBy(username);
				contract.getReferenceContract().setCreatedBy(username);
				contract.getReferenceContract().setActiveInd('Y');
				contract.setActiveInd('Y');
				if (fileUpload != null && !"".equals(fileUpload.getOriginalFilename())) {
					FilesUpload uploadFile = new FilesUpload();
					uploadFile.setFileName(fileUpload.getOriginalFilename());
					uploadFile.setContentType(fileUpload.getContentType());
					uploadFile.setData(fileUpload.getBytes());
					uploadFile.setCreatedBy(username);
					uploadFile.setUpdatedBy(username);
					contract.setFilesUpload(uploadFile);
				}
				logger.info("Returning ContractEditSuccess.jsp page after update");
				contractService.update(contract);
			}
			model.addAttribute("Message", "Insurance Provider Contract Updated Successfully");
			model.addAttribute("insuranceequired", true);
			List<Contract> listBean = contractService.findAllContractsByRefId("providerInsurance", id);
			model.addAttribute("contractList", listBean);
			model.addAttribute("insuranceRequired", true);
			model.addAttribute("pmpmRequired", true);
			return TileDefinitions.PROVIDERCONTRACTLIST.toString();
		}
	}

	/**
	 * @param id
	 * @param contract
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/provider/{id}/prvdrInsContract/save.do", method = RequestMethod.POST, params = {
			"delete" })
	public String deleteInsuranceProviderContractAction(@PathVariable Integer id, @Validated Contract contract,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {

		model.addAttribute("contractType", "Third Party Agreement");
		if (bindingResult.hasErrors()) {
			Set<Insurance> insList = getInsuranceList(id, false);
			model.addAttribute("insuranceList", insList);
			model.addAttribute("pmpmRequired", true);
			model.addAttribute("insuranceRequired", true);
			logger.info("Returning providerContractEdit.jsp page");
			contract.setActiveInd('Y');
			return TileDefinitions.PROVIDERCONTRACTEDIT.toString();
		}
		final Integer cntId = contract.getId();
		if (cntId != null) {
			logger.info("Returning ContractEditSuccess.jsp page before delete");
			Contract dbContract = contractService.findById(cntId);

			dbContract.setUpdatedBy(username);
			dbContract.setActiveInd('N');
			dbContract.getReferenceContract().setUpdatedBy(username);
			dbContract.getReferenceContract().setActiveInd('N');
			contractService.update(dbContract);
			model.addAttribute("Message", "Provider contract deleted successfully");
			model.addAttribute("pmpmRequired", true);
			model.addAttribute("insuranceequired", true);
			List<Contract> listBean = contractService.findAllContractsByRefId("providerInsurance", id);
			model.addAttribute("contractList", listBean);
			model.addAttribute("insuranceRequired", true);
			return TileDefinitions.PROVIDERCONTRACTLIST.toString();
		}
		return TileDefinitions.PROVIDERCONTRACTEDIT.toString();

	}

	/**
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/insurance/{id}/contractJsonList",
			"/user/insurance/{id}/contractJsonList" }, method = RequestMethod.GET)
	public Message viewInsuranceContract(@PathVariable Integer id)  {
		List<Contract> listBean = contractService.findAllContractsByRefId("insuranceProvider", id);
		return Message.successMessage(CommonMessageContent.CONTRACT_LIST, JsonConverter.getJsonObject(listBean));
	}

	/**
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/provider/{id}/contractJsonList",
			"/user/provider/{id}/contractJsonList" }, method = RequestMethod.GET)
	public Message viewProviderContract(@PathVariable Integer id)  {
		List<Contract> listBean = contractService.findAllContractsByRefId("provider", id);
		return Message.successMessage(CommonMessageContent.CONTRACT_LIST, JsonConverter.getJsonObject(listBean));
	}

	/**
	 * @param id
	 * @param add
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Set<Insurance> getInsuranceList(Integer id, boolean add) {
		Pagination page = contractService.getPage(0, 20000);
		List<Contract> contracts = (List<Contract>) page.getList();
		Set<Insurance> insList = new HashSet<>();
		insList.clear();
		contracts.forEach(contract1 -> {
			if (contract1.getActiveInd() == 'Y' 
					&& contract1.getReferenceContract().getActiveInd() == 'Y'
					&& contract1.getReferenceContract().getIns() != null) {
				
				if (contract1.getReferenceContract().getPrvdr() == null) {
					insList.add(contract1.getReferenceContract().getIns());
				} else if (contract1.getReferenceContract().getPrvdr().getId() == id && add) {
						insList.remove(contract1.getReferenceContract().getIns());
				}
			}
		});
		return insList;
	}

}
