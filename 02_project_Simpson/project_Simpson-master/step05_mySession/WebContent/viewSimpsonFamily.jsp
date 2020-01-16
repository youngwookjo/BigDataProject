<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.dao.SimpsonDAO" %>
<%@ page import="model.dto.SimpsonDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%for(SimpsonDTO s : SimpsonDAO.getFamily()) {
			out.println(s.toString() + "<br>");}%>

<button onclick='location.href=\"search.html\"'>검색 화면</button>
<button onclick='location.href=\"end\"'>로그아웃</button>
</body>
</html>