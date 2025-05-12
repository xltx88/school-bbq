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
            <div >
              <el-card class="box-card" shadow="hover" style="min-height: 600px">
                <span class="span">我的提问</span><br/>
                <div class="que">
                  <ul v-for="(item,index) in questions" :key="index">
                    <li>{{ item.title }}  </li><a href="javascript:;"> 删除</a>
                  </ul>
                </div>
              </el-card>
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
      this.$axios.get('/profile/myquestion?username=' + this.$store.state.username).then(res=>{
            console.log(res);
            this.questions = res.data
      })
    }
    // page() {
    //   const _this = this
    //   this.$axios.get('/profile/myquestion/?page=').then((res) => {
    //     _this.questions = res.data.data.questionDtos
    //     _this.questions.search=''
    //     _this.currentPage = res.data.data.currentPage
    //     _this.total = res.data.data.totalPages*_this.pageSize
    //   })
    // },
    // handleEdit(questionId) {
    //   this.$router.push("/publish/"+questionId);
    // },
    // handleDelete(questionId) {
    //   this.$axios.delete('/question/' + questionId).then((res) => {
    //     if(res.data.code==200){
    //       this.$message.success("删除成功")
    //       this.reload()
    //     }
    //     else{
    //       this.$message.error("删除问题失败")
    //     }
    //   })
    // }
  },
  created() {
    this.page()
  },
}
</script>

<style scoped>
    .span{
      color: rgb(168, 168, 168);
      font-size: 15px;
      font-weight: bold;
      width: 100%;
      padding-bottom: 10px;
      
    }
</style>