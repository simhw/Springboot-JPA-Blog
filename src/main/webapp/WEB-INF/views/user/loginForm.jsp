<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../layout/header.jsp"%>


<!-- 
REST_API_KEY = 784bf132b14053e60a1032b39c3818d2
REDIRECT_URI = http://localhost:8080/auth/kakao/callback
 -->

<div class="container">
	<form action="/auth/login" method="post">
		<div class="form-group">
			<label for="username">USERNAME</label> <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username">
		</div>

		<div class="form-group">
			<label for="password">PASSWORD</label> <input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
		</div>

		<button id="btn-login" class="btn btn-primary">Login</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=784bf132b14053e60a1032b39c3818d2&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"> <img height="38px" src="/image/kakao_login_button.png"></a>
	</form>

</div>
<br>


<script src="javascript/user.js"></script>

<%@ include file="../layout/footer.jsp"%>x