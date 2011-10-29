<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.community.*"%>

<%
	User[] members = null;
	{
		String myContext = request.getParameter("cid");
		if (myContext == null)
			myContext = SmartWorks.CONTEXT_HOME;

		if (SmartWorks.isSameContextPrefix(
				SmartWorks.CONTEXT_PREFIX_GROUP_SPACE, myContext)) {
			members = SmartWorks.getGroupById(
					SmartWorks.getSpaceIdFromContentContext(myContext))
					.getMembers();
		} else if (SmartWorks.isSameContextPrefix(
				SmartWorks.CONTEXT_PREFIX_DEPARTMENT_SPACE, myContext)) {
			members = SmartWorks.getDepartmentById(
					SmartWorks.getSpaceIdFromContentContext(myContext))
					.getMembers();
		}
	}
%>

<ul>
	<%
		if (members != null) {
			String contextId = null;
			for (User member : members) {
				contextId = SmartWorks.CONTEXT_PREFIX_USER_SPACE
						+ member.getId();
	%>
	<li><img src="<%=member.getMinPicture()%>" border="0"> <a
		href="user_space.sw?cid=<%=contextId%>&wid=<%=member.getId()%>"><%=member.getPosition()%>
			<%=member.getName()%></a>
	</li>
	<%
		}
	}
	%>
</ul>
