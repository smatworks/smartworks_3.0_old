/*	
 * $Id$
 * created by    : SHIN HYUN SEONG
 * creation-date : 2011. 10. 31.
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
public class NoticeController {

	@RequestMapping("/notice_message_box")
	public ModelAndView noticeMessageBox(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return SmartUtil.returnMnv(request,
				"jsp/notice/notice_message_box.jsp",
				"notice_message_box.tiles");
	}

	@RequestMapping("/notification_list_box")
	public ModelAndView notificationListBox(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return SmartUtil.returnMnv(request,
				"jsp/notice/notification_list_box.jsp",
				"notification_list_box.tiles");
	}

	@RequestMapping("/message_list_box")
	public ModelAndView messageListBox(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return SmartUtil.returnMnv(request,
				"jsp/notice/message_list_box.jsp",
				"message_list_box.tiles");
	}

	@RequestMapping("/comments_list_box")
	public ModelAndView commentsListBox(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return SmartUtil.returnMnv(request,
				"jsp/notice/comments_list_box.jsp",
				"comments_list_box.tiles");
	}

	@RequestMapping("/assigned_list_box")
	public ModelAndView assignedListBox(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return SmartUtil.returnMnv(request,
				"jsp/notice/assigned_list_box.jsp",
				"assigned_list_box.tiles");
	}

	@RequestMapping("/mailbox_list_box")
	public ModelAndView mailboxListBox(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return SmartUtil.returnMnv(request,
				"jsp/notice/mailbox_list_box.jsp",
				"mailbox_list_box.tiles");
	}

	@RequestMapping("/savedbox_list_box")
	public ModelAndView savedboxListBox(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return SmartUtil.returnMnv(request,
				"jsp/notice/savedbox_list_box.jsp",
				"savedbox_list_box.tiles");
	}

}