<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptSelect</title>
</head>
<body>
<%@include file="/common/header.jsp" %>
	<h3>부서 상세 조회</h3>
	<table border="1">
		<tr>
			<td>부서현황</td>
			<td>${dept.department_id}</td>
		</tr>
		<tr>
			<td>부서명</td>
			<td>${dept.department_name}</td>
		</tr>
		<tr>
			<td>매니저</td>
			<td>${dept.manager_id}</td>
		</tr>
		<tr>
			<td>지역</td>
			<td>${dept.location_id}</td>
		</tr>
</table>
</body>
</html>