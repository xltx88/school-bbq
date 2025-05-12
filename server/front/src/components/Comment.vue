// <div v-for="(question, index) in questions" :key="index">
//     <el-card shadow="hover" style="display: flex" >
//       <!--同层次div水平分布，然后在居中对齐align-items: center-->
//       <div style="display: flex; ">
//         <div class="block">
//           <el-link >
//             <el-avatar shape="square" :size="55" :src="question.user.avatarUrl"></el-avatar>
//           </el-link>
//         </div>
//         <div style="margin-left: 10px">
//           <h4>
//             <router-link :to="{name: 'Question', params: {questionId: question.id}}">
//               <el-link style="font-size: 16px">{{question.title}}</el-link>
//             </router-link>
//           </h4>
//             <!--<p>{{question.description}}</p>-->
//           <div style="padding: 7px 0px;color: #909399;">
//             <!--传入这个评论创建者的id-->
//             <i class="iconfont el-icon-view"></i><span style="font-size: 15px"> {{question.view_count}} </span>
//             <i class="iconfont el-icon-chat-dot-square"></i><span style="font-size: 15px"> {{question.comment_count}} </span>
//             <i class="iconfont el-icon-time"></i><span style="font-size: 15px"> {{question.gmt_create}}</span>
//           </div>
//         </div>
//       </div>
//       </el-card>
//   </div>