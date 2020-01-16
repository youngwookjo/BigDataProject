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
  <form action="/action_page.php">
       
   <h2>내가 작성한 코드를 수정합니다</h2>
   <hr><br>
   
    <div class="row">
      <div class="col-25">
        <label for="quizlevel">문제 레벨을 선택하세요</label>
      </div>
      <div class="col-75">
        <select id="quizlevel" name="quizlevel">
          <option value="level1">레벨1</option>
          <option value="level2">레벨2</option>
          <option value="level3">레벨3</option>
          <option value="level4">레벨4</option>
          <option value="level5">레벨5</option>
        </select>
      </div>
    </div>
    
    <div class="row">
      <div class="col-25">
        <label for="quizname">문제 이름을 선택하세요</label>
      </div>
      <div class="col-75">
        <select id="quizname" name="quizname">
          <option value="australia">완주하지 못한 선수</option>
          <option value="canada">모의고사</option>
          <option value="usa">체육복</option>
          <option value="usa">K번째수</option>
        </select>
      </div>
    </div>
   
    <div class="row">
      <div class="col-25">
        <label for="qiuzcontent">나는 이렇게 풀었답니다</label>
      </div>
      <div class="col-75">
        <textarea id="quizcontent" name="quizcontent" style="height:200px"></textarea>
      </div>
    </div>
  </form>
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
  text-align: right; 
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
  color: white;"WebContent/mystate.jsp"
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
<form action="mycodeupdate.jsp">
<button class="btn update">수정</button>
</form>
</td>
<td>
<form action="mycodedelete.jsp">
<button class="btn delete">삭제</button>
</form>
</td>
<td>
<form action="mystate.jsp">
<button class="btn complete">완료</button>
</form>
</td>
</table>
</body>
</html>

