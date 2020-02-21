<template>
  <div class="hello">
    <div class="left">
      <h3>用户名</h3>
      <input type="text" v-model="name" >
      <h3>评论内容</h3>
      <textarea v-model="info" ></textarea>
      <el-button type="primary" @click="add">提交</el-button>
      <el-button  type="warning" @click="del">删除最新一条</el-button>
    </div>
    <div class="right">
      <div v-for="(p,index) in infoarry" :key="index">        
        <h3>{{p.name}}用户:</h3>
        <p>{{p.info}}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  data() {
    return {
      name: "903202359",
      info:'',
      infoarry:[],
    };
  },
  methods: {
    add(){
      this.infoarry.unshift({'name':this.name,'info':this.info});
    },
    del(){
      this.infoarry.shift();
    }
  },
  
  
  
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .left{
    height: 200px;
    float: left;
    width: 30%;
    background-color:transparent;
  }
  .right{
    height: 400px;
    float: left;
    width: 70%;
    overflow: auto;
  }
  .h3{
    margin-bottom: 0px;
    padding: 0px;
  }
</style>
