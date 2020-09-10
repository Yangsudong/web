<%@page import="member.MemberVO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function inputCheck() {
	//id, pw 필수입력체크
	if(frm.id.value == ""){
		window.alert("id 입력");
		frm.id.focus();
		return false; 
	}
	if(frm.pass.value == ""){
		alert("pw 입력");
		frm.pass.focus();
		return false;		
	}	
	
	//job(select태그)
	if(frm.job.value == "") {
		alert("job 선택");
		frm.job.focus();
		return false;
	}
	//radio, checkbox
	var gender =
		document.querySelectorAll("[name ='gender']:checked").length;
	if(gender == 0) {
		alert("성별 적어도 하나는 선택");
		return false;
	}
	
	//frm.submit();
	return true;
} 


</script>
</head>
<body>
<%@include file="/common/header.jsp" %>

<h3 class="page_title">회원정보수정</h3>
<form method="post" name="frm" id="frm"
	  action = "memberUpdate"
	  onsubmit="return inputCheck()">
	  
	<div>
		<label>id</label><input type="text" name="id" value ="${login.id}"><br>
		<label>pass</label><input type="password" name="pass" value ="${login.pass}">
	</div>			
	<div>
		<label> 성별</label> 
		<input type="radio" name="gender" value=man
			<c:if test="${login.gender=='man'}">checked="checked"</c:if>
			>남자 
    	<input type="radio" name="gender" value=woman
    		<c:if test="${login.gender=='woman'}">checked="checked"</c:if>
    		>여자 
    </div><br>
    
	<div>
		<p><label>직업</label></p>
		<select name="job" id="job">
			<option value="">선택</option>
			<option value="devlope">개발자</option>
			<option value="dba">관리자</option>
			<option value="doctor">의사</option>
			<option value="student">학생</option>	
		</select>
	</div><br>
	<div>
		<label>메일수신여부</label>	
		<input type="checkbox" name="mailYN" value ="y"
		<c:if test="${login.mailYn=='y'}">checked="checked"</c:if>>동의
		
	</div><br>
	<div>
		<label for="hobby">취미</label>	
		<input type="checkbox" name="hobby" value="read"
		<c:if test="${login.hobby=='read'}">checked="checked"</c:if>>독서
		<input type="checkbox" name="hobby" value="game"
		<c:if test="${login.hobby=='game'}">checked="checked"</c:if>>게임
		<input type="checkbox" name="hobby" value="ski"
		<c:if test="${login.hobby=='ski'}">checked="checked"</c:if>>스키
		
	</div><br>
	<div>
		<label>가입동기</label><br>
		<textarea id = "reason" name="reason">${sessionScope.login.reason}</textarea>
	</div>
	<div>
	<button type="reset">초기화</button>
	<button>등록</button>
	</div>
	
	
</form>
</body>
</html>