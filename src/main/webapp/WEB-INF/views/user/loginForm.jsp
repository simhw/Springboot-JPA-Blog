<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../layout/header.jsp"%>


<!-- 
REST_API_KEY = 784bf132b14053e60a1032b39c3818d2
REDIRECT_URI = http://localhost:8080/auth/kakao/callback
 -->

<div class="container">
	<form action="/auth/api/user/login" method="post">
		<div class="form-group">
			<label for="id">아이디</label>
			<input type="text" class="form-control" id="id" name="username" placeholder="ID">
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label>
			<input type="password" class="form-control" id="password" name="password" placeholder="PASSWORD">
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=784bf132b14053e60a1032b39c3818d2&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"> <img height="38px" src="/image/kakao_login_button.png"></a>
	</form>
</div>
<br>

<!--script src="/javascript/user.js"/-->
<%@ include file="../layout/footer.jsp"%>x