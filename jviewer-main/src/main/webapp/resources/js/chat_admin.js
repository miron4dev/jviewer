/**
 * Client-side javascript for providing WebSocket implementation for senders.
 */

var socket;
var editor;

$(document).ready(function() {
    socket = new WebSocket("ws://" + window.location.host + "/main/chat/" + $('#currentRoom').text())
});

function initViewer() {
    editor = PF('codeMirror').instance;
}

function sendCode() {
    socket.send(editor.getValue());
}

function executeCode() {
    socket.send('/execute');
    $('#result').attr('src', 'data:text/html;charset=utf-8,' + escape(editor.getValue()));
}

function clearIO() {
    socket.send('/clear');
    editor.setValue('');
    $('#result').attr('src', '');
}

function leaveRoom() {
    socket.close();
}
