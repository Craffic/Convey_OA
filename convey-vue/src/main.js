import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store'
// import 'font-awesome/css/font-awesome.min.css'
import {initMenu} from "@/utils/menu";

Vue.use(ElementUI);
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

/*新增全局导航守卫*/
router.beforeEach((to, from, next) => {
  if (to.path == '/') {
    next();
  } else {
    initMenu(router, store);
    next();
  }
})
