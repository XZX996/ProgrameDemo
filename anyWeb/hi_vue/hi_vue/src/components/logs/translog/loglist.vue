<template>
    <div class="loglist">
     <h2>loglist </h2>
     <el-table
     :data="tableData"
     height="250"
     border
     style="width: 100%">
     <el-table-column
       prop="date"
       label="日期"
       width="180">
     </el-table-column>
     <el-table-column
       prop="name"
       label="姓名"
       width="180">
     </el-table-column>
     <el-table-column
       prop="address"
       label="地址">
     </el-table-column>
   </el-table>
    </div>
  </template>
  
  <script>
  import moment from "moment";
  export default {
    props: {
      pdata: "",
      title: ""
    },
    name: "log页面",
    data() {
      return {
        nowday: new Date(),
        orderTy: 0,
        msg: "DEMO:903202359",
        search: ""
      };
    },
    computed: {
     
    },
  
    methods: {
     
    },
    //局部filter
    filters: {
     
    },
    directives: {
     
    }
  };
  </script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped></style>
  