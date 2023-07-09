import { ajax } from './ajax.js';

ajax.setBaseUrl('http://127.0.0.1:10101');

//#region 登录功能测试

let txtUser = document.getElementById('txtUser');
let txtPassword = document.getElementById('txtPassword');
let btnTest = document.getElementById('btnTest');
let btnUserInfo = document.getElementById('btnUserInfo');
let preResult = document.getElementById('preResult');

function saveToken(data) {
  if (data && data.token) {
    localStorage.setItem('security_token', data.token);
  }
}

function loadToken(data) {
  let token = localStorage.getItem('security_token');
  return token ? token : '';
}

btnTest.addEventListener('click', () => {
  ajax.post(
    '/auth/login?security_token=' + loadToken(),
    {
      username: txtUser.value,
      password: txtPassword.value,
    },
    (data) => {
      saveToken(data);
      preResult.innerHTML = JSON.stringify(data);
    }
  );
});

btnUserInfo.addEventListener('click', () => {
  ajax.get(
    '/auth/userinfo',
    {
      security_token: loadToken(),
    },
    (data) => {
      preResult.innerHTML = JSON.stringify(data);
    }
  );
});

//#region
