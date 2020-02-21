// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
//组件嵌套，在这里引用相当于全局组件。
import Vue from "vue";
import App from "./App";
import router from "./router";
//引入全局组件
import demo from "./components/demo";
import moment from "moment";
//注册全局组件
Vue.component("demo", demo);
//Vue.components("users", demo);
Vue.config.productionTip = false;

//全局filter
Vue.filter("dateStr", value => {
  return moment(value).format("YYYY-MM-DD");
});
import myPlugin from "./plugin/myPlugin";
//全局调用自定义插件
Vue.use(myPlugin);
//element ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);

//bus
import VueBus from 'vue-bus';
 
Vue.use(VueBus);
/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  components: { App },
  template: "<App/>"
});
