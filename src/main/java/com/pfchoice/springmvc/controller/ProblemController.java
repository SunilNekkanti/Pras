package com.pfchoice.springmvc.controller;

import static com.pfchoice.common.SystemDefaultProperties.DEFAULT_PAGE_NO;
import static com.pfchoice.common.SystemDefaultProperties.HUGE_LIST_SIZE;
import static com.pfchoice.common.SystemDefaultProperties.MEDIUM_LIST_SIZE;
import static com.pfchoice.common.CommonMessageContent.ICD_LIST;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import com.pfchoice.core.entity.ICDMeasure;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.entity.Problem;
import com.pfchoice.core.service.ProblemService;
import com.pfchoice.core.service.ICDMeasureService;
import com.pfchoice.core.service.InsuranceService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class ProblemController {

	private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);

	@Autowired
	private ProblemService problemService;

	@Autowired
	private ICDMeasureService icdMeasureService;

	@Autowired
	private InsuranceService insuranceService;

	/*
	 * @Autowired
	 * 
	 * @Qualifier("hedisMeasureRuleValidator") private Validator validator;
	 * 
	 * /**
	 * 
	 * @param binder
	 *
	 * @InitBinder("hedisMeasureRule") public void initBinder(WebDataBinder
	 * binder) { SimpleDateFormat dateFormat = new
	 * SimpleDateFormat("MM/dd/yyyy"); dateFormat.setLenient(true);
	 * binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,
	 * true)); binder.setValidator(validator); }
	 */

	/**
	 * @return
	 */
	@ModelAttribute("problem")
	public Problem createProblemModel() {
		return new Problem();
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
	@RequestMapping(value = "/admin/problem/new")
	public String addProblemPage(Model model) {

		Problem problem = createProblemModel();
		model.addAttribute("problem", problem);
		return TileDefinitions.PROBLEMNEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/problem/{id}", "/user/problem/{id}" }, method = RequestMethod.GET)
	public String updateProblemPage(@PathVariable Integer id, Model model) {

		Problem dbProblem = problemService.findById(id);
		logger.info("Returning Problem.getId()" + dbProblem.getId());

		Set<ICDMeasure> icdMeasureList = dbProblem.getIcdCodes();

		model.addAttribute("icdMeasureListAjax", icdMeasureList);
		model.addAttribute("Problem", dbProblem);
		logger.info("Returning ProblemEdit.jsp page");
		return TileDefinitions.PROBLEMEDIT.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "admin/problemAjax/{id}", method = RequestMethod.GET)
	public String updateProblemAjaxPage(@PathVariable Integer id, Model model) {

		Problem dbProblem = problemService.findById(id);
		logger.info("Returning Problem.getId()" + dbProblem.getId());

		Set<ICDMeasure> icdMeasureList = dbProblem.getIcdCodes();

		model.addAttribute("problem", dbProblem);
		model.addAttribute("icdMeasureListAjax", icdMeasureList);
		logger.info("Returning ProblemEditAjax.jsp page");
		return TileDefinitions.PROBLEMEDITAJAX.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/problem/{id}/display", method = RequestMethod.GET)
	public String displayProblemPage(@PathVariable Integer id, Model model) {

		Problem dbProblem = problemService.findById(id);
		logger.info("Returning Problem.getId()" + dbProblem.getId());

		model.addAttribute("problem", dbProblem);
		logger.info("Returning ProblemDisplay.jsp page");
		return TileDefinitions.PROBLEMDISPLAY.toString();
	}

	/**
	 * @param Problem
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/problem/save.do", method = RequestMethod.POST, params = { "add" })
	public String addProblemAction(@ModelAttribute("problem") @Validated Problem problem,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning ProblemEdit.jsp page");
			return TileDefinitions.PROBLEMNEW.toString();
		}

		model.addAttribute("problem", problem);
		problem.setCreatedBy(username);
		problem.setUpdatedBy(username);
		model.addAttribute("Message", "Problem added successfully");
		logger.info("Returning ProblemEditSuccess.jsp page after create");
		problemService.save(problem);
		return TileDefinitions.PROBLEMLIST.toString();
	}

	/**
	 * @param id
	 * @param Problem
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/problem/{id}/save.do", method = RequestMethod.POST, params = { "update" })
	public String saveProblemAction(@PathVariable Integer id, @ModelAttribute("problem") @Validated Problem problem,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("Problem id is" + id);
		problem.setActiveInd('Y');
		if (bindingResult.hasErrors()) {
			problem.setActiveInd('Y');
			logger.info("Returning  ProblemEdit.jsp page");
			return TileDefinitions.PROBLEMEDIT.toString();
		}

		if (null != problem.getId()) {
			logger.info("Returning ProblemEditSuccess.jsp page after update");
			problemService.update(problem);
			problem.setUpdatedBy(username);
			model.addAttribute("Message", "Problem updated successfully");
			return TileDefinitions.PROBLEMLIST.toString();
		}
		return TileDefinitions.PROBLEMEDIT.toString();
	}

	/**
	 * @param id
	 * @param Problem
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/admin/problem/{id}/save.do", method = RequestMethod.POST, params = { "delete" })
	public String deleteProblemAction(@PathVariable Integer id,
			@ModelAttribute("problem") @Validated Problem problem, BindingResult bindingResult, Model model,
			@ModelAttribute("username") String username) {
		logger.info("Problem id is" + id);
		if (bindingResult.hasErrors()) {
			problem.setActiveInd('Y');
			logger.info("Returning  ProblemEdit.jsp page");
			return TileDefinitions.PROBLEMEDIT.toString();
		}

		if (null != problem.getId()) {
			logger.info("Returning ProblemEditSuccess.jsp page after update");
			problem.setActiveInd('N');
			problem.setUpdatedBy(username);
			problemService.update(problem);
			model.addAttribute("Message", "Problem deleted successfully");
			return TileDefinitions.PROBLEMLIST.toString();
		}
		return TileDefinitions.PROBLEMEDIT.toString();
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = { "/admin/problem/icd", "/user/problem/icd" })
	public Message getICDMeasure() {
		Pagination page = icdMeasureService.getPage(DEFAULT_PAGE_NO, HUGE_LIST_SIZE);
		List<ICDMeasure> icdMeasureList = (List<ICDMeasure>) page.getList();
		return Message.successMessage(ICD_LIST, JsonConverter.getJsonObject(icdMeasureList));

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
	@RequestMapping(value = { "/admin/problem/{id}/icd/icdMeasureLists",
			"/user/problem/{id}/icd/icdMeasureLists" }, method = RequestMethod.GET)
	public Message viewICDMeasureList(@PathVariable Integer id, @RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Problem dbProblem = problemService.findById(id);
		Set<ICDMeasure> problemICDMeasureList = dbProblem.getIcdCodes();

		Pagination pagination = icdMeasureService.getPage(pageNo, pageSize, sSearch, sort, sortdir);
		List<ICDMeasure> icdMeasureList = (List<ICDMeasure>) pagination.getList();

		List<ICDMeasure> retainICDList = new ArrayList<>(problemICDMeasureList);
		retainICDList.retainAll(icdMeasureList);

		icdMeasureList.removeAll(problemICDMeasureList);

		if (sSearch != null) {
			final int count = pagination.getTotalCount() - problemICDMeasureList.size();
			pagination.setTotalCount(count);
		} else {
			final int count = pagination.getTotalCount() - retainICDList.size();
			pagination.setTotalCount(count);
		}

		pagination.setList(icdMeasureList);

		return Message.successMessage(ICD_LIST, JsonConverter.getJsonObject(pagination));
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/problemList", "/user/problemList" })
	public String handleRequest() {

		return TileDefinitions.PROBLEMLIST.toString();
	}
	
	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/problem/list", "/user/problem/list" }, method = RequestMethod.GET)
	public Message viewProviderList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize,
			@RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String sortdir){

		Pagination pagination = problemService.getPage(pageNo, pageSize, sSearch, sort, sortdir);

		return Message.successMessage(ICD_LIST, JsonConverter.getJsonObject(pagination));
	}
}
