/*	
 * $Id: ContentController.java,v 1.1 2011/10/29 00:34:23 ysjung Exp $
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
public class ContentController {

	@RequestMapping("/home")
	public ModelAndView home(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/home.jsp");
		else
			return new ModelAndView("home.tiles");

	}

	@RequestMapping("/smartcaster")
	public ModelAndView smartcaster(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/smartcaster.jsp");
		else
			return new ModelAndView("smartcaster.tiles");

	}

	@RequestMapping("/dashboard")
	public ModelAndView dashboard(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/dashboard.jsp");
		else
			return new ModelAndView("dashboard.tiles");

	}

	@RequestMapping("/my_profile")
	public ModelAndView myProfile(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/my_profile.jsp");
		else
			return new ModelAndView("my_profile.tiles");

	}
}