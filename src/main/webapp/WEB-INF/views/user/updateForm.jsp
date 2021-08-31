<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden" id="id" value="${principal.user.id}" />

		<div class="form-group">
			<label for="username">USERNAME</label> <input type="text" value="${principal.user.username}" class="form-control" id="username" name="username" readonly="readonly">
		</div>
		
		<!-- 기존 회원 -->
		<c:choose>
		
			<c:when test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">PASSWORD</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
			</div>

			<div class="form-group">
				<label for="email">EMAIL</label> <input type="email" value='${principal.user.email}' class="form-control" id="email" name="email">
			</div>
			
			</c:when>
		
		</c:choose>
		
		<!-- 카카오 회원 -->
		<c:choose>
		
			<c:when test="${not empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">PASSWORD</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" readonly="readonly">
			</div>

			<div class="form-group">
				<label for="email">EMAIL</label> <input type="email" value='${principal.user.email}' class="form-control" id="email" name="email" readonly="readonly">
			</div>
			
			</c:when>
		
		</c:choose>
	</form>

	<button id="btn-update" class="btn btn-primary">Submit</button>

</div>
<br>

<script src="/javascript/user.js"></script>
<%@ include file="../layout/footer.jsp"%>