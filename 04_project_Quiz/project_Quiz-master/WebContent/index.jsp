<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <meta charset="UTF-8">
<title>Insert title here</title>
<style>
body, html {
  height: 100%;
  margin: 0;
}
.open--button {
  background-color: #555;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  position: fixed;
  bottom: 23px;
  right: 28px;
  width: 280px;
}

.bgimg {
  background-image: url('https://www.w3schools.com/w3images/forestbridge.jpg');
  height: 100%;
  background-position: center;
  background-size: cover;
  position: relative;
  color: white;
  font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", 
  Helvetica, Arial, "Lucida Grande", sans-serif;
  font-size: 25px;
}

.topleft {
  position: absolute;
  top: 0;
  left: 16px;
}

.bottomleft {
  position: absolute;
  bottom: 0;
  left: 16px;
}

.middle {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

.middledown {
  position: absolute;
  top: 77%;
  left: 50.5%;
  transform: translate(-60%, -50%);
  text-align: center;
   width: 400px;
  
}

.middledowndown{
  position: absolute;
  top: 88%;
  left: 50.5%;
  transform: translate(-60%, -50%);
  text-align: center;
   width: 400px;
}


hr {
  margin: auto;
  width: 40%;
}
</style>
 
<body>

<div class="bgimg">
  <div class="topleft">
    <p>programmers'</p>
  </div>
  <div class="middle">
    <h1>남은 시간</h1>
    <hr>
    <p id="demo" style="font-size:30px"></p>
  </div>
  <div class="bottomleft">
    <p>left for you before the program ends</p>
  </div>
</div>

<div class="middledown">
	<jsp:include page="login.html" />
</div>

<div class="middledowndown">
<button class="open--button" onclick="location='signup.html'">가입하기</button>
</div>
<script>
// Set the date we're counting down to
var countDownDate = new Date("Jan 16, 2020 18:00:00").getTime();

// Update the count down every 1 second
var countdownfunction = setInterval(function() {

  // Get todays date and time
  var now = new Date().getTime();
  
  // Find the distance between now an the count down date
  var distance = countDownDate - now;
  
  // Time calculations for days, hours, minutes and seconds
  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);
  
  // Output the result in an element with id="demo"
  document.getElementById("demo").innerHTML = days + "d " + hours + "h "
  + minutes + "m " + seconds + "s ";
  
  // If the count down is over, write some text 
  if (distance < 0) {
    clearInterval(countdownfunction);
    document.getElementById("demo").innerHTML = "EXPIRED";
  }
}, 1000);


</script>
</body>
</html>