/*	
 * $Id: CommunityController.java,v 1.1 2011/10/29 00:34:23 ysjung Exp $
 * created by    : SHIN HYUN SEONG
 * creation-date : 2011. 10. 15.
 * =========================================================
 * Copyright (c) 2011 ManinSoft, Inc. All rights reserved.
 */

package net.smartworks;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommunityController {

	@RequestMapping("/department_space")
	public ModelAndView departmentSpace(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/space/department_space.jsp");
		else
			return new ModelAndView("department_space.tiles");

	}

	@RequestMapping("/group_space")
	public ModelAndView groupSpace(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/group_space.jsp");
		else
			return new ModelAndView("group_space.tiles");

	}

	@RequestMapping("/user_space")
	public ModelAndView userSpace(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/user_space.jsp");
		else
			return new ModelAndView("user_space.tiles");

	}
}