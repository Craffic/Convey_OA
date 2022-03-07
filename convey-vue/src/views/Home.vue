<template>
  <div>
    <el-container>
      <el-header class="header">
        <div class="title">科汇自动化办公系统</div>
        <!--用户信息下拉框-->
        <el-dropdown class="userInfo" @command="commandHandler">
          <span class="el-dropdown-link">
            {{userName}}
            <i>
              <img :src="url" alt="">
            </i>
          </span>
          <!--点用户姓名后展示下拉框-->
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="profile">个人中心</el-dropdown-item>
            <el-dropdown-item command="setting">设置</el-dropdown-item>
            <el-dropdown-item command="logout">注销</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-container>
        <!--左侧菜单栏-->
        <el-aside width="200px">
          <el-menu :default-openeds="['1']">
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-message"></i>导航一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-menu"></i>导航二</template>
              <el-menu-item index="1-1">选项3</el-menu-item>
              <el-menu-item index="1-2">选项4</el-menu-item>
            </el-submenu><el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>导航三</template>
            <el-menu-item index="1-1">选项5</el-menu-item>
            <el-menu-item index="1-2">选项6</el-menu-item>
          </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <div class="homeWelcome">
              科汇自动办公系统
            </div>
          </el-breadcrumb>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      userName: "刘成炎",
      url: "https://tse1-mm.cn.bing.net/th/id/R-C.a5196bbf244c1f32eb1e2fa4c3be39c0?rik=FV57G%2bMxQOOxMg&riu=http%3a%2f%2fimg.qqtouxiangzq.com%2fdongwu%2flang%2flang12.jpg&ehk=O6spZJ2txOQLpL9s755LCvmT34ae5vzUWJ9chVibHac%3d&risl=&pid=ImgRaw&r=0"
    }
  },
  methods: {
    /*在el-dropdown标签里使用@command=commandHandler，然后就会调用methods里的commandHandler方法
    * commandHandler里有个参数cmd就是用来接收菜单项el-dropdown-item里的command的值*/
    commandHandler(cmd){
      if ('logout' == cmd) {
        this.$confirm('此操作将注销登录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          /*todo 调用注销接口*/
          /*清除sessionStorage里的用户信息*/
          window.sessionStorage.removeItem(null);
          /*todo 跳转回登录页*/
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消操作'
          });
        });
      }
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
