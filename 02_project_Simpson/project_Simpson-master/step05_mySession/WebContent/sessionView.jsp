<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>${requestScope.id}</div><br>
<div>${requestScope.name}</div><br>
<div>${requestScope.searchResult}</div>
<button onclick='location.href=\"search.html\"'>검색화면</button>
<button onclick='location.href=\"end\"'>로그아웃</button>
</body>
</html>