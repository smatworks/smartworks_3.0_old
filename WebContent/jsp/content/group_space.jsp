<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.*"%>
<%
	SmartWorks smartWorks = (SmartWorks)request.getAttribute("smartWorks");
	String contextId = request.getParameter("cid");
	if (contextId == null)
		session.setAttribute("cid", SmartWorks.CONTEXT_HOME);
	else
		session.setAttribute("cid", contextId);
	String workSpaceId = request.getParameter("wid");
	if (workSpaceId == null)
		session.setAttribute("wid", smartWorks.getCurrentUser().getId());
	else
		session.setAttribute("wid", workSpaceId);
%>

Content for Group Space ~!!!! contextId =
<%=session.getAttribute("cid")%>
workSpaceId =
<%=session.getAttribute("wid")%>