import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import axios from 'axios'
import "./axios"
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import moment from 'moment'; //导入时间模块
// import './permission' // 路由拦截

import VueCropper from 'vue-cropperjs';
import 'cropperjs/dist/cropper.css';

// 在应用启动时检查localStorage中是否有用户信息
if (localStorage.getItem('userinfo')) {
  try {
    const userinfo = JSON.parse(localStorage.getItem('userinfo'));
    const username = localStorage.getItem('username');
    
    // 恢复Vuex状态
    if (userinfo && username) {
      store.commit('SET_USERINFO', userinfo);
      store.commit('SET_USERNAME', username);
      console.log('应用启动时恢复用户状态成功');
    }
  } catch (e) {
    console.error('恢复登录状态失败', e);
    // 清除可能损坏的数据
    localStorage.removeItem('userinfo');
    localStorage.removeItem('username');
  }
}



// use
Vue.component(VueCropper)

Vue.use(mavonEditor)

Vue.prototype.$axios = axios

Vue.use(Element)
Vue.config.productionTip = false

moment.locale('zh-cn'); //设置语言 或 moment.lang('zh-cn');
Vue.prototype.$moment = moment;//赋值使用

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

// 注意：这段代码是重复的，已在上面处理过用户信息恢复，这里可以删除
// 在全局作用域中不能使用this.$store