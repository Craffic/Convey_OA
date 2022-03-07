<template>
  <div>
    <el-container>
      <el-header class="header">
        <div class="title">科汇自动化办公系统</div>
        <!--用户信息下拉框-->
        <el-dropdown>
          <span class="el-dropdown-link">
            {{userName}}
            <i>
              <img :src="url" alt="">
            </i>
          </span>
        </el-dropdown>
      </el-header>
      <el-container>
        <el-aside width="200px">Aside</el-aside>
        <el-main>Main</el-main>
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
</style>
