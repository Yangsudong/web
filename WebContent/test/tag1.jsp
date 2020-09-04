<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    autoFlush="false" buffer="1kb"
    errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tag1.jsp</title>
</head>
<body>
<h3>jsp 스크립트 테스트</h3>
<!-- html 주석 -->
<%--jsp 주석 --%>
<% //자바주석 
	for (int i=0; i<1024; i++) {
		out.print(1);
	}
	//버퍼 지우기
	//out.clearBuffer();  //버퍼 내용을 지우기
	//out.flush();		  //버퍼 비우기 == 클라이언트전송	
%>
<hr>
버퍼 남은 크기 : <%=out.getRemaining() %>
</body>
</html>