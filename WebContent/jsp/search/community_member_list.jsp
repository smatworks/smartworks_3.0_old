<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.community.*"%>
<%
	SmartWorks smartWorks = (SmartWorks) request
			.getAttribute("smartWorks");
	String key = request.getParameter("key");
	User[] users = smartWorks.searchCommunityMemberList(SmartWorks
			.getCurrentUser().getId(), key);
%>

<ul>
	<%
		for (User user : users) {
			String picName = user.getMinPicture();
			String comContext = SmartWorks.CONTEXT_PREFIX_USER_SPACE
					+ user.getId();
			String targetContent = "user_space.sw";
			String comName = user.getName();
			String comId = user.getId();
	%>
	<li><img src="<%=picName%>" border="0"><a
		href="<%=targetContent%>?cid=<%=comContext%>&wid=<%=comId%>"><%=comName%></a>
	</li>
	<%
		}
	%>
</ul>
