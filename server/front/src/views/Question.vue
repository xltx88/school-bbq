<template>
  <div>
    <el-container>
      <!--导航栏-->
      <el-header>
        <Header></Header>
      </el-header>
      <el-main>
        <el-row :gutter="10">
          <!--左边分布-->
          <el-col :xs="24" :sm="24" :md="16" :lg="16" :xl="16">
            <el-card shadow="never">
              <h3 class="question-title"><span>{{ question.title }}</span></h3>
              <span class="text-desc">
                作者：<span>{{ question.username }}</span> 
                <!-- 发布时间：<span>{{question.creat_time}}</span> | -->
                <!-- 阅读数： <span>{{viewc}}</span> -->
              </span>
              <router-link style="margin-left: 10px" :to="{ name: 'Publish', params: { questionId: question.id } }"
                v-if="ownQuestion">
                <el-link icon="el-icon-edit">
                  编辑
                </el-link>
              </router-link>

              <el-divider></el-divider>
              <div style="min-height: 400px" class="markdown-body" v-html="question.content"></div>
              <el-divider></el-divider>

              <!--标签内容-->
              <div class="tag-group">
                <el-tag v-for="Tag in question.tag" :key="Tag" effect="plain">
                  {{ Tag }}
                </el-tag>
              </div>
            </el-card>

            <!--评论模块-->
            <el-card class="comment-section" style="margin-top: 20px;" shadow="never">
              <div slot="header" class="clearfix">
                <span>评论区</span>
              </div>
              
              <!-- 评论输入框 -->
              <div class="my-reply">
                <div class="header-img">
                  <el-avatar :size="40" :src="myHeader"></el-avatar>
                </div>
                <div class="reply-info">
                  <div
                    id="replyInput"
                    contenteditable="true"
                    class="reply-input"
                    placeholder="请输入评论内容..."
                    @focus="showReplyBtn"
                    @input="onDivInput"
                  ></div>
                  <div class="reply-btn-box" v-show="btnShow">
                    <el-button class="reply-btn" size="small" type="primary" @click="sendComment">发表评论</el-button>
                  </div>
                </div>
              </div>
              
              <!-- 评论列表 -->
              <div v-if="comments.length > 0">
                <div v-for="(comment, index) in comments" :key="index" class="author-title">
                  <div class="header-img">
                    <!-- comment.avatarUrl ? comment.avatarUrl : myHeader -->
                    
                    <el-avatar :size="40" :src="comment.avatarUrl ? comment.avatarUrl : myHeader"></el-avatar>
                  </div>
                  <div class="author-info">
                    
                    <span class="author-name">{{ comment.user ? comment.user.name : (comment.receiver_name ? comment.receiver_name : '匿名用户') }}</span>
                    <span class="author-time">{{ comment.gmt_create }}</span>
                    <!-- 添加删除按钮，仅当评论是当前用户发表的才显示 -->
                    <el-button 
                      v-if="comment.comment_creator === myId || (comment.user && comment.user.id === myId)" 
                      type="text" 
                      size="mini" 
                      icon="el-icon-delete" 
                      @click="deleteComment(comment.id, index)">删除</el-button>
                  </div>
                  <div class="talk-box">
                    <p class="reply">{{ comment.content }}</p>
                  </div>
                </div>
              </div>
              <div v-else style="text-align: center; padding: 20px;">
                <span style="color: #909399;">暂无评论，快来发表第一条评论吧！</span>
              </div>
            </el-card>

          </el-col>

          <!--右边分布-->
          <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
            <el-card class="box-card" shadow="hover">
              <div class="card-header">
                <span>相关问题</span>
              </div>
              <div v-for="related in relatedQuestions">
                <router-link :to="{ name: 'Question', params: { questionId: related.id } }">
                  <el-link :underline="false">
                    <span class="related-text">{{ related.title }}</span>
                  </el-link>
                </router-link>

                <i style="padding: 5px 0px;color: #909399;margin-left: 10px" class="el-icon-chat-dot-square">
                  <span style="font-size: 13px"> {{ related.comment_count }} </span>
                </i>
              </div>
              <div v-show="relatedQuestions.length === 0">
                <div style="display: flex; justify-content: center;">
                  <span class="page-text">
                    暂无问题
                  </span>
                </div>
              </div>

            </el-card>
            <el-card class="box-card" shadow="never">
              <div class="card-header">
                <span>作者微信</span>
              </div>
              <div style="display: flex; justify-content: center;">
                <el-image class="avatar" :src="require('../assets/img/wechat.jpg')"></el-image>
              </div>
              <p class="desc">我的快递，想寄就寄！</p>
              <div class="social">
                <div style=" padding: 8px;float: left;margin-top: 0px" class="desc">社交:</div>
                <li style="padding: 8px; list-style:none;text-align: center;">
                  <a target="_blank"><img class="link-img" src="../assets/img/QQ.png" alt=""></a>
                 
                  <a style="margin-left: 5%; margin-right: 10%" target="_blank"><img class="link-img"
                      src="../assets/img/gitee.png" alt=""></a>
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
//import 'github-markdown-css/github-markdown.css'; // 然后添加样式markdown-body
import Header from "@/components/Header";
const clickoutside = {
  // 初始化指令
  bind(el, binding, vnode) {
    function documentHandler(e) {
      // 这里判断点击的元素是否是本身，是本身，则返回
      if (el.contains(e.target)) {
        return false;
      }
      // 判断指令中是否绑定了函数
      if (binding.expression) {
        // 如果绑定了函数 则调用那个函数，此处binding.value就是handleClose方法
        binding.value(e);
      }
    }
    // 给当前元素绑定个私有变量，方便在unbind中可以解除事件监听
    el.vueClickOutside = documentHandler;
    document.addEventListener('click', documentHandler);
  },
  update() { },
  unbind(el, binding) {
    // 解除事件监听
    document.removeEventListener('click', el.vueClickOutside);
    delete el.vueClickOutside;
  },
};
export default {
  name: "Question.vue",
  components: { Header },
  inject: ['reload'],
  data() {
    return {
      viewc: '0',
      question: {
        id: "",
        title: "",
        content: "",
        tag: "",
        username: "",
        creat_time: "",
        view_count: "",
        comment_count: "",
      },
      comments: [],
      relatedQuestions: {},
      ownQuestion: false,
      btnShow: false,
      index: '0',//当前输入框对应的一级评论
      replyComment: '',//h回复的内容
      myName: '匿名用户',
      myHeader: require('../assets/img/default_avatar.png'),
      myId: 19870621,
      receiver_name: '',//用于表示当前输入框中的评论要发送的对象名称
      receiver_id: -1,//评论要发送的对象id
      questionId: '',
    }
  },

  created() {

    this.questionId = this.$route.params.questionId

    const _this = this
    // 获取帖子作者
    console.log(1);
    console.log(this.questionId);
     
    this.$axios.get("/getcomu?id=" + this.questionId).then(qwe => {
      console.log(this.questionId);
     
      
      

      console.log("帖子作者名字：" + qwe.data);
      this.question.username = qwe.data

    })

    this.$axios.get('/allqu?id=' + this.questionId).then(res => {
      this.viewc = res.data[0].view_count

      const question = res.data[0]
      console.log(res);
      this.question.view_count = question.view_count
      _this.question.title = question.title
      // 不要在这里设置comments，避免覆盖评论数组
      _this.question.id = question.id

      _this.question.tag = question.tag.split(",")
      _this.question.comment_count = question.comment_count
      console.log(this.question);

      //做时间格式化处理
      // for (const comment of _this.comments) {
      //   comment.gmt_create = _this.$moment(comment.gmt_create).format('YYYY-MM-DD')
      //   for (const reply of comment.replys) {
      //     reply.gmt_create = _this.$moment(reply.gmt_create).format('YYYY-MM-DD')
      //   }
      // }

      // _this.relatedQuestions = res.data.data.relatedQuestions


      // _this.question.username = question.user.name

      // _this.question.creat_time = _this.$moment(question.gmt_create).format('YYYY-MM-DD')

      // 内容展示
      var MardownIt = require("markdown-it")
      var md = new MardownIt()

      var result = md.render(question.description)
      _this.question.content = result
      if (_this.$store.getters.getUser != null) {
        _this.ownQuestion = (question.creator === _this.$store.getters.getUser.id)
      }
    })


    // 获取评论
    this.$axios.get("/Comment?id=" + this.questionId).then(res => {
      console.log(this.questionId);
      

      if (res.data && Array.isArray(res.data)) {
        // comment_creator
        this.comments = res.data;
        
        // for(var i=0;i<res.data.length;i++){
        //   var j = i
        //   this.$axios.get("/getU?id=" + res.data[i].comment_creator).then(res=>{
        //     this.comments[j].avatarUrl = res.data
        //     console.log(this.comments[j]);
        //   })

        // }
        // 格式化评论时间
        for (const comment of this.comments) {
          if (comment.gmt_create) {
            comment.gmt_create = this.dateStr(comment.gmt_create);
          }
        }
      } else {
        this.comments = [];
      }
      console.log(this.comments);
    })
    // 获取帖子数据



    // 获取当前登录用户信息
    if (this.$store.getters.getUser && this.$store.getters.getUser.name) {
      const userInfo = this.$store.getters.getUser
      this.myHeader = userInfo.avatarUrl || require('../assets/img/default_avatar.png')
      this.myName = userInfo.name
      this.myId = userInfo.id
    }
  },
  // watch: {
  //   '$route.params.questionId':function(val, old) {
  //     if (val !== old) {
  //       this.reload()
  //     }
  //   },
  // },
  // directives: {clickoutside},
  methods: {
    inputFocus() {
      var replyInput = document.getElementById('replyInput');
      replyInput.style.padding = "8px 8px"
      replyInput.style.border = "2px solid blue"
      replyInput.focus()
    },
    showReplyBtn() {
      this.btnShow = true
    },
    hideReplyBtn() {
      this.btnShow = false
      replyInput.style.padding = "10px"
      replyInput.style.border = "none"
    },
    //输入将要发送评论对象的id和name，更新当前输入框评论对象的信息
    showReplyInput(i, name, id) {
      this.comments[this.index].inputShow = false
      this.index = i
      this.comments[i].inputShow = true
      this.receiver_name = name
      this.receiver_id = id
    },
    _inputShow(i) {
      return this.comments[i].inputShow
    },
    // 删除评论方法
    deleteComment(commentId, index) {
      console.log(commentId);
      
      // 确认是否删除
      this.$confirm('确定要删除这条评论吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 调用后端删除接口
        this.$axios.delete(`/comment/${commentId}`, {
          
        }).then(res => {
          // 删除成功，从评论列表中移除
          this.comments.splice(index, 1);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(err => {
          this.$message({
            type: 'error',
            message: err.response?.data || '删除失败，请重试'
          });
        });
      }).catch(() => {
        // 取消删除
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    // 解决用户id传输bug
    changeId(){
      var user ={
          username:this.$store.state.username
      }
      this.axios.get("/cid?username=" + user.username).then(res=>{
        console.log(res.data);
        
      })
    },
    //发表主评论主评论就不用带回复的id了，直接查问题作业就行
    sendComment() {
      if (!this.replyComment) {
        this.$message({
          showClose: true,
          type: 'warning',
          message: '评论不能为空'
        })
      }//判断用户有没有登录
      else if (!this.$store.getters.getUser || !this.$store.getters.getUser.name) {
        this.$message({
          showClose: true,
          type: 'error',
          message: '请先登录账户'
        })
      }
      else {
        let a = {}
        let input = document.getElementById('replyInput')
        let timeNow = new Date().getTime();
        let time = this.dateStr(timeNow);
        let user = {}
        let commentDto = {}//和后端传输需要的数据保持一致
        commentDto.parent_id = this.question.id
        commentDto.content = this.replyComment
        commentDto.type = 1//表示回复的是问题
        commentDto.receiver_name = this.$store.state.username // 添加接收者用户名
        commentDto.comment_creator = this.$store.state.userInfo.id//添加发送方id
        console.log(commentDto);
        
        // 设置用户信息
        user.name = this.myName
        user.avatarUrl = this.myHeader
        a.user = user
        a.content = this.replyComment
        a.gmt_create = time
        a.comment_count = 0
        a.like_count = 0
        a.receiver_name = this.question.username // 确保本地评论对象也有receiver_name字段
        a.comment_creator = this.$store.state.userInfo.id
        
        // 添加到评论列表顶部
        this.comments.unshift(a)
        
        // 清空输入框
        this.replyComment = ''
        input.innerHTML = '';
        this.btnShow = false; // 隐藏发表按钮
        
        // 发送评论到后端
        this.$axios.post('/comment', commentDto, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(res => {
          
          this.$message({
            type: 'success',
            message: '评论发表成功'
            
          });
        }).catch(err => {
          this.$message({
            type: 'error',
            message: '评论发表失败，请重试'
          });
          // 移除刚才添加的评论
          this.comments.shift();
        })
      }

      // this.changeId()
    },
    //发表二级评论带上接受者的id，这样方便通知
    sendCommentReply(i) {
      if (!this.replyComment) {
        this.$message({
          showClose: true,
          type: 'warning',
          message: '评论不能为空'
        })
      }
      else if (!this.$store.getters.getUser || !this.$store.getters.getUser.name) {
        this.$message({
          showClose: true,
          type: 'error',
          message: '请先登录账户'
        })
      }
      else {
        let a = {}
        let timeNow = new Date().getTime();
        let time = this.dateStr(timeNow);
        let user = {}
        let commentDto = {}//和后端传输需要的数据保持一致
        commentDto.parent_id = this.comments[i].id
        commentDto.content = this.replyComment
        commentDto.type = 2//表示回复的是评论
        commentDto.receiver_id = this.receiver_id
        a.user = user
        a.user.name = this.myName
        a.receiver_name = this.$store.state.username
        a.user.avatarUrl = this.myHeader
        a.content = this.replyComment
        a.gmt_create = time
        a.comment_count = 0
        a.like_count = 0
        this.comments[i].replys.push(a)
        this.replyComment = ''
        document.getElementsByClassName("reply-comment-input")[i].innerHTML = ""
        //发送一个post请求
        this.$axios.post('/comment', commentDto, {
          headers: {
            "Authorization": localStorage.getItem("token")
          }
        }).then(res => {
          this.$alert('操作成功', '提示', {
            confirmButtonText: '确定'
          });

        })
      }
    },
    onDivInput: function (e) {
      this.replyComment = e.target.innerHTML;
    },
    dateStr(date) {
      //获取js 时间戳
      var time = new Date().getTime();
      //去掉 js 时间戳后三位，与php 时间戳保持一致
      time = parseInt((time - date) / 1000);
      //存储转换值
      var s;
      if (time < 60 * 10) {//十分钟内
        return '刚刚';
      } else if ((time < 60 * 60) && (time >= 60 * 10)) {
        //超过十分钟少于1小时
        s = Math.floor(time / 60);
        return s + "分钟前";
      } else if ((time < 60 * 60 * 24) && (time >= 60 * 60)) {
        //超过1小时少于24小时
        s = Math.floor(time / 60 / 60);
        return s + "小时前";
      } else if ((time < 60 * 60 * 24 * 30) && (time >= 60 * 60 * 24)) {
        //超过1天少于30天内
        s = Math.floor(time / 60 / 60 / 24);
        return s + "天前";
      } else {
        //超过30天ddd
        var date = new Date(parseInt(date));
        return date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate();
      }
    }
  },
}
</script>

<style scoped>
@import "../assets/css/comment.css";

.el-divider--horizontal {
  height: 0.5px;
}

.related-text {
  display: inline-block;
  max-width: 22em;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  /*超出部分用...代替*/
}

.page-text {
  line-height: 30px;
  width: 50%;
  color: #909399;
  text-align: center;
  justify-content: center;
}

.box-card {
  margin-bottom: 10px;
}

.avatar {
  position: relative;
  margin: 30px auto 15px;
  width: 150px;
  height: 150px;
  border-radius: 50%;
  z-index: 9
}

.card-header {
  padding: 10px 0px;

}

.desc {
  font-size: 15px;
  color: #878d99;
  line-height: 30px;
  font-weight: 100;
  text-align: center;
}

.social {
  padding: 0 20px;
  margin-top: 15px;
}

.link-img {
  display: inline-block;
  width: 44px;
  height: 44px;
  margin: auto;
}
</style>