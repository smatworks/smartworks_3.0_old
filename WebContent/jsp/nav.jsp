<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	SmartWorks smartworks = (SmartWorks)request.getAttribute("smartworks");
	String contentContext = request.getParameter("cid");
	if (contentContext == null)
		contentContext = SmartWorks.CONTEXT_HOME;
	String workSpaceId = request.getParameter("wid");
	if (workSpaceId == null)
		workSpaceId = smartworks.getCurrentUser().getId();
%>


<div class="personal_info">
	<%@ include file="nav/space_profile.jsp"%>
</div>

<%
	if (workSpaceId == null
			|| workSpaceId.equals(smartworks().getId())) {
%>
<div class="nav_list">
	<%@ include file="nav/works.jsp"%>
</div>
<div class="nav_list">
	<%@ include file="nav/communities.jsp"%>
</div>
<%
	} else if (smartworks.isSameContextPrefix(
			SmartWorks.CONTEXT_PREFIX_GROUP_SPACE, contentContext)
			|| smartworks.isSameContextPrefix(
					SmartWorks.CONTEXT_PREFIX_DEPARTMENT_SPACE,
					contentContext)) {
%>
<div class="nav_list">
	<%@ include file="nav/community_members.jsp"%>
</div>
<%
	}
%>

<div class="navi_tit_mail">
	<%@ include file="nav/mail.jsp"%>
</div>
<div class="navi_tit_chat">
	<%@ include file="nav/chatting.jsp"%>
</div>
