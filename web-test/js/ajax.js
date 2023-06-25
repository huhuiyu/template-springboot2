import axios from './axios.min.js';
import qs from './qs.js';

let baseUrl = '';

let ajax = {
  setBaseUrl: (url) => {
    baseUrl = url;
  },
  post: (url, params, callback) => {
    ajax.send(url, params, callback, 'POST');
  },
  get: (url, params, callback) => {
    ajax.send(url, params, callback, 'GET');
  },
  delete: (url, callback) => {
    ajax.send(url, {}, callback, 'DELETE');
  },
  // 1：请求的地址，2：请求的参数（json格式）
  // 3：应答结果的处理（回调）函数，4：请求方式，可选,默认为POST
  send: (url, params, callback, method = 'POST') => {
    // 参数和地址处理
    let rdata = qs.stringify(params, { allowDots: true });
    // get请求是处理url
    let rurl = baseUrl + url;
    if ('get' == method.toLowerCase()) {
      rurl = rurl + '?' + rdata;
      rdata = '';
    }
    // axios的ajax请求
    let promise = axios({
      url: rurl,
      data: rdata,
      method: method,
      headers: {
        ts: new Date().getTime(),
      },
    });
    // 请求的结果处理
    promise
      .then((resp) => {
        console.log('应答结果', resp);
        // 正常请求结果
        callback(resp.data);
      })
      .catch((err) => {
        console.error('请求错误', err);
        callback({ success: false, message: '请求异常' });
      });
  },
  sendJson: (url, params, callback, method = 'POST') => {
    // 参数和地址处理
    let rdata = JSON.stringify(params);
    // get请求是处理url
    let rurl = baseUrl + url;
    if ('get' == method.toLowerCase()) {
      rurl = rurl + '?' + rdata;
      rdata = '';
    }
    // axios的ajax请求
    let promise = axios({
      url: rurl,
      data: params,
      method: method,
      headers: {
        ts: new Date().getTime(),
      },
    });
    // 请求的结果处理
    promise
      .then((resp) => {
        console.log('应答结果', resp);
        // 正常请求结果
        callback(resp.data);
      })
      .catch((err) => {
        console.error('请求错误', err);
        callback({ success: false, message: '请求异常' });
      });
  },
  postJson: (url, params, callback) => {
    ajax.sendJson(url, params, callback, 'POST');
  },
  getJson: (url, params, callback) => {
    ajax.sendJson(url, params, callback, 'GET');
  },
};

export default ajax;

export { ajax as ajax };
