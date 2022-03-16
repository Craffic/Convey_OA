import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    // 1. state
    state:{
        // 1.1 定义一个数组用来存放菜单项数据
        routes:[]
    },
    // 2. mutations
    mutations:{
        initRoutes(state, data){
            state.routes = data;
        }
    },
    // 3. actions
    actions: {

    }
})