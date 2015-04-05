/**
 * Client-side javascript for providing WebSocket implementation for recipients.
 */

var socket;
var serviceLocation = "ws://jviewer.tk:8080/jviewer/chat/";

$(document).ready(function() {
    socket = new WebSocket(serviceLocation + $('#currentRoom').text());

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
});

function leaveRoom() {
    socket.close();
}
