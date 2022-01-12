<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="layout/header.jsp"%>

<div class="container">
	<c:forEach var="board" items="${boardList.content}">
		<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
	</c:forEach>
	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${boardList.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boardList.number - 1}">이전</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boardList.number - 1}">이전</a></li>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${boardList.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boardList.number + 1}">다음</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boardList.number + 1}">다음</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>

<%@ include file="layout/footer.jsp"%>