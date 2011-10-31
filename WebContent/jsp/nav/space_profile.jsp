<%@page import="net.smartworks.util.SmartUtil"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%@ page import="net.smartworks.model.community.*"%>

<%
	ISmartWorks smartWorks = (ISmartWorks) request.getAttribute("smartWorks");
	String cid = request.getParameter("cid");
	if (cid == null)
		cid = ISmartWorks.CONTEXT_HOME;
	String wid = request.getParameter("wid");
	if (wid == null)
		wid = SmartUtil.getCurrentUser().getId();

	Group thisGroup = null;
	Department thisDepartment = null;
	User thisUser = null;
	String spaceId = SmartUtil.getSpaceIdFromContentContext(cid);
	if (SmartUtil.isSameContextPrefix(ISmartWorks.CONTEXT_PREFIX_GROUP_SPACE, cid)) {
		thisGroup = (Group) smartWorks.getWorkSpaceById(spaceId);
	} else if (SmartUtil.isSameContextPrefix(ISmartWorks.CONTEXT_PREFIX_DEPARTMENT_SPACE, cid)) {
		thisDepartment = (Department) smartWorks.getWorkSpaceById(spaceId);
	} else if (SmartUtil.isSameContextPrefix(ISmartWorks.CONTEXT_PREFIX_USER_SPACE, cid)) {
		thisUser = (User) smartWorks.getWorkSpaceById(spaceId);
	} else if (!wid.equals(SmartUtil.getCurrentUser().getId())) {
		WorkSpace workSpace = smartWorks.getWorkSpaceById(wid);
		if (workSpace == null) {
			thisUser = SmartUtil.getCurrentUser();
		} else if (workSpace.getClass() == User.class) {
			thisUser = (User) workSpace;
		} else if (workSpace.getClass() == Group.class) {
			thisGroup = (Group) workSpace;
		} else if (workSpace.getClass() == Department.class) {
			thisDepartment = (Department) workSpace;
		}
	} else {
		thisUser = SmartUtil.getCurrentUser();
	}
	/*
	 if(smartWorks.isSameContextPrefix(ISmartWorks.CONTEXT_PREFIX_GROUP_SPACE, navContext)){
	 thisGroup = smartWorks.getGroupById(smartWorks.getSpaceIdFromContentContext(navContext));
	 }else if(smartWorks.isSameContextPrefix(ISmartWorks.CONTEXT_PREFIX_DEPARTMENT_SPACE, navContext)){
	 thisDepartment = smartWorks.getDepartmentById(smartWorks.getSpaceIdFromContentContext(navContext));		
	 }else if(smartWorks.isSameContextPrefix(ISmartWorks.CONTEXT_PREFIX_USER_SPACE, navContext)){
	 thisUser = smartWorks.getUserById(smartWorks.getSpaceIdFromContentContext(navContext));
	 */
%>

<ul>
	<%
		if (thisGroup != null) {
	%>
	<li><img src="<%=thisGroup.getOrgPicture()%>"></li>
	<li><%=thisGroup.getName()%><br /> <b><%=thisGroup.getDesc()%></b><br />
		<fmt:message key="group.text.leader" /> : <%=thisGroup.getLeader().getLongName()%><br />
	</li>
	<%
		} else if (thisDepartment != null) {
	%>
	<li><img src="<%=thisDepartment.getOrgPicture()%>"></li>
	<li><%=thisDepartment.getName()%><br /> <b><%=thisDepartment.getDesc()%></b><br />
		<fmt:message key="department.text.head" /> : <%=thisDepartment.getHead().getLongName()%><br />
	</li>
	<%
		} else if (thisUser != null) {
	%>
	<li><img src="<%=thisUser.getOrgPicture()%>"></li>
	<li><%=thisUser.getPosition()%><br /> <b><%=thisUser.getName()%></b><br />
		<%=thisUser.getDepartment()%><br /></li>
	<%
		}
	%>
</ul>
