const MyPlugin = {};
MyPlugin.install = function(Vue, options) {
  // 1. 添加全局方法或属性
  Vue.myGlobalMethod = function() {
    console.log("myGlobalMethod构造函数");
  };
  Vue.prototype.$Myoption = "插件里的属性";
  // 2. 添加全局资源
  Vue.directive("my-directive", {
    bind(el, binding) {
      el.textContent = bind.value.toLowerCase(); //toUpperCase()
    }
  });

  // 3. 注入组件选项
  Vue.mixin({
    created: function() {
      // 逻辑...
    }
  });

  // 4. 添加实例方法
  Vue.prototype.$myMethod = function(methodOptions) {
    console.log("myMethod实例方法");
  };
};
export default MyPlugin;
