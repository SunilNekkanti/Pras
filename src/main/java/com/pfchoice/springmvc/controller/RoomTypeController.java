package com.pfchoice.springmvc.controller;

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
import com.pfchoice.common.util.TileDefinitions;
import com.pfchoice.core.entity.PlaceOfService;
import com.pfchoice.core.service.PlaceOfServiceService;

import ml.rugal.sshcommon.page.Pagination;
import ml.rugal.sshcommon.springmvc.util.Message;

@Controller
@SessionAttributes({ "username", "userpath" })
public class RoomTypeController {

	private static final Logger logger = LoggerFactory.getLogger(RoomTypeController.class);

	@Autowired
	private PlaceOfServiceService roomTypeService;

	/**
	 * @return
	 */
	@ModelAttribute("newPlaceOfService")
	public PlaceOfService createPlaceOfServiceModel() {
		return new PlaceOfService();
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/roomType/new" })
	public String addUserPage(final Model model) {

		PlaceOfService roomType = createPlaceOfServiceModel();
		roomType.setActiveInd('Y');
		model.addAttribute("roomType", roomType);
		return TileDefinitions.ROOMTYPENEW.toString();
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/admin/roomType/{id}", "/user/roomType/{id}" }, method = RequestMethod.GET)
	public String updateUserPage(@PathVariable Integer id, Model model) {

		PlaceOfService dbPlaceOfService = roomTypeService.findById(id);
		logger.info("Returning roomType.getCode()" + dbPlaceOfService.getCode());

		model.addAttribute("roomType", dbPlaceOfService);
		logger.info("Returning roomTypeEdit.jsp page");
		return TileDefinitions.ROOMTYPEEDIT.toString();
	}

	/**
	 * @param roomType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/roomType/save.do",
			"/user/roomType/save.do" }, method = RequestMethod.POST, params = { "add" })
	public String newUserAction(@ModelAttribute("roomType") @Validated PlaceOfService roomType,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning roomTypeEdit.jsp page");
			return TileDefinitions.ROOMTYPENEW.toString();
		}

		logger.info("Returning roomTypeSuccess.jsp page after create");
		roomType.setCreatedBy(username);
		roomType.setUpdatedBy(username);
		roomTypeService.save(roomType);
		model.addAttribute("Message", "New plan type added successfully");
		return TileDefinitions.ROOMTYPELIST.toString();
	}

	/**
	 * @param id
	 * @param roomType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/roomType/{id}/save.do",
			"/user/roomType/{id}/save.do" }, method = RequestMethod.POST, params = { "update" })
	public String updateUserAction(@PathVariable Integer id, @Validated PlaceOfService roomType,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		roomType.setActiveInd('Y');
		logger.info("room type id is" + id);
		if (bindingResult.hasErrors()) {
			logger.info("Returning roomTypeEdit.jsp page");
			return TileDefinitions.ROOMTYPEEDIT.toString();
		}
		if (roomType.getId() != null) {
			logger.info("Returning roomTypeEditSuccess.jsp page after update");
			roomType.setUpdatedBy(username);
			roomTypeService.update(roomType);
			model.addAttribute("Message", "Plan Type  updated successfully");
		}
		return TileDefinitions.ROOMTYPELIST.toString();
	}

	/**
	 * @param id
	 * @param roomType
	 * @param bindingResult
	 * @param model
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/admin/roomType/{id}/save.do",
			"/user/roomType/{id}/save.do" }, method = RequestMethod.POST, params = { "delete" })
	public String deleteInsuranceAction(@PathVariable Integer id, @Validated PlaceOfService roomType,
			BindingResult bindingResult, Model model, @ModelAttribute("username") String username) {
		logger.info("room type id is" + id);
		if (bindingResult.hasErrors()) {
			roomType.setActiveInd('Y');
			logger.info("Returning roomTypeEdit.jsp page");
			return TileDefinitions.ROOMTYPEEDIT.toString();
		}

		if (null != roomType.getCode()) {
			logger.info("Returning roomTypeSuccess.jsp page after update");
			roomType.setActiveInd('N');
			roomType.setUpdatedBy(username);
			model.addAttribute("Message", "Plan Type  deleted successfully");
			roomTypeService.update(roomType);
		}
		return TileDefinitions.ROOMTYPELIST.toString();
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/admin/roomTypeList", "/user/roomTypeList" })
	public String handleRequest() {

		return TileDefinitions.ROOMTYPELIST.toString();
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
	@RequestMapping(value = { "/admin/roomType/list", "/user/roomType/list" }, method = RequestMethod.GET)
	public Message viewProviderList(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String sSearch,
			@RequestParam(required = false) String sort, @RequestParam(required = false) String sortdir) {

		Pagination pagination = roomTypeService.getPage(pageNo, pageSize);

		return Message.successMessage(CommonMessageContent.FILE_TYPE_LIST, JsonConverter.getJsonObject(pagination));
	}

}
