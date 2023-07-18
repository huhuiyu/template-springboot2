import { ajax } from './ajax.js';

ajax.setBaseUrl('http://127.0.0.1:10100');

//#region 查询

let txtDeptName = document.getElementById('txtDeptName');
let btnQuery = document.getElementById('btnQuery');
let divDeptList = document.getElementById('divDeptList');

btnQuery.addEventListener('click', () => {
  ajax.get(
    '/manage/dept/queryAll',
    {
      deptName: txtDeptName.value,
    },
    (data) => {
      if (!data.success) {
        alert(data.message);
        return;
      }
      divDeptList.innerHTML = '';
      let list = data.data;
      list.forEach((info) => {
        let div = document.createElement('div');
        div.append(JSON.stringify(info));
        divDeptList.append(div);
        let btnDel = document.createElement('button');
        btnDel.append('删除');
        btnDel.addEventListener('click', () => {
          del(info);
        });
        div.append(btnDel);
      });
    }
  );
});

//#region

//#region 删除

function del(info) {
  if (confirm(`是否删除：${info.deptName}?`)) {
    ajax.delete(`/manage/dept/delete/${info.deptId}`, {}, (data) => {
      alert(data.message);
      btnQuery.click();
    });
  }
}
//#endregion
