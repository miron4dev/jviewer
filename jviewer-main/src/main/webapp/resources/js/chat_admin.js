/**
 * Client-side javascript for providing WebSocket implementation for senders.
 */

var socket;
var serviceLocation = "ws://" + window.location.host + "/main/chat/";

$(document).ready(function() {
    socket = new WebSocket(serviceLocation + $('#currentRoom').text());

    editor.on('keyup', function() {
        socket.send(editor.getValue());
    });
});

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
