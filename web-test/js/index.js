import { ajax } from './ajax.js';
console.log('in index.js...');

let divData = document.getElementById('divData');

ajax.send('/json.action', {}, (data) => {
  divData.innerHTML = JSON.stringify(data, null, 2);
});
