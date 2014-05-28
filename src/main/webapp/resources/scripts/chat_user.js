/**
 * Client-side javascript for providing WebSocket implementation for recipients.
 */

var socket = new WebSocket("ws://jviewer:8080/chat");

socket.onmessage = function (event) {
    refreshForm();
};
