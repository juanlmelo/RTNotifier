
var wsUri = "ws://" + document.location.host + document.location.pathname + "ws/status";

var count = 0;
var websocket;
var output = document.getElementById("output");

start();

function start(){
    websocket = new WebSocket(wsUri);
    websocket.onmessage = function(evt) { onMessage(evt) };
    websocket.onerror = function(evt) { onError(evt) };
    websocket.onclose = function(){
        setTimeout(function(){start(wsUri)}, 5000);
    };
}

function sendText(json) {
    console.log("sending text: " + json);
    websocket.send(json);
}

function sendBinary(bytes) {
    console.log("sending binary: " + Object.prototype.toString.call(bytes));
    websocket.send(bytes);
}

function onMessage(evt) {
    console.log("received: " + evt.data);
    if (typeof evt.data == "string") {
        writeToScreen(evt.data);
    } else {
        writeToScreen(evt.data);
    }
    changeValue(JSON.parse(evt.data).value);
}

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function writeToScreen(message) {

    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    if (count > 4) {
        document.getElementById("output").innerHTML = '';
        count = 0;
    }
    pre.innerHTML = message;
    document.getElementById("output").appendChild(pre);
    count++;

}