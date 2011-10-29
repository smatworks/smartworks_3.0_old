/*	
 * $Id: WorkListController.java,v 1.1 2011/10/29 00:34:23 ysjung Exp $
 * created by    : SHIN HYUN SEONG
 * creation-date : 2011. 10. 15.
 * =========================================================
 * Copyright (c) 2011 ManinSoft, Inc. All rights reserved.
 */

package net.smartworks.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.smartworks.util.SmartUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WorkListController {

	@RequestMapping("/iwork_list")
	public ModelAndView iworkList(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request,
				"jsp/content/work/list/iwork_list.jsp", "iwork_list.tiles");
	}

	@RequestMapping("/pwork_list")
	public ModelAndView pworkList(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request,
				"jsp/content/work/list/pwork_list.jsp", "pwork_list.tiles");
	}

	@RequestMapping("/swork_list")
	public ModelAndView sworkList(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/list/swork_list.jsp");
		else
			return new ModelAndView("swork_list.tiles");

	}

	@RequestMapping("/board_list")
	public ModelAndView boardList(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/list/board_list.jsp");
		else
			return new ModelAndView("board_list.tiles");

	}

	@RequestMapping("/event_list")
	public ModelAndView eventList(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/list/event_list.jsp");
		else
			return new ModelAndView("event_list.tiles");

	}

	@RequestMapping("/file_list")
	public ModelAndView fileList(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/list/file_list.jsp");
		else
			return new ModelAndView("file_list.tiles");

	}

	@RequestMapping("/image_list")
	public ModelAndView imageList(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/list/image_list.jsp");
		else
			return new ModelAndView("image_list.tiles");

	}

	@RequestMapping("/memo_list")
	public ModelAndView memoList(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/list/memo_list.jsp");
		else
			return new ModelAndView("memo_list.tiles");

	}

	@RequestMapping("/mail_list")
	public ModelAndView mailList(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request,
				"jsp/content/work/list/mail_list.jsp", "mail_list.tiles");
	}

	@RequestMapping("/saved_list")
	public ModelAndView savedList(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request,
				"jsp/content/work/list/saved_list.jsp", "saved_list.tiles");
	}
}