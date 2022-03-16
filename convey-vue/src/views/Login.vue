<template>
  <div>
    <el-form :rules="rules" :model="loginForm" ref="loginForm" class="loginContainer"
              v-loading="loading"
              element-loading-text="正在登录..."
              element-loading-spinner="el-icon-loading"
              element-loading-background="rgba(0, 0, 0, 0.8)">
      <h3 class="loginTitle">系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text" ref="username" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" ref="password" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码" @keydown.enter.native="submitLogin"></el-input>
      </el-form-item>
<!--      记住密码-->
      <el-checkbox v-model="checked" class="remember">记住密码</el-checkbox>
<!--      登录按钮-->
      <el-button type="primary" style="width: 100%" @click="submitLogin()">登录</el-button>
    </el-form>
  </div>
</template>

<script>
import {postKeyValueRequest} from "@/utils/api";
export default {
  name: "Login",
  data() {
    return {
      /*登录加载*/
      loading: false,
      // 登录规则
      rules: {
        username: [{required:true, message: '请输入用户名', trigger: 'blur'}],
        password: [{required:true, message: '请输入密码', trigger: 'blur'}]
      },
      // 定义登录数据
      loginForm: {
        username: 'admin',
        password: '123'
      },
      // 记住密码
      checked: true
    }
  },
  // login
  methods: {
    submitLogin(){
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          postKeyValueRequest('/doLogin', this.loginForm).then(response => {
            /*登录成功还是失败后，登录条都是隐藏掉*/
            this.loading = false;
            if (response){
              window.sessionStorage.setItem("user", JSON.stringify(response.obj));
              this.$router.replace('/home');
            }
          })
        } else {
          console.log('登录数据不能为空！');
          return false;
        }
      });
    }
  }
}
</script>

<style>
.loginTitle{
  margin: 20px auto 20px auto;
  text-align: center;
  color: #1d2936;
}
.loginContainer{
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px  auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.remember{
  text-align: left;
  margin: 0px 0px 15px 0px;
}
</style>