<template>
  <div>
    <el-container>
      <el-header :class="isVisible?'slideDown':'slideUp'"><Header></Header></el-header>

      <el-main>
        <el-row :gutter="10">
          <el-col :xs="24" :sm="24" :md="16" :lg="16" :xl="16">
            <!--左边的分布-->
            <div>
              <el-card shadow="never" >
                <h3 style="font-family:Hiragino Sans GB"><i class="el-icon-reading"></i> 芜湖~</h3>
                <el-tabs v-model="sort" @tab-click="handleClick">
                  <el-tab-pane label="最新" name="new"></el-tab-pane>
                  <!-- <el-tab-pane label="月榜" name="hot30"></el-tab-pane>
                  <el-tab-pane label="周榜" name="hot7"></el-tab-pane>
                  <el-tab-pane label="最热" name="hot"></el-tab-pane>
                  <el-tab-pane label="消灭零回复" name="no"></el-tab-pane> -->
                </el-tabs>
              </el-card>
              <div class="line"></div>
              


              <!--问题列表-->
              <div v-for="(question, index) in questions" :key="index">
                <el-card shadow="hover" style="display: flex" >
                  <!--同层次div水平分布，然后在居中对齐align-items: center-->
                  <div style="display: flex; ">
                    <div class="block">
                      <!-- <el-link >
                        <el-avatar shape="square" :size="55" :src="question.user.avatarUrl"></el-avatar>
                      </el-link> -->
                    </div>
                    <div style="margin-left: 10px">
                      <h4>
                        <router-link :to="{name: 'Question', params: {questionId: question.id}}">
                          <el-link style="font-size: 16px">{{question.title}}</el-link>
                        </router-link>
                      </h4>
                        <!--<p>{{question.description}}</p>-->
                      <div style="padding: 7px 0px;color: #909399;">
                        <!--传入这个评论创建者的id-->
                        <i class="iconfont el-icon-view"></i><span style="font-size: 15px"> {{question.view_count}} </span>
                        <i class="iconfont el-icon-chat-dot-square"></i><span style="font-size: 15px"> {{question.comment_count}} </span>
                        <i class="iconfont el-icon-time"></i><span style="font-size: 15px"> {{question.gmt_create}}</span>
                      </div>
                    </div>
                  </div>
                  </el-card>
              </div>


              
           


              <div v-show="questions.length===0">
                <el-card shadow="hover" >
                  <div style="display: flex; justify-content: center;">
                    <span class="page-text">
                    暂无问题
                  </span>
                  </div>
                </el-card>
              </div>
            </div>
            <!--页码-->
            <el-pagination class="mpage"
                           background
                           layout="prev, pager, next"
                           :total="total"
                           :current-page="currentPage"
                           :page-size="pageSize"
                           @current-change="page"
                           >
            </el-pagination>
          </el-col>
          <!--右边分布-->
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-card class="box-card" shadow="never">
              <div class="card-header">
                <span>热门话题</span>
              </div>
              <!--标签内容-->
              <div class="tag-group">
                <el-button
                    v-for="hotTag in hots"
                    :key="hotTag"
                    style="margin:5px"
                    type="primary"
                    plain
                    size="small"
                    @click="hottags(hotTag)">
                  {{ hotTag }}
                </el-button>
              </div>
            </el-card>
            <el-card class="box-card" shadow="never">
              <div class="card-header">
                <span>作者微信</span>
              </div>
              <div  style="display: flex; justify-content: center;">
                <el-image class="avatar"
                          :src="require('../assets/img/wechat.jpg')"></el-image>
              </div>
              <p class="desc">我的快递，想寄就寄！</p>
              <div class="social">
                <div style=" padding: 8px;float: left;margin-top: 0px" class="desc">社交:</div>
                <li style="padding: 8px; list-style:none;text-align: center;">
                  <a target="_blank"><img class="link-img" src="../assets/img/QQ.png"  alt=""></a>
                  <a style="margin-left: 5%;" href="https://github.com/KyrieWang233" target="_blank"><img class="link-img" src="../assets/img/github.png" alt=""></a>
                  <a style="margin-left: 5%; margin-right: 10%" target="_blank"><img class="link-img" src="../assets/img/gitee.png"  alt=""></a>
                </li>
              </div>
            </el-card>
          </el-col>
        </el-row>

      </el-main>
    </el-container>
  </div>
</template>

<script>
import Header from "@/components/Header";
export default {
  name: "Index.vue",
  components: {Header},
  data(){
    return{
      
      message:false,
      isVisible: true, //控制web端导航的隐藏和显示
      questions: [],
      total:0,
      currentPage: 1,
      pageSize: 5,
      search:'',
      sort: 'new',//控制开头选项
      hots: {},
      tag: '',//用来搜索tag
    }
  },
  methods: {
    handleClick(tab, event) {
      this.currentPage = 1
      this.page(1)
    },
    page(pageNum) {
      if (pageNum) {
        this.currentPage = pageNum
      }
      
      //包含请求头，这样登录首页直接根据token登录
      const _this = this
      // 构建分页参数
      const params = {
        page: this.currentPage,
        size: this.pageSize,
        search: this.search,
        tag: this.tag,
        sort: this.sort
      }
      
      // 发送请求获取分页数据
       this.$axios.get('/', { params }).then((res) => {
         console.log('获取数据:', res.data);
         
         // 处理后端返回的分页数据结构
         if (res.data && res.data.data && res.data.data.questionDtos) {
           _this.questions = res.data.data.questionDtos
           _this.total = res.data.data.totalCount || 0
           _this.currentPage = res.data.data.currentPage || 1
         } else {
           // 兼容旧的数据格式
           _this.questions = res.data || []
           _this.total = _this.questions.length
         }
         
         console.log('当前页问题:', _this.questions);
         console.log('总数:', _this.total, '当前页:', _this.currentPage);
        
        // 确保时间格式化正确处理
        for (const question of _this.questions) {
          if (question.gmt_create) {
            // 兼容字符串和数字类型的时间戳
            const timestamp = Number(question.gmt_create)
            if (!isNaN(timestamp) && timestamp > 0) {
              question.gmt_create = _this.$moment(timestamp).format('YYYY-MM-DD')
            } else {
              question.gmt_create = '未知时间'
            }
          } else {
            question.gmt_create = '未知时间'
          }
        }
      }).catch(error => {
        console.error('获取数据失败:', error)
        _this.questions = []
        _this.total = 0
      })
      
      // 获取热门标签
      this.$axios.get("/hottags").then((res) => {
        if (res.data && res.data.data) {
          _this.hots = res.data.data
        } else {
          _this.hots = res.data || {}
        }
      }).catch(error => {
        console.error('获取热门标签失败:', error)
        _this.hots = {}
      })
    },
    hottags(tag){
      this.$router.push({path:'/',query:{tag}})
      this.$router.go(0);
    },
    sousuo(){
      console.log('执行搜索:', this.search);
      this.currentPage = 1; // 搜索时重置到第一页
      this.page(1); // 直接调用分页方法，后端会处理搜索
    }
  },
  created() {
    this.page()
    if(this.$route.query.search!=null&&this.$route.query.search!=undefined){
      this.search = this.$route.query.search
       this.sousuo()
    }
    if(this.$route.query.tag!=null&&this.$route.query.tag!=undefined){
      this.tag = this.$route.query.tag
    }
   
    
  },
  mounted() { 
    var that = this;
    var offset = 300;
    var after = 0;
    window.addEventListener("scroll", function () {
      let scrollTop = document.documentElement.scrollTop; //当前的的位置
      let scrollHeight = document.documentElement.scrollHeight; //最高的位置
      if (scrollTop > offset) {
        that.isCdTopVisible = true;
      } else {
        that.isCdTopVisible = false;
      }
      if (scrollTop > after) {
        that.isVisible = false;
      } else {
        that.isVisible = true;
      }
      after = scrollTop;
    });
  },
}
</script>

<style scoped>

.el-image{
  border-radius: 2px;
}
.el-card {
  border: 1px #EBEEF5;
  background-color: #FFF;
  color: #303133;
  border-radius: 1px;
  padding: 10px 20px;
}
::v-deep .el-card__body {
  padding: 0px;
}
.tag-group{
  margin: 5px 0px 0px 0px;
  text-align: left;
}
.page-text{
  line-height: 60px;
  width: 50%;
  color: #909399;
  text-align: center;
  justify-content: center;
}
.box-card{
  margin-bottom: 10px;
}
.avatar{
  position: relative;
  margin: 30px auto 15px;
  width: 150px;
  height: 150px;
  border-radius: 50%;
  z-index: 9
}
.card-header{
  padding: 10px 0px;

}
.desc{
  font-size: 15px;
  color: #878d99;
  line-height: 30px;
  font-weight: 100;
  text-align: center;
}
.social{
  padding: 0 20px;
  margin-top: 15px;
}
.link-img{
  display: inline-block;
  width: 44px;
  height: 44px;
  margin: auto;
}

</style>