import { AutoWebSocket } from './WebSocket.js';

//#region echo服务

const wsecho = new AutoWebSocket('ws://127.0.0.1:10100/ws/echo');

wsecho.addListener('open', (event) => {
  console.log('连接到echo服务', event);
  changeStatus();
});
wsecho.addListener('close', (event) => {
  console.log('echo服务已经断开', event);
  changeStatus();
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

function changeStatus() {
  if (wsecho.isConnect) {
    btnOpenEcho.setAttribute('disabled', true);
    btnEcho.removeAttribute('disabled');
    btnCloseEcho.removeAttribute('disabled');
  } else {
    btnOpenEcho.removeAttribute('disabled');
    btnEcho.setAttribute('disabled', true);
    btnCloseEcho.setAttribute('disabled', true);
  }
}

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

changeStatus();

//#endregion

//#region 简易聊天室

const wschat = new AutoWebSocket('ws://127.0.0.1:10100/ws/chat');

wschat.addListener('open', (event) => {
  console.log('连接到wschat服务', event);
  wschat.send({
    username: user,
    message: '进入聊天室',
  });
  changeChatStatus();
});
wschat.addListener('close', (event) => {
  console.log('wschat服务已经断开', event);
  changeChatStatus();
});

wschat.addListener('message', (data) => {
  console.log('chat消息', data);
  if (data.success) {
    let chatinfo = data.message;
    preChat.append(`${chatinfo.username}：${chatinfo.message}\r\n`);
  }
});

function changeChatStatus() {
  if (wschat.isConnect) {
    btnChat.removeAttribute('disabled');
  } else {
    btnChat.setAttribute('disabled', true);
  }
}

let divUser = document.getElementById('divUser');
let txtUser = document.getElementById('txtUser');
let btnUser = document.getElementById('btnUser');
let divChat = document.getElementById('divChat');

let preChat = document.getElementById('preChat');
let txtChat = document.getElementById('txtChat');
let btnChat = document.getElementById('btnChat');

let user = '';

function checkUser() {
  user = localStorage.getItem('chat_user');
  if (user) {
    divUser.style.display = 'none';
    divChat.style.display = 'block';
  } else {
    user = '';
    divUser.style.display = 'block';
    divChat.style.display = 'none';
  }
  // 用户名存在且没有连接服务器就启动连接
  if (user && !wschat.isConnect) {
    wschat.connect();
  }
}

btnUser.addEventListener('click', () => {
  user = txtUser.value.trim();
  if (user) {
    localStorage.setItem('chat_user', user);
  }
  checkUser();
});

btnChat.addEventListener('click', () => {
  if (!wschat.isConnect || txtChat.value.trim() == '') {
    return;
  }
  wschat.send({
    username: user,
    message: txtChat.value.trim(),
  });
  txtChat.value = '';
});

checkUser();

//#endregion
