
document.querySelector('#chat-outgoing-btn').addEventListener("click", function () {
    let chat = document.querySelector('#chat-content');
    let chatOutgoingContent = document.createElement('div');
    chatOutgoingContent.innerHTML = "안녕 !!!!";
    chat.append(chatOutgoingContent);
});

document.querySelector('#chat-outgoing-txt').addEventListener("keydown", function (key) {
    if (key.keyCode === 13) {
        // Enter
        console.log("send the message");
    }
});