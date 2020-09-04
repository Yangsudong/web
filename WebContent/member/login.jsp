<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>
	<%@include file="/common/header.jsp" %>
	<%=request.getAttribute("erromsg") %>
	
	<form method="post" name="frm" id="frm"
			action = "login">
		<div>
			<label for="id">id</label>
			<input name="id" id="id">
		</div>		
		<div>
			<label for="pass">pass</label>
			<input name="pass" id="pass">
		</div>			
		<button>로그인</button>
	</form>
</body>
</html>