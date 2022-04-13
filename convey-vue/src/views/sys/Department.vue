<template>
  <div>
    <!--部门搜索框-->
    <el-input v-model="filterText" prefix-icon="el-icon-search" placeholder="输入部门名称进行搜索..." style="width: 500px"></el-input>
    <!--部门树-->
    <el-tree :data="deptTree" style="width: 500px" class="filter-tree" :expand-on-click-node="false"
             :props="defaultProps" :filter-node-method="filterNode" ref="tree">
      &lt;!&ndash;部门树节点&ndash;&gt;
      <span class="custom-tree-node" slot-scope="{ node, data }" style="display: flex;justify-content: space-between;width: 100%">
        <span>{{ node.label }}</span>
        <!--编辑删除按钮-->
        <span>
          <el-button class="deptButton" type="primary" size="small" icon="el-icon-plus"></el-button>
          <el-button class="deptButton" type="danger" size="small" icon="el-icon-delete"></el-button>
        </span>
      </span>
    </el-tree>
  </div>
</template>

<script>
import {getRequest} from "../../utils/api";

export default {
  name: "department",
  data(){
    return {
      filterText: '',
      deptTree: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
    }
  },
  methods: {
    /*调用部门树接口*/
    initDeptTree(){
      getRequest('/dept/root').then(resp => {
         if (resp && resp.code == 200000) {
           this.deptTree = resp.obj;
         }
      })
    },
    filterNode(value, data) {
      if (!value) return true;
      return data.name.indexOf(value) !== -1;
    }
  },
  /*加载页面时执行的方法*/
  mounted(){
    this.initDeptTree();
  },
  watch: {
    filterText(val) {
      /*当filterText发生变化时，就会调用filter这个方法去处理搜索逻辑
      * filter方法也就是tree组件里的:filter-node-method="filterNode"配置，实际上是调用filterNode方法*/
      this.$refs.tree.filter(val);
    }
  }
}
</script>

<style scoped>
.deptButton {
  padding: 2px;
}
</style>
