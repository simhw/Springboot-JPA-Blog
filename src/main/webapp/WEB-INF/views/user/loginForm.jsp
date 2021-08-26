<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h2>Stacked form</h2>

	<form action="/auth/login" method="post">
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" >
		</div>

		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" class="form-control" id="password" name="password" placeholder="Enter password" >
		</div>
		
	<button id="btn-login" type="submit" class="btn btn-primary">Login</button>
	</form>
	
</div>
<br>

<script src="javascript/user.js"></script>

<%@ include file="../layout/footer.jsp"%>x