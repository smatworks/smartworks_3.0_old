<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.*"%>
<%
	String cid = request.getParameter("cid");
	if (cid == null)
		cid = SmartWorks.CONTEXT_HOME;
	String wid = request.getParameter("wid");
	if (wid == null)
		wid = SmartWorks.getCurrentUser().getId();
%>

<div id="upload">
	<ul class="js_select_action">
		<li class="up_works"><a id="action_work" href=""><fmt:message key='common.upload.work' /> </a></li>
		<li class="up_file"><a id="action_file" href=""><fmt:message key='common.upload.file' /> </a></li>
		<li class="up_event"><a id="action_event" href=""><fmt:message key='common.upload.event' /> </a></li>
		<li class="up_memo"><a id="action_memo" href=""><fmt:message key='common.upload.memo' /> </a></li>
		<li class="up_board"><a id="action_board" href=""><fmt:message key='common.upload.board' /> </a></li>
	</ul>

	<!-- 새업무 등록 -->
	<div class="js_upload_form" id="action_work_box">
		<%@ include file="start_work.jsp"%>
	</div >
	<!-- 새업무 등록 //-->
	<!-- 파일 올리기  -->
	<div class="js_upload_form" id="action_file_box" style="display:none">
		<%@ include file="upload_file.jsp"%>
	</div>
	<!-- 파일 올리기  //-->
	<!-- 이벤트 등록 -->
	<div class="js_upload_form" id="action_event_box" style="display:none">
		<%@ include file="register_event.jsp"%>
	</div>
	<!-- 이벤트 등록 //-->
	<!-- 메모 등록 -->
	<div class="js_upload_form" id="action_memo_box" style="display:none">
		<%@ include file="register_memo.jsp"%>
	</div>
	<!-- 메모 등록 //-->
	<!-- 공지 등록 -->
	<div class="js_upload_form" id="action_board_box" style="display:none">
		<%@ include file="register_board.jsp"%>
	</div>
	<!-- 공지 등록 //-->
</div>
<!-- 등록하기//-->
