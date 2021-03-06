<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="net.smartworks.*"%>
<%
	SmartWorks smartWorks = (SmartWorks) request
			.getAttribute("smartWorks");
	String cid = request.getParameter("cid");
	if (cid == null)
		cid = SmartWorks.CONTEXT_HOME;
	String wid = request.getParameter("wid");
	if (wid == null)
		wid = smartWorks.getCurrentUser().getId();
%>


<div class="personal_info">
	<jsp:include page="/jsp/nav/space_profile.jsp"/></div>

<%
	if (wid == null || wid.equals(smartWorks.getCurrentUser().getId())) {
%>
<div class="nav_list">
	<jsp:include page="/jsp/nav/works.jsp"/>
</div>
<div class="nav_list">
	<jsp:include page="/jsp/nav/communities.jsp"/>
</div>
<%
	} else if (smartWorks.isSameContextPrefix(
			SmartWorks.CONTEXT_PREFIX_GROUP_SPACE, cid)
			|| smartWorks.isSameContextPrefix(
					SmartWorks.CONTEXT_PREFIX_DEPARTMENT_SPACE, cid)) {
%>
<div class="nav_list">
	<jsp:include page="/jsp/nav/community_members.jsp"/>
</div>
<%
	}
%>

<div class="navi_tit_mail">
	<jsp:include page="nav/mail.jsp" />
</div>
<div class="navi_tit_chat">
	<jsp:include page="nav/chatting.jsp" />
</div>
