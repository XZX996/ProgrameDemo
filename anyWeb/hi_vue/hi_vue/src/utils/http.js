import Axios from 'axios'

//默认路径
Axios.defaults.baseURL='http://localhost:8089/Kettle';

//
 Axios.defaults.headers['Access-Control-Allow-Origin'] = '*'
 Axios.defaults.headers['Access-Control-Allow-Methods'] = 'POST'
 Axios.defaults.headers['Access-Control-Allow-Headers'] = 'x-requested-with,content-type'
// 设置请求头
Axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
 
Axios.interceptors.request.use(config => {
//   config.headers['Accept-Key'] = Store.getters.key
  return config
}, error => {
  Promise.reject(error)
})
 
// 设置拦截 出现错误时提示 错误信息
Axios.interceptors.response.use(response => response, error => {
  return Promise.reject(error)
})
 
export default Axios
