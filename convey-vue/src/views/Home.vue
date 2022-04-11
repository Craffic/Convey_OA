<template>
  <div>
    <el-container>
      <el-header class="header">
        <div class="title">科汇自动化办公系统</div>
        <!--用户信息下拉框-->
        <el-dropdown class="userInfo" @command="commandHandler">
          <span class="el-dropdown-link">
            {{user.nameZh}}<i><img :src="user.iconUrl" alt=""></i>
          </span>
          <!--点用户姓名后展示下拉框-->
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="profile">个人中心</el-dropdown-item>
            <el-dropdown-item command="modify_pwd">修改密码</el-dropdown-item>
            <el-dropdown-item command="logout" divided>注销</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-container>
        <!--左侧菜单栏-->
        <el-aside width="200px">
          <el-menu class="el-menu-vertical-demo" router unique-opened>
            <el-submenu :index="index+''" v-for="(item, index) in routes" :key="index">
              <template slot="title">
                <i :class="item.iconCls" style="color: #4e9cef;margin-right: 5px"></i>
                <span>{{item.name}}</span>
              </template>
              <el-menu-item v-for="(child, indexj) in item.children" :index="child.path" :key="indexj">{{child.name}}</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path != '/home'">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
          </el-breadcrumb>
          <div class="homeWelcome" v-if="this.$router.currentRoute.path == '/home'">
            科汇办公管理系统
          </div>
          <router-view class="homeRouterView"/>
        </el-main>
      </el-container>
    </el-container>

    <!--打开修改密码弹窗-->
    <el-dialog :visible.sync="modifyPwdVisible" width="18%" style="padding-bottom: 15px">
      <el-descriptions title="修改密码">
        <el-descriptions-item label="姓名">{{user.nameZh}}</el-descriptions-item>
        <el-descriptions-item label="账号">{{user.acct}}</el-descriptions-item>
      </el-descriptions>
      <div style="border: 1px solid;"></div>
      <div style="padding-top: 10px">
        <el-form v-model="passwdForm" ref="passwdForm" :rules="formRules">
          <el-form-item label="请输入旧密码: " prop="oldPass">
            <el-input type="password" style="width: 120px" size="mini" v-model="passwdForm.oldPass"></el-input>
          </el-form-item>
          <el-form-item label="请输入新密码: " prop="newPass">
            <el-input type="password" style="width: 120px" size="mini" v-model="passwdForm.newPass"></el-input>
          </el-form-item>
          <el-form-item label="请确认新密码: " prop="verifyPass">
            <el-input type="password" style="width: 120px" size="mini" v-model="passwdForm.verifyPass"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="modifyPwdVisible = false">取 消</el-button>
            <el-button type="primary" @click="modifyPwdEvent()">确 定</el-button>
      </span>
    </el-dialog>

    <!--打开个人中心弹窗-->
    <el-dialog :visible.sync="profileVisible" width="18%">
      <el-card class="box-card" style="width: 300px">
        <div slot="header" class="clearfix">
          <span>{{user.nameZh}}</span>
        </div>
        <div>
          <div style="display: flex;justify-content: center">
            <el-upload :show-file-list="false" :on-success="onSuccess" :data="profile" action="/user/icon_url">
              <img title="点击修改用户图像" src="https://inews.gtimg.com/newsapp_bt/0/14119841313/1000" style="width: 100px;height: 100px;border-radius: 50px" alt="">
            </el-upload>
          </div>
          <div>性别：
            <el-tag>{{profile.genderDesc}}</el-tag>
          </div>
          <div>手机号码：
            <el-tag>{{profile.phone}}</el-tag>
          </div>
          <div>邮箱：
            <el-tag>{{profile.email}}</el-tag>
          </div>
          <div>职位：
<!--            <el-tag>{{profile.posDesc}}</el-tag>-->
            <el-tag>JAVA开发工程师</el-tag>
          </div>
          <div>部门：
            <el-tag>科技信息部</el-tag>
          </div>
          <div>状态：
            <el-tag>{{profile.workStatDesc}}</el-tag>
          </div>
          <div>用户角色：
            <el-tag type="success" style="margin-right: 5px" v-for="(role,index) in profile.roles" :key="index">
              {{role.roleName}}
            </el-tag>
          </div>
        </div>
      </el-card>
      <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="updateProfileVisible = true">修改资料</el-button>
      </span>
    </el-dialog>

    <!--打开修改个人信息弹窗-->
    <el-dialog title="修改用户信息" :visible.sync="updateProfileVisible" width="20%">
      <div>
        <table>
          <tr>
            <td>
              <el-tag>用户姓名：</el-tag>
            </td>
            <td>
              <el-input v-model="profile.nameZh" size="mini"></el-input>
            </td>
          </tr>
          <tr>
            <td>
              <el-tag>用户账号：</el-tag>
            </td>
            <td>
              <el-input v-model="profile.acct" size="mini"></el-input>
            </td>
          </tr>
          <tr>
            <td>
              <el-tag>手机号码：</el-tag>
            </td>
            <td>
              <el-input v-model="profile.phone" size="mini"></el-input>
            </td>
          </tr>
          <tr>
            <td>
              <el-tag>用户姓名：</el-tag>
            </td>
            <td>
              <el-input v-model="profile.email" size="mini"></el-input>
            </td>
          </tr>
          <tr>
            <td>
              <el-tag>职位：</el-tag>
            </td>
            <td>
              <el-input v-model="profile.posId" size="mini"></el-input>
            </td>
          </tr>
          <tr>
            <td>
              <el-tag>部门：</el-tag>
            </td>
            <td>
              <el-input v-model="profile.dptId" size="mini"></el-input>
            </td>
          </tr>
          <tr>
            <td>
              <el-tag>状态：</el-tag>
            </td>
            <td>
              <el-input v-model="profile.workStat" size="mini"></el-input>
            </td>
          </tr>
          <tr></tr>
          <tr></tr>
        </table>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="updateProfileVisible = false">取消</el-button>
            <el-button type="primary" @click="updateProfileView()">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {getRequest, putRequest} from "../utils/api";

export default {
  name: "Home",
  data() {
    return {
      user: JSON.parse(window.sessionStorage.getItem("user")),
      /*个人中心*/
      profile: {
        id: '',
        workId: '',
        idCardNo: '',
        acct: '',
        nameZh: '',
        gender: '',
        genderDesc: '',
        posId: '',
        posDesc: '',
        dptId: '',
        dptDesc: '',
        phone: '',
        email: '',
        workStat: '',
        workStatDesc: '',
        beginDate: '',
        convertDate: '',
        roles:[
          {
            id: '',
            role: '',
            roleName: ''
          }
        ]
      },
      /*修改密码弹窗开关*/
      modifyPwdVisible: false,
      /*个人中心弹窗开关*/
      profileVisible: false,
      /*修改个人信息*/
      updateProfileVisible: false,
      /*修改密码表单*/
      passwdForm: {
        oldPass: '',
        newPass: '',
        verifyPass: '',
        idCardNo: ''
      },
      formRules: {
        oldPass: [
          {required: false, message: '旧密码不能为空', trigger: 'blur'}
        ],
        newPass: [
          {required: false, message: '新密码不能为空', trigger: 'blur'}
        ],
        verifyPass: [
          {required: false, message: '确认密码不能为空', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    /*在el-dropdown标签里使用@command=commandHandler，然后就会调用methods里的commandHandler方法
    * commandHandler里有个参数cmd就是用来接收菜单项el-dropdown-item里的command的值*/
    commandHandler(cmd) {
      if (cmd == 'logout') {
        this.$confirm('此操作将注销登录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          getRequest('/logout', null);
          window.sessionStorage.removeItem(null);
          this.$store.commit('initRoutes', []);
          // this.$router.push("/");
          this.$router.replace("/");
        }).catch(() => {
          console.log("err")
          this.$message({
            type: 'info',
            message: '已取消操作'
          });
        });
      } else if (cmd == 'profile') {
        this.showProfileView();
      } else if (cmd == 'modify_pwd'){
        // 修改密码
        this.showModifyPwdView();
      }
    },

    /*--------------------------------------修改密码-----------------------------------------------*/

    /*打开修改密码窗口*/
    showModifyPwdView(){
      this.modifyPwdVisible = true;
    },
    /*修改密码*/
    modifyPwdEvent(){
      if (this.passwdForm.newPass === this.passwdForm.verifyPass) {
        this.passwdForm.idCardNo = this.user.idCardNo;
        putRequest("/user/modifyPwd", this.passwdForm).then(resp => {
          if (resp && resp.code == 200000) {
            this.$message({
              message: resp.obj,
              type: 'success'
            });
            // 修改成功，删除用户登录缓存，登出
            getRequest('/logout', null);
            window.sessionStorage.removeItem(null);
            this.$store.commit('initRoutes', []);
          } else {
            // 更新失败
            this.$message.error(resp.message);
          }
        });
        this.$router.replace("/");
      } else {
        this.$message.error('输入的两次密码不匹配，请重新输入');
        this.passwdForm.newPass = '';
        this.passwdForm.verifyPass = '';
      }
    },

    //----------------------------------个人中心-------------------------------------------

    /*打开个人中心窗口*/
    showProfileView(){
      this.initProfile();
      this.profileVisible = true;
    },
    /*初始化profile对象*/
    onSuccess() {
      // this.initProfile();
    },
    /*初始化profile*/
    initProfile(){
      getRequest('/user/profile?idCardNo=' + this.user.idCardNo).then(resp => {
        if (resp && resp.code == 200000) {
          this.profile = resp.obj;
        }
      })
    },
    //-------------------------------------------------------------------------------------
    updateProfileView(){
      putRequest('/user/update', this.profile).then(resp => {
        if (resp && resp.code == 200000) {
          this.updateProfileVisible = false;
          this.initProfile();
        }
      })
    }
  },
  computed: {
    // 修改菜单数据的数据源
    routes(){
      return this.$store.state.routes;
    }
  },
  mounted() {
    this.initProfile();
  }
}
</script>

<style>
.title {
  font-size: 30px;
  font-family: 幼圆;
}
.header {
  background: #4e9cef;
  display: flex; /*todo what is display: flex*/
  align-items: center;
  justify-content: space-between;
  padding: 0px 15px;
  box-sizing: border-box;
}
/*头像样式*/
.el-dropdown-link img {
  width: 48px;
  height: 48px;
  border-radius: 24px;
  margin-left: 8px;
}
/*角色下拉框样式*/
.el-dropdown-link {
  display: flex;
  align-items: center;
}
.userInfo {
  cursor: pointer;
}
.homeWelcome{
  font-family: 幼圆;
  text-align: center;
  font-size: 50px;
  margin-top: 100px;
}
/*面包屑和routerView有15px像素间隔*/
.homeRouterView {
  margin-top: 15px;
}
</style>
