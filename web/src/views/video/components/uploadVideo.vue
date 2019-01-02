<template>
  <div class="app-container">

    <el-form ref="form" :model="videoForm" label-width="80px" enctype="multipart/form-data">
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

  </div>
</template>

<script>
export default {
  name: 'UploadVideo',
  data() {
    return {
      videoForm: {},
      uploadUrl: '/api/VideoWebSite/test20',
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
    }

  }
}
</script>

<style scoped>

</style>
