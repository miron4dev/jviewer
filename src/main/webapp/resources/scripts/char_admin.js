/**
 * Client-side javascript for providing WebSocket implementation for senders.
 */

var socket = new WebSocket("ws://jviewer:8080/chat");

$(document).ready(function() {
   $('#displayForm\\:input').on('keyup', function() {
       socket.send('notification');
   });
});