<template>
  <div>
    <nav-menu :active-index="'5'"/>
    <div v-loading="pageLoading" class="app-container">
      <br>
      <br>
      <!--<el-card center>-->
      <el-row :gutter="10">
        <el-col :span="16" :offset="4">
          <div style="text-align: center;">

            <el-row :gutter="10">
              <el-col :span="20">
                <!--<video class="video" src="http://www.w3school.com.cn/i/movie.ogg" controlslist="nodownload" controls="controls" preload="auto"/>-->
                <!--<video class="video" src="@/assets/video/test.mp4" controls="controls" preload="auto"/>-->
                <video :src="videoItem.address" class="video" controls="controls" preload="auto"/>
              <!--<video style="width:100%; height:100%; object-fit: fill" src="@/assets/video/test.mp4" controls="controls" preload="auto"/>-->
              </el-col>
              <el-col :span="4">
                <ul class="list-group">
                  <li v-for="(item,index) in videoList" :key="index" class="list-group-item" @click="showVideo(item.id)">{{ item.name }}</li>
                </ul>
              </el-col>
            </el-row>

          <!--class="el-menu-demo" mode="horizontal" @select="handleSelect"-->
          </div>

        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="13" :offset="4">
          <span style="font-size: 30px;font-weight: bold"><span style="color:#606266">视频名称:</span> {{ videoItem.name }}</span>
          <el-rate
            v-model="value"
            style="float: right"
            disabled
            show-score
            text-color="#ff9900"
            score-template="{value}"/>
        </el-col>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="16" :offset="4">

          <hr style="border: solid 0.5px #EBEEF5">
          <el-row :gutter="20">
            <el-col :span="2">
              <img :src="videoUserItem.image" class="headImage" >
            </el-col>
            <el-col :span="22">
              <el-row :gutter="10">
                <span><span style="color:#909399">作者昵称:</span>    {{ videoUserItem.name }}</span>
              </el-row>
              <br>
              <el-row :gutter="10">
                <span><span style="color:#909399">自我介绍: </span> {{ videoUserItem.introduce }}</span>
              </el-row>
            </el-col>
            <el-row :gutter="10"/>
            <br>
            <el-row :gutter="10">
              <el-col :span="16" :offset="2">
                <el-tag>标签一</el-tag>
                <el-tag type="success">标签二</el-tag>
                <el-tag type="info">标签三</el-tag>
                <el-tag type="warning">标签四</el-tag>
                <el-tag type="danger">标签五</el-tag>
              </el-col>
            </el-row>

          </el-row>
        </el-col>
      </el-row>
      <!-- --------------------------------------以下内容是用户信息以及评论分页--------------------------------------- -->
      <!--<el-row :gutter="10">-->
      <!--<el-col :span="16" :offset="4">-->

      <!--<hr style="border: solid 0.5px #EBEEF5">-->
      <!--<el-row :gutter="20">-->
      <!--<el-col :span="2">-->
      <!--<img class="headImage" src="@/assets/videoImage/hamburger.png">-->
      <!--</el-col>-->
      <!--<el-col :span="22">-->
      <!--<el-row :gutter="10">-->
      <!--<span>昵称</span>-->
      <!--</el-row>-->
      <!--<br>-->
      <!--<el-row :gutter="10">-->
      <!--<span>自我介绍哦</span>-->
      <!--</el-row>-->
      <!--</el-col>-->
      <!--</el-row>-->

      <!--</el-col>-->
      <!--</el-row>-->
      <el-row :gutter="10">
        <el-col :span="16" :offset="4">

          <hr style="border: solid 0.5px #EBEEF5">
          <br>
          <br>
          <br>
          <span style="font-size: 20px;font-weight: bold;color: #606266;">{{ videoCommentListQuery.total }}评论</span>
          <mypagination :appearance="2" type="videoComment" base-type="video" style="float:right"/>
        </el-col>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="16" :offset="4">
          <hr style="border: solid 0.5px #EBEEF5">
        </el-col>
      </el-row>

      <!-- --------------------------------------以下内容是评论信息--------------------------------------- -->
      <el-row :gutter="20">
        <el-col :span="16" :offset="4">
          <el-row :gutter="10">
            <el-col :span="2">
              <img :src="loginAvatar" class="headImage">
            </el-col>
            <el-col :span="20">
              <el-form ref="commentForm" :model="commentForm" :rules="rules">
                <!-- <div v-model="commentForm" contenteditable="true" class="textArea" placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。"/> -->
                <el-form-item prop="content">
                  <text-area v-model="commentForm.content" placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。"/>
                  <!-- <el-input v-model="commentForm.content" class="textArea" type="textarea" placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。"/> -->
                </el-form-item>
              </el-form>
            </el-col>
            <el-col :span="2">
              <el-button style="height: 65px" type="primary" @click="handleCommentSubmit">发表评论</el-button>
              <br>
              <br>
            </el-col>
          </el-row>
        </el-col>
      </el-row>

      <el-row v-for="(item, index) in videoCommentList" :key="index" :gutter="10">
        <el-col :span="16" :offset="4">

          <hr style="border: solid 0.5px #EBEEF5">
          <el-row :gutter="20">
            <el-col :span="2">
              <img :src="item.image" class="headImage">
            </el-col>
            <el-col :span="22">
              <el-row :gutter="10">
                <span><span style="color:#606266">昵称:</span>{{ item.userName }}</span>
              </el-row>
              <br>
              <el-row :gutter="10">
                <span><span style="color:#606266">内容:</span>{{ item.content }}</span>
              </el-row>
            </el-col>
          </el-row>

        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="16" :offset="4">
          <div class="block">
            <mypagination type="videoComment" base-type="video"/>
          </div>

        </el-col>
      </el-row>

    </div>
  </div>
</template>

<script>
import Mypagination from '@/components/Mypagination/index'
import navMenu from '@/views/video/components/navMenu'
import TextArea from '@/components/TextArea/index'
import Data from '@/views/video/mixin/Data'
// import store from '@/store'
export default {
  name: 'VideoShow',
  components: { navMenu, Mypagination, TextArea },
  mixins: [Data],
  data() {
    return {
      value: 3.7,
      pageLoading: false,
      comment: '',
      commentForm: {
        videoId: this.$route.params.id,
        userId: sessionStorage.getItem('userId'),
        content: ''
      },
      rules: {
        content: [{ required: true, trigger: 'blur', message: '请输入评论内容' }]
      }
      // private String id;
      // private String videoId;
      // private String userId;
      // private String content;
      // private Date date;
      // private String userName;//用户
      // private String videoName;// 视频
      // private String image;//用户
      // private String introduce;//用户

    }
  },
  computed: {
    loginAvatar: function() {
      return sessionStorage.getItem('avatar')
    }
  },
  async created() {
    this.pageLoading = true
    this.getVideoList()
    await this.getVideoItem({ uuid: this.$route.params.id }).then(() => {
      // 查询作者信息
      this.getUserByVideoId({ videoId: this.videoItem.id })
      // 查询评论信息
      this['setVideoCommentListQuery']({ usingKey: true, k: 'page', v: 1 })
      this['setVideoCommentListQuery']({ usingKey: true, k: 'limit', v: 10 })
      this['setVideoCommentListQuery']({ usingKey: true, k: 'videoId', v: this.videoItem.id })
      this.getVideoCommentList()
      this.pageLoading = false
    })
  },
  methods: {
    async showVideo(id) {
      this.$router.push({ path: `/video-show/${id}` })
      await this.getVideoItem({ uuid: this.$route.params.id }).then(() => {
      // 查询作者信息
        this.getUserByVideoId({ videoId: this.videoItem.id })
        // 查询评论信息
        this['setVideoCommentListQuery']({ usingKey: true, k: 'page', v: 1 })
        this['setVideoCommentListQuery']({ usingKey: true, k: 'limit', v: 10 })
        this['setVideoCommentListQuery']({ usingKey: true, k: 'videoId', v: this.videoItem.id })
        this.getVideoCommentList()
        this.pageLoading = false
      })
    },
    handleCommentSubmit() {
      this.$refs['commentForm'].validate(valid => {
        if (valid) {
          console.log('content', this.commentForm)
          this.setCommentForm({ pF: JSON.parse(JSON.stringify(this.commentForm)) })
          this.createComment().then(() => {
            this.getVideoCommentList()
            this.$refs['commentForm'].resetFields()
          })
        }
      })
    }
  }
}
</script>

<style scoped>

.headImage{
  border-radius:50%;
  width: 50px;
}
.textArea{
  font-size: 12px;
  display: inline-block;
  box-sizing: border-box;
  background-color: #f4f5f7;
  border: 1px solid #e5e9ef;
  overflow: auto;
  border-radius: 4px;
  color: #555;
  width: 100%!important;
  height: 65px;
  transition: 0s;
  padding: 5px 10px;
  line-height: normal;
}
div:empty:before{
  content: attr(placeholder);
  color:#bbb;
}
div:focus:before{
  content:none;
}
.video{
  background-color: #000000;
  max-width: 98%;
  max-height: 600px;
  min-width: 95%;
  min-height: 550px
}
.list-group {
  padding-left: 0;
  /*margin-bottom: 20px;*/
  margin-top: 0px;
}
.list-group-item {
  position: relative;
  display: block;
  padding: 10px 15px;
  margin-bottom: -1px;
  background-color: #fff;
  border: solid 0.5px #EBEEF5;
}
.list-group-item:first-child {
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
}
.list-group-item:last-child {
  margin-bottom: 0;
  border-bottom-right-radius: 4px;
  border-bottom-left-radius: 4px;
}
</style>
