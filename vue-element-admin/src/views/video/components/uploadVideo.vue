<template>
  <div v-loading="pageLoading">
    <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect">
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

    <el-row :gutter="30">
      <el-col :span="7" :offset="4">
        <div style="border: 0.5px #E4E7ED solid;padding: 20px;">
          <el-form ref="form" :model="form" :rules="rules" label-width="80px" enctype="multipart/form-data">
            <el-form-item label="视频名字" prop="name">
              <el-input v-model="form.name" placeholder="请输入视频名称"/>
            </el-form-item>
            <el-form-item label="视频类别" prop="category">
              <el-select v-model="form.category" style="width: 100%" value="" placeholder="请选择视频类别">
                <el-option v-for="item in videoTypeList" :key="item.id" :label="item.name" :value="item.id"/>
                <el-option value="其他类别"/>
              </el-select>
            </el-form-item>
            <el-form-item label="图像上传" prop="image">
              <el-upload
                :on-success="handleImageSuccess"
                drag
                action="/api/VideoWebSite/image/upload"
                style="text-align: center;width: 100%;position: relative;overflow:hidden"
                multiple>
                <i class="el-icon-upload"/>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
              </el-upload>
            </el-form-item>

            <el-form-item label="视频上传" prop="videoURL">
              <!-- action必选参数, 上传的地址 -->
              <el-upload
                v-model="form.videoURL"
                :show-file-list="false"
                :on-success="handleVideoSuccess"
                :before-upload="beforeUploadVideo"
                :on-progress="uploadVideoProcess"
                action="/api/VideoWebSite/video/upload"
                style="border: 1px dashed #409EFF;border-radius: 6px;cursor: pointer;position: relative;overflow: hidden; text-align: center"
                class="avatar-uploader el-upload--text">
                <video
                  v-if="form.videoURL !='' && videoFlag == false"
                  :src="form.videoURL"
                  class="avatar"
                  style="max-width: 400px"
                  controls="controls">您的浏览器不支持视频播放</video>
                <i v-else-if="form.videoURL =='' && videoFlag == false" class="el-icon-plus avatar-uploader-icon"/>
                <el-progress
                  v-if="videoFlag == true"
                  :percentage="videoUploadPercent"
                  type="circle"
                  style="margin-top:30px;"/>
              </el-upload>
              <P class="text">请保证视频格式正确，且不超过10M</P>
            </el-form-item>
            <el-form-item>
              <el-button style="width: 100%" type="primary" @click="handleSubmit()">提交</el-button>
            </el-form-item>
          </el-form>
        </div>

      </el-col>
      <el-col :span="9">
        <el-table
          :data="tableData5"
          style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="商品名称">
                  <span>{{ props.row.name }}</span>
                </el-form-item>
                <el-form-item label="所属店铺">
                  <span>{{ props.row.shop }}</span>
                </el-form-item>
                <el-form-item label="商品 ID">
                  <span>{{ props.row.id }}</span>
                </el-form-item>
                <el-form-item label="店铺 ID">
                  <span>{{ props.row.shopId }}</span>
                </el-form-item>
                <el-form-item label="商品分类">
                  <span>{{ props.row.category }}</span>
                </el-form-item>
                <el-form-item label="店铺地址">
                  <span>{{ props.row.address }}</span>
                </el-form-item>
                <el-form-item label="商品描述">
                  <span>{{ props.row.desc }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column
            label="商品 ID"
            prop="id"/>
          <el-table-column
            label="商品名称"
            prop="name"/>
          <el-table-column
            label="描述"
            prop="desc"/>
        </el-table>

      </el-col>

    </el-row>

  </div>
</template>

<script>
import Data from '@/views/video/mixin/Data'
import store from '@/store'
import { mapMutations } from 'vuex'
export default {
  name: 'UploadVideo',
  mixins: [Data],
  data() {
    return {
      form: {
        videoURL: '',
        name: '',
        image: '',
        category: ''
      },
      uploadUrl: '',
      videoUploadPercent: 0,
      videoFlag: false,
      rules: {
        videoURL: [{ required: true, trigger: 'blur', message: '请上传视频' }],
        category: [{ required: true, trigger: 'blur', message: '请选择视频类别' }],
        image: [{ required: true, trigger: 'blur', message: '视频封面不能为空' }],
        name: [{ required: true, trigger: 'blur', message: '视频名称不能为空' }]
      },
      pageLoading: false,
      activeIndex: '1',
      tableData5: [{
        id: '12987122',
        name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }, {
        id: '12987123',
        name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }, {
        id: '12987125',
        name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }, {
        id: '12987126',
        name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }]
    }
  },
  created() {
    this.loading = true
    this.getVideoTypeList().then(() => {
      this.loading = false
    })
  },
  methods: {
    ...mapMutations('video', ['setVideoForm']),
    handleSubmit() {
      this.$refs['form'].validate(valid => {
        if (valid === true) {
          this.pageLoading = true
          this.setVideoForm({ k: 'name', v: this.form.name })
          this.setVideoForm({ k: 'image', v: this.form.image })
          this.setVideoForm({ k: 'address', v: this.form.videoURL })
          this.setVideoForm({ k: 'category', v: this.form.category })
          this.setVideoForm({ k: 'userId', v: store.getters.id })
          console.log(this.videoForm)
          this.createVideo().then(() => {
            this.pageLoading = false
          })
          // id: '',
          //   name: '',
          //   image: '',
          //   address: '',
          //   time: '',
          //   category: '',
          //   state: '',
          //   userId: '',
          //   date: ''
        }
      })
    },
    handleImageSuccess(res, file) {
      console.log(res)
      if (res.flag === true) {
        this.form.image = res.url
      } else {
        this.$message.error('图片上传失败，请重新上传！')
      }
    },
    handleVideoSuccess(res, file) { // 获取上传图片地址
      this.videoFlag = false
      this.videoUploadPercent = 0
      this.form.videoURL = res
      console.log(res)
      if (res.flag === true) {
        this.form.videoURL = res.url
      } else {
        this.$message.error('视频上传失败，请重新上传！')
      }
    },
    uploadVideoProcess(event, file, fileList) {
      this.videoFlag = true
      this.videoUploadPercent = file.percentage.toFixed(0)
    },
    beforeUploadVideo(file) {
      // const isLt10M = file.size / 1024 / 1024 < 10
      if (['video/mp4', 'video/ogg', 'video/flv', 'video/avi', 'video/wmv', 'video/rmvb'].indexOf(file.type) === -1) {
        this.$message.error('请上传正确的视频格式')
        return false
      }
      // if (!isLt10M) {
      //   this.$message.error('上传视频大小不能超过10MB哦!')
      //   return false
      // }
    },
    handleSelect() {},
    handlePersonalCenter() {},
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
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  /*.avatar {*/
    /*width: 178px;*/
    /*height: 178px;*/
    /*display: block;*/
  /*}*/
</style>
