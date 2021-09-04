<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">이전</button>

	<c:if test="${board.user.id == principal.user.id}">
		<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
		<button class="btn btn-danger" id="btn-delete">삭제</button>
	</c:if>

	<br /> <br />
	<div class="form-group">
		<div>
			글 번호 : <span id="id"><i>${board.id}</i></span> <br /> 작성자 : <span><i>${board.user.username}</i></span> <br />
		</div>
		<h3>${board.title}</h3>
		<hr />
		<div>${board.content}</div>
	</div>
	<hr />
	<div class="card">
		<div class="card-body">
			<textarea class="form-control" rows="1"></textarea>
		</div>
		<div class="card-footer">
			<button class="btn btn-primary">등록</button>
		</div>
	</div>
	<br />
	<div class="card">
		<div class="card-header">댓글</div>
		<ul id="reply--box" class="list-group">
			<c:forEach var="reply" items="${board.replies}">

				<li id="reply--1" class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div>
					<div class="d-flex">
						<div class="font-italic">
							작성자 : ${reply.user.username} &nbsp;
							<button class="badge">삭제</button>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>

<script src="/javascript/board.js"></script>
<%@ include file="../layout/footer.jsp"%>