<template>
    <div class="lef-nav">
    <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
        <el-button @click="isCollapse=!isCollapse;" >{{stext}}</el-button>
      </el-radio-group>
      <el-menu
         class="el-menu-vertical-demo" 
         @open="handleOpen" 
         @close="handleClose" 
        :collapse="isCollapse"
        :default-active="$route.name" 
        @select="addtab"
      >
        <!-- 循环数据格式 -->
        <el-submenu :index="`${index}`" v-for="(menu,index) in menuList" :key="index">
          <template slot="title">
            <i :class="menu.icont"></i>
            <span>{{menu.name}}</span>
          </template>
          <el-menu-item-group>
            <el-menu-item :index="item.routeName" v-for="item in menu.menuItem" :key="item.index" >{{item.name}}</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
      </div>
</template>
  <style scoped>
    .el-menu-vertical-demo:not(.el-menu--collapse) {
      width: 200px;
      min-height: 400px;
    }
  </style>
  
  <script>
    export default {
      data() {
        return {
          isCollapse: true,
          stext:'展开',
           // 将所需submenu,menu和tabs所需参数写成数据格式
          menuList: [
                {
                  icont: 'el-icon-s-tools',
                  name: '日志管理',
                  menuItem: [
                    {
                      title: 'trans日志',
                      routeName: 'trans',
                      name: 'translog'
                    },
                    {
                      title: 'job日志',
                      routeName: 'demo',
                      name: 'joblog'
                    }
                  ]
                },
                {
                  icont: 'el-icon-s-order',
                  name: '实体管理',
                  menuItem: [
                    {
                      title: 'trans管理',
                      routeName: 'orderList',
                      name: 'Jobs管理'
                    }
                  ]
                }
            ],
        }
      },
      methods: {
        handleOpen(key, keyPath) {
          console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
          console.log(key, keyPath);
        },
        addtab(key, keyPath){
          console.log(keyPath);
          //this.$bus.emit("navPath",keyPath)
          
          this.$router.push(key);
          //console.log(this.$router);
          //this.$router.push({path:'key'});
        }
      },
      watch:{
          isCollapse(val){
              if(val){
                  this.stext='展开';
              }
              else{
                  this.stext='关闭';
              }
          }
      },
    }
  </script>