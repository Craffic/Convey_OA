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
            <el-dropdown-item command="setting">设置</el-dropdown-item>
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
  </div>
</template>

<script>
import {getRequest} from "../utils/api";

export default {
  name: "Home",
  data() {
    return {
      user: JSON.parse(window.sessionStorage.getItem("user"))
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
      }else if (cmd == 'userInfo') {
        // this.$router.push('/hrinfo');
      }
    }
  },
  computed: {
    // 修改菜单数据的数据源
    routes(){
      return this.$store.state.routes;
    }
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
</style>
