import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: resolve => require(['../components/HelloWorld.vue'], resolve)
    },
    {
      path: '/login',
      name: 'Login',
      component: resolve => require(['../components/common/login.vue'], resolve)
    },
    {
      path: '/home',
      component: resolve => require(['../components/common/home.vue'], resolve)
    }
  ]
})
