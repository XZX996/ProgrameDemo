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
            <el-menu-item :index="`${index}`" v-for="(item,index) in menu.menuItem" :key="index">{{item.name}}</el-menu-item>
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
                  url: 'trans',
                  name: '日志管理',
                  menuItem: [
                    {
                      url: 'trans',
                      name: 'trans日志',
                    },
                    {
                      url: 'job',
                      name: 'job日志',
                    }
                  ]
                },
                {
                  url: 'job',
                  icont: 'el-icon-s-order',
                  name: '实体管理',
                  menuItem: [
                    {
                      url: 'trans',
                      name: 'trans管理',
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
          //this.$bus.emit("navPath",{})          
          //this.$router.push(key);
          var s_inex=parseInt(keyPath[1]);
          console.log(keyPath);
          console.log(this.menuList[key]);
          console.log(this.menuList[key].menuItem[0]);
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