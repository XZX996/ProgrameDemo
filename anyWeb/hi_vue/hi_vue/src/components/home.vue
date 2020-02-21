<template>
    <div class="Home">
        <transition name="move" mode="out-in">
          <keep-alive>
              <router-view></router-view>
          </keep-alive>
        </transition>
    </div>
       
    </div>
  </template>
  
  <script>
import nav from "./left_nav";
import tab from "./tabs";

export default {
  name: "Home",
  components:{"v-nav": nav,"v-tab":tab},
    data() {
      return {
      };
    },
    computed: {
    },
  
    methods: {
    //   子组件修改父组件
    //   changeTitle() {
    //     console.log(this.$myMethod());
    //     console.log("start");
    //     注册事件
    //     this.$emit("changetitle", "这是从demo传过来的");
    //   },
    //   changeOrderby(val, event) {
    //     this.orderTy = val;
    //   }
    },

  };
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped></style>
  