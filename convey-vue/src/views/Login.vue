<!--<template>-->
<!--  <div>-->
<!--    <el-form :rules="rules" ref="loginForm" :model="loginForm" class="loginContainer"-->
<!--             v-loading='loading'-->
<!--             element-loading-text="正在登录..."-->
<!--             element-loading-spinner="el-icon-loading"-->
<!--             element-loading-background="rgba(0, 0, 0, 0.8)">-->
<!--      <h3 class="loginTitle">系统登录</h3>-->
<!--      &lt;!&ndash;用户名输入框&ndash;&gt;-->
<!--      <el-form-item prop="userName">-->
<!--        <el-input v-model="loginForm.userName" type="text" auto-complete="off" placeholder="请输入用户名" ref="userName" size="normal">-->
<!--        </el-input>-->
<!--      </el-form-item>-->
<!--      &lt;!&ndash;密码输入框&ndash;&gt;-->
<!--      <el-form-item prop="password">-->
<!--        <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="请输入密码" ref="password" size="normal">-->
<!--        </el-input>-->
<!--      </el-form-item>-->
<!--      &lt;!&ndash;记住密码&ndash;&gt;-->
<!--      <div class="rememberMe">-->
<!--        <el-checkbox>记住密码</el-checkbox>-->
<!--      </div>-->
<!--      &lt;!&ndash;登录按钮&ndash;&gt;-->
<!--      <el-button type="primary" style="width: 100%" @click="submitLogin">登录</el-button>-->
<!--    </el-form>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import {postKeyValueRequest} from "@/utils/api";-->
<!--export default {-->
<!--  name: "Login",-->
<!--  data () {-->
<!--    return {-->
<!--      loading: false,-->
<!--      // 初始化默认的登录用户-->
<!--      loginForm: {-->
<!--        userName: 'admin',-->
<!--        password: '123'-->
<!--      },-->
<!--      // 添加验证规则-->
<!--      rules: {-->
<!--        userName: [{required: false, message: '请输入用户名', trigger: 'blur'}],-->
<!--        password: [{required: false, message: '请输入密码', trigger: 'blur'}]-->
<!--      },-->
<!--      // 记住密码-->
<!--      checked: true-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    submitLogin(){-->
<!--      // 验证登录规则-->
<!--      this.$refs.loginForm.validate((valid) => {-->
<!--        if (valid) {-->
<!--          alert(this.loginForm.userName + "  " + this.loginForm.password)-->
<!--          postKeyValueRequest('/doLogin', this.loginForm).then(response => {-->
<!--            /*登录成功还是失败后，登录条都是隐藏掉*/-->
<!--            this.loading = false;-->
<!--            if (response){-->
<!--              alert(JSON.stringify(response));-->
<!--              window.sessionStorage.setItem("user", JSON.stringify(response.obj));-->
<!--              this.$router.replace('/home');-->
<!--            }-->
<!--          })-->
<!--          /*if (true) {-->
<!--            // 登录成功，把用户信息存入到sessionStorage中-->
<!--            window.sessionStorage.setItem("user", "user_info");-->
<!--            // 跳转到home页-->
<!--            this.$router.replace("/home");-->
<!--          }*/-->
<!--        } else {-->
<!--          alert("用户名或密码不能为空！");-->
<!--        }-->
<!--      })-->

<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style>-->
<!--.loginContainer {-->
<!--  border: 1px solid #eaeaea;-->
<!--  width: 350px;-->
<!--  /* 背景颜色 */-->
<!--  background: #fff;-->
<!--  /* auto: 让浏览器自己选择一个合适的外边距。有时，在一些特殊情况下，该值可以使元素居中。 */-->
<!--  margin: 180px auto;-->
<!--  /* 圆角的弧度为15px */-->
<!--  border-radius: 15px;-->
<!--  background-clip: padding-box;-->
<!--  /* 内边距：上边 | 右边 | 下边 | 左边 */-->
<!--  padding: 35px 35px 15px 35px;-->
<!--  /* 阴影 */-->
<!--  box-shadow: 0 0 25px #cac6c6;-->
<!--}-->
<!--.loginTitle {-->
<!--  margin: 20px auto 20px auto;-->
<!--  /* 文本居中 */-->
<!--  text-align: center;-->
<!--  /* 文本颜色 */-->
<!--  color: #1d2936;-->
<!--}-->
<!--.rememberMe {-->
<!--  text-align: left;-->
<!--  margin: 0px 0px 15px 0px;-->
<!--}-->
<!--</style>-->
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
        username: 'Liu Chengyan',
        password: 'Craffic'
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
