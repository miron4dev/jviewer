/**
 * Client-side javascript for providing WebSocket implementation for recipients.
 */
var editor;
var socket;

$(document).ready(function() {
    socket = new WebSocket("ws://" + window.location.host + "/main/chat/" + $('#currentRoom').text())
});

function initViewer() {
    editor = PF('viewerContent').instance;

    socket.onmessage = function (event) {
        var msg = event.data;
        if (msg === '/execute') {
            $('#result').attr('src', 'data:text/html;charset=utf-8,' + escape(editor.getValue()));
        }
        else if (msg === '/clear') {
            editor.setValue('');
            $('#result').attr('src', '');
        }
        else {
            editor.setValue(msg);
        }
    };
}

function sendContent() {
    //do nothing
}

function leaveRoom() {
    socket.close();
}
