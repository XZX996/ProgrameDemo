<template>
  <div class="demo">
    <h1 style="left: auto;" >{{ pdata }}| {{ nowday | dateStr1 }}</h1>
  
    <slot name="sl1"></slot>
    <h1>{{ title }}</h1>
    <transition name="fade">
      <button @click="changeTitle">改变标题</button>
    </transition>
    <div>
      <p v-lower-text="msg"></p>
      <input type="text" v-model="search" />

      <div v-for="p in fitleinfo" :key="p.xh">
        {{ p.xh }}--{{ p.name }}--{{ p.age }}
      </div>
      
    </div>
    <button @click="changeOrderby(1, $event)">升序排序</button>
    <button @click="changeOrderby(2, $event)">降序排序</button>
    <keep-alive>
      <router-view/>
    </keep-alive>
  </div>
</template>

<script>
import moment from "moment";
export default {
  props: {
    pdata: "",
    title: ""
  },
  name: "demo页面",
  data() {
    return {
      nowday: new Date(),
      orderTy: 0,
      msg: "DEMO:903202359",
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
      let finfo = this.info.filter(p => p.name.indexOf(this.search) !== -1);
      if (this.orderTy != 0) {
        finfo.sort(function(a, b) {
          // if (this.orderTy == 1) return a.xh - b.xh;
          // else if (this.orderTy == 2) {
          //   return b.xh - a.xh;
          // }
          return a.xh - b.xh;
        });
      }
      return finfo;
    }
  },

  methods: {
    //子组件修改父组件
    changeTitle() {
      console.log(this.$myMethod());
      console.log("start");
      //注册事件
      this.$emit("changetitle", "这是从demo传过来的");
    },
    changeOrderby(val, event) {
      this.orderTy = val;
    }
  },
  //局部filter
  filters: {
    dateStr1(val) {
      return moment(val).format("YYYY-MM-DD hh:mm:ss");
    }
  },
  directives: {
    "lower-text": (el, bind) => {
      el.textContent = bind.value.toLowerCase(); //toUpperCase()
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
