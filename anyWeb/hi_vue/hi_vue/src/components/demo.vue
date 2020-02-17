<template>
  <div class="demo">
    <h1>{{ pdata }}</h1>
    <slot name="sl1"></slot>
    <h1>{{ title }}</h1>
    <button @click="changeTitle">改变标题</button>
    <div>
      <input type="text" v-model="search" />
      <div v-for="p in info">{{ p.xh }}--{{ p.name }}--{{ p.age }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    pdata: "",
    title: ""
  },
  name: "demo页面",
  data() {
    return {
      msg: "demo:903202359",
      search: "",
      info: [
        { xh: "0", name: "tom", age: "18" },
        { xh: "1", name: "jaryy", age: "22" },
        { xh: "2", name: "xiang", age: "34" },
        { xh: "3", name: "zhao", age: "12" },
        { xh: "4", name: "wang", age: "16" }
      ]
    };
  },
  computed: {
    fitleinfo() {
      let finfo = this.info.filter(p => {
        p.name.indexof(this.search) != "-1";
      });
      return finfo;
    }
  },
  methods: {
    //子组件修改父组件
    changeTitle() {
      console.log("start");
      //注册事件
      this.$emit("changetitle", "这是从demo传过来的");
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
