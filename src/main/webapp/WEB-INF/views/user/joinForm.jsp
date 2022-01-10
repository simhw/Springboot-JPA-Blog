<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <h2>회원가입</h2>
    <form>
        <div class="form-group">
            <label for="id">아이디</label>
            <input type="text" class="form-control" id="id" placeholder="ID" name="id">
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" id="password" placeholder="PASSWORD" name="password">
        </div>
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" placeholder="EMAIL" name="email">
        </div>
    </form>
    <button id="btn-save" class="btn btn-primary">회원가입</button>
</div>
<br>

<script src="/javascript/user.js"></script>

<!-- /views/user/user.jsp -->

<%@ include file="../layout/footer.jsp" %>
