	<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<% 
String url = application.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br><br><br>
<center>

<h3>유저 정보</h3>
<hr><p> 
 
<table border="1">
	<tr>
		<th>user number</th><th>user id</th><th>user name</th><th>user grade</th><th>user quiz count</th><th>user total ponint</th>
	</tr>
 	<tr>
 		<td> ${user.userNumber} </td>
 		<td> ${user.userId} </td>
 		<td> ${user.userName} </td>
 		<td> ${user.userGrade} </td>
 		<td> ${user.userQuizCount} </td>
 		<td> ${user.userTotalPoint} </td>
 	</tr>
</table>

<br><br><br>

</center>
</body>
</html>