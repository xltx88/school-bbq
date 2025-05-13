<template>
  <div class="header">
    <div class="logo">
      <router-link to="/supplierAllBack">
        <a href="#" >KK社区</a>
      </router-link>
    </div>
    <el-input
        style="margin: 10px 10px; width:150px"
        placeholder="请输入搜索内容"
        v-model="search"
        clearable
        @change="onSearch(search)">
    </el-input>
    <div  class="user">
      <div v-if="hasLogin">
        <span class="publish-btn">
          <router-link to="/publish/add">
            <el-link style="font-size: 15px" icon="el-icon-edit" :underline="false">发布新帖</el-link>
          </router-link>
        </span>
        <el-dropdown @command="handleCommand" class="userInfoAvatar">
          <el-badge :is-dot="userInfo.message_notification">
            <span class="el-dropdown-link">
              <el-avatar :src="userInfo.avatarUrl"></el-avatar>
            </span>
          </el-badge>
          <el-dropdown-menu slot="dropdown"  >
            <el-dropdown-item command="profile"><i class="el-icon-user">个人主页</i></el-dropdown-item>
            <el-dropdown-item command="logout" @click="tuichu()" divided><i class="el-icon-warning-outline">退出</i></el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span class="header-name">{{userInfo.name}}</span>
      </div>
      <div  v-else>
        <router-link to="/login">
          <el-link style="padding: 10px" :underline="false">登录</el-link>
        </router-link>
        <el-link style="margin-left: 10px"
                 href="https://github.com/login/oauth/authorize?client_id=6161e3b8ad33e85e5cce&scope=user&state=1"
                 :underline="false"
        >
          <el-avatar style="background: rgb(255 255 255);"
                     :src="require('../assets/img/LogoGitHub.png')"
                     fit="contain"
          ></el-avatar>
        </el-link>
      </div>
    </div>
  </div>
</template>

<script>
import router from '../router';
export default {
  name: "Header.vue",
  inject:['reload'],
  data() {
    return {
      activeIndex: '1',
      search: '',
      userInfo: {
        name:"请先登录",
        avatar_url:"null",
        message_notification:false,
      },
      hasLogin: false,
    };
  },
  methods: {
    tuichu(){
      // 调用已经完善的logout方法
      this.logout()
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    onSearch(search) {
      this.$router.push({path:'/',query:{search}})
      this.$router.go(0);
    },
    logout() {
      // 使用Vuex mutation清除用户状态
      this.$store.commit('REMOVE_INFO')
      this.hasLogin = false
      
      // 提示用户
      this.$message.success("退出成功");
      
      // 刷新页面以确保状态完全清除
      this.$router.push('/')
      this.$router.go(0)
    },
    handleCommand(command) {
      if(command==="logout"){
        this.logout()
      }
      else if(command==="profile"){
        this.$router.push("/profile/myquestion")
      }
    },
  },
  created() {
    // 首先检查Vuex中是否有用户信息
    if(this.$store.state.username && this.$store.state.userInfo) {
      this.userInfo = this.$store.state.userInfo;
      
      // 如果有未读消息，设置消息通知标记
      if(this.userInfo && this.userInfo.unreadCount > 0){
        this.userInfo.message_notification = true;
      }
      this.hasLogin = true;
    } 
    // 如果Vuex中没有但localStorage中有，则从localStorage恢复
    else if(localStorage.getItem('username')) {
      try {
        const userinfo = JSON.parse(localStorage.getItem('userinfo'));
        const username = localStorage.getItem('username');
        
        if(userinfo && username) {
          // 使用mutations更新Vuex状态
          this.$store.commit('SET_USERINFO', userinfo);
          this.$store.commit('SET_USERNAME', username);
          
          // 更新组件状态
          this.userInfo = userinfo;
          this.hasLogin = true;
          
          console.log('从localStorage恢复用户登录状态成功');
        }
      } catch(e) {
        console.error('恢复登录状态失败', e);
        // 清除可能损坏的数据
        localStorage.removeItem('userinfo');
        localStorage.removeItem('username');
      }
    }
  }
}
</script>

<style scoped>
.header{
  height: 100%;
  display: flex;
  color: #909399;
  border-bottom: solid 1px #e6e6e6;
  position: relative;
}
.el-menu.el-menu--horizontal{
  border-bottom: 0px #e6e6e6;
}
.user{
  position: absolute;
  right: 10px;
  margin: 10px 0px;
}
.publish-btn{
  float: left;
  margin: 8px 20px 0px 0px;
}
.header-name{
  float: right;
  margin: 9px;
}
</style>