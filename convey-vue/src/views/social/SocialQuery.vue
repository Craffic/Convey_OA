<template>
  <div>
    <div>
      <el-row style="margin-top: 10px">
        <el-col :lg="3" :xs="24" class="lightgreen-box">姓名：
          <el-input v-model="personForm.name" size="mini" style="width: 200px"></el-input>
        </el-col>
        <el-col :lg="3" :xs="24" class="lightgreen-box">身份证号：
          <el-input v-model="personForm.idCardNo" size="mini" style="width: 180px"></el-input>
        </el-col>
        <el-col :lg="4" :xs="24" class="lightgreen-box">户籍地址:
          <el-cascader v-model="personForm.homeAddrCondi" :options="regionData" :props="{ checkStrictly: true }" clearable size="mini" style="width: 300px"></el-cascader>
        </el-col>
        <el-col :lg="4" :xs="24" class="lightgreen-box">工作地址:
          <el-cascader v-model="personForm.workAddrCondi" :options="regionData" :props="{ checkStrictly: true }" clearable size="mini" style="width: 300px"></el-cascader>
        </el-col>
      </el-row>
      <el-row style="margin-top: 10px">
        <el-col :lg="3" :xs="24" class="lightgreen-box">性别：
          <el-radio-group v-model="personForm.gender">
            <el-radio label="M">男</el-radio>
            <el-radio label="F">女</el-radio>
          </el-radio-group>
        </el-col>
        <el-col :lg="3" :xs="24" class="lightgreen-box">收藏：
          <el-select v-model="personForm.favorite" style="width: 212px" size="mini" clearable>
            <el-option key="1" label="收藏" value="1"></el-option>
            <el-option key="0" label="未收藏" value="0"></el-option>
          </el-select>
        </el-col>
        <el-col :lg="4" :xs="24" class="lightgreen-box">毕业院校:
          <el-select v-model="personForm.granduteSchool" placeholder="院校" style="width: 300px" size="mini">
            <el-option v-for="item in this.granduteSchoolsEnum" :key="item.value" :label="item.value" :value="item.key"></el-option>
          </el-select></el-col>
        <el-col :lg="4" :xs="24" class="orange-box">专业：
          <el-cascader v-model="personForm.professionCondition" :options="professionData" :props="{ checkStrictly: true }" clearable size="mini" style="width: 320px"></el-cascader>
        </el-col>
      </el-row>
      <el-row style="margin-top: 10px">
        <el-col :lg="3" :xs="24" class="lightgreen-box">
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
        <el-table-column prop="favorite" label="收藏" width="120" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.favorite"><el-button size="medium" type="success" icon="el-icon-star-on" @click="favorPerson(scope.row)"></el-button></span>
            <span v-else-if="!scope.row.favorite"><el-button size="medium" icon="el-icon-star-on" @click="favorPerson(scope.row)"></el-button></span>
          </template>
        </el-table-column>
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
        granduteSchool: '',
        professionCondition: '',
        favorite: ''
      },
      persons: [],
      regionData: [],
      regionCondition:'',
      granduteSchoolsEnum: [],
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
      // 性别
      if (this.personForm.gender) {
        url = url + '&gender=' + this.personForm.gender;
      }
      // 身份证
      if (this.personForm.idCardNo) {
        url = url + '&idCardNo=' + this.personForm.idCardNo;
      }
      // 收藏
      if (this.personForm.favorite) {
        url = url + '&favorite=' + this.personForm.favorite;
      }
      console.log(url);
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
    /*收藏*/
    favorPerson(data) {
      console.log(data);
      postRequest('/person/upadte', data).then(resp => {
        if (resp && resp.code == 200000) {
          this.initPersons();
        }
      })
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
      this.personForm = {
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
        granduteSchool: '',
        professionCondition: '',
        favorite: ''
      },
      this.page = 1;
      this.size = 10;
    },
    /*毕业院校下拉框数据*/
    initGranduteSchool() {
      if (!window.sessionStorage.getItem("granduteSchools")) {
        getRequest('/dict/item_name?item_name=UNIVERSITY').then(resp => {
          if (resp) {
            this.granduteSchoolsEnum = resp.obj;
          }
        })
      } else {
        this.granduteSchoolsEnum = JSON.parse(window.sessionStorage.getItem("granduteSchools"));
      }
    }
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
