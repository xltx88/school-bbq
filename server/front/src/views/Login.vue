<template>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">登录</div>
      <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
        <el-form-item prop="username">
          <el-input v-model="param.name" placeholder="username">
            <template #prepend>
              <el-button icon="el-icon-user"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              type="password"
              placeholder="password"
              v-model="param.password"
              @keyup.enter="submitForm()"
          >
            <template #prepend>
              <el-button icon="el-icon-lock"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="submitForm()">登录</el-button>
        </div>
        <div class="login-btn">
          <el-button type="primary" @click="toRegister()">注册</el-button>
        </div>
        <!-- <p class="login-tips">Tips : 用户名和密码随便填。</p> -->
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login.vue",
  data() {
    return {
      param: {
        name: "",
        password: ""
      },
      rules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    submitForm() {
      
      this.$refs.login.validate(valid => {
        if (valid) {
          const _this = this
          this.$axios.post('/login',this.param).then(res=>{
            
            console.log(res.data);
            
            const jwt = res.headers['authorization']
            const userinfo = res.data.data
            // 如果出现错误
            if(res.data.code === 400){
              this.$message.error(res.data.message || "登录失败");
              return;
            }
            // elementui功能
            this.$message.success("登录成功");
              
            // 使用Vuex mutations正确设置用户信息
            this.$store.commit('SET_USERINFO', res.data.data[0])
            this.$store.commit('SET_USERNAME', this.param.name)
            
            console.log(this.$store.state.userInfo);  
           
            this.$store.state.username = res.data.data[0].name
            // 跳转到首页
            this.$router.push("/");
            console.log( "state。username=" +  this.$store.state.username);
          })
        } else {
          this.$message.error("请输入账号和密码");
          return false;
        }
      });
    },
    toRegister(){
      this.$router.push("/register");
    }
  },
  // created(){
  //   this.$axios.get('/cs?username=asd').then(res=>{
  //     console.log(res);
      
  //   })
  // }
};
</script>

<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url(../assets/img/login-bg.jpg);
  background-size: 100%;
}
.ms-title {
  width: 100%;
  padding: 20px 0;
  text-align: center;
  font-size: 20px;
  color: #fff;
  border-bottom: 1px solid #ddd;
}
.ms-login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 350px;
  margin: -190px 0 0 -175px;
  border-radius: 5px;
  background: rgba(255, 255, 255, 0.3);
  overflow: hidden;
}
.ms-content {
  padding: 30px 30px;
}
.login-btn {
  text-align: center;
}
.login-btn button {
  width: 100%;
  height: 36px;
  margin-bottom: 10px;
}
.login-tips {
  font-size: 12px;
  line-height: 30px;
  color: #fff;
}
</style>