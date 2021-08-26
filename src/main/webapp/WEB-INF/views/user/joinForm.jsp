<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h2>Stacked form</h2>
	<form>
		<div class="form-group">
			<label for="username">USERNAME</label> 
			<input type="text" class="form-control" id="username" placeholder="Enter Username" name="username">
		</div>

		<div class="form-group">
			<label for="password">PASSWORD</label> 
			<input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
		</div>
		
		<div class="form-group">
			<label for="email">EMAIL</label> 
			<input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
		</div>
	</form>
	
	<button id="btn-save" class="btn btn-primary">Submit</button>
	
</div>
<br>

<script src="/javascript/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
