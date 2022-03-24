// 보낸 메세지
function getSendMsgContent(data) {
    return `<div class="media-body">
                <p>${data.msg}</p>
                <p class="meta"><time datetime="2022">${data.createdAt}</time></p>            
            </div>`;
}

// 받은 메세지
function getReceiveMsgContent(data) {
    return `<div class="media media-chat">
                 <img src="/image/chat_profile.png">
                 <div class="media-body">
                    <p>${data.msg}</p>
                    <p class="meta"><time datetime="2022">23:58</time></p>
                 </div>
             </div>`;
}

// 초기화
// 기존 메세지 생성
function initChatContent(data) {
    let chat = document.querySelector("#chat-content");
    let chatOutgoingContent = document.createElement("div");
    chatOutgoingContent.className = "media media-chat media-chat-reverse";
    chatOutgoingContent.innerHTML = getSendMsgContent(data);
    chat.append(chatOutgoingContent);
}

// 메세지 전송 및 저장
// 새로운 메세지 생성
function addChatContent() {
    let inputTxt = document.querySelector('#chat-outgoing-txt');
    let user = document.getElementById('user');
    let chat = {
        sender: user.value,
        receiver: "kim",
        room: 1,
        msg: inputTxt.value,
    };
    console.log(chat);

    fetch("http://localhost:9090/chat", {
        method: "POST",
        headers: {"Content-Type": "application/json; charset=utf-8"},
        body: JSON.stringify(chat),
    });
    console.log(chat);
    inputTxt.value = "";
}

// SSE 서버 연결 및 기존 메세지 출력
const eventSource = new EventSource("http://localhost:9090/chat/room/1");
eventSource.onmessage = function (event) {
    const data = JSON.parse(event.data);
    initChatContent(data);
}

// Press Button
// 새로운 메세지 출력
document.querySelector('#chat-outgoing-btn').addEventListener("click", function () {
    addChatContent();
});

// Enter
// 새로운 메세지 출력
document.querySelector('#chat-outgoing-txt').addEventListener("keydown", function (key) {
    if (key.keyCode === 13) {
        addChatContent();
    }
});