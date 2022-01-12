<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h1>글쓰기</h1>
	<form>
		<div class="form-group">
			<label for="title">제목</label>
			<input type="text" class="form-control" id="title" placeholder="Enter Title">
		</div>
		<div class="form-group">
			<label for="content">내용</label>
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">저장</button>
</div>

<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 300
	});
</script>
<script src="/javascript/board.js"></script>
<%@ include file="../layout/footer.jsp"%>