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
	String cid = request.getParameter("cid");
	if (cid == null)
		session.setAttribute("cid", SmartWorks.CONTEXT_HOME);
	else
		session.setAttribute("cid", cid);
	String wid = request.getParameter("wid");
	if (wid == null)
		session.setAttribute("wid", cUser.getId());
	else
		session.setAttribute("wid", wid);

	CompanyCalendar[] threeDaysCC = smartWorks.getCompanyCalendars(
			new LocalDate(), 3);
	LocalDate today = threeDaysCC[0].getDate();
	LocalDate tomorrow = threeDaysCC[1].getDate();
	EventInstance[] events = smartWorks.getEventInstances(
			new LocalDate(), 10);
%>
<!-- 이벤트,공지 포틀릿 -->
<div id="section_portlet">
	<div class="tab_portlet">
		<div class="tab_portlet_l"></div>
		<div class="tab_portletx">
			<span class="date"><%=today.toLocalDateString()%></span> <span
				class="eventd"> <%
 	if (threeDaysCC[0].getCompanyEvents().length > 0) {
 %> ( <%
 	}
 %> <%
 	CompanyEvent[] cesToday = threeDaysCC[0].getCompanyEvents();
 	CompanyEvent[] cesTomorrow = threeDaysCC[1].getCompanyEvents();
 	for (int i = 0; i < cesToday.length; i++) {
 		if (i != 0) {
 %>, <%
 	}
 %><%=cesToday[i].getName()%> <%
 	}
 %> <%
 	if (threeDaysCC[0].getCompanyEvents().length > 0) {
 %>)<%
 	}
 %> </span>
			<%=today.toLocalTimeString()%>
		</div>
	</div>
	<div class="portlet_l" style="display: block;">
		<ul class="portlet_r" style="display: block;">
			<!-- 이벤트 목록 영역 -->
			<div class="event_space">
				<ul>
					<%
						for (int cnt = 0; cnt < threeDaysCC.length; cnt++) {
					%>
					<li class="float_left">
						<div id="event">
							<div class="event_t">
								<div class="event_t_l"></div>
							</div>
							<div class="event_l" style="display: block;">
								<ul class="event_r" style="display: block;">
									<%
										if (cnt == 0) {
									%>
									<li class="line_dashed center"><span class="t_bold"><fmt:message
												key='content.threedays.today' /> </span> <%=today.toLocalDateShortString()%></li>
									<%
										} else if (cnt == 1) {
									%>
									<li class="line_dashed center"><span class="t_bold"><fmt:message
												key='content.threedays.tomorrow' /> </span> <%=tomorrow.toLocalDateShortString()%></li>
									<%
										} else if (cnt == 2) {
									%>
									<li class="line_dashed center"><span class="t_bold"><fmt:message
												key='content.threedays.after' /> </span>
									</li>
									<%
										}
									%>
									<li><span class="t_red"> <%
 	for (int i = 0; (cnt != 2) && (i < cesToday.length); i++) {
 			if (i != 0) {
 %>, <%
 	}
 %><%=cesToday[i].getName()%> <%
 	}
 %> </span>
									</li>
									<%
										for (EventInstance event : events) {

												if (((cnt == 0) && today.isSameDate(event.getStart()))
														|| ((cnt == 1) && tomorrow.isSameDate(event
																.getStart()))
														|| ((cnt == 2) && tomorrow.isAfterDate(event
																.getStart()))) {
													User owner = event.getOwner();
													if (cnt < 2) {
									%>
									<li><span class="t_gbold"><%=event.getStart().toLocalTimeShortString()%></span>
										<%
											} else {
										%>
									
									<li><span class="t_gbold"><%=event.getStart().toLocalString()%></span>
										<%
											}
														if (!owner.getId().equals(cUser.getId())) {
										%> <span class="t_name"><%=owner.getLongName()%></span><span
										class="arr">▶</span> <%
 	}
 %> <%
 	if (!event.getWorkSpace().getId().equals(owner.getId())) {
 %> <span class="ico_division_s"><%=event.getWorkSpace().getName()%></span>
										<%
											}
										%> <%=event.getSubject()%></li>
									<%
										}
											}
									%>
								</ul>
							</div>
							<div class="event_b" style="display: block;"></div>
						</div></li>
					<%
						}
					%>
				</ul>
				<!-- 공지사항 -->
				<jsp:include page="/jsp/content/today/recent_board_list.jsp" />
				<!--공지사항//-->
			</div>
			<!-- 이벤트 목록 영역 //-->
		</ul>
	</div>
	<div class="portlet_b" style="display: block;"></div>
</div>
<!-- 이벤트,공지 포틀릿//-->