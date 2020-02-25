<template>    
    <div class="loglist">
      <el-main>
     <h2>JOB日志</h2>
     <div ref="element" class="align">
      <el-form :inline="true"  labelPosition="right" labelWidth="100px"  >
          <!-- <el-form-item label="机构名称：">
              <el-input v-model="params.orgnm" placeholder="请输入机构名称" size="small" clearable></el-input>
          </el-form-item> -->
          <!-- <el-form-item label="单位类型：">
          <el-select v-model="params.orgtype" clearable placeholder="请选择">
              <el-option
                  v-for="type in types"
                  :key="type.codeKey"
                  :label="type.codeValue"
                  :value="type.codeKey">
              </el-option>
          </el-select>
          </el-form-item>

          <el-form-item label="身份证号码：" labelWidth="110px" >
              <el-input v-model="params.IdCard" placeholder="请输入身份证号码" size="small" clearable></el-input>
          </el-form-item> -->

          <!--<el-form-item label="备案时间：">
              <el-date-picker
                  v-model="params.apptime"
                  type="datetimerange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  value-format="yyyy-MM-dd HH:mm:ss">
              </el-date-picker>
          </el-form-item>-->
          <el-row>
          <!-- <el-form-item label="备案时间：">
              <el-date-picker
                  size="small"
                  v-model="params.starTime"
                  type="date"
                  placeholder="选择日期时间"
                  align="right"
                  :picker-options="pickerOptions">
              </el-date-picker>
          </el-form-item>
          <el-form-item label="至" labelWidth="20px">
              <el-date-picker
                  v-model="params.endTime"
                  type="date"
                  placeholder="选择日期时间"
                  align="right"
                  :picker-options="pickerOptions">
              </el-date-picker>
          </el-form-item> -->

          <el-form-item>
            <el-button type="primary" @click="getList">提交</el-button>          
          </el-form-item>
          </el-row>
      </el-form>
     </div>
     <el-row>         
      <el-table :data="tableData" height="550" >
        <el-table-column prop="idBatch" label="批次ID" width="180"></el-table-column>
        <el-table-column prop="channelId" label="通道ID" width="180"></el-table-column>
        <el-table-column prop="transname" label="转换名称" width="180"></el-table-column>
        <el-table-column prop="status" label="状态" width="180"></el-table-column>
        <el-table-column prop="startdate" label="开始时间"> </el-table-column>
        <el-table-column  prop="enddate" label="结束时间"></el-table-column>
        <el-table-column  prop="linesRead" label="读"></el-table-column>
        <el-table-column prop="linesWritten" label="写"> </el-table-column>
        <el-table-column prop="linesUpdated" label="更新"></el-table-column>
        <el-table-column prop="linesInput" label="输入"></el-table-column>
        <el-table-column  prop="linesOutput" label="输出"></el-table-column>
      </el-table>
    </el-row>
    <el-row>
      <el-pagination :current-page.sync:="params.pageNum" background @current-change="handlePageChange" :page-size="params.pageSize" :total="total" ></el-pagination>
    </el-row>
  </el-main>
    </div>
</template>
  
  <script>
  //import moment from "moment";
  export default {
    // props: {
    //   pdata: "",
    //   title: ""
    // },
    name: "log页面",
    data() {
      return {
        params:{
          pageNum:1,
          pageSize:10,
        },
        tableData:[],
        total:0,
      };
    },
    computed: {
     
    },  
    methods: {
      handlePageChange(){

      },
      getList(){
        this.$http.get('/log/getList').then((res) => {
            this.tableData=res.data.data.records;
            this.total=res.data.data.total;
            console.log(res.data.data);
          }).catch((error) => {
            console.log(error)
          });
      },
    },
    //局部filter
    filters: {
     
    },
    directives: {
     
    }
  }
  </script>
  <!-- Add "scoped" attribute to limit CSS to this component only -->
  <style scoped></style>
  