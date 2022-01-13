<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h1>글쓰기</h1>
	<form>
	<input type="hidden" id="id" value="${board.id}"/>
		<div class="form-group">
			<input id="title"  value='${board.title}' type="text" class="form-control" placeholder="Title">
		</div>
		<div class="form-group">
			<textarea id="content" class="form-control summernote" rows="5" >${board.content}</textarea>
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">수정</button>
</div>

<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 300
	});
</script>
<script src="/javascript/board.js"></script>
<%@ include file="../layout/footer.jsp"%>