<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <div class="row container d-flex justify-content-center">
        <div class="col-md-6">
            <div class="card card-bordered">

                <div class="card-header">
                    <h4 class="card-title"><strong>메세지</strong></h4>
                </div>

                <div class="ps-container ps-theme-default ps-active-y" id="chat-content"
                     style="overflow-y: scroll !important; height:400px !important;">
                    <div class="media media-chat"><img src="/image/chat_profile.png">
                        <div class="media-body">
                            <p>Hi How are you ...???</p>
                            <p>What are you doing tomorrow?<br> Can we come up a bar?</p>
                            <p class="meta">
                                <time datetime="2018">23:58</time>
                            </p>
                        </div>
                    </div>
                    <div class="media media-chat media-chat-reverse">
                        <div class="media-body">
                            <p>Hiii, I'm good.</p>
                            <p>How are you doing?</p>
                            <p>Long time no see! Tomorrow office. will be free on sunday.</p>
                            <p class="meta">
                                <time datetime="2018">00:06</time>
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
                    <img class="avatar avatar-xs"
                         src="https://img.icons8.com/color/36/000000/administrator-male.png"
                         alt="...">
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

