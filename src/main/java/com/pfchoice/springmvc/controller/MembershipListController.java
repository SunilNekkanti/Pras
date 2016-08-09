package com.pfchoice.springmvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.PrasUtil;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.service.MembershipService;
import com.pfchoice.springmvc.controller.service.DBConnection;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * A Membership controller class for GET/DELETE/POST/PUT.
 *
 * @author sarath
 */
@Controller
@SessionAttributes({ "username", "userpath" })
public class MembershipListController {

	private static final Logger LOG = LoggerFactory.getLogger(MembershipListController.class.getName());

	@Autowired
	private MembershipService membershipService;

	@Autowired
	private DBConnection dBConnection;

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/membershipList", "/user/membershipList" })
	public String handleRequest() {
		LOG.info("returning membershipList.jsp");
		return TileDefinitions.MEMBERSHIPLIST.toString();
	}

	/**
	 * @return
	 * @throws JRException
	 */
	@RequestMapping(value = { "/admin/reports/membershipList", "/user/reports/membershipList" })
	public void handleRequest1(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer insId,
			@RequestParam(required = true) String reportFormat)
			throws JRException, NamingException, SQLException, IOException {
		LOG.info("returning membershipList.jsp");

		String fileName = "MbrCount4MbrStatusVsPrvdr";
		JasperReport jp = PrasUtil.getCompiledFile(fileName, request);
		HashMap<String, Object> rptParams =  new HashMap<>();
		rptParams.put("insId",insId);
		switch (reportFormat) {
		case "PDF":
		case "pdf":
			PrasUtil.generateReportPDF(response,rptParams , jp, dBConnection.getConnection());
			break;
		case "XLS":
		case "XLSX":
			PrasUtil.generateReportPDF(response, rptParams, jp, dBConnection.getConnection());
			break;
		case "CSV":
		case "csv":
			PrasUtil.generateReportPDF(response, rptParams, jp, dBConnection.getConnection());
			break;
		default:
			PrasUtil.generateReportPDF(response, rptParams, jp, dBConnection.getConnection());
			break;
		}
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param sSearch
	 * @param sSearchIns
	 * @param sSearchPrvdr
	 * @param sort
	 * @param sortdir
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/admin/membership/list", "/user/membership/list" }, method = RequestMethod.GET)
	public Message viewMembershipList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) Integer sSearchIns, @RequestParam(required = false) Integer sSearchPrvdr,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		// provided 0 to ignore hedisMeasureRule join
		Integer i = 0;
		List<Integer> ruleIds = Collections.singletonList(i);
		Pagination pagination = membershipService.getPage(pageNo, pageSize, sSearch, sSearchIns, sSearchPrvdr, 0,
				ruleIds, sort, sortdir);
		LOG.info("returning membershipList");
		return Message.successMessage(CommonMessageContent.MEMBERSHIP_LIST, JsonConverter.getJsonObject(pagination));
	}

}
