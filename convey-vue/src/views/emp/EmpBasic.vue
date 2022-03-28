<template>
<div>
  <div>
    <div style="display: flex;justify-content: space-between"></div>
    <!--输入框、搜索、高级搜索-->
    <div>
      <el-input placeholder="请输入员工姓名进行搜索..." prefix-icon="el-icon-search" style="width: 300px;margin-right: 10px"
                v-model="keyword" @keydown.enter.native="initEmps" :disabled="advancedisabledView"
                clearable @clear="initEmps"></el-input>
      <el-button type="primary" icon="el-icon-search">搜索</el-button>
      <el-button type="primary">
        <i :class="advanceDisabledView?'fa fa-angle-double-up':'fa fa-angle-double-down'" aria-hidden="true"></i>
        高级搜索
      </el-button>
    </div>
  </div>
</div>
</template>

<script>
  export default {
    name: "EmpBasic",
    /*是否展示高级搜索框*/
    advanceDisabledView: false
  }
</script>

<style scoped>

</style>
