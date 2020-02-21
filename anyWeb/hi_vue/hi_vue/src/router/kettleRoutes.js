//kettle相关路由
export default [
    {
        path: "trans",
        name: "trans",
        component: resolve => require(['../components/logs/translog/loglist.vue'], resolve),
    },
    {
        path: "demo",
        name: "demo",
        component: resolve => require(['../components/demo.vue'], resolve),
    },

]