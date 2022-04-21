<template>
  <div>
    <div>
      <el-select v-model="item_name" placeholder="字典项" style="width: 180px; padding-right: 10px" size="mini">
        <el-option v-for="item in this.itemNameEnum" :key="item.value" :label="item.itemName" :value="item.itemName"></el-option>
      </el-select>
      <el-button style="padding-left: 10px" size="mini" type="primary" icon="el-icon-search">查询</el-button>
    </div>
  </div>
</template>

<script>
import {getRequest} from "../../utils/api";

export default {
  name: "Dict",
  data(){
    return{
      loading: false,
      itemNameEnum: [],
      item_name: ''
    }
  },
  methods: {
    /*初始化下拉框数据*/
    initSelectionData() {
      /*初始化字典项列表*/
      if (!window.sessionStorage.getItem("itemNameList")) {
        getRequest('/dict/item_name/list').then(resp => {
          if (resp) {
            this.itemNameEnum = resp.obj;
            window.sessionStorage.setItem("itemNameList", JSON.stringify(resp.obj));
          }
        })
      } else {
        this.itemNameEnum = JSON.parse(window.sessionStorage.getItem("itemNameList"));
      }
    },
    /*当前页*/
    currentChange(currentPage) {
      this.page = currentPage;
      this.initUsers();
    },
    /*改变每页大小*/
    sizeChange(currentSize){
      this.size = currentSize;
      this.initUsers();
    }
  },
  /*加载页面*/
  mounted() {
    this.initSelectionData();
  }
}
</script>

<style scoped>

</style>
