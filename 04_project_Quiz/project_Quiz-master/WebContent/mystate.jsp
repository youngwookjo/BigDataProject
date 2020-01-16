<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

		<link rel="stylesheet" type="text/css" href="header.css">
		<link rel="stylesheet" type="text/css" href="about.css">
		
<style>
* {
  box-sizing: border-box;
}

body {
  background-image: url('https://www.w3schools.com/w3images/forestbridge.jpg');
  background-color: #f1f1f1;
  font-family: Arial, Helvetica, sans-serif; 
  
}

/* Center website */
.main {
  max-width: 1000px;
  margin-top: 100px;
  margin-right: auto;
  margin-bottom: auto;
  margin-left: auto;
  
}

h1 {
  font-size: 50px;
  word-break: break-all;
  color: white
  
}

h2 {
  color: white
}

.row {
  margin: 8px -16px;
  
}

/* Add padding BETWEEN each column */
.row,
.row > .column {
  padding: 8px;
}

/* Create four equal columns that floats next to each other */
.column {
  float: left;
  width: 25%;
}

/* Clear floats after rows */ 
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Content */
.content {
  background-color: white;
  padding: 10px;
}

/* Responsive layout - makes a two column-layout instead of four columns */
@media screen and (max-width: 900px) {
  .column {
    width: 50%;
  }
}

/* Responsive layout - makes the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .column {
    width: 100%;
  }
}
</style>
</head>
<body>

	<jsp:include page="header.html"/>
		
<button class="tablink" onclick="openCity('London', this, 'black'); location.href ='about.jsp';">어디까지 가봤니?</button>
<button class="tablink" onclick="openCity('Paris', this, 'black');" id="defaultOpen">나는 어디까지 가봤나?</button>
<button class="tablink" onclick="openCity('Tokyo', this, 'black'); location.href = 'ourstate.jsp';">친구들은 어디까지 가봤나?</button>
<button class="tablink" onclick="openCity('Oslo', this, 'black'); location.href ='codesearch.jsp';" >친구들은 어떻게 풀었나?</button>


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
</script> 
		
<!-- MAIN (Center website) -->
<div class="main">


<h2>나의 기록은 다음과 같습니다</h2>

<!-- Portfolio Gallery Grid -->
<div class="row">
  <div class="column">
    <div class="content">
      <img src="images/mountains.jpg" alt="Mountains" style="width:100%">
      <h3>MY NAME & ID</h3>
      <p>이름 :  ${requestScope.user.userName} <br>아이디: ${requestScope.user.userId}  </p>
    </div>
  </div>
  <div class="column">
    <div class="content">
    <img src="images/lights.jpg" alt="Lights" style="width:100%">
      <h3>MY GRADE</h3>
      <p>그레이드: ${requestScope.user.userGrade}<br>동일 그레이드내 회원 수: 5명</p>
    </div>
  </div>
  <div class="column">
    <div class="content">
    <img src="images/nature.jpg" alt="Nature" style="width:100%">
      <h3>MY RECORD</h3>
      <p>푼 문항수: ${requestScope.user.userQuizCount}<br>총점: ${requestScope.user.userTotalPoint}</p>
    </div>
  </div>
  <div class="column">
    <div class="content">
    <img src="images/mountains.jpg" alt="Mountains" style="width:100%">
      <h3>DISTANCE to the KING</h3>
      <p>왕이 되기까지 필요한 점수: 100점<br></p>
    </div>
  </div>
<!-- END GRID -->
</div>


<div class="content" class="main">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['누가누가 잘하나', '총점', '푼문항수'],
          ['나의 기록', 150, 6],
          ['프로그래머스 왕', 300, 20],
          ['우리반 평균', 100, 3],
          
        ]);

        var options = {
          chart: {
            title: '나와 친구들의 기록',
            subtitle: '누가누가 잘하나',        
          }
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
        
      }
    </script>
  
  
    <div id="columnchart_material" style="width: 800px; height: 500px;"></div>

<!-- END MAIN -->
</div>
</div>
</body>

<style>
.btn {
  border: 2px solid black;
  background-color: white;
  color: black;
  padding: 13px 25px;
  font-size: 13px;
  cursor: pointer;
  margin-left: 30px;
}

/* Green */
.read{
  border-color: #16510d;
  color: #16510d;
  
}

.read:hover {
  background-color: #16510d;
  color: white;
 
}

</style>
  <form action="mycodesearch.jsp">
<button class="btn read" onclick="mycodesearch.jsp">내 코드 보기</button>
 </form>
</html>