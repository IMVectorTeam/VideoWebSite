<template>
  <div class="app-container">
    <el-row class="el-title">
      <i class="el-icon-d-arrow-right"/>
      <span>基本信息</span>
    </el-row>
    <el-form ref="form" style="padding-left: 25px" inline>
      <!--判断是否为空，如果为空不加载-->
      <el-row type="flex" justify="space-around">
        <el-col :span="8">
          <el-form-item label="视频名称：">
            <span>{{ convertNullToBlack(videoItem).name }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="视频类别：">
            <span>{{ convertNullToBlack(videoItem).categoryContent }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="上传用户：">
            <span>{{ convertNullToBlack(videoItem).userName }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="上传时间">
            <span style="height:30px;">{{ textDateFormat(convertNullToBlack(videoItem).date) }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="视频封面：">
            <img :src="videoItem.image" alt="用户头像" style="width:100px;border-radius:10%">
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <el-row class="el-title">
      <i class="el-icon-d-arrow-right"/>
      <span>视频内容</span>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="16" :offset="4">
        <video :src="videoItem.address" class="video" controls="controls" preload="auto"/>
      </el-col>
    </el-row>
    <el-row class="el-title">
      <i class="el-icon-d-arrow-right"/>
      <span>视频评论</span>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="20" :offset="2">
        <el-row :gutter="10">
          <el-col :span="16" :offset="4">

            <hr style="border: solid 0.5px #EBEEF5">
            <br>
            <br>
            <br>
            <span style="font-size: 20px;font-weight: bold;color: #606266;">{{ videoCommentListQuery.total }}评论</span>
            <mypagination :appearance="2" type="videoComment" base-type="video" style="float:right"/>
            <!-- <el-pagination
              :page-size="20"
              :pager-count="11"
              :total="1000"
              style="float: right"
              layout="prev, pager, next"/> -->
          </el-col>
        </el-row>

        <el-row :gutter="10">
          <el-col :span="16" :offset="4">
            <hr style="border: solid 0.5px #EBEEF5">
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
              <span>{{ item.userName }}</span>
            </el-row>
            <br>
            <el-row :gutter="10">
              <!--private String id;-->
              <!--private String videoId ;-->
              <!--private String userId;-->
              <!--private String content;-->
              <!--private Date date;-->
              <span>{{ item.content }}</span>
            </el-row>
          </el-col>
        </el-row>

      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="21">
        <mypagination type="videoComment" base-type="video" style="float:right"/>

      </el-col>
      <!-- <el-col :span="16" :offset="4">
        <div class="block">
          <el-pagination
            :current-page= "1"
            :page-sizes="[5, 10, 15, 20]"
            :page-size="100"
            :total="400"
            background
            layout="total, sizes, prev, pager, next, jumper"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"/>
        </div>
      </el-col> -->
    </el-row>

  </div>
</template>

<script>
import Data from '@/views/video/mixin/Data'
import Mypagination from '@/components/Mypagination/index'
export default {
  name: 'Info',
  components: { Mypagination },
  mixins: [Data],
  data() {
    return {
      detailsModal: false,
      detailsItem: {}
    }
  },
  async created() {
    this.pageLoading = true
    await this.getVideoItem({ uuid: this.$route.params.id }).then(() => {
      // 查询作者信息
    //   this.getUserByVideoId({ videoId: this.videoItem.id })
      // console.log(this.videoItem)
      // 查询评论信息
      this['setVideoCommentListQuery']({ usingKey: true, k: 'page', v: 1 })
      this['setVideoCommentListQuery']({ usingKey: true, k: 'limit', v: 10 })
      this['setVideoCommentListQuery']({ usingKey: true, k: 'videoId', v: this.videoItem.id })
      this.getVideoCommentList()
      this.pageLoading = false
    })
  },
  methods: {
    handleDeleteComment(row, index) {
      this.deleteComment(row.id).then(() => {
        console.log(index)
        this.commentList.splice(index, 1)
      })
    },
    handleDetails(row) {
      this.detailsModal = true
      this.detailsItem = JSON.parse(JSON.stringify(row))
    },
    handleDeleteVideo(row, index) {
      this.deleteVideo(row.id).then(() => {
        this.videoList.splice(index, 1)
      })
    },
    handleSizeChange(limit) { // pageSize 改变时会触发	每页条数
      console.log(limit)
    },
    handleCurrentChange(page) { // currentPage 改变时会触发	当前页
      console.log(page)
    }
  }
}
</script>

<style scoped>

    .headImage{
    border-radius:50%;
    width: 50px;
    }
  .form-boder{
    border: #F2F6FC 0.5px solid;
    border-collapse: collapse;
  }
  .el-title {
    margin: 10px 0px 10px 0px;
    color: #606266;
    background-color: #F5F7FA;
    line-height: 40px;
    font-size: 16px;
    font-weight: 600;
  }

  .el-form-item {
    color: #666666;
    margin-bottom: 20px;
  }
</style>
