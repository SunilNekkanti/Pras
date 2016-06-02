package com.pfchoice.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Provider;
import com.pfchoice.core.service.InsuranceService;
import com.pfchoice.core.service.ProviderService;

import ml.rugal.sshcommon.page.Pagination;

@Controller
@SessionAttributes({ "username", "userpath" })
public class ProviderController {

	private static final Logger logger = LoggerFactory.getLogger(ProviderController.class);

	@Autowired
	private InsuranceService insuranceService;

	@Autowired
	private ProviderService providerService;

	@Autowired
	@Qualifier("providerValidator")
	private Validator validator;

	/**
	 * @param binder
	 */
	@InitBinder("provider")
	public void initBinder(final WebDataBinder binder) {
		binder.setValidator(validator);
	}

	/**
	 * @return
	 */
	@ModelAttribute("provider")
	public Provider createProviderModel() {
		return new Provider();
	}

	/**
	 * @return
	 */
	@ModelAttribute("activeIndMap")
	public Map<String, String> populateActiveIndList() {
		return PrasUtil.getActiveIndMap();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("insuranceList")
	public List<Insurance> populateInsuranceList() {
		Pagination page = insuranceService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<Insurance>) page.getList();
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/new" })
	public String addProviderPage(final Model model) {

		Provider provider = createProviderModel();
		model.addAttribute("provider", provider);
		return TileDefinitions.PROVIDERNEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}", "/user/provider/{id}" }, method = RequestMethod.GET)
	public String updateProviderPage(@PathVariable Integer id, Model model) {

		Provider dbProvider = providerService.findById(id);
		logger.info("Returning provider.getId()" + dbProvider.getId());

		model.addAttribute("provider", dbProvider);
		logger.info("Returning providerView.jsp page");
		return TileDefinitions.PROVIDERDETAILS.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}/details",
			"/user/provider/{id}/details" }, method = RequestMethod.GET)
	public String viewProviderPage(@PathVariable Integer id, Model model) {

		Provider dbProvider = providerService.findById(id);
		logger.info("Returning provider.getId()" + dbProvider.getId());

		model.addAttribute("provider", dbProvider);
		logger.info("Returning providerView.jsp page");
		return TileDefinitions.PROVIDEREDIT.toString();
	}

	/**
	 * @param provider
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newProviderAction(@Validated Provider provider, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {

		if (bindingResult.hasErrors()) {
			logger.info("Returning providerEdit.jsp page");
			return TileDefinitions.PROVIDERNEW.toString();
		}

		if (providerService.findByPrvdrNPI(provider.getCode()) != null) {
			FieldError prvdrError = new FieldError("code", "code", provider.getCode(), false, null, null,
					provider.getCode() + " already exist");
			bindingResult.addError(prvdrError);
			return TileDefinitions.PROVIDERNEW.toString();
		}

		logger.info("Returning ProviderSuccess.jsp page after create");
		model.addAttribute("provider", provider);
		provider.setCreatedBy(username);
		provider.setUpdatedBy(username);
		providerService.save(provider);
		model.addAttribute("Message", "Provider details added Successfully");
		return TileDefinitions.PROVIDERLIST.toString();
	}

	/**
	 * @param id
	 * @param provider
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String updateProviderAction(@PathVariable Integer id, @Validated Provider provider,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		provider.setActiveInd('Y');
		logger.info("provider id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning providerEdit.jsp page");
			return TileDefinitions.PROVIDEREDIT.toString();
		}

		if (!providerService.isUniquePrvdrNPI(provider.getId(), provider.getCode())) {
			FieldError prvdrError = new FieldError("code", "code", provider.getCode(), false, null, null,
					provider.getCode() + " already exist");
			bindingResult.addError(prvdrError);
			return TileDefinitions.PROVIDEREDIT.toString();
		}

		if (provider.getId() != null) {
			logger.info("Returning ProviderEditSuccess.jsp page after update");
			provider.setUpdatedBy(username);
			provider.setCreatedBy(username);
			providerService.update(provider);
			model.addAttribute("Message", "Provider Details Updated Successfully");
		}
		return TileDefinitions.PROVIDEREDIT.toString();
	}

	/**
	 * @param id
	 * @param provider
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/provider/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteInsuranceAction(@PathVariable Integer id, Model model,
			@ModelAttribute("username") String username) {

		Provider dbProvider = providerService.findById(id);
		dbProvider.setActiveInd(new Character('N'));
		dbProvider.setUpdatedBy(username);
		providerService.update(dbProvider);
		logger.info("Returning providerSuccess.jsp page after delete");
		model.addAttribute("Message", "Provider Details Deleted Successfully");
		return TileDefinitions.PROVIDEREDIT.toString();
	}

}
