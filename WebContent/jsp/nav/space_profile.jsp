<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.community.*"%>

<%
	String navContext = request.getParameter("cid");
	if (navContext == null)
		navContext = SmartWorks.CONTEXT_HOME;
	String spaceId = request.getParameter("wid");
	if (spaceId == null)
		spaceId = SmartWorks.getCurrentUser().getId();

	Group thisGroup = null;
	Department thisDepartment = null;
	User thisUser = null;

	if (!spaceId.equals(SmartWorks.getCurrentUser().getId())) {
		WorkSpace workSpace = SmartWorks.getWorkSpaceById(spaceId);
		if (workSpace == null) {
			thisUser = SmartWorks.getCurrentUser();
		} else if (workSpace.getClass() == User.class) {
			thisUser = (User) workSpace;
		} else if (workSpace.getClass() == Group.class) {
			thisGroup = (Group) workSpace;
		} else if (workSpace.getClass() == Department.class) {
			thisDepartment = (Department) workSpace;
		}
	} else {
		thisUser = SmartWorks.getCurrentUser();
	}
	/*
	 if(SmartWorks.isSameContextPrefix(SmartWorks.CONTEXT_PREFIX_GROUP_SPACE, navContext)){
	 thisGroup = SmartWorks.getGroupById(SmartWorks.getSpaceIdFromContentContext(navContext));
	 }else if(SmartWorks.isSameContextPrefix(SmartWorks.CONTEXT_PREFIX_DEPARTMENT_SPACE, navContext)){
	 thisDepartment = SmartWorks.getDepartmentById(SmartWorks.getSpaceIdFromContentContext(navContext));		
	 }else if(SmartWorks.isSameContextPrefix(SmartWorks.CONTEXT_PREFIX_USER_SPACE, navContext)){
	 thisUser = SmartWorks.getUserById(SmartWorks.getSpaceIdFromContentContext(navContext));
	 */
%>

<ul>
	<%
		if (thisUser != null) {
	%>
	<li><img src="<%=thisUser.getOrgPicture()%>">
	</li>
	<li><%=thisUser.getPosition()%><br /> <b><%=thisUser.getName()%></b><br />
		<%=thisUser.getDepartment()%><br /></li>
	<%
		} else if (thisGroup != null) {
	%>
	<li><img src="<%=thisGroup.getOrgPicture()%>">
	</li>
	<li><%=thisGroup.getName()%><br /> <b><%=thisGroup.getDesc()%></b><br />
		<fmt:message key="group.text.leader" /> : <%=thisGroup.getLeader().getLongName()%><br />
	</li>
	<%
		} else if (thisDepartment != null) {
	%>
	<li><img src="<%=thisDepartment.getOrgPicture()%>">
	</li>
	<li><%=thisDepartment.getName()%><br /> <b><%=thisDepartment.getDesc()%></b><br />
		<fmt:message key="department.text.head" /> : <%=thisDepartment.getHead().getLongName()%><br />
	</li>
	<%
		}
	%>
</ul>