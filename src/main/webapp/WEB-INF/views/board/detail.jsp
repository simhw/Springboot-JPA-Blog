<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">이전</button>
    <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
        <button class="btn btn-danger" id="btn-delete">삭제</button>
    </c:if>
    <br/><br/>
    <div class="form-group">
        <div>
            글 번호: <span id="id"><i>${board.id}</i></span>&nbsp
            작성자: <span><i>${board.user.id}</i></span>
        </div>
        <br/>
        <h3>${board.title}</h3>
        <hr/>
        <div>${board.content}</div>
    </div>
    <hr/>
    <div class="card">
        <form>
            <input type="hidden" id="userId" value='${principal.user.idx}'/>
            <input type="hidden" id="boardId" value='${board.id}'/>
            <div class="card-body">
                <textarea id="reply-content" class="form-control" rows="1"></textarea>
            </div>
            <div class="card-footer">
                <button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
            </div>
        </form>
    </div>
    <br/>

    <div class="card">
        <div class="card-header">댓글</div>
        <ul id="reply--box" class="list-group">
            <c:forEach var="reply" items="${board.replies}">
                <li id="reply--1" class="list-group-item d-flex justify-content-between">
                    <div>${reply.content}</div>
                    <div class="d-flex">
                        <div class="font-italic">작성자: ${reply.user.id}&nbsp
                            <button class="badge">삭제</button>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<script src="/javascript/board.js"></script>
<%@ include file="../layout/footer.jsp" %>