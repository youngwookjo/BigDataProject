<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="header.css">

<style>
	
body, html {
  height: 100%;
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  background-image: url('https://www.w3schools.com/w3images/forestbridge.jpg');
  color : white 
}

h1 {
  font-size: 50px;
  word-break: break-all;
  color: white 
}

.hero-image {

  height: 50%;
  margin: 0;
  margin-top: 65px;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

.hero-text {
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
}

.hero-text button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 10px 25px;
  color: black;
  background-color: #ddd;
  text-align: center;
  cursor: pointer;
}

.hero-text button:hover {
  background-color: #555;
  color: white;
}

#myProgress {
  width: 100%;
  background-color: #ddd;
}

#myBar {#e5e4e2"
  width: 1%;
  height: 30px;
  background-color: #4CAF50;
}

.accordion {
  background-color: #eee;
  color: #444;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
  transition: 0.4s;
}

.active, .accordion:hover {
  background-color: #ccc; 
}

.panel {
  padding: 0 18px;
  display: none;
  background-color: #ccc;
  overflow: hidden;
}

.middledown {
  position: absolute;
  top: 77%;
  left: 50.5%;
  transform: translate(-60%, -50%);
  text-align: center;
   width: 400px;
  
}

.middledown {
  position: absolute;
  top: 300px;
  left: 400px;
  text-align: center;
   width: 600px;
  
}

.middleright {
  position: absolute;
  top: 70%;
  transform: translate(250%, 10%);
   width: 400px;
  
}

.middleleft {
  position: absolute;
 top: 70%;
  transform: translate(70%, 10%)
}

</style>
</head>
<body>


	<jsp:include page="header.html"/>

<button class="tablink" onclick="openCity('London', this, 'black');location.href ='about.jsp';">어디까지 가봤니?</button>
<button class="tablink" onclick="openCity('Paris', this, 'black'); getUser();">나는 어디까지 가봤나?</button>
<button class="tablink" onclick="openCity('Tokyo', this, 'black');" id="defaultOpen">친구들은 어디까지 가봤나?</button>
<button class="tablink" onclick="openCity('Oslo', this, 'black'); location.href ='codesearch.jsp';">친구들은 어떻게 풀었나?</button>

<form name="myState">
<input type="hidden" name="userId" value = "${sessionScope.userId}"/>
</form>

<script>
function openCity(cityName,elmnt,color) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablink");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].style.backgroundColor = "";
  }
  document.getElementById(cityName).style.display = "block";
  elmnt.style.backgroundColor = color;

}
// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();

function getUser(){
	var f = document.myState;
	 f.action="quizFrontController?command=getUser&userId="+f.userId.value
	 f.method="post";
     f.submit();
  }
  
</script> 


<div class="hero-image">
  <div class="hero-text">
  
  <p id="demo"></p>

<script>
var d = new Date();
document.getElementById("demo").innerHTML = d;
</script>

<h1 style="font-size:30px">우리반 프로그래머스 왕은</h1>
<div class="middledown">
	<jsp:include page="sildebottom.html"/>
</div>

  </div>
</div>


</head>
<body>

<div class="container">
	<div class="middleleft">
  <img src="images/img_avatar.png" alt="Avatar" class="image" style="width=300px;width: 300px;">
  <div class="overlay">
    <div class="text">2위<br>김종성씨입니다</div>
    </div>
  </div>
</div>

<div class="container">
 <div class="middleright">
  <img src="images/img_avatar3.png" alt="Avatar" class="image" style="width=300px;width: 300px;">
  <div class="overlay">
    <div class="text">3위<br> 유은나씨입니다</div>
    </div>
  </div>
</div>

</body>
</html>
