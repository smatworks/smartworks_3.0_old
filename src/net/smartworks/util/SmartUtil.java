/*	
 * $Id$
 * created by    : SHIN HYUN SEONG
 * creation-date : 2011. 7. 15.
 * =========================================================
 * Copyright (c) 2011 ManinSoft, Inc. All rights reserved.
 */

package net.smartworks.util;

import javax.servlet.http.HttpServletRequest;

import net.smartworks.model.community.User;
import net.smartworks.model.work.SmartWork;
import net.smartworks.service.ISmartWorks;
import net.smartworks.service.impl.SmartWorks;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

public class SmartUtil {

	public SmartUtil() {
		super();
	}

	/**
	 * beanName; getBean
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
		ISmartWorks smartworks = (ISmartWorks)SmartUtil.getBean("smartWorks", request);
		if (getHeader != null)
			return new ModelAndView(defaultPage, "smartWorks", smartworks);
		else
			return new ModelAndView(ajaxPage, "smartWorks", smartworks);
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#isSameContextPrefix(java.lang.String, java.lang.String)
	 */
	public static boolean isSameContextPrefix(String contextPrefix,
			String contextId) throws Exception {
		if (contextPrefix == null || contextId == null
				|| contextPrefix.length() >= contextId.length())
			return false;
		if (contextId.subSequence(0, contextPrefix.length()).equals(
				contextPrefix))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#isWorkContextType(java.lang.String)
	 */
	public static boolean isWorkContextType(String contextId) throws Exception {
		if (contextId == null || contextId.length() < 3)
			return false;
		if (contextId.substring(0, 3).equals("iw.")
				|| contextId.substring(0, 3).equals("pw.")
				|| contextId.substring(0, 3).equals("sw.")
				|| contextId.substring(0, 3).equals("fl.")
				|| contextId.substring(0, 3).equals("fl.")
				|| contextId.substring(0, 3).equals("mm.")
				|| contextId.substring(0, 3).equals("im.")
				|| contextId.substring(0, 3).equals("ev.")
				|| contextId.substring(0, 3).equals("bd.")
				|| contextId.substring(0, 3).equals("ml.")
				|| contextId.substring(0, 3).equals("sv."))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#isWorkSpaceContextType(java.lang.String)
	 */
	public static boolean isWorkSpaceContextType(String contextId)
			throws Exception {
		if (contextId == null || contextId.length() < 6)
			return false;
		if (contextId.substring(0, 6).equals("iw.sp.")
				|| contextId.substring(0, 6).equals("pw.sp.")
				|| contextId.substring(0, 6).equals("sw.sp.")
				|| contextId.substring(0, 6).equals("fl.sp.")
				|| contextId.substring(0, 6).equals("mm.sp.")
				|| contextId.substring(0, 6).equals("im.sp.")
				|| contextId.substring(0, 6).equals("ev.sp.")
				|| contextId.substring(0, 6).equals("bd.sp.")
				|| contextId.substring(0, 6).equals("ml.sp."))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#isTaskSpaceContextType(java.lang.String)
	 */
	public static boolean isTaskSpaceContextType(String contextId)
			throws Exception {
		if (contextId == null || contextId.length() < 6)
			return false;
		if (contextId.substring(0, 6).equals("iw.ts.")
				|| contextId.substring(0, 6).equals("pw.ts.")
				|| contextId.substring(0, 6).equals("sw.ts."))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#isCommunitySpaceContextType(java.lang.String)
	 */
	public static boolean isCommunitySpaceContextType(String contextId)
			throws Exception {
		if (contextId == null || contextId.length() < 6)
			return false;
		if (contextId.substring(0, 6).equals(SmartWorks.CONTEXT_PREFIX_DEPARTMENT_SPACE)
				|| contextId.substring(0, 6).equals(SmartWorks.CONTEXT_PREFIX_GROUP_SPACE)
				|| contextId.substring(0, 6).equals(SmartWorks.CONTEXT_PREFIX_USER_SPACE))
			return true;
		return false;
	}

	public static String getSpaceIdFromContentContext(String contentContext)
			throws Exception {
		if (contentContext == null || contentContext.length() <= SmartWorks.CONTEXT_PREFIX_LENGTH)
			return null;
		return contentContext.substring(SmartWorks.CONTEXT_PREFIX_LENGTH);
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getContextPrefixByWorkType(int, int)
	 */
	public static String getContextPrefixByWorkType(int smartWorkType,
			int spaceType) throws Exception {

		if (spaceType == SmartWorks.SPACE_TYPE_WORK_LIST) {
			if (smartWorkType == SmartWork.TYPE_INFORMATION)
				return SmartWorks.CONTEXT_PREFIX_IWORK_LIST;
			if (smartWorkType == SmartWork.TYPE_PROCESS)
				return SmartWorks.CONTEXT_PREFIX_PWORK_LIST;
			if (smartWorkType == SmartWork.TYPE_SCHEDULE)
				return SmartWorks.CONTEXT_PREFIX_SWORK_LIST;
		} else if(spaceType == SmartWorks.SPACE_TYPE_WORK_INSTANCE){
			if (smartWorkType == SmartWork.TYPE_INFORMATION)
				return SmartWorks.CONTEXT_PREFIX_IWORK_SPACE;
			if (smartWorkType == SmartWork.TYPE_PROCESS)
				return SmartWorks.CONTEXT_PREFIX_PWORK_SPACE;
			if (smartWorkType == SmartWork.TYPE_SCHEDULE)
				return SmartWorks.CONTEXT_PREFIX_SWORK_SPACE;
		} else if(spaceType == SmartWorks.SPACE_TYPE_TASK_INSTANCE){
			if (smartWorkType == SmartWork.TYPE_INFORMATION)
				return SmartWorks.CONTEXT_PREFIX_IWORK_TASK;
			if (smartWorkType == SmartWork.TYPE_PROCESS)
				return SmartWorks.CONTEXT_PREFIX_PWORK_TASK;
			if (smartWorkType == SmartWork.TYPE_SCHEDULE)
				return SmartWorks.CONTEXT_PREFIX_SWORK_TASK;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getTargetContentByWorkType(int, int)
	 */
	public static String getTargetContentByWorkType(int smartWorkType,
			int spaceType) throws Exception {

		if (spaceType == SmartWorks.SPACE_TYPE_WORK_LIST) {
			if (smartWorkType == SmartWork.TYPE_INFORMATION)
				return "iwork_list.sw";
			if (smartWorkType == SmartWork.TYPE_PROCESS)
				return "pwork_list.sw";
			if (smartWorkType == SmartWork.TYPE_SCHEDULE)
				return "swork_list.sw";
		} else if(spaceType == SmartWorks.SPACE_TYPE_WORK_INSTANCE){
			if (smartWorkType == SmartWork.TYPE_INFORMATION)
				return "iwork_space.sw";
			if (smartWorkType == SmartWork.TYPE_PROCESS)
				return "pwork_space.sw";
			if (smartWorkType == SmartWork.TYPE_SCHEDULE)
				return "swork_space.sw";
		} else if(spaceType == SmartWorks.SPACE_TYPE_TASK_INSTANCE){
			if (smartWorkType == SmartWork.TYPE_INFORMATION)
				return "iwork_task.sw";
			if (smartWorkType == SmartWork.TYPE_PROCESS)
				return "pwork_task.sw";
			if (smartWorkType == SmartWork.TYPE_SCHEDULE)
				return "swork_task.sw";

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getCurrentUser()
	 */
	public static User getCurrentUser() throws Exception {
		User user = new User();
		user.setId("jisook@maninsoft.co.kr");
		user.setName("김지숙");
		user.setPosition("CEO");
		user.setDepartment("경영기획실 디자인팀");
		user.setLocale("ko"); // ko, en
		user.setTimeZone("SEOUL");
		user.setCompany("(주)맨인소프트");
		user.setPicturePath("images/");
		user.setOrgPictureName(user.getId() + ".jpg");
		user.setMinPictureName(user.getId() + "_min.gif");
		user.setMidPictureName(user.getId() + "_mid.gif");

		return user;
	}
}