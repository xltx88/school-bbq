<template>
    <div id="app">
      <router-view v-if="isRouterAlive"></router-view>
      <div class="footer">
      </div>
    </div>
</template>

<script>
export default {
  name: 'App',
  provide() {
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      isRouterAlive: true//用来刷新当前页面而不出现空白页
    }
  },
  methods: {
    reload() {
      this.isRouterAlive = false
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    }
  },
  components:{
  }
}
</script>

<style>
@import "./assets/css/main.css";
@import "./assets/css/color-dark.css";
#app {
  margin: 0 auto;
}
.footer {
  text-align: center;
  font-size: 12px;
  color: #666;;
  padding: 0 0 15px 0;
}
</style>

