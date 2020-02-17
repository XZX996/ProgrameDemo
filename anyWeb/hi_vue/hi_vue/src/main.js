// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
//组件嵌套，在这里引用相当于全局组件。
import Vue from "vue";
import App from "./App";
import router from "./router";
//引入全局组件
import demo from "./components/demo";
//注册全局组件
Vue.component("demo", demo);
//Vue.components("users", demo);
Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  components: { App },
  template: "<App/>"
});
