<template>
  <div id="app" >  
    <div class="top" style="margin: 1px;padding: 0px;width: 100%;background-color: coral; line-height: 50px;">
      <h2>{{ title }}</h2>
    </div> 
    <!-- <div class="container">  -->
      <div class="left">
        <v-nav></v-nav>
      </div>
      <div class="right" >
        <v-tab></v-tab>    
        <router-view></router-view>
      </div>
    <!-- </div> -->
  </div>
</template>

<script>
import nav from "./components/left_nav";
import tab from "./components/tabs";

export default {
  name: "App",
 
  components:{"v-nav": nav,"v-tab":tab},
  data() {
    return {
      onedata: "父级页面的数据",
      title: "日志管理",
    };
  },
  methods: {
    updatetitle(title) {
      this.title = title;
    }
  },
  mounted:function() {
        // this.$bus.on('navClass',(val) =>{
        //   处理传过来的值 
        //   this.r_class=val;   
        // });
  },
};
</script>

<style scoped>
* { margin: 0;padding: 0 }
 
 #app{
     width: 100%;
     height: 100%; 
 }
 /* .container {    
     width: 100%;
     height: 100%; 
     display:flex;/*设为伸缩容器 
 } */

.left{
  float: left;
  width:auto;
  margin-right: 10px;
}
.right{ 
  overflow-y: hidden;
    /* flex:1;/*这里设置为占比1，填充满剩余空间  */
}
</style>
