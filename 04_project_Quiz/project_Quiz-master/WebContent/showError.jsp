<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body, html {
  height: 100%;
  margin: 0;
}

.bg {
  /* The image used */
  background-image: url('https://www.w3schools.com/w3images/forestbridge.jpg');

  /* Full height */
  height: 50%; 

  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}


.btn {
  border: 2px solid black;
  background-color: white;
  color: black;
  padding: 13px 25px;
  font-size: 13px;
  cursor: pointer;
  text-align: right; 
}

/* Gray */
.back{
  border-color: #e7e7e7;
  color: black;
}

.back:hover {
  background: #e7e7e7;	
}
</style>
</head>
<body>

<div class="bg"></div>

<br><br><br>
	<center>
	   <h3>${requestScope.errorMsg}</h3>
<br><br><br>	   	   
	    <button class="btn back" onclick="history.back()">이전 페이지로 가기</button>
	</center>
</body>
</html>
