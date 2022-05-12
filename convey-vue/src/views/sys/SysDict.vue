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
      <el-button style="padding-left: 10px" size="mini" type="success" icon="el-icon-circle-plus-outline" @click="showAddDictView()">添加</el-button>
    </div>
    <div style="margin-top: 20px">
      <el-table border style="width: 51%" :data="dictList"
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
        <el-table-column prop="idCard" label="操作" fixed>
          <template slot-scope="scope">
            <el-button size="mini">编辑</el-button>
            <el-button size="mini" type="danger">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="display: flex;justify-content: flex-end; width: 50%">
        <el-pagination
          background
          @current-change="currentChange"
          @size-change="sizeChange"
          layout="sizes, prev, pager, next, jumper, ->, total, slot"
          :total="total">
        </el-pagination>
      </div>
    </div>

    <!--新增字典值对话框-->
    <el-dialog :visible.sync="showAddDictFlag" title="新增字典值" width="60%">
      <div>
        <el-form ref="dictForm">
          <el-row>
            <el-col :span="6">
              <el-form-item label="字典项：" prop="addItemName">
                <el-select filterable v-model="addItemName" placeholder="字典项" style="width: 180px; padding-right: 10px" size="mini">
                  <el-option v-for="item in this.itemNameEnum" :key="item.value" :label="item.itemName" :value="item.itemName"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="pKey值：" prop="addPKey">
                <el-input placeholder="pKey值" v-model="addPKey" prefix-icon="el-icon-edit" style="width: 120px" size="mini"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="上级字典值：" prop="addPValue">
                <el-input placeholder="上级字典值" v-model="addPValue" prefix-icon="el-icon-edit" style="width: 130px" size="mini"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
                <el-form-item label="key值：" prop="addKey">
                  <el-input placeholder="key值" v-model="addKey" prefix-icon="el-icon-edit" style="width: 180px" size="mini"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="5">
            </el-col>
            <el-col :span="6">
              <el-form-item label="字典值：" prop="addValue">
                <el-input placeholder="字典值" v-model="addValue" prefix-icon="el-icon-edit" style="width: 130px" size="mini"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="备注：" prop="addComments">
                <el-input placeholder="备注" v-model="addComments" prefix-icon="el-icon-edit" style="width: 130px" size="mini"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="showAddDictFlag = false">取 消</el-button>
            <el-button type="primary" @click="addOrUpdateDict()">确 定</el-button>
      </span>
    </el-dialog>
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
      size: 10,
      /*新增字典值*/
      showAddDictFlag: false,
      /*新增表单字典*/
      addItemName: '',
      addPKey: '',
      addPValue: '',
      addKey: '',
      addValue: '',
      addComments: ''
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
      this.page = 1;
      this.size = 10;
    },
    /*父节点改变*/
    pNodeChange(pNode){
      this.p_key = pNode.key;
    },
    /*打开新增字典表对话框*/
    showAddDictView(){
      this.showAddDictFlag = true,
      this.addItemName = this.item_name;
      this.addPKey = this.p_key;
      this.addPValue = this.p_name.value;
    },
    addOrUpdateDict(){
      if (this.addKey && this.addValue) {
        // 字典键值对不为空就是修改
      } else {
        // 为空则是新增

      }
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
