/*	
 * $Id$
 * created by    : SHIN HYUN SEONG
 * creation-date : 2011. 7. 15.
 * =========================================================
 * Copyright (c) 2011 ManinSoft, Inc. All rights reserved.
 */

package net.smartworks.util;

import javax.servlet.http.HttpServletRequest;

import net.smartworks.SmartWorks;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

public class SmartUtil {

	public SmartUtil() {
		super();
	}

	/**
	 * beanName; serviceCall
	 * @param beanName
	 * @param request
	 * @return
	 */
	public static Object getBean(String beanName, HttpServletRequest request) {

		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());

		return (Object) wac.getBean(beanName);
	}

	public static ModelAndView returnMnv(HttpServletRequest request, String defaultPage, String ajaxPage) {
		String getHeader = request.getHeader("X-Requested-With");
		SmartWorks smartworks = (SmartWorks)SmartUtil.getBean("smartWorks", request);
		if (getHeader != null)
			return new ModelAndView(defaultPage, "smartworks", smartworks);
		else
			return new ModelAndView(ajaxPage, "smartworks", smartworks);
	}

}