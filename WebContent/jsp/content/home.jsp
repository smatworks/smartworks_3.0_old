<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.calendar.*" %>
<%@ page import="net.smartworks.util.*" %>
<%@ page import="net.smartworks.model.instance.*" %>
<%@ page import="net.smartworks.model.community.*" %>
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
%>

<!--등록하기-->
<jsp:include page="/jsp/content/upload/select_upload_action.jsp" />
<!-- 등록하기//-->

<!-- 이벤트,공지 포틀릿 -->
<jsp:include page="/jsp/content/today/three_days_event.jsp"/>
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
						</div>
					</li>
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
						</div>
					</li>
				</ul>
			</div>
		</ul>


	</div>
	<div class="portlet_b" style="display: block;"></div>
</div>
<!-- 나의 진행중인 업무 //-->
<!-- Contents//-->
