<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.calendar.*"%>
<%@ page import="net.smartworks.util.*"%>
<%@ page import="net.smartworks.model.instance.*"%>
<%@ page import="net.smartworks.model.community.*"%>
<%
	SmartWorks smartWorks = (SmartWorks) request
			.getAttribute("smartWorks");
	User cUser = smartWorks.getCurrentUser();

	BoardInstance[] boards = smartWorks.getBoardInstances(
			new LocalDate(), 10);
%>
<!-- 공지사항 -->
<div id="notice">
	<ul>
		<%
			for (BoardInstance board : boards) {
				User owner = board.getOwner();
		%>
		<li>
			<div class="noti_pic">
				<img src="<%=owner.getMidPicture()%>"
					alt="<%=owner.getLongName()%>" align="bottom" />
			</div>
			<div class="noti_in">
				<span class="t_name"><%=owner.getName()%></span>
				<%
					if (!board.getWorkSpace().getId().equals(owner.getId())) {
				%>
				<span class="arr">▶</span><span class="ico_division_s"><%=board.getWorkSpace().getName()%></span>
				<%
					}
				%>
				<span class="t_date"> <%=board.getLastModifiedDate().toLocalString()%></span>
				<span class="noti_tit"><%=board.getSubject()%></span>
			</div></li>
		<%
			}
		%>
	</ul>
</div>
<!--공지사항//-->