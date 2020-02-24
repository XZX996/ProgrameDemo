<template>
    <div class="loglist">
     <h2>转换日志</h2>
     <el-table
     :data="tableData"
     height="250"
     border
     style="width: 100%">
     <el-table-column
       prop="date"
       label="批次ID"
       width="180">
     </el-table-column>
     <el-table-column
       prop="name"
       label="通道ID"
       width="180">
     </el-table-column>
     <el-table-column
       prop="name"
       label="转换名称"
       width="180">
     </el-table-column>
     <el-table-column
       prop="name"
       label="状态"
       width="180">
     </el-table-column>
     <el-table-column
       prop="address"
       label="开始时间">
     </el-table-column>
     <el-table-column
       prop="address"
       label="结束时间">
     </el-table-column>
     <el-table-column
       prop="address"
       label="读">
     </el-table-column>
     <el-table-column
       prop="address"
       label="写">
     </el-table-column>
     <el-table-column
       prop="address"
       label="更新">
     </el-table-column>
     <el-table-column
       prop="address"      
       label="输入">
     </el-table-column>
     <el-table-column
       prop="address"
       label="输出">
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
  