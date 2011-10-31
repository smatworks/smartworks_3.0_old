/*	
 * $Id: CommunityController.java,v 1.1 2011/10/29 00:34:23 ysjung Exp $
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
public class CommunityController {

	@RequestMapping("/department_space")
	public ModelAndView departmentSpace(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return SmartUtil.returnMnv(request,
				"jsp/content/department_space.jsp",
				"department_space.tiles");
	}

	@RequestMapping("/group_space")
	public ModelAndView groupSpace(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/group_space.jsp",
				"group_space.tiles");
	}

	@RequestMapping("/user_space")
	public ModelAndView userSpace(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/user_space.jsp",
				"user_space.tiles");
	}
}