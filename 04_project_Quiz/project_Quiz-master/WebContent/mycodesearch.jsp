<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

      <link rel="stylesheet" type="text/css" href="header.css">
     <link rel="stylesheet" type="text/css" href="codesearch.css">
</head>
<body>


<jsp:include page="header.html"/>
 
<div class="container" >

       
   <h2>내가 작성한 코드를 다시 봅니다</h2>
   <hr><br>
   
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
        
      </form>
         </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="quizcontent">내가 작성한 코드</label>
      </div>
      <div class="col-75">
        <textarea id="quizcontent" name="quizcontent" style="height:200px">나의 코드</textarea>
      </div>
    </div>
   
   <form name="myState">
      <input type="hidden" name="userId" value = "${sessionScope.userId}"/>
   </form>
 <script type="text/javascript">
   
   
   
var level = "";
var levelIndex1="";
var levelValue1="";
var levelIndex2="";
var levelValue2="";
var levelText2="";
var data = [];
var selectHTML;
var f = document.myState;
   
   function selectLevel(){
      levelIndex1 = document.a.quizLevelSelect.options.selectedIndex;
      levelValue1 = document.a.quizLevelSelect.options[levelIndex1].value;
      getLevelQuiz(levelValue1);
   }
   
   function selectQuizName(){
      
      levelIndex2 = document.a.quizNameSelect.options.selectedIndex;
      levelValue2 = document.a.quizNameSelect.options[levelIndex2].value;
      getCodeQuiz(levelValue1, levelValue2);
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
   
   function getCodeQuiz(levelValue, nameValue) {
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
            data = eval(this.responseText);
            console.log(data);
            selectHTML = data;
            document.getElementById("quizcontent").innerHTML = selectHTML;
         }
      };
      xhttp.open("POST", "quizFrontController?command=getSolvedQuizCode&levelValue="+levelValue+"&nameValue="+nameValue+"&userIdValue="+f.userId.value, true);
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
      xhttp.open("POST", "quizFrontController?command=getMySolvedList&levelValue="+levelValue+"&userIdValue="+f.userId.value, true);
      xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
      xhttp.send();
   }
   
   </script> 
 

</div>
</body>
<style>
body, html {


  font-family: Arial, Helvetica, sans-serif;
  background-image: url('https://www.w3schools.com/w3images/forestbridge.jpg');
  }

.btn {
  border: 2px solid black;
  background-color: white;
  color: black;
  padding: 13px 25px;
  font-size: 13px;
  cursor: pointer;

}

/* Gray */
.previous {
  border-color: #e7e7e7;
  color: black;
}

.previous:hover {
  background: #e7e7e7;   
}

/* Green */
.add{
  border-color: #4CAF50;
  color: green;
}

.add:hover {
  background-color: #4CAF50;
  color: white;
}

/* Orange */
.update {
  border-color: #ff9800;
  color: orange;
}

.update:hover {
  background: #ff9800;
  color: white;
}

/* Red */
.delete {
  border-color: #f44336;
  color: red;
}

.delete:hover {
  background: #f44336;
}

/* Gray */
.complete {
  border-color: #e7e7e7;
  color: black;
}

.complete:hover {
  background: #e7e7e7;   
}
</style>
 
<body>
<table>
<td> 
<button class="btn previous"  onclick="history.back()">이전</button>
</td>
<td>
<form action="mycodeadd.jsp">
<button class="btn add" >추가</button>
</form>
</td>
<td>
<form action="mycodedelete.jsp">
<button class="btn delete">삭제</button>
</form>
</td>
</table>
</body>
</html>