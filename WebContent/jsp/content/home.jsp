<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<!--등록하기-->
<%@ include file="upload/select_upload_action.jsp" %>
<!-- 등록하기//-->

<!-- 이벤트,공지 포틀릿 -->
<div id="section_portlet">
	<div class="tab_portlet">
		<div class="tab_portlet_l"></div>
		<div class="tab_portletx">
			<span class="date">2011.10.11 화요일</span> <span class="eventd">(창립기념일)</span>
			PM 15:50
		</div>
	</div>
	<div class="portlet_l" style="display: block;">
		<ul class="portlet_r" style="display: block;">

			<!-- 이벤트 목록 영역 -->
			<div class="event_space">

				<ul>
					<!-- 이벤트 오늘 -->
					<li class="float_left">
						<div id="event">
							<div class="event_t">
								<div class="event_t_l"></div>
							</div>

							<div class="event_l" style="display: block;">
								<ul class="event_r" style="display: block;">
									<li class="line_dashed center"><span class="t_bold">오늘</span>
										10.12 수 <span class="t_red">창립기념일</span>
									</li>
									<li class="space_top"><span class="t_gbold">09:00</span> <span
										class="t_name">Minashin</span><span class="arr">▶</span><span
										class="ico_division_s">마케팅/디자인팀</span> 스마트웍스닷넷3.0 기획회의</li>
									<li><span class="t_gbold">12:00</span> 하이닉스 점심식사</li>
									<li><span class="t_gbold">15:00</span> 주간보고 회의</li>
									<li><span class="t_gbold">15:00</span> 주간보고 회의</li>
									<li><span class="t_gbold">15:00</span> 주간보고 회의</li>
									<li><span class="t_gbold">15:00</span> 주간보고 회의</li>
									<li><span class="t_gbold">15:00</span> 주간보고 회의</li>
								</ul>
							</div>

							<div class="event_b" style="display: block;"></div>
						</div></li>
					<!-- 이벤트 오늘 //-->



					<!-- 이벤트 내일 -->
					<li class="float_left">
						<div id="event">
							<div class="event_t">
								<div class="event_t_l"></div>
							</div>

							<div class="event_l" style="display: block;">
								<ul class="event_r" style="display: block;">
									<li class="line_dashed center"><span class="t_bold">내일</span>
										10.13 목</li>
									<li class="space_top"><span class="t_gbold">09:00</span> <span
										class="t_name">Minashin</span><span class="arr">▶</span><span
										class="ico_division_s">마케팅/디자인팀</span> 스마트웍스닷넷3.0 기획회의</li>
									<li><span class="t_gbold">12:00</span> 하이닉스 점심식사</li>
									<li><span class="t_gbold">15:00</span> 주간보고 회의</li>
								</ul>
							</div>

							<div class="event_b" style="display: block;"></div>
						</div></li>
					<!-- 이벤트 내일 //-->



					<!-- 이벤트 모레 -->
					<li class="space_right">
						<div id="event">
							<div class="event_t">
								<div class="event_t_l"></div>
							</div>

							<div class="event_l" style="display: block;">
								<ul class="event_r" style="display: block;">
									<li class="line_dashed"><span class="t_bold">모레</span>
										10.14 금</li>
									<li class="space_top">등록된 이벤트가 일정이 없습니다</li>
								</ul>
							</div>

							<div class="event_b" style="display: block;"></div>
						</div></li>
					<!-- 이벤트 모레 //-->
				</ul>

				<!-- 공지사항 -->
				<div id="notice">
					<ul>
						<li>
							<div class="noti_pic">
								<img src="images/pic_size_29.jpg" alt="신민아" align="bottom" />
							</div>
							<div class="noti_in">
								<span class="t_name">Minashin</span><span class="arr">▶</span><span
									class="ico_division_s">마케팅/디자인팀</span><span class="t_date">
									2011.10.13</span> <span class="noti_tit">하반기 해외 B2B마케팅 성공사례 세미나</span>
							</div></li>
						<li>
							<div class="noti_pic">
								<img src="images/pic_size_29.jpg" alt="신민아" align="bottom" />
							</div>
							<div class="noti_in">
								<span class="t_name">Minashin</span><span class="arr">▶</span><span
									class="ico_division_s">마케팅/디자인팀</span><span class="t_date">
									2011.10.13</span> <span class="noti_tit">하반기 해외 B2B마케팅 성공사례 세미나</span>
							</div></li>

					</ul>
				</div>
				<!--공지사항//-->

			</div>
			<!-- 이벤트 목록 영역 //-->

		</ul>


	</div>
	<div class="portlet_b" style="display: block;"></div>
</div>
<!-- 이벤트,공지 포틀릿//-->

<!-- 나의 진행중인 업무 -->
<div id="section_portlet">
	<div class="portlet_t">
		<div class="portlet_tl"></div>
	</div>
	<div class="portlet_l" style="display: block;">
		<ul class="portlet_r" style="display: block;">
			<div id="work_ing">
				<ul>
					<li><span class="tit">나의 진행중인 업무</span>
						<div class="srch">
							<input id="" class="input" type="text" title="업무찾기" value="업무찾기">
							<button title="검색" onclick=""></button>
						</div></li>
					<li class="working_br">
						<div class="pic">
							<img src="images/ic_state_ing.jpg" alt="진행중" /> <img
								src="images/pic_size_48.jpg" alt="임종훈" />
						</div>
						<div>
							<span class="t_name">Jisook Kim</span>의 업무가 <span
								class="t_woname">대표이사 승인</span>을 기다리고 있습니다
						</div>
						<div>
							<span class="ico_iworks t_date">일반관리 > 근태품의</span> <span
								class="t_bold">건강검진으로 인해 근태신청합니다</span>
						</div>
						<div>
							<span class="t_date">2011.08.12 18:00</span>
						</div></li>
				</ul>
			</div>
		</ul>


	</div>
	<div class="portlet_b" style="display: block;"></div>
</div>
<!-- 나의 진행중인 업무 //-->
<!-- Contents//-->
