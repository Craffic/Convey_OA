<template>
  <div>
    <div>
      <el-select v-model="item_name" placeholder="字典项" style="width: 180px; padding-right: 10px" size="mini">
        <el-option v-for="item in this.itemNameEnum" :key="item.value" :label="item.itemName" :value="item.itemName"></el-option>
      </el-select>
      <el-select v-model="p_name" placeholder="父节点" style="width: 180px; padding-right: 10px" size="mini" @change="pNodeChange" filterable>
        <el-option v-for="item in this.pNodes" :key="item.key" :label="item.value" :value="{key: item.key, value: item.value}"></el-option>
      </el-select>
      <el-button style="padding-left: 10px" size="mini" type="primary" icon="el-icon-search" @click="initDicts()">查询</el-button>
    </div>
    <div style="margin-top: 20px">
      <el-table border style="width: 70%" :data="dictList"
                v-loading="loading"
                element-loading-text="正在加载..."
                element-loading-spinner="el-icon-loading"
                element-loading-background="Transparent">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="itemName" label="字典项名" width="150" fixed align="left"></el-table-column>
        <el-table-column prop="pkey" label="pKey值" width="100" fixed align="left"></el-table-column>
        <el-table-column prop="pvalue" label="上级字典值" width="200" fixed align="left"></el-table-column>
        <el-table-column prop="key" label="key值" width="100" fixed align="left"></el-table-column>
        <el-table-column prop="value" label="字典值" width="200" fixed align="left"></el-table-column>
        <el-table-column prop="comments" label="备注" width="220" fixed align="left"></el-table-column>
        <el-table-column prop="idCard" label="操作" fixed width="150">
          <template slot-scope="scope">
            <el-button size="mini">编辑</el-button>
            <el-button size="mini" type="danger">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="display: flex;justify-content: flex-end; width: 70%">
        <el-pagination
          background
          @current-change="currentChange"
          @size-change="sizeChange"
          layout="sizes, prev, pager, next, jumper, ->, total, slot"
          :total="total">
        </el-pagination>
      </div>
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
      /*字典值下拉框数据*/
      itemNameEnum: [],
      /*查询字典列表*/
      dictList: [],
      /*父节点下拉框数据*/
      pNodes: [],
      /*父节点下拉框显示值*/
      p_name: '',
      /*父节点的key绑定值*/
      p_key: '',
      /*搜索下拉框绑定值*/
      item_name: '',
      /*分页参数*/
      total: 0,
      page:1,
      size: 10
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
    /* 当前页 */
    currentChange(currentPage) {
      this.page = currentPage;
      this.initDicts();
    },
    /* 改变每页大小 */
    sizeChange(currentSize){
      this.size = currentSize;
      this.initDicts();
    },
    initDicts(){
      this.loading = true;
      let url = '/dict/query/?page=' + this.page + '&size=' + this.size;
      if (this.item_name) {
        url += '&itemName=' + this.item_name;
      }
      if (this.p_key) {
        url += '&pKey=' + this.p_key;
      }
      getRequest(url).then(resp => {
        this.loading = false;
        if (resp && resp.code == 200000) {
          this.dictList = resp.obj.list;
          this.total = resp.obj.totalNum;
        }
      })
    },
    /*父节点改变*/
    pNodeChange(pNode){
      this.p_key = pNode.key;
    }
  },
  /*加载页面*/
  mounted() {
    this.initSelectionData();
    this.initDicts();
  },
  watch: {
    /*如果字典值发生变化，就查询该字典值下的所有父节点*/
    item_name(){
      this.p_name = '',
      this.p_key = '',
      this.pNodes = [],
      // 再次调用一下接口
      getRequest('/dict/pNode?item_name=' + this.item_name).then(resp => {
        if (resp) {
          this.pNodes = resp.obj;
        }
      }),
      /*切换字典值查询，查询参数要恢复默认*/
      this.size = 10;
      this.page = 1;
    }
  }
}
</script>

<style scoped>

</style>
