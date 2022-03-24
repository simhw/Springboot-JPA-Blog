<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>
<input type="hidden" id="user" value="${principal.user.idx}"/>
<div class="container">
    <div class="row container d-flex justify-content-center">
        <div class="col-md-6">
            <div class="card card-bordered">
                <div class="card-header">
                    <h4 class="card-title"><strong>메세지</strong></h4>
                </div>
                <div id="chat-content" class="ps-container ps-theme-default ps-active-y"
                     style="overflow-y: scroll !important; height:600px !important;">
                    <%-- 받은 메세지 --%>
                    <div class="media media-chat">
                        <img src="/image/chat_profile.png">
                        <div class="media-body">
                            <p>Hi How are you ...???</p>
                            <p class="meta">
                                <time datetime="2022">23:58</time>
                            </p>
                        </div>
                    </div>
                    <%-- 보낸 메세지 --%>
                    <div class="media media-chat media-chat-reverse">
                        <div class="media-body">
                            <p>Hiii, I'm good.</p>
                            <p class="meta">
                                <time datetime="2022">00:06</time>
                            </p>
                        </div>
                    </div>
                    <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;">
                        <div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px;"></div>
                    </div>
                    <div class="ps-scrollbar-y-rail" style="top: 0px; height: 0px; right: 2px;">
                        <div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 2px;"></div>
                    </div>
                </div>
                <div class="publisher bt-1 border-light">
                    <img class="avatar avatar-xs" src="/image/chat_profile.png" alt="...">
                    <input id="chat-outgoing-txt" class="publisher-input" type="text">
                    <button id="chat-outgoing-btn" class="publisher-btn text-info" data-abc="true"><i
                            class="fa fa-paper-plane"></i></button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/javascript/chat.js"></script>
<%@ include file="../layout/footer.jsp" %>

