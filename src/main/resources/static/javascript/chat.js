function getSendMsgContent(text, time) {
    return `<div class="media-body">
                <p>${text}</p>
                <p class="meta"><time datetime="2022">${time}</time></p>            
            </div>`;
}

// 기존 메세지 생성
function initChatContent(message, createdAt) {
    let date = new Date(createdAt);
    let time = date.getHours() + ":" + date.getMinutes();

    let chat = document.querySelector("#chat-content");
    let chatOutgoingContent = document.createElement("div");
    chatOutgoingContent.className = "media media-chat media-chat-reverse";
    chatOutgoingContent.innerHTML = getSendMsgContent(message, time);
    chat.append(chatOutgoingContent);
}

// 새로운 메세지 생성
function addChatContent() {
    let chatContent = document.querySelector('#chat-content');
    let inputTxt = document.querySelector('#chat-outgoing-txt');
    let chatOutgoingContent = document.createElement('div');
    chatOutgoingContent.className = "media media-chat media-chat-reverse";

    let date = new Date();
    let time = date.getHours() + ":" + date.getMinutes();

    // 메세지 전송 및 저장
    let chat = {
        sender: "sim",
        receiver: "kim",
        msg: inputTxt.value,
        createdAt: date
    };

    fetch("http://localhost:9090/chat", {
        method: "POST",
        headers: {"Content-Type": "application/json; charset=utf-8"},
        body: JSON.stringify(chat),
    }).then(function (response) {
        console.log(response)
    });

    chatOutgoingContent.innerHTML = getSendMsgContent(inputTxt.value, time);
    chatContent.append(chatOutgoingContent);
    inputTxt.value = "";
}
// 기존 메세지 출력
const eventSource = new EventSource("http://localhost:9090/sender/sim/receiver/kim");
eventSource.onmessage = function (event) {
    const data = JSON.parse(event.data);
    initChatContent(data.msg, data.createdAt)
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