<template>
  <div>
    <div>
      <div style="display: flex;justify-content: space-between">
        <!--输入框、搜索、高级搜索-->
        <div>
          <el-input placeholder="请输入用户姓名或账号模糊搜索..." prefix-icon="el-icon-search" style="width: 300px;margin-right: 10px"
                    v-model="keyword" @keydown.enter.native="initUsers" :disabled="advancedisabledView"
                    clearable @clear="initUsers"></el-input>
          <el-button type="primary" icon="el-icon-search" @click="initUsers" :disabled="advancedisabledView">搜索</el-button>
          <el-button type="primary" @click="advancedisabledView = !advancedisabledView">
            <i :class="advancedisabledView?'fa fa-angle-double-up':'fa fa-angle-double-down'" aria-hidden="true"></i>
            高级搜索
          </el-button>
        </div>
        <!--导出、导入、添加用户-->
        <div>
          <el-button type="success" @click="exportData"><i class="fa fa-arrow-down" style="margin-right: 5px" aria-hidden="true"></i>导出</el-button>
          <el-upload action="/employee/basic/import/user_info" style="display: inline-flex;margin-left: 8px;margin-right: 8px"
                     :before-upload="beforeUpload"
                     :on-success="onSuccess"
                     :on-error="onError"
                     :show-file-list="false"
                     :disabled="importDataDisabled">
            <el-button type="success" :disabled="importDataDisabled" :icon="importDataBtnIcon">{{importDataBtnText}}</el-button>
          </el-upload>
          <el-button type="primary" prefix-icon="el-icon-plus" @click="showUserAddDialog">添加用户</el-button>
        </div>
      </div>
      <!--高级搜索条件框-->
      <transition name="slide-fade">
        <div class="advanceCondition" v-show="advancedisabledView">
          <el-row style="margin-top: 10px">
            <el-col :span="5">政治面貌:
              <el-select v-model="searchValue.politicId" placeholder="政治面貌" size="mini" style="width: 130px;">
                <el-option v-for="item in politicsstatus" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-col>
            <el-col :span="4">民族:
              <el-select v-model="searchValue.nationId" placeholder="民族" size="mini" style="width: 130px;">
                <el-option v-for="item in nations" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-col>
            <el-col :span="4">职位:
              <el-select v-model="searchValue.posId" placeholder="职位" size="mini" style="width: 130px;">
                <el-option v-for="item in positions" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-col>
            <el-col :span="4">职称:
              <el-select v-model="searchValue.jobLevelId" placeholder="职称" size="mini" style="width: 130px;">
                <el-option v-for="item in joblevels" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-col>
            <el-col :span="7">聘用形式:
              <el-radio-group v-model="searchValue.engageForm">
                <el-radio label="劳动合同">劳动合同</el-radio>
                <el-radio label="劳务合同">劳务合同</el-radio>
              </el-radio-group>
            </el-col>
          </el-row>
          <el-row style="margin-top: 10px">
            <el-col :span="6">所属部门:
              <el-popover
                placement="right"
                title="请选择部门"
                width="200"
                trigger="manual"
                v-model="advDeptPopVisable">
                <el-tree :data="departmentTree" :props="defaultProps" @node-click="advHandleNodeClick" default-expand-all></el-tree>
                <div slot="reference" style="width: 150px;height: 26px;display: inline-flex;font-size: 13px;border: 1px solid #dedede;border-radius: 5px;
                                            cursor: pointer;align-items: center;padding-left: 8px;box-sizing: border-box" @click="advshowDepTree">{{inputDepName}}
                </div>
              </el-popover>
              <el-button type="text" style="color: red; margin-left: 5px" icon="el-icon-delete" @click="clearSelectDept"></el-button>
            </el-col>
            <el-col :span="9">入职日期:
              <el-date-picker
                v-model="searchValue.beginDateScope"
                type="daterange"
                size="mini"
                unlink-panels
                value-format="yyyy-MM-dd"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期">
              </el-date-picker>
            </el-col>
            <el-col :span="5" :offset="4">
              <el-button size="mini" @click="resetCondition">重置条件</el-button>
              <el-button size="mini" icon="el-icon-search" type="primary" @click="initUsers('advanced')">搜索</el-button>
            </el-col>
          </el-row>
        </div>
      </transition>
    </div>
    <div style="margin-top: 20px">
      <el-table :data="users" border style="width: 100%"
                v-loading="loading"
                element-loading-text="正在加载..."
                element-loading-spinner="el-icon-loading"
                element-loading-background="Transparent"
                @cell-dblclick="tableDbEdit">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="workId" label="工号" width="150" fixed align="left"></el-table-column>
        <el-table-column prop="idCardNo" label="身份证号" width="200" fixed align="left"></el-table-column>
        <el-table-column prop="acct" label="账号" width="120" fixed align="left"></el-table-column>
        <el-table-column prop="nameZh" label="姓名" width="120" fixed align="left"></el-table-column>
        <el-table-column prop="genderDesc" label="性别" width="120" fixed align="left"></el-table-column>
        <el-table-column prop="email" label="电子邮箱" width="220"></el-table-column>
        <el-table-column prop="phone" label="电话号码" width="150"></el-table-column>
        <el-table-column prop="dptId" label="所属部门" width="120"></el-table-column>
        <el-table-column prop="posId" label="职位" width="150"></el-table-column>
        <el-table-column prop="beginDate" label="入职日期" width="150"></el-table-column>
        <el-table-column prop="convertDate" label="转正日期" width="150"></el-table-column>
        <el-table-column prop="workStatDesc" label="在职状态" width="150"></el-table-column>
        <el-table-column prop="idCard" label="操作" fixed="right" width="320">
          <template slot-scope="scope">
            <el-button size="mini" @click="showUserEditDialog(scope.row)">编辑</el-button>
            <el-button size="mini" type="primary">查看高级资料</el-button>
            <el-button size="mini" type="danger" @click="deleteUser(scope.row)">删除</el-button>
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
    <!--添加用户对话框-->
    <el-dialog :title="title" :visible.sync="userDialogVisible" width="50%">
      <div>
        <el-form :rules="rules" :model="user" ref="userForm">
          <el-row>
            <el-col :span="6">
              <el-form-item label="工号：" prop="acct">
                <el-input v-model="user.workId" prefix-icon="el-icon-edit" style="width: 180px" size="mini" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="姓名：" prop="nameZh">
                <el-input placeholder="请输入用户姓名" v-model="user.nameZh" prefix-icon="el-icon-edit" style="width: 150px" size="mini"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="账号：" prop="acct">
                <el-input placeholder="请输入用户账号" v-model="user.acct" prefix-icon="el-icon-edit" style="width: 150px" size="mini"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="身份证号：" prop="acct">
                <el-input placeholder="请输入身份证号" v-model="user.idCardNo" prefix-icon="el-icon-edit" style="width:180px" size="mini"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="性别：" prop="genderDesc">
                <el-radio-group v-model="user.gender">
                  <el-radio label="M">男</el-radio>
                  <el-radio label="F" style="margin-left: 0px">女</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="电话号码：" prop="acct">
                <el-input placeholder="请输入电话号码" v-model="user.phone" prefix-icon="el-icon-edit" style="width: 120px" size="mini"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="职位：" prop="posId">
                <el-input placeholder="请输入职位" v-model="user.posId" prefix-icon="el-icon-edit" style="width: 150px" size="mini"></el-input>
              </el-form-item>

            </el-col>
            <el-col :span="6">
              <el-form-item label="所属部门：" prop="dptId">
                <el-input placeholder="请输入所属部门" v-model="user.dptId" prefix-icon="el-icon-edit" style="width: 180px" size="mini"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="入职日期：" prop="beginDate">
                <el-date-picker v-model="user.beginDate" type="date" placeholder="出生日期" size="mini" value-format="yyyy-MM-dd" style="width: 150px"></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="转正日期：" prop="convertDate">
                <el-input placeholder="请选择转正日期" v-model="user.convertDate" prefix-icon="el-icon-edit" style="width: 120px" size="mini"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="在职状态：" prop="workStatDesc">
                <el-input placeholder="请选择在职状态" v-model="user.workStatDesc" prefix-icon="el-icon-edit" style="width: 125px" size="mini"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="电子邮箱：" prop="nameZh">
                <el-input placeholder="请输入电子邮箱" v-model="user.email" prefix-icon="el-icon-edit" style="width: 180px" size="mini"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="userDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addOrUpdateUser">确 定</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
  import {deleteRequest, getRequest, postRequest, putRequest} from "../../utils/api";

  export default {
    name: "EmpBasic",
    data() {
      return {
        /*定义一个高级搜索条件对象*/
        searchValue: {
          politicId: null,
          nationId: null,
          jobLevelId: null,
          posId: null,
          engageForm: null,
          departmentId: null,
          beginDateScope: null
        },
        /*是否展示高级搜索框*/
        advancedisabledView: false,

        /*导入数据*/
        importDataBtnIcon: 'el-icon-upload2',
        importDataBtnText: '导入',
        importDataDisabled: false,
        /*添加或修改弹窗标题*/
        title: '',
        users: [],
        /*部门树结构体*/
        departmentTree:[],
        /*选中后的部门名称*/
        inputDepName: '所属部门',
        loading: false,
        total: 0,
        page:1,
        size: 10,
        keyword: '',
        userDialogVisible: false,
        /*下拉框集合*/
        nations: [],
        politicsstatus: [],
        joblevels: [],
        positions: [],
        tiptopDegree: ['博士后', '博士', '研究生', '本科', '大专', '高中', '中专', '初中', '小学', '其他'],
        user:{
          acct: "yaoSen",
          nameZh: "姚森",
          genderDesc: "M",
          idCardNo: "610122199102058952",
          posId: 3,
          email: "yaosen@qq.com",
          phone: "14785559936",
          dptmentId: 92,
          beginDate: "2017-01-02",
          workStatDesc: "1",
          workId: 1,
          convertDate: "2017-04-02"
        },
        /*添加页面 - 部门树弹框*/
        departmentVisable: false,
        /*高级搜索 - 部门树弹框开关*/
        advDeptPopVisable: false,
        defaultProps: {
          children: 'children',
          label: 'name'
        },
        /*校验规则*/
        rules: {
          nameZh: [{required: true, message: '请输入用户名', trigger: 'blur'}],
          acct: [{required: true, message: '请输入账号', trigger: 'blur'}],
          genderDesc: [{required: true, message: '请输入性别', trigger: 'blur'}],
          idCard: [{required: true, message: '请输入身份证号码', trigger: 'blur'}, {
            pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
            message: '身份证号码格式不正确',
            trigger: 'blur'
          }],
          email: [{required: true, message: '请输入邮箱地址', trigger: 'blur'}, {
            type: 'email',
            message: '邮箱格式不正确',
            trigger: 'blur'
          }],
          phone: [{required: true, message: '请输入电话号码', trigger: 'blur'}],
          dptId: [{required: true, message: '请输入部门名称', trigger: 'blur'}],
          posId: [{required: true, message: '请输入职位', trigger: 'blur'}],
          beginDate: [{required: true, message: '请输入入职日期', trigger: 'blur'}],
          workStatDesc: [{required: true, message: '请输入在职状态', trigger: 'blur'}],
          workId: [{required: true, message: '请输入工号', trigger: 'blur'}],
          convertDate: [{required: true, message: '请输入转正日期', trigger: 'blur'}]
        }
      }
    },
    methods: {
      /*重置高级查询条件*/
      resetCondition(){
        this.searchValue = {
          politicId: null,
          nationId: null,
          jobLevelId: null,
          posId: null,
          engageForm: null,
          departmentId: null,
          beginDateScope: null
        };
        this.inputDepName = null;
      },
      /*清除高级搜索选中的部门*/
      clearSelectDept(){
        this.inputDepName = null;
      },
      /*高级搜索 - 弹出部门树pop弹框*/
      advshowDepTree(){
        this.advDeptPopVisable = !this.advDeptPopVisable;
      },
      /*高级搜索 - 点击部门树方法*/
      advHandleNodeClick(data){
        // 给所属部门赋值
        this.inputDepName = data.name;
        this.searchValue.departmentId = data.id;
        this.advDeptPopVisable = !this.advDeptPopVisable;
      },
      /*上传文件前的钩子函数*/
      beforeUpload() {
        this.importDataBtnText = '正在导入';
        this.importDataBtnIcon = 'el-icon-loading';
        this.importDataDisabled = true;
      },
      onSuccess(response, file, fileList) {
        this.importDataBtnText = '导入';
        this.importDataBtnIcon = 'el-icon-upload2';
        this.importDataDisabled = false;
        this.$message({
          message: '用户信息表导入成功！',
          type: 'success'
        });
        this.initUsers();
      },
      onError(err, file, fileList) {
        this.importDataBtnText = '导入';
        this.importDataBtnIcon = 'el-icon-upload2';
        this.importDataDisabled = false;
        this.$message.error('用户信息表导入失败！');
      },
      /*导出用户数据*/
      exportData() {
        window.open('/employee/basic/export/user_info', '_parent');
      },
      /*双击行记录，打开编辑弹框*/
      tableDbEdit(row, column, cell, event) {
        this.showUserEditDialog(row);
      },

      /*清空user*/
      /*因为先进入编辑后，user有数据，然后再进入添加user需要清除掉信息*/
      setUserEmpty(){
        this.user = {
          nameZh: '',
          genderDesc: '',
          birthday: '',
          idCardNo: '',
          wedlock: '',
          nationId: '',
          nativePlace: '',
          politicId: '',
          email: '',
          phone: '',
          address: '',
          departmentId: '',
          jobLevelId: '',
          posId: '',
          engageForm: '',
          tiptopDegree: '',
          specialty: '',
          school: '',
          beginDate: '',
          workStatDesc: '在职',
          workId: '',
          contractTerm: '',
          conversionTime: '',
          notWorkDate: null,
          beginContract: '',
          endContract: '',
          workAge: '',
          salary: null
        }
      },
      /*添加或者修改用户方法*/
      addOrUpdateUser(){
        if (this.user.id) {
          // 有id就是修改
          this.$refs.userForm.validate((valid) => {
            if (valid) {
              putRequest('/employee/basic/', this.user).then(resp => {
                if (resp) {
                  this.userDialogVisible = false;
                  this.initUsers();
                }
              })
            }
          })
        } else {
          // 无id就是新增
          this.$refs.userForm.validate((valid) => {
            if (valid) {
              postRequest('/employee/basic/', this.user).then(resp => {
                if (resp) {
                  this.userDialogVisible = false;
                  this.initUsers();
                }
              })
            }
          })
        }
      },
      /*添加用户 - 加载下拉框数据*/
      initSelectionData(){
        // 从sessionStorage里拿下拉框数据，如果从sessionStorage里拿不到数据，则重新调用接口获取数据
        /*if (!window.sessionStorage.getItem("nations")) {
          getRequest('/employee/basic/nations').then(resp => {
            if (resp) {
              this.nations = resp;
            }
          })
        } else {
          // 从sessionStorage里有数据的，就从从sessionStorage里赋值给表单变量
          this.nations = JSON.parse(window.sessionStorage.getItem("nations"));
        }
        if (!window.sessionStorage.getItem("politicsstatus")) {
          getRequest('/employee/basic/politicsstatus').then(resp => {
            if (resp) {
              this.politicsstatus = resp;
            }
          })
        } else {
          this.politicsstatus = JSON.parse(window.sessionStorage.getItem("politicsstatus"));
        }
        if (!window.sessionStorage.getItem("joblevels")) {
          getRequest('/employee/basic/joblevels').then(resp => {
            if (resp) {
              this.joblevels = resp;
            }
          })
        } else {
          this.joblevels = JSON.parse(window.sessionStorage.getItem("joblevels"));
        }
        if (!window.sessionStorage.getItem("deps")) {
          getRequest('/employee/basic/deps').then(resp => {
            if (resp) {
              this.departmentTree = resp;
            }
          })
        } else {
          this.departmentTree = JSON.parse(window.sessionStorage.getItem("deps"));
        }*/
      },
      /*初始化职位下拉框数据，在弹出添加对话框时调用*/
      /*initPositions(){
        getRequest('/employee/basic/positions').then(resp => {
          if (resp) {
            this.positions = resp;
          }
        })
      },*/
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
      /*普通搜索或者高级搜索：通过type来区分*/
      initUsers(type) {
        this.loading = true;
        let url = '/user/query/?page=' + this.page + '&size=' + this.size;
        if (type && type == 'advanced') {
          // 高级搜索
          if (this.searchValue.politicId) {
            url += '&politicId=' + this.searchValue.politicId;
          }
          if (this.searchValue.nationId) {
            url += '&nationId=' + this.searchValue.nationId;
          }
          if (this.searchValue.jobLevelId) {
            url += '&jobLevelId=' + this.searchValue.jobLevelId;
          }
          if (this.searchValue.posId) {
            url += '&posId=' + this.searchValue.posId;
          }
          if (this.searchValue.engageForm) {
            url += '&engageForm=' + this.searchValue.engageForm;
          }
          if (this.searchValue.departmentId) {
            url += '&departmentId=' + this.searchValue.departmentId;
          }
          if (this.searchValue.beginDateScope) {
            url += '&beginDateScope=' + this.searchValue.beginDateScope;
          }
        } else {
          // 普通搜索
          url += '&acct=' + this.keyword;
        }
        console.log(url);
        getRequest(url).then(resp => {
          this.loading = false;
          if (resp && resp.code == 200000) {
            console.log();
            this.users = resp.obj.list;
            this.total = resp.obj.totalNum;
          }
        })
      },
      /*弹出添加用户对话框*/
      showUserAddDialog() {
        /*添加之前要清空user对象*/
        this.setUserEmpty();
        /*打开弹框就显示工号*/
        this.generateWorkID();
        // this.initPositions();
        this.title = '添加用户';
        this.userDialogVisible = true;
        // this.initPositions();
        this.inputDepName = '';
      },
      /*弹出修改用户对话框*/
      showUserEditDialog(data) {
        this.title = '修改用户信息';
        // this.initPositions();
        this.userDialogVisible = true;
        this.user = data;
        // this.inputDepName = data.department.name;
      },
      /*生成工号*/
      generateWorkID(){
        getRequest('/user/maxWorkID').then(resp => {
          this.loading = false;
          if (resp && resp.code == 200000) {
            console.log(resp.obj);
            this.user.workId = resp.obj;
            alert(this.user.workId);
          }
        })
      },
      /*弹出部门树弹框*/
      showDepView(){
        this.departmentVisable = !this.departmentVisable
      },
      /*点击部门书*/
      handleNodeClick(data) {
        this.inputDepName = data.name;
        this.user.departmentId = data.id;
        this.departmentVisable = !this.departmentVisable;
      },
      /*删除用户*/
      deleteUser(data){
        this.$confirm('此操作将永久删除【' +data.name+ '】用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteRequest('/employee/basic/' + data.id).then(resp => {
            this.initUsers();
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    },
    mounted() {
      this.initUsers();
      /*加载添加用户页面下拉框数据*/
      this.initSelectionData();
      /*打开弹框就显示工号*/
      this.generateWorkID();
      /*初始化职位*/
      // this.initPositions();
    }
  }
</script>

<style>
  /*高级搜索条件框*/
  .advanceCondition {
    border: 1px solid #39a0f5;
    border-radius: 5px;
    box-sizing: border-box;
    padding: 5px 10px;
    margin-top: 8px;
  }
  /* 可以设置不同的进入和离开动画 */
  /* 设置持续时间和动画函数 */
  .slide-fade-enter-active {
    transition: all .8s ease;
  }

  .slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }

  .slide-fade-enter, .slide-fade-leave-to
    /* .slide-fade-leave-active for below version 2.1.8 */
  {
    transform: translateX(10px);
    opacity: 0;
  }
</style>
