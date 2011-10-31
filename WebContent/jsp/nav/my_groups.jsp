<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.community.*"%>

<%
	SmartWorks smartWorks = (SmartWorks) request
			.getAttribute("smartWorks");
	Group[] groups = smartWorks.getMyGroups("currentUser");
%>

<ul>
	<%
		for (Group group : groups) {
			String groupContext = SmartWorks.CONTEXT_PREFIX_GROUP_SPACE
					+ group.getId();
	%>
	<li class="ico_depart"><a
		href="group_space.sw?cid=<%=groupContext%>&workSpaceId=<%=group.getId()%>"><%=group.getName()%></a>
	</li>
	<%
		}
	%>
</ul>
