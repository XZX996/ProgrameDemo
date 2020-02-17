<template>
  <div id="app">
    <h2>{{ title }}</h2>
    <img src="./assets/logo.png" />
    <!-- 路由展示，默认找到hello页面 -->
    <keep-alive>
      <router-view />
    </keep-alive>
    <demo @changetitle="updatetitle" :pdata="onedata" :title="title">
      <h1 slot="sl1">slot值</h1>
    </demo>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      onedata: "父级页面的数据",
      title: "title"
    };
  },
  methods: {
    updatetitle(title) {
      console.log(title);
      this.title = title;
    }
  }
};
</script>

<style></style>
