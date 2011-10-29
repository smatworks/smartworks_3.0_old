<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.community.*"%>
<%
	SmartWorks smartworks = (SmartWorks)request.getAttribute("smartworks");
	String key = request.getParameter("key");
	User[] chatters = smartworks.searchAvailableChatterList(key);
%>

<ul>
	<%
		for (User chatter : chatters) {
	%>
	<li><img src="<%=chatter.getMinPicture()%>" border="0"><a
		title="<%=chatter.getDepartment()%>"><%=chatter.getPosition()%>
			<%=chatter.getName()%></a>
	</li>
	<%
		}
	%>
</ul>
