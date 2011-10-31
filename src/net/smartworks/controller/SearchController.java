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
public class SearchController {

	@RequestMapping("/available_chatter_list")
	public ModelAndView availableChatterList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return SmartUtil.returnMnv(request,
				"jsp/search/available_chatter_list.jsp",
				"");
	}

	@RequestMapping("/community_list")
	public ModelAndView communityList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return SmartUtil.returnMnv(request,
				"jsp/search/community_list.jsp",
				"");
	}

	@RequestMapping("/community_member_list")
	public ModelAndView communityMemberList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return SmartUtil.returnMnv(request,
				"jsp/search/community_member_list.jsp",
				"");
	}

	@RequestMapping("/running_instance_list")
	public ModelAndView runningInstanceList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return SmartUtil.returnMnv(request,
				"jsp/search/running_instance_list.jsp",
				"");
	}

	@RequestMapping("/work_list")
	public ModelAndView workList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return SmartUtil.returnMnv(request,
				"jsp/search/work_list.jsp",
				"");
	}
}