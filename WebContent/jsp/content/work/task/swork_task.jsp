<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%
	ISmartWorks smartWorks = (ISmartWorks) request
			.getAttribute("smartWorks");
	String cid = request.getParameter("cid");
	if (cid == null)
		session.setAttribute("cid", ISmartWorks.CONTEXT_HOME);
	else
		session.setAttribute("cid", cid);
	String wid = request.getParameter("wid");
	if (wid == null)
		session.setAttribute("wid", smartWorks.getCurrentUser().getId());
	else
		session.setAttribute("wid", wid);
%>

Content for Swork Task ~!!!! contentId=
<%=session.getAttribute("cid")%>
workSpaceId =
<%=session.getAttribute("wid")%>