import { AutoWebSocket } from './WebSocket.js';

//#region echo服务

const wsecho = new AutoWebSocket('ws://127.0.0.1:10100/ws/echo');

wsecho.addListener('open', (event) => {
  console.log('连接到echo服务', event);
});
wsecho.addListener('close', (event) => {
  console.log('echo服务已经断开', event);
});
wsecho.addListener('message', (data) => {
  console.log('echo消息', data);
  preEcho.innerHTML = JSON.stringify(data);
});

let btnOpenEcho = document.getElementById('btnOpenEcho');
let btnCloseEcho = document.getElementById('btnCloseEcho');
let preEcho = document.getElementById('preEcho');
let txtEcho = document.getElementById('txtEcho');
let btnEcho = document.getElementById('btnEcho');

btnOpenEcho.addEventListener('click', () => {
  if (wsecho.isConnect) {
    return;
  }
  wsecho.connect();
});

btnCloseEcho.addEventListener('click', () => {
  wsecho.disconnet();
});

btnEcho.addEventListener('click', () => {
  wsecho.send(txtEcho.value);
});

//#endregion
