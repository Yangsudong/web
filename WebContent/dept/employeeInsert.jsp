<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style>
	label { display: inline-block; width : 100px}
</style>
</head>
<body>
   <h1>사원등록</h1>
   <form action="empInsert" method="post">  
    <div><label>employeeId</label> <input name="employee_id"></div>
    <div><label>firstName</label> <input name="first_name"></div>
    <div><label>lastName</label> <input name="last_name"></div>
    <div><label>email</label> <input name="email"></div>
   
    <div><label>hireDate</label> <input name="hire_date"></div>
    <div><label>department_id</label><br> 
    		<c:forEach items="${deptlist}" var="deptid">
    			<input type="radio" name="department_id" value="${deptid.department_id}">${deptid.department_id} ${deptid.department_name}  <br>				 
			</c:forEach>
			</div>
    <div><label>jobId</label> <select name="job_id">
    			<option value="">선택 
			<c:forEach items="${joblist}" var="job">
				<option value="${job.job_id}">${job.job_id} ${job.job_title}
			</c:forEach>	
    	  </select></div>
    	  
    <div><label>manager_id</label> <select name="manager_id">
    			<option value="">선택 
			<c:forEach items="${emplist}" var="manager">
				<option value="${manager.manager_id}">${manager.manager_id}&nbsp;${manager.first_name}&nbsp;${manager.last_name}
			</c:forEach>
    	</select></div>   	
    <button>등록</button>
    </form>
</body>
</html>