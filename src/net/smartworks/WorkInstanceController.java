/*	
 * $Id: WorkInstanceController.java,v 1.1 2011/10/29 00:34:23 ysjung Exp $
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
public class WorkInstanceController {

	@RequestMapping("/iwork_space")
	public ModelAndView iworkSpace(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/space/iwork_space.jsp");
		else
			return new ModelAndView("iwork_space.tiles");

	}

	@RequestMapping("/pwork_space")
	public ModelAndView pworkSpace(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/space/pwork_space.jsp");
		else
			return new ModelAndView("pwork_space.tiles");

	}

	@RequestMapping("/swork_space")
	public ModelAndView sworkSpace(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/space/swork_space.jsp");
		else
			return new ModelAndView("swork_space.tiles");

	}

	@RequestMapping("/board_space")
	public ModelAndView boardSpace(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/space/board_space.jsp");
		else
			return new ModelAndView("board_space.tiles");

	}

	@RequestMapping("/event_space")
	public ModelAndView eventSpace(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/space/event_space.jsp");
		else
			return new ModelAndView("event_space.tiles");

	}

	@RequestMapping("/file_space")
	public ModelAndView fileSpace(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/space/file_space.jsp");
		else
			return new ModelAndView("file_space.tiles");

	}

	@RequestMapping("/image_space")
	public ModelAndView imageSpace(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/space/image_space.jsp");
		else
			return new ModelAndView("image_space.tiles");

	}

	@RequestMapping("/memo_space")
	public ModelAndView memoSpace(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/space/memo_space.jsp");
		else
			return new ModelAndView("memo_space.tiles");

	}

	@RequestMapping("/mail_space")
	public ModelAndView mailSpace(HttpServletRequest request,
			HttpServletResponse response) {

		String getHeader = request.getHeader("X-Requested-With");

		if (getHeader != null)
			return new ModelAndView("jsp/content/work/space/mail_space.jsp");
		else
			return new ModelAndView("mail_space.tiles");

	}
}