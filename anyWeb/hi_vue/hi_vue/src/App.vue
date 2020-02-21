<template>
  <div id="app" >  
    <div class="top" style="margin: 1px;padding: 0px;width: 100%;background-color: coral; line-height: 50px;">
      <h2>{{ title }}</h2>
    </div>
    <div class="left" style="float: left;width:auto;margin-right: 2px;">
      <v-nav></v-nav>
    </div>
    <div class="right" style="float: left;width:auto;margin-left: 2px;">
    <v-tab></v-tab>    
     <router-view></router-view>
  </div>
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
      title: "日志管理"
    };
  },
  methods: {
    updatetitle(title) {
      this.title = title;
    }
  }
};
</script>

<style></style>
