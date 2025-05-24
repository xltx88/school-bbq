<template>
  <div>
    <el-container>
      <el-header><Header></Header></el-header>
      <el-main>
        <el-row>
          <el-col :xs="24" :sm="5" :md="5" :lg="5" :xl="5">
            <Profilebar></Profilebar>
          </el-col>
          <el-col :xs="24" :sm="19" :md="19" :lg="19" :xl="19">
            <div>
              <el-card shadow="never">
                <span class="span">我的提问</span>
              </el-card>
              <div class="line"></div>
              
              <!--问题列表-->
              <div v-for="(item, index) in questions" :key="index">
                <el-card shadow="hover" style="display: flex">
                  <!--同层次div水平分布，然后在居中对齐align-items: center-->
                  <div style="display: flex;">
                    <div class="block">
                      <!-- 可以添加头像 -->
                    </div>
                    <div style="margin-left: 10px; width: 800px">
                      <div style="display: flex; justify-content: space-between; align-items: center;">
                        <h4 style="margin: 0;">
                          <router-link :to="{name: 'Question', params: {questionId: item.id}}">
                            <el-link style="font-size: 16px">{{item.title}}</el-link>
                          </router-link>
                        </h4>
                        <div style="display: flex; align-items: center; margin-left: 10px;">
                          <el-button type="danger" size="mini" icon="el-icon-delete" circle @click="handleDelete(item.id)"></el-button>
                        </div>
                      </div>
                      <div style="padding: 7px 0px;color: #909399;">
                        <i class="iconfont el-icon-view" v-if="item.view_count"></i><span style="font-size: 15px" v-if="item.view_count"> {{item.view_count}} </span>
                        <i class="iconfont el-icon-chat-dot-square" v-if="item.comment_count"></i><span style="font-size: 15px" v-if="item.comment_count"> {{item.comment_count}} </span>
                        <i class="iconfont el-icon-time"></i><span style="font-size: 15px"> {{item.gmt_create || '未知时间'}} </span>
                      </div>
                    </div>
                  </div>
                </el-card>
              </div>
              
              <div v-show="questions.length===0">
                <el-card shadow="hover">
                  <div style="display: flex; justify-content: center;">
                    <span class="page-text">
                      暂无问题
                    </span>
                  </div>
                </el-card>
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
            </div>
          </el-col>
        </el-row>
      </el-main>
    </el-container>

  </div>
</template>

<script>
import Header from "@/components/Header";
import Profilebar from "../../components/Profilebar";
import router from "@/router";
export default {
  name: "Myquestion.vue",
  components: {Profilebar, Header},
  inject:['reload'],
  data() {
    return {
      questions:[],
      total:0,
      currentPage: 1,
      pageSize: 5,
      search: ''
    }
  },
  methods: {  
    page(){
      const _this = this
      this.$axios.get('/profile/myquestion?username=' + this.$store.state.username).then(res=>{
        console.log(res);
        this.questions = res.data
        
        // 格式化时间
        for (const question of _this.questions) {
          if (question.gmt_create) {
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
        
        // 设置分页信息
        if(res.data && res.data.data) {
          _this.currentPage = res.data.data.currentPage || 1
          _this.total = (res.data.data.totalPages || 1) * _this.pageSize
        }
      })
    },
    handleEdit(questionId) {
      this.$router.push("/publish/"+questionId);
    },
    handleDelete(questionId) {
      this.$confirm('确认删除这个问题吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.delete('/question/' + questionId).then((res) => {
          // 无论返回什么状态码，只要请求成功就认为删除成功
          this.$message.success("删除成功")
          // 从当前列表中移除已删除的问题
          this.questions = this.questions.filter(q => q.id !== questionId)
          // 然后再重新加载数据
          setTimeout(() => {
            this.page()
          }, 300)
        }).catch((error) => {
          console.error(error)
          this.$message.error("删除问题失败")
        })
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    }
  },
  created() {
    this.page()
  },
}
</script>

<style scoped>
.span {
  color: rgb(168, 168, 168);
  font-size: 15px;
  font-weight: bold;
  width: 100%;
  padding-bottom: 10px;
}

.el-card {
  border: 1px #EBEEF5;
  background-color: #FFF;
  color: #303133;
  border-radius: 1px;
  padding: 10px 20px;
  margin-bottom: 10px;
}

::v-deep .el-card__body {
  padding: 0px;
}

.line {
  height: 1px;
  background-color: #e0e0e0;
  margin: 5px 0;
}

.page-text {
  line-height: 60px;
  width: 50%;
  color: #909399;
  text-align: center;
  justify-content: center;
}

.mpage {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}

.box-card {
  margin-bottom: 10px;
}

.block {
  display: flex;
  align-items: center;
}
</style>