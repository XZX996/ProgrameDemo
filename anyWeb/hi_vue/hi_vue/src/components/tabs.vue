<template>
    <div>
  <div style="margin-bottom: 20px;">
    <el-button
      size="small"
      @click="addTab(editableTabsValue)"
    >
      add tab
    </el-button>
  </div>
  <el-tabs v-model="editableTabsValue" type="card" closable @tab-remove="removeTab">
    <el-tab-pane
      v-for="(item, index) in editableTabs"
      :key="item.name"
      :label="item.title"
      :name="item.name"
    >
    </el-tab-pane>
  </el-tabs>
</div>
</template>

<script>
export default {
    data() {
        return {
        editableTabsValue: '2',
        editableTabs: [{
            title: 'Tab 1',
            name: '1',
        }],
        tabIndex: 1,
        navPath:'',
        }
    },
    methods: {
        addTab(targetName) {
        let newTabName = ++this.tabIndex + '';
        this.editableTabs.push({
            title: targetName,
            name: newTabName,
        });
        this.editableTabsValue = newTabName;
        },
        removeTab(targetName) {
        let tabs = this.editableTabs;
        let activeName = this.editableTabsValue;
        if (activeName === targetName) {
            tabs.forEach((tab, index) => {
            if (tab.name === targetName) {
                let nextTab = tabs[index + 1] || tabs[index - 1];
                if (nextTab) {
                activeName = nextTab.name;
                }
            }
            });
        }
        
        this.editableTabsValue = activeName;
        this.editableTabs = tabs.filter(tab => tab.name !== targetName);
        },
        tabclick(val) {
              console.log(val)
              this.$router.push(val.name);
              //点击tabs触发路由跳转，到相应路由
        }
    },
    mounted:function() {
        this.$bus.on('navPath',(name,val) =>{//处理传过来的值
        console.log(name[1]);
        this.addTab(name[1]);
       // var titname=name;  
    });
   },
}
</script>