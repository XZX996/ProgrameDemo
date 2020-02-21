import Vue from "vue";
import Router from "vue-router";
import home from "@/components/home";
import HelloWorld from "@/components/HelloWorld";

import kettleRoutes from "./kettleRoutes"

Vue.use(Router);

let routes=[
  {
    path: "/hi",
    name: "hi",
    component: HelloWorld
  },
  {
    path: '/',
    component:home,
    //component: resolve => require(['../components/common/Home.vue'], resolve),
    //meta: {title: '自述文件'},
    children: [...kettleRoutes]
  },
  {
    path: '*',
    redirect: '/404'
  }
];

let router=new Router({routes});
export default router
