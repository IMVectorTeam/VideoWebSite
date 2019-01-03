<template>
  <div class="app-container">
    <el-menu :default-active="activeIndex" style="margin-top: -20px;" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">处理中心</el-menu-item>
      <el-submenu index="2">
        <template slot="title">我的工作台</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项1</el-menu-item>

        </el-submenu>
      </el-submenu>
      <el-menu-item index="3" disabled>消息中心</el-menu-item>

      <el-menu-item index="4" style="float: right">
        <img src="@/assets/videoImage/hamburger.png" style="height: 50px;border-radius: 50%;">
      </el-menu-item>
      <el-submenu style="float: right" index="5">
        <template slot="title">昵称</template>
        <el-menu-item index="5-1" @click="handlePersonalCenter">个人中心</el-menu-item>
        <el-menu-item index="5-2" @click="logout">退出登录</el-menu-item>
      </el-submenu>
    </el-menu>
    <div style="height: 160px;width: 100%;background: url('src/assets/videoImage/head.png') no-repeat;background-size: 100% 100%;"/>
    <br>
    <br>
    <el-row :gutter="10">
      <el-col :span="6" :offset="4">
        <el-form ref="form" :model="videoForm" label-width="80px" enctype="multipart/form-data">
          <el-form-item label="视频名字">
            <el-input placeholder="请输入视频名称"/>
          </el-form-item>
          <el-form-item label="视频类别">
            <el-select value="" placeholder="请选择视频类别">
              <el-option />
            </el-select>
          </el-form-item>
          <el-form-item label="图像上传">
            <el-upload
              class="upload-demo"
              drag
              action="/api/VideoWebSite/image/upload"
              multiple>
              <i class="el-icon-upload"/>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
          </el-form-item>

          <el-form-item label="视频上传" prop="Video">
            <!-- action必选参数, 上传的地址 -->
            <el-upload :action="uploadUrl" :show-file-list="false" :on-success="handleVideoSuccess" :before-upload="beforeUploadVideo" :on-progress="uploadVideoProcess" class="avatar-uploader el-upload--text">
              <video v-if="videoForm.Video !='' && videoFlag == false" :src="videoForm.Video" class="avatar" controls="controls">您的浏览器不支持视频播放</video>
              <i v-else-if="videoForm.Video =='' && videoFlag == false" class="el-icon-plus avatar-uploader-icon"/>
              <el-progress v-if="videoFlag == true" :percentage="videoUploadPercent" type="circle" style="margin-top:30px;"/>
            </el-upload>
            <P class="text">请保证视频格式正确，且不超过10M</P>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="14">
        <el-table
          :data="[{ date: '2016-05-02', name: '王小虎', address: '上海市普陀区金沙江路 1518 弄' }, { date: '2016-05-04', name: '王小虎', address: '上海市普陀区金沙江路 1517 弄' }]"
          style="width: 100%">
          <el-table-column
            prop="date"
            label="日期"
            width="180"/>
          <el-table-column
            prop="name"
            label="姓名"
            width="180"/>
          <el-table-column
            prop="address"
            label="地址"/>
        </el-table>
      </el-col>

    </el-row>

  </div>
</template>

<script>
export default {
  name: 'UploadVideo',
  data() {
    return {
      videoForm: {},
      uploadUrl: '/api/VideoWebSite/video/upload',
      videoUploadPercent: 0,
      videoFlag: false
    }
  },
  methods: {
    handleVideoSuccess(res, file) { // 获取上传图片地址
      this.videoFlag = false
      this.videoUploadPercent = 0
      this.videoForm.Video = res
      // console.log(res)
      // if (res.status === 200) {
      //   this.videoForm.videoUploadId = res.data.uploadId
      //   this.videoForm.Video = res.data.uploadUrl
      // } else {
      //   this.$message.error('视频上传失败，请重新上传！')
      // }
    },
    uploadVideoProcess(event, file, fileList) {
      this.videoFlag = true
      this.videoUploadPercent = file.percentage.toFixed(0)
    },
    beforeUploadVideo(file) {
      const isLt10M = file.size / 1024 / 1024 < 10
      if (['video/mp4', 'video/ogg', 'video/flv', 'video/avi', 'video/wmv', 'video/rmvb'].indexOf(file.type) === -1) {
        this.$message.error('请上传正确的视频格式')
        return false
      }
      if (!isLt10M) {
        this.$message.error('上传视频大小不能超过10MB哦!')
        return false
      }
    },
    logout() {
      this.$store.dispatch('LogOut').then(() => {
        location.reload()// In order to re-instantiate the vue-router object to avoid bugs
        this.$router.push({ path: '/login' })
      })
    }

  }
}
</script>

<style scoped>

</style>
