/*	
 * $Id: TaskInstanceController.java,v 1.1 2011/10/29 00:34:23 ysjung Exp $
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
public class TaskInstanceController {

	@RequestMapping("/iwork_task")
	public ModelAndView iworkTask(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/task/iwork_task.jsp");
		else
			return new ModelAndView("iwork_task.tiles");

	}

	@RequestMapping("/pwork_task")
	public ModelAndView pworkTask(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/task/pwork_task.jsp");
		else
			return new ModelAndView("pwork_task.tiles");

	}

	@RequestMapping("/swork_task")
	public ModelAndView sworkTask(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/task/swork_task.jsp");
		else
			return new ModelAndView("swork_task.tiles");

	}

}