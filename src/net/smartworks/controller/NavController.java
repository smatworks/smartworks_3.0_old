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
public class NavController {

	@RequestMapping("/community_members.sw")
	public ModelAndView communityMembers(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return SmartUtil.returnMnv(request,
				"jsp/nav/community_members.jsp",
				"");
	}

	@RequestMapping("/members_by_community")
	public ModelAndView membersByCommunity(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/nav/members_by_community.jsp",
				"");
	}

	@RequestMapping("/my_all_works")
	public ModelAndView myAllWorks(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/nav/my_all_works.jsp",
				"");
	}

	@RequestMapping("/my_departments")
	public ModelAndView myDepartments(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/nav/my_departments.jsp",
				"");
	}

	@RequestMapping("/my_favorite_works")
	public ModelAndView myFavoriteWorks(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/nav/my_favorite_works.jsp",
				"");
	}

	@RequestMapping("/my_groups")
	public ModelAndView myGroups(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/nav/my_groups.jsp",
				"");
	}

	@RequestMapping("/my_recent_instances")
	public ModelAndView myRecentInstances(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/nav/my_recent_instances.jsp",
				"");
	}

	@RequestMapping("/worklist_by_category")
	public ModelAndView worklistByCategory(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/nav/worklist_by_category.jsp",
				"");
	}

	@RequestMapping("/worklist_by_group")
	public ModelAndView worklistByGroup(HttpServletRequest request,
			HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/nav/worklist_by_group.jsp",
				"");
	}
}