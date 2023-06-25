import { ajax } from './ajax.js';

ajax.setBaseUrl('http://127.0.0.1:10100');

//#region echo功能测试

let preResult = document.getElementById('preResult');
let txtEcho = document.getElementById('txtEcho');
let btnEcho = document.getElementById('btnEcho');

btnEcho.addEventListener('click', () => {
  preResult.innerHTML = '';
  ajax.get('', { echo: txtEcho.value }, (data) => {
    preResult.append(JSON.stringify(data, null, 2));
  });

  ajax.get('/demo.action', { echo: txtEcho.value }, (data) => {
    preResult.append(JSON.stringify(data, null, 2));
  });
});

//#region
