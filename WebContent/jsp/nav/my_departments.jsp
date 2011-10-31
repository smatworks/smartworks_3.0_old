<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.community.*"%>

<%
	SmartWorks smartWorks = (SmartWorks) request
			.getAttribute("smartWorks");
	Department[] departments = smartWorks
			.getMyDepartments("currentUser");
%>

<ul>
	<%
		for (Department department : departments) {
			String departmentContext = SmartWorks.CONTEXT_PREFIX_DEPARTMENT_SPACE
					+ department.getId();
	%>
	<li class="ico_depart"><a
		href="department_space.sw?cid=<%=departmentContext%>&wid=<%=department.getId()%>"><%=department.getName()%></a>
	</li>
	<%
		}
	%>
</ul>
