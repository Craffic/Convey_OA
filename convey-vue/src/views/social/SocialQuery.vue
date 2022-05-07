<template>
  <div>
    <div>
      <el-row style="margin-top: 10px">
        <el-col :span="4">姓名:
          <el-input v-model="personForm.name" size="mini" style="width: 225px"></el-input>
        </el-col>
        <el-col :span="4">性别:
          <el-radio-group v-model="personForm.gender">
            <el-radio label="M">男</el-radio>
            <el-radio label="F">女</el-radio>
          </el-radio-group>
        </el-col>
        <el-col :span="7">出生日期:
          <el-date-picker v-model="personForm.birthDateRange" style="width: 342px" type="daterange" size="mini" unlink-panels value-format="yyyy-MM-dd"
                          range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期">
          </el-date-picker>
        </el-col>
      </el-row>
      <el-row style="margin-top: 10px">
        <el-col class="block" :span="4">户籍地址:
          <el-cascader v-model="personForm.homeAddrCondi" :options="regionData" :props="{ checkStrictly: true }" clearable size="mini"></el-cascader>
        </el-col>
        <el-col class="block" :span="4">工作地址:
          <el-cascader v-model="personForm.workAddrCondi" :options="regionData" :props="{ checkStrictly: true }" clearable size="mini"></el-cascader>
        </el-col>
        <el-col :span="3">院校：
          <el-select v-model="personForm.granduteSchool" placeholder="院校" style="width: 150px" size="mini">
            <el-option v-for="item in this.personForm.granduteSchoolsEnum" :key="item.value" :label="item.value" :value="item.key"></el-option>
          </el-select>
        </el-col>
        <el-col class="block" :span="4">专业:
          <el-cascader v-model="personForm.professionCondition" :options="professionData" :props="{ checkStrictly: true }" clearable size="mini"></el-cascader>
        </el-col>
        <el-col :span="3">
          <el-button type="primary" size="mini" icon="el-icon-search" @click="initPersons">搜索</el-button>
          <el-button type="success" size="mini" icon="el-icon-search" @click="clearCondition">重置</el-button>
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
        <el-table-column prop="idCardNo" label="身份证号" width="200" fixed align="left"></el-table-column>
        <el-table-column prop="name" label="姓名" width="120" fixed align="left"></el-table-column>
        <el-table-column prop="genderDesc" label="性别" width="60" fixed align="left"></el-table-column>
        <el-table-column prop="birthDate" label="出生年月" width="120" align="left"></el-table-column>
        <el-table-column prop="age" label="年龄" width="80" fixed align="left"></el-table-column>
        <el-table-column prop="nativePlaceDesc" label="籍贯" width="120" align="left"></el-table-column>
        <el-table-column prop="homeAddress" label="家庭地址" width="330" align="left"></el-table-column>
        <el-table-column prop="workAddress" label="工作地址" width="330" align="left"></el-table-column>
        <el-table-column prop="professionDesc" label="专业" width="200" align="left"></el-table-column>
        <el-table-column prop="granduteSchoolDesc" label="毕业学校" width="200" align="left"></el-table-column>
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
import {getRequest, postRequest} from "../../utils/api";

export default {
  name: "SocialQuery",
  data() {
    return {
      personForm: {
        name: '',
        idCardNo: '',
        gender: '',
        birthDate: '',
        nativePlaceCode: '',
        nativePlaceDesc: '',
        homeAddress: '',
        workAddress: '',
        professionCode: '',
        professionDesc: '',
        granduteSchoolCode: '',
        granduteSchoolDesc: '',
        homeAddrCondi: '',
        workAddrCondi: '',
        homeProvince: '',
        homeCity: '',
        homeArea: '',
        workProvince: '',
        workCity: '',
        workArea: '',
        birthDateRange: [],
        birthDateStart: '',
        birthDateEnd: '',
        /*毕业院校*/
        granduteSchoolsEnum: [],
        granduteSchool: '',
        professionCondition: ''
      },
      persons: [],
      regionData: [],
      regionCondition:'',
      professionData: [],
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
      let url = '/get/person/?page=' + this.page + '&size=' + this.size;
      if (this.personForm.homeAddrCondi[0]) {
        url = url + '&homeProvince=' + this.personForm.homeAddrCondi[0];
      }
      if (this.personForm.homeAddrCondi[1]) {
        url = url + '&homeCity=' + this.personForm.homeAddrCondi[1];
      }
      if (this.personForm.homeAddrCondi[2]) {
        url = url + '&homeArea=' + this.personForm.homeAddrCondi[2];
      }
      if (this.personForm.workAddrCondi[0]) {
        url = url + '&workProvince=' + this.personForm.workAddrCondi[0];
      }
      if (this.personForm.workAddrCondi[1]) {
        url = url + '&workCity=' + this.personForm.workAddrCondi[1];
      }
      if (this.personForm.workAddrCondi[2]) {
        url = url + '&workArea=' + this.personForm.workAddrCondi[2];
      }
      if (this.personForm.name) {
        url = url + '&name=' + this.personForm.name;
      }
      if (this.personForm.birthDateRange[0]) {
        url = url + '&birthDateStart=' + this.personForm.birthDateRange[0];
      }
      if (this.personForm.birthDateRange[1]) {
        url = url + '&birthDateEnd=' + this.personForm.birthDateRange[1];
      }
      if (this.personForm.granduteSchool) {
        url = url + '&granduteSchoolCode=' + this.personForm.granduteSchool;
      }
      // 专业
      if (this.personForm.professionCondition[0] && this.personForm.professionCondition[1]) {
        url = url + '&professionCode=' + this.personForm.professionCondition[1];
      } else if (this.personForm.professionCondition[0] && !this.personForm.professionCondition[1]){
        url = url + '&professionCode=' + this.personForm.professionCondition[0];
      }
      getRequest(url).then(resp => {
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
      this.initPersons();
    },
    /*改变每页大小*/
    sizeChange(currentSize){
      this.size = currentSize;
      this.initPersons();
    },
    /*双击行记录，打开编辑弹框*/
    tableDbEdit(row, column, cell, event) {
      alert("double click")
    },
    initRegionData() {
      getRequest('/query/region').then(resp => {
        this.loading = false;
        if (resp && resp.code == 200000) {
          this.regionData = resp.obj;
        }
        this.loading = false;
      })
    },
    initProfessionData() {
      getRequest('/query/profession').then(resp => {
        this.loading = false;
        if (resp && resp.code == 200000) {
          this.professionData = resp.obj;
        }
        this.loading = false;
      })
    },
    /*重置查询条件*/
    clearCondition() {
      this.personForm = {}
    },
    /*毕业院校下拉框数据*/
    initGranduteSchool() {
      if (!window.sessionStorage.getItem("granduteSchools")) {
        getRequest('/dict/item_name?item_name=UNIVERSITY').then(resp => {
          if (resp) {
            this.personForm.granduteSchoolsEnum = resp.obj;
          }
        })
      } else {
        this.personForm.granduteSchoolsEnum = JSON.parse(window.sessionStorage.getItem("granduteSchools"));
      }
    },
  },
  mounted() {
    this.initPersons();
    this.initRegionData();
    this.initGranduteSchool();
    this.initProfessionData();
  }
}
</script>

<style scoped>

</style>
