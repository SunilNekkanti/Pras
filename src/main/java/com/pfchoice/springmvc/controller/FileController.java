package com.pfchoice.springmvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pfchoice.common.CommonMessageContent;
import com.pfchoice.common.SystemDefaultProperties;
import com.pfchoice.common.util.JsonConverter;
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.File;
import com.pfchoice.core.entity.Insurance;
import com.pfchoice.core.service.FileService;
import com.pfchoice.core.service.InsuranceService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileService fileService;
	
	@Autowired
	private InsuranceService insuranceService;

	/**
	 * @return
	 */
	@ModelAttribute("newFile")
	public File createFileModel() {
		return new File();
	}

	

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/fileList", "/user/fileList" })
	public String handleRequest() {

		return TileDefinitions.FILETYPELIST.toString();
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
	@RequestMapping(value = { "/admin/file/list", "/user/file/list" }, method = RequestMethod.GET)
	public Message viewProviderList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {
		logger.info("Returning File List");
		Pagination pagination = fileService.getPage(pageNo, pageSize);

		return Message.successMessage(CommonMessageContent.FILE_TYPE_LIST, JsonConverter.getJsonObject(pagination));
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("insList")
	public List<Insurance> populateInsuranceList() {
		Pagination page = insuranceService.getPage(SystemDefaultProperties.DEFAULT_PAGE_NO,
				SystemDefaultProperties.MEDIUM_LIST_SIZE);
		return (List<Insurance>) page.getList();
	}

	
}
