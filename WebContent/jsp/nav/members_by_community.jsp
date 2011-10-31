<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%@ page import="net.smartworks.model.community.*"%>

<%
	ISmartWorks smartWorks = (ISmartWorks) request
			.getAttribute("smartWorks");
	User[] members = null;
	{
		String cid = request.getParameter("cid");
		if (cid == null)
			cid = ISmartWorks.CONTEXT_HOME;

		if (smartWorks.isSameContextPrefix(
				ISmartWorks.CONTEXT_PREFIX_GROUP_SPACE, cid)) {
			members = smartWorks.getGroupById(
					smartWorks.getSpaceIdFromContentContext(cid))
					.getMembers();
		} else if (smartWorks.isSameContextPrefix(
				ISmartWorks.CONTEXT_PREFIX_DEPARTMENT_SPACE, cid)) {
			members = smartWorks.getDepartmentById(
					smartWorks.getSpaceIdFromContentContext(cid))
					.getMembers();
		}
	}
%>

<ul>
	<%
		if (members != null) {
			String contextId = null;
			for (User member : members) {
				contextId = ISmartWorks.CONTEXT_PREFIX_USER_SPACE
						+ member.getId();
	%>
	<li><img src="<%=member.getMinPicture()%>" border="0"> <a
		href="user_space.sw?cid=<%=contextId%>"><%=member.getPosition()%>
			<%=member.getName()%></a></li>
	<%
		}
		}
	%>
</ul>
