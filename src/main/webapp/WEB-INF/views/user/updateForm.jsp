<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden" id="idx" value="${principal.user.idx}" />
		<div class="form-group">
			<label for="id">아이디</label> <input type="text" value="${principal.user.id}" class="form-control" id="id" name="id" readonly="readonly">
		</div>
		<!-- 기존 회원 -->
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
			</div>
			<div class="form-group">
				<label for="email">이메일</label> <input type="email" value='${principal.user.email}' class="form-control" id="email" name="email">
			</div>
<%--		<!-- 카카오 회원 -->--%>
<%--		<c:choose>--%>
<%--			<c:when test="${not empty principal.user.oauth}">--%>
<%--			<div class="form-group">--%>
<%--				<label for="password">PASSWORD</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" readonly="readonly">--%>
<%--			</div>--%>
<%--			<div class="form-group">--%>
<%--				<label for="email">EMAIL</label> <input type="email" value='${principal.user.email}' class="form-control" id="email" name="email" readonly="readonly">--%>
<%--			</div>--%>
<%--			</c:when>--%>
<%--		</c:choose>--%>
	</form>
	<button id="btn-update" class="btn btn-primary">수정</button>
</div>
<br>

<script src="/javascript/user.js"></script>
<%@ include file="../layout/footer.jsp"%>