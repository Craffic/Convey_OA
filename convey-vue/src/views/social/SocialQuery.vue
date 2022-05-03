<template>
  <div>
    <div>
      <el-row style="margin-top: 10px">
        <el-col :span="3">姓名:
          <el-input v-model="personForm.name" size="mini" style="width: 120px"></el-input>
        </el-col>
        <el-col :span="3">性别:
          <el-radio-group v-model="personForm.gender">
            <el-radio label="M">男</el-radio>
            <el-radio label="F">女</el-radio>
          </el-radio-group>
        </el-col>
        <el-col :span="6">出生日期:
          <el-date-picker style="width: 300px" type="daterange" size="mini" unlink-panels value-format="yyyy-MM-dd"
                          range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期">
          </el-date-picker>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" size="mini" icon="el-icon-search" @click="initPersons">搜索</el-button>
        </el-col>
      </el-row>
    </div>
    <div style="margin-top: 20px">
      <el-table :data="persons" border style="width: 100%"
                v-loading="loading"
                element-loading-text="正在加载..."
                element-loading-spinner="el-icon-loading"
                element-loading-background="Transparent"
                @cell-dblclick="tableDbEdit">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="idcardNo" label="身份证号" width="200" fixed align="left"></el-table-column>
        <el-table-column prop="name" label="姓名" width="120" fixed align="left"></el-table-column>
        <el-table-column prop="gender" label="性别" width="120" fixed align="left"></el-table-column>
        <el-table-column prop="birthDate" label="出生年月" width="120" fixed align="left"></el-table-column>
        <el-table-column prop="nativePlaceDesc" label="籍贯" width="120" fixed align="left"></el-table-column>
        <el-table-column prop="homeAddress" label="家庭地址" width="300" fixed align="left"></el-table-column>
        <el-table-column prop="workAddress" label="工作地址" width="300" fixed align="left"></el-table-column>
        <el-table-column prop="professionDesc" label="专业" width="120" fixed align="left"></el-table-column>
        <el-table-column prop="granduteSchoolDesc" label="毕业学校" width="120" fixed align="left"></el-table-column>
      </el-table>
      <div style="display: flex;justify-content: flex-end">
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
import {postRequest} from "../../utils/api";

export default {
  name: "SocialQuery",
  data() {
    return {
      personForm: {
        name: '',
        idcardNo: '',
        gender: '',
        birthDate: '',
        nativePlaceCode: '',
        nativePlaceDesc: '',
        homeAddress: '',
        workAddress: '',
        professionCode: '',
        professionDesc: '',
        granduteSchoolCode: '',
        granduteSchoolDesc: ''
      },
      persons: [],
      /*分页参数*/
      total: 0,
      page:1,
      size: 10,
      loading: false
    }
  },
  methods: {
    /*查询*/
    initPersons() {
      this.loading = true;
      let url = '/get/person';
      postRequest(url, this.personForm).then(resp => {
        console.log(resp.obj.list);
        if (resp && resp.code == 200000) {
          this.loading = false;
          this.persons = resp.obj.list;
          this.total = resp.obj.totalNum;
        }
      })
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
    },
    /*双击行记录，打开编辑弹框*/
    tableDbEdit(row, column, cell, event) {
      alert("double click")
    }
  },
  mounted() {
    this.initPersons();
  }
}
</script>

<style scoped>

</style>
