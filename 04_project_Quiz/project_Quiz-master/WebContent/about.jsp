<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>about.jsp</title>

 
<meta name="viewport" content="width=device-width, initial-scale=1">

		<link rel="stylesheet" type="text/css" href="header.css">
		<link rel="stylesheet" type="text/css" href="about.css">

 </head>
<body>

	<jsp:include page="header.html"/>
	
<button class="tablink" onclick="openCity('London', this, 'black');"id="defaultOpen">어디까지 가봤니?</button>
<button class="tablink" onclick="openCity('Paris', this, 'black'); getUser();">나는 어디까지 가봤나?</button>
<button class="tablink" onclick="openCity('Tokyo', this, 'black'); location.href ='ourstate.jsp';">친구들은 어디까지 가봤나?</button>
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

function getUser(){
	var f = document.myState;
	 f.action="quizFrontController?command=getUser&userId="+f.userId.value
	 f.method="post";
     f.submit();
  }

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
</script> 


	<div class="timeline">
    <div class="container left">
    <div class="contentleft">
      <h2>ABOUT</h2>
      <p>'어디까지 가봤니'는 알고리즘 학습을 고취하기 위해 만들어졌습니다. PROGRAMMERS에서의  학습내용을 기록하고, 다른 친구들의 진행상황과 비교/분석하는 가운데 함께 성장하는 기회를 제공합니다. 알고리즘 보완관님의 수고가 줄어드는 효과는 덤이겠죠?</p>
    </div>
  </div>
  <div class="container right">
    <div class="content">
      <h2>DEVELOPED</h2>
      <p> by 김동성, 김수경, 조영욱 in August, 2019</p>
    </div>
  </div>
  <div class="container left">
    <div class="contentleft">
      <h2>FEATURE #1</h2>
      <p>PROGRAMMERS에서 지금까지 자신이 해결한 문제와 그 해결내용을 검색하고 수정할 수 있습니다.</p>
    </div>
  </div>
  <div class="container right">
    <div class="content">
      <h2>FEATURE #2</h2>
      <p>PROGRAMMERS에서 지금까지 다른 친구들이 해결한 문제와 그 해결내용을 검색할 수 있습니다.</p>
    </div>
  </div>
  <div class="container left">
    <div class="contentleft">
      <h2>FEATURE #3</h2>
      <p>해결한 문제수와 난이도에 따라 산출된 총점으로 등급을 부여받으며, 나와 다른 친구들의 성적을 한 눈에 확인할 수 있습니다.</p>
    </div>
  </div>
  <div class="container right">
    <div class="content">
      <h2>LANGUAGES & SKILLSETS</h2>
      <p>Selenium, Java, HTML, CSS, JavaScript, Servlet and JSP, Asynchronous programming, SQL/PLSQL, Google Charts etc.</p>
    </div>
  </div>
</div>
	
</body>
</html> 
