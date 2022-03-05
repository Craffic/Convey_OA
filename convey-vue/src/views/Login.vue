<template>
  <div>
    <el-form :rules="rules" ref="loginForm" :model="loginForm" class="loginContainer"
             v-loading='loading'
             element-loading-text="正在登录..."
             element-loading-spinner="el-icon-loading"
             element-loading-background="rgba(0, 0, 0, 0.8)">
      <h3 class="loginTitle">系统登录</h3>
      <!--用户名输入框-->
      <el-form-item prop="userName">
        <el-input v-model="loginForm.userName" type="text" auto-complete="off" placeholder="请输入用户名" ref="userName" size="normal">
        </el-input>
      </el-form-item>
      <!--密码输入框-->
      <el-form-item prop="password">
        <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="请输入密码" ref="password" size="normal">
        </el-input>
      </el-form-item>
      <!--记住密码-->
      <el-checkbox class="rememberMe">记住密码</el-checkbox>
      <!--登录按钮-->
      <el-button type="primary" style="width: 100%" @click="submitLogin">登录</el-button>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data () {
    return {
      // 初始化默认的登录用户
      loginForm: {
        userName: 'admin',
        password: '123'
      },
      // 添加验证规则
      rules: {
        userName: [{required: false, message: '请输入用户名', trigger: 'blur'}],
        password: [{required: false, message: '请输入密码', trigger: 'blur'}]
      },
      // 记住密码
      checked: true
    }
  },
  methods: {
    submitLogin(){
      console.log(this.$refs.userName.value);
      console.log(this.$refs.password.value);
      // 验证登录规则
      this.$refs.loginForm.validate((valid) => {
        console.log(valid);
        if (valid) {
          this.$notify({
            title: '登录成功',
            message: '欢迎登录科汇OA办公系统',
            type: 'success'
          });
        } else {
          aler("");
        }
      })

    }
  }
}
</script>

<style>
.loginContainer {
  border: 1px solid #eaeaea;
  width: 350px;
  /* 背景颜色 */
  background: #fff;
  /* auto: 让浏览器自己选择一个合适的外边距。有时，在一些特殊情况下，该值可以使元素居中。 */
  margin: 180px auto;
  /* 圆角的弧度为15px */
  border-radius: 15px;
  background-clip: padding-box;
  /* 内边距：上边 | 右边 | 下边 | 左边 */
  padding: 35px 35px 15px 35px;
  /* 阴影 */
  box-shadow: 0 0 25px #cac6c6;
}
.loginTitle {
  margin: 20px auto 20px auto;
  /* 文本居中 */
  text-align: center;
  /* 文本颜色 */
  color: #1d2936;
}
.rememberMe {
  text-align: left;
  margin: 0px 0px 15px 0px;
}
</style>
