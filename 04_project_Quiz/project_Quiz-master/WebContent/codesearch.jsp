<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

      <link rel="stylesheet" type="text/css" href="codesearch.css">
      <link rel="stylesheet" type="text/css" href="header.css">
</head>
 
<body>

<jsp:include page="header.html"/>

<button class="tablink" onclick="openCity('London', this, 'black'); location.href ='about.jsp';">어디까지 가봤니?</button>
<button class="tablink" onclick="openCity('Paris', this, 'black'); getUser();">나는 어디까지 가봤나?</button>
<button class="tablink" onclick="openCity('Tokyo', this, 'black'); location.href = 'ourstate.jsp';">친구들은 어디까지 가봤나?</button>
<button class="tablink" onclick="openCity('Oslo', this, 'black')" id="defaultOpen">친구들은 어떻게 풀었나?</button>

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

<div class="container" >
    
   <h2>친구들이 작성한 코드를 참고하세요</h2>
   <hr>    
    <div class="row">
      <div class="col-25">
        <label for="quizLevelSelect">문제 레벨을 선택하세요</label>
      </div>
      <div class="col-75">
      <form name="a" action="quizFrontController" method="post">
        <select id="quizLevelSelect" name="quizLevelSelect" onChange="selectLevel();">
          <option value="" selected>레벨을 선택하세요.</option>
          <option value="Lv.1">레벨1</option>
          <option value="Lv.2">레벨2</option>
          <option value="Lv.3">레벨3</option>
          <option value="Lv.4">레벨4</option>
          <option value="Lv.5">레벨5</option>
        </select>
     <br>
     <br>
        <select id="quizNameSelect" name="quizNameSelect" onChange="selectQuizName();">
           <option value="" selected>문제선택</option>
          </select>
     <br>
     <br>
           <select id="userNameSelect" name="userNameSelect" onChange="selectUserName();">
           <option value="" selected>친구 이름 : 친구 등급</option>
          </select>
           
     <br>
     <br>
        
      </form>
         </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="quizcontent">친구는 이렇게 풀었답니다</label>
      </div>
      <div class="col-75">
        <textarea id="quizcontent" name="quizcontent" style="height:200px">친구의 코드</textarea>
      </div>
    </div>
   
 
<!-- 
   <div class="row">
      <div class="col-25">
        <label for="quizlevel">문제 레벨을 선택하세요</label>
      </div>
      <div class="col-75">
        <select id="quizLevel" name="quizLevel" >
           <div id='dataView'>
            
         </div>
        </select>
      </div>
    </div>  
     -->

   



<!-- <script type="text/javascript">

var level = "";
var levelIndex="";
var levelValue="";
var data = [];
var selectHTML;
   
   function selectLevel(){
      levelIndex = document.a.quizLevelSelect.options.selectedIndex;
      levelValue = document.a.quizLevelSelect.options[levelIndex].value;
      getLevelQuiz(levelValue);
      alert(levelIndex);
      alert(levelValue);
   }
      function getLevelQuiz(levelValue) {
         var xhttp = new XMLHttpRequest();
         xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
               selectHTML = '<option value=\"\" selected>문제선택</option>';
               data = eval(this.responseText);
               for(var i = 0 ; i < data.length ; i++){
                  selectHTML += '<option value=\"'+data[i]+'\">'+data[i]+'</option>';
               }
               document.getElementById("quizNameSelect").innerHTML = selectHTML;
            }
         };
         xhttp.open("POST", "quizFrontController?command=getLevelQuiz&levelValue="+levelValue, true);
         xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
         xhttp.send();
         
      }
   </script> -->
   
   
   <%-- 이름선택 --%>
 <script type="text/javascript">
   
   
   
var level = "";
var levelIndex1="";
var levelValue1="";
var levelIndex2="";
var levelValue2="";
var levelText2="";
var levelIndex3="";
var levelValue3="";
var data = [];
var selectHTML;
   
   
   function selectLevel(){
      levelIndex1 = document.a.quizLevelSelect.options.selectedIndex;
      levelValue1 = document.a.quizLevelSelect.options[levelIndex1].value;
      getLevelQuiz(levelValue1);
   }
   
   function selectQuizName(){
      
      levelIndex2 = document.a.quizNameSelect.options.selectedIndex;
      levelValue2 = document.a.quizNameSelect.options[levelIndex2].value;
      levelText2 = document.a.quizNameSelect.options[levelIndex2].text;
      getNameQuiz(levelValue1, levelValue2);
   }
   
   function selectUserName() {
      levelIndex3 = document.a.userNameSelect.options.selectedIndex;
      levelValue3 = document.a.userNameSelect.options[levelIndex3].value;
      getCodeQuiz(levelValue1, levelValue2, levelValue3);
   }
   
   
   function getNameQuiz(levelValue, nameValue) {
      console.log(levelValue, nameValue);
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
            selectHTML = '';
            data = eval(this.responseText);
            for(var i = 0 ; i < data.length ; i++){
               /* var user = data[i].split(','); */
               selectHTML += '<option value=\"'+data[i][0]+'\">'+data[i][1]+ ' : ' +data[i][2] + '</option>';
            }
            document.getElementById("userNameSelect").innerHTML = selectHTML;
         }
      };
      
      xhttp.open("POST", "quizFrontController?command=getSolvedList&levelValue="+levelValue+"&nameValue="+nameValue, true);
      xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
      xhttp.send();
   } 
   
   function getCodeQuiz(levelValue, nameValue, userIdValue) {
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
            data = eval(this.responseText);
            selectHTML = data;
            document.getElementById("quizcontent").innerHTML = selectHTML;
         }
      };
      xhttp.open("POST", "quizFrontController?command=getSolvedQuizCode&levelValue="+levelValue+"&nameValue="+nameValue+"&userIdValue="+userIdValue, true);
      xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
      xhttp.send();
   }

   function getLevelQuiz(levelValue) {
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
            selectHTML = '<option value=\"\" selected>문제선택</option>';
            data = eval(this.responseText);
            console.log(data);
            for(var i = 0 ; i < data.length ; i++){
               selectHTML += '<option value=\"'+data[i]+'\">'+data[i]+'</option>';
            }
            document.getElementById("quizNameSelect").innerHTML = selectHTML;
         }
      };
      xhttp.open("POST", "quizFrontController?command=getLevelQuiz&levelValue="+levelValue, true);
      xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
      xhttp.send();
   }
   
   </script> 

    
    
<%--
    <div class="row">
      <div class="col-25">
        <label for="quizname">문제 이름을 선택하세요</label>
      </div>
      <div class="col-75">
      <form action="quizFrontController" method="post">
        <select id="quizName" name="quizName">
         <c:forEach items="${requestScope.quizNameList}" var="quizName"> 
            <option value="${quizName.quizName}">${quizName.quizName}</option>
         </c:forEach>
        </select>
        <input type="hidden" name = "command" value="getSolverList">
        <input type="hidden" name = "quizLevel" value="${requestScope.quizLevel}">
         <input type="submit" value="문제 선택">
      </form>
      </div>
    </div>
    
--%>
  
<%--    <div class="row">
      <div class="col-25">
        <label for="username">친구를 선택하세요</label>
      </div>
      <div class="col-75">
      <form action="quizFrontController" method="post">
        <select id="userId" name="userId">
         <c:forEach items="${requestScope.quizSolverList}" var="quizSolver">
            <option value="${quizSolver.userId}">${quizSolver.userName} ${quizSolver.userGrade}</option>
         </c:forEach>
        </select>
        <input type="hidden" name = "command" value="getSolvedQuizCode">
        <input type="hidden" name = "quizLevel" value="${requestScope.quizLevel}">
        <input type="hidden" name = "quizName" value="${requestScope.quizName}">
         <input type="submit" value="친구 선택">
        </form>
      </div>
    </div> --%>

    

</div>

</body>
</html>