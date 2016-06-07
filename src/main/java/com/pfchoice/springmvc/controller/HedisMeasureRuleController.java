package com.pfchoice.springmvc.controller;

import static com.pfchoice.common.SystemDefaultProperties.DEFAULT_PAGE_NO;
import static com.pfchoice.common.SystemDefaultProperties.HUGE_LIST_SIZE;
import static com.pfchoice.common.SystemDefaultProperties.MEDIUM_LIST_SIZE;
import static com.pfchoice.common.SystemDefaultProperties.SMALL_LIST_SIZE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.pfchoice.common.CommonMessageContent;

import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.CPTMeasure;
import com.pfchoice.core.entity.FrequencyType;
import com.pfchoice.core.entity.Gender;
import com.pfchoice.core.entity.HedisMeasure;
import com.pfchoice.core.entity.HedisMeasureRule;
import com.pfchoice.core.entity.ICDMeasure;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Problem;
import com.pfchoice.core.service.CPTMeasureService;
import com.pfchoice.core.service.FrequencyTypeService;
import com.pfchoice.core.service.GenderService;
import com.pfchoice.core.service.HedisMeasureRuleService;
import com.pfchoice.core.service.HedisMeasureService;
import com.pfchoice.core.service.ICDMeasureService;
import com.pfchoice.core.service.InsuranceService;

import ml.rugal.sshcommon.hibernate.Updater;
import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class HedisMeasureRuleController {

	private static final Logger logger = LoggerFactory.getLogger(HedisMeasureRuleController.class);

	@Autowired
	private HedisMeasureService hedisMeasureService;

	@Autowired
	private HedisMeasureRuleService hedisMeasureRuleService;

	@Autowired
	private CPTMeasureService cptMeasureService;

	@Autowired
	private FrequencyTypeService frequencyTypeService;

	@Autowired
	private ICDMeasureService icdMeasureService;

	@Autowired
	private GenderService genderService;

	@Autowired
	private InsuranceService insuranceService;

	@Autowired
	@Qualifier("hedisMeasureRuleValidator")
	private Validator validator;

	/**
	 * @param binder
	 */
	@InitBinder("hedisMeasureRule")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.setValidator(validator);
	}

	/**
	 * @return
	 */
	@ModelAttribute("hedisMeasureRule")
	public HedisMeasureRule createHedisMeasureRuleModel() {
		return new HedisMeasureRule();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("hedisMeasureList")
	public List<HedisMeasure> populateHedisMeasureList() {

		Pagination page = hedisMeasureService.getPage(DEFAULT_PAGE_NO, MEDIUM_LIST_SIZE);
		return (List<HedisMeasure>) page.getList();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("cptMeasureList")
	public List<CPTMeasure> populateCPTMeasureList() {

		Pagination page = cptMeasureService.getPage(DEFAULT_PAGE_NO, HUGE_LIST_SIZE);
		return (List<CPTMeasure>) page.getList();
	}

	/**
	 * @return
	 */
	@ModelAttribute("cptMeasureListAjax")
	public List<CPTMeasure> populateCPTMeasureListAjax() {
		List<CPTMeasure> cptMeasureList = new ArrayList<>();
		return cptMeasureList;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("frequencyTypeList")
	public List<FrequencyType> populateFrequencyList() {

		Pagination page = frequencyTypeService.getPage(DEFAULT_PAGE_NO, SMALL_LIST_SIZE);
		return (List<FrequencyType>) page.getList();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("genderList")
	public List<Gender> populateGenderList() {

		Pagination page = genderService.getPage(DEFAULT_PAGE_NO, SMALL_LIST_SIZE);
		return (List<Gender>) page.getList();
	}

	/**
	 * @return
	 */
	@ModelAttribute("icdMeasureListAjax")
	public List<ICDMeasure> populateICDMeasureListAjax() {
		List<ICDMeasure> icdMeasureList = new ArrayList<>();
		return icdMeasureList;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("icdMeasureList")
	public List<ICDMeasure> populateICDMeasureList() {

		Pagination page = icdMeasureService.getPage(DEFAULT_PAGE_NO, HUGE_LIST_SIZE);
		return (List<ICDMeasure>) page.getList();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("insuranceList")
	public List<Insurance> populateInsuranceList() {

		Pagination page = insuranceService.getPage(DEFAULT_PAGE_NO, MEDIUM_LIST_SIZE);
		return (List<Insurance>) page.getList();
	}

	/**
	 * @return
	 */
	@ModelAttribute("effYearList")
	public SortedSet<Integer> populateEffectiveYearList() {
		return PrasUtil.getHedisEffectiveYearList();
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/hedisMeasureRule/new")
	public String addHedisMeasureRulePage(Model model) {

		HedisMeasureRule hedisMeasureRule = createHedisMeasureRuleModel();
		model.addAttribute("hedisMeasureRule", hedisMeasureRule);
		return TileDefinitions.HEDISMEASURERULENEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/hedisMeasureRule/{id}",
			"/user/hedisMeasureRule/{id}" }, method = RequestMethod.GET)
	public String updateHedisMeasureRulePage(@PathVariable Integer id, Model model) {

		HedisMeasureRule dbHedisMeasureRule = hedisMeasureRuleService.findById(id);
		logger.info("Returning hedisMeasureRule.getId()" + dbHedisMeasureRule.getId());

		Set<CPTMeasure> cptMeasureList = dbHedisMeasureRule.getCptCodes();
		Set<ICDMeasure> icdMeasureList = dbHedisMeasureRule.getIcdCodes();

		model.addAttribute("cptMeasureListAjax", cptMeasureList);
		model.addAttribute("icdMeasureListAjax", icdMeasureList);
		model.addAttribute("hedisMeasureRule", dbHedisMeasureRule);
		logger.info("Returning hedisMeasureRuleEdit.jsp page");
		return TileDefinitions.HEDISMEASURERULEEDIT.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin/hedisMeasureRuleAjax/{id}", method = RequestMethod.GET)
	public String updateHedisMeasureRuleAjaxPage(@PathVariable Integer id, Model model) {

		HedisMeasureRule dbHedisMeasureRule = hedisMeasureRuleService.findById(id);
		logger.info("Returning hedisMeasureRule.getId()" + dbHedisMeasureRule.getId());

		Set<CPTMeasure> cptMeasureList = dbHedisMeasureRule.getCptCodes();
		Set<ICDMeasure> icdMeasureList = dbHedisMeasureRule.getIcdCodes();

		model.addAttribute("hedisMeasureRule", dbHedisMeasureRule);
		model.addAttribute("cptMeasureListAjax", cptMeasureList);
		model.addAttribute("icdMeasureListAjax", icdMeasureList);
		logger.info("Returning hedisMeasureRuleEditAjax.jsp page");
		return TileDefinitions.HEDISMEASURERULEEDITAJAX.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/hedisMeasureRule/{id}/display", method = RequestMethod.GET)
	public String displayHedisMeasureRulePage(@PathVariable Integer id, Model model) {

		HedisMeasureRule dbHedisMeasureRule = hedisMeasureRuleService.findById(id);
		logger.info("Returning hedisMeasureRule.getId()" + dbHedisMeasureRule.getId());

		model.addAttribute("hedisMeasureRule", dbHedisMeasureRule);
		logger.info("Returning hedisMeasureRuleDisplay.jsp page");
		return TileDefinitions.HEDISMEASURERULEDISPLAY.toString();
	}

	/**
	 * @param hedisMeasureRule
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/hedisMeasureRule/save.do", method = RequestMethod.POST, params = { "add" })
	public String addHedisMeasureAction(
			@ModelAttribute("hedisMeasureRule") @Validated HedisMeasureRule hedisMeasureRule,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning hedisMeasureRuleEdit.jsp page");
			return TileDefinitions.HEDISMEASURERULENEW.toString();
		}

		model.addAttribute("hedisMeasureRule", hedisMeasureRule);
		hedisMeasureRule.setCreatedBy(username);
		hedisMeasureRule.setUpdatedBy(username);
		model.addAttribute("Message", "Hedis Measure added successfully");
		logger.info("Returning hedisMeasureRuleEditSuccess.jsp page after create");
		hedisMeasureRuleService.save(hedisMeasureRule);
		return TileDefinitions.HEDISMEASURERULELIST.toString();
	}

	/**
	 * @param id
	 * @param hedisMeasureRule
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/hedisMeasureRule/{id}/save.do", method = RequestMethod.POST, params = { "update" })
	public String saveHedisMeasureRuleAction(@PathVariable Integer id,
			@ModelAttribute("hedisMeasureRule") @Validated HedisMeasureRule hedisMeasureRule,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		
		logger.info("hedisMeasurerule id is" + id);
		hedisMeasureRule.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			hedisMeasureRule.setActiveInd('Y');
			logger.info("Returning  hedisMeasureRuleEdit.jsp page");
			return TileDefinitions.HEDISMEASURERULEEDIT.toString();
		}

		if (hedisMeasureRule.getId() != null) {
			logger.info("Returning hedisMeasureRuleEditSuccess.jsp page after update");

			if (hedisMeasureRule.getCptOrIcd() != 2) {
				hedisMeasureRule.setPbm(null);
			}
			hedisMeasureRule.setUpdatedBy(username);
			hedisMeasureRuleService.update(hedisMeasureRule);
			model.addAttribute("Message", "Hedis Measure update successfully");
			return TileDefinitions.HEDISMEASURERULELIST.toString();
		}
		return TileDefinitions.HEDISMEASURERULEEDIT.toString();
	}

	/**
	 * @param id
	 * @param hedisMeasureRule
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/hedisMeasureRule/{id}/save.do", method = RequestMethod.POST, params = { "delete" })
	public String deleteHedisMeasureAction(@PathVariable Integer id,
			@ModelAttribute("hedisMeasureRule") @Validated HedisMeasureRule hedisMeasureRule,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("hedisMeasurerule id is" + id);
		if (bindingResult.hasErrors()) {
			hedisMeasureRule.setActiveInd('Y');
			logger.info("Returning  hedisMeasureRuleEdit.jsp page");
			return TileDefinitions.HEDISMEASURERULEEDIT.toString();
		}

		if (null != hedisMeasureRule.getId()) {
			logger.info("Returning hedisMeasureRuleEditSuccess.jsp page after update");
			hedisMeasureRule.setActiveInd('N');
			hedisMeasureRule.setUpdatedBy(username);
			hedisMeasureRuleService.update(hedisMeasureRule);
			model.addAttribute("Message", "Hedis Measure delete successfully");
			return TileDefinitions.HEDISMEASURERULELIST.toString();
		}
		return TileDefinitions.HEDISMEASURERULEEDIT.toString();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = { "/admin/hedisMeasureRule/cpt", "/user/hedisMeasureRule/cpt" })
	public Message getCPTMeasure() {
		Pagination page = cptMeasureService.getPage(DEFAULT_PAGE_NO, HUGE_LIST_SIZE);
		List<CPTMeasure> cptMeasureList = (List<CPTMeasure>) page.getList();
		return Message.successMessage(CommonMessageContent.CPT_LIST, cptMeasureList);

	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = { "/admin/hedisMeasureRule/icd", "/user/hedisMeasureRule/icd" })
	public Message getICDMeasure() {
		Pagination page = icdMeasureService.getPage(DEFAULT_PAGE_NO, HUGE_LIST_SIZE);
		List<ICDMeasure> icdMeasureList = (List<ICDMeasure>) page.getList();
		return Message.successMessage(CommonMessageContent.ICD_LIST, JsonConverter.getJsonObject(icdMeasureList));

	}

	/**
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = { "/admin/hedisMeasureRule/{id}/cpt/cptMeasureLists",
			"/user/hedisMeasureRule/{id}/cpt/cptMeasureLists" }, method = RequestMethod.GET)
	public Message viewCPTMeasureList(@PathVariable Integer id, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		HedisMeasureRule dbHedisMeasureRule = hedisMeasureRuleService.findById(id);
		Set<CPTMeasure> hedisRuleCPTMeasureList = dbHedisMeasureRule.getCptCodes();

		Pagination pagination = cptMeasureService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		List<CPTMeasure> cptMeasureList = (List<CPTMeasure>) pagination.getList();

		List<CPTMeasure> retainCPTList = new ArrayList<>(hedisRuleCPTMeasureList);
		retainCPTList.retainAll(cptMeasureList);

		cptMeasureList.removeAll(hedisRuleCPTMeasureList);

		if (sSearch != null && "".equals(sSearch)) {
			final int count = pagination.getTotalCount() - hedisRuleCPTMeasureList.size();
			pagination.setTotalCount(count);
		} else {
			final int count = pagination.getTotalCount() - retainCPTList.size();
			pagination.setTotalCount(count);
		}

		pagination.setList(cptMeasureList);

		return Message.successMessage(CommonMessageContent.CPT_LIST, JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = { "/admin/hedisMeasureRule/{id}/icd/icdMeasureLists",
			"/user/hedisMeasureRule/{id}/icd/icdMeasureLists" }, method = RequestMethod.GET)
	public Message viewICDMeasureList(@PathVariable Integer id, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		HedisMeasureRule dbHedisMeasureRule = hedisMeasureRuleService.findById(id);
		Set<ICDMeasure> hedisRuleICDMeasureList = dbHedisMeasureRule.getIcdCodes();

		Pagination pagination = icdMeasureService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		List<ICDMeasure> icdMeasureList = (List<ICDMeasure>) pagination.getList();

		List<ICDMeasure> retainICDList = new ArrayList<>(hedisRuleICDMeasureList);
		retainICDList.retainAll(icdMeasureList);

		icdMeasureList.removeAll(hedisRuleICDMeasureList);

		if (sSearch != null && "".equals(sSearch)) {
			final int count = pagination.getTotalCount() - hedisRuleICDMeasureList.size();
			pagination.setTotalCount(count);
		} else {
			final int count = pagination.getTotalCount() - retainICDList.size();
			pagination.setTotalCount(count);
		}

		pagination.setList(icdMeasureList);

		return Message.successMessage(CommonMessageContent.ICD_LIST, JsonConverter.getJsonObject(pagination));
	}

}
