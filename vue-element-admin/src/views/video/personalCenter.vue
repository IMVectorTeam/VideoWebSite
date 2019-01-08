<template>
  <div>
    <nav-menu :active-index="'5'"/>
    <div style="height: 160px;width: 100%;background: url('src/assets/videoImage/head.png') no-repeat;background-size: 100% 100%;"/>
    <div class="app-container">
      <el-row>
        <el-col :span="20" :offset="2">
          <el-tabs :tab-position="'left'" style="height:600px;" @tab-click="handleTabClick">
            <el-tab-pane label="信息修改">
              <br>
              <br>
              <br>
              <el-row :gutter="10">
                <el-col :span="4" :offset="4">
                  <img
                    v-if="uploadShow"
                    :src="userForm.image"
                    style="border-radius: 10%;height:236px;width:235px;"
                    alt
                  >
                  <el-upload
                    v-else
                    v-model="userForm.image"
                    :show-file-list="false"
                    :on-success="handleImageSuccess"
                    class="avatar-uploader"
                    action="/api/VideoWebSite/image/upload">
                    <img v-if="uploadShow" :src="userForm.image" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"/>
                  </el-upload>
                  <el-button type="primary" style="margin-left: 60px" @click="changImage">更换头像</el-button>

                </el-col>
                <el-col :span="12" class="edit-me-container">
                  <el-form
                    ref="userForm"
                    :model="userForm"
                    :rules="rules"
                    label-width="80px"
                    auto-complete="on"
                  >
                    <el-form-item label="用户名" prop="name">
                      <el-input v-model="userForm.name" placeholder="请输入用户名"/>
                    </el-form-item>
                    <el-form-item label="签名" prop="introduce">
                      <el-input v-model="userForm.introduce" placeholder="请输入签名"/>
                    </el-form-item>
                    <el-form-item label="性别" prop="sex">
                      <el-select
                        v-model="userForm.sex"
                        value
                        style="width: 110%"
                        placeholder="请选择性别"
                      >
                        <el-option value="男"/>
                        <el-option value="女"/>
                        <el-option value="保密"/>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="email">
                      <el-input
                        v-model="userForm.email"
                        placeholder="请输入邮箱"
                        name="email"
                        type="text"
                        auto-complete="on"
                      />
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                      <el-input
                        :type="passwordType"
                        v-model="userForm.password"
                        placeholder="请输入密码"
                        name="password"
                        auto-complete="on"
                      />
                      <span class="svg-container" @click="showPwd">
                        <svg-icon icon-class="eye"/>
                      </span>

                      <!-- <el-input :type="passwordType" v-model="userForm.passwordRepeat" placeholder="请输入密码"/> -->
                    </el-form-item>
                    <el-form-item label="重复密码" prop="passwordRepeat">
                      <el-input
                        :type="passwordType"
                        v-model="userForm.passwordRepeat"
                        placeholder="请输入上述重复密码"
                        name="password"
                        auto-complete="on"
                      />
                      <span class="svg-container" @click="showPwd">
                        <svg-icon icon-class="eye"/>
                      </span>

                      <!-- <el-input :type="passwordType" v-model="userForm.passwordRepeat" placeholder="请重复输入上述密码"/> -->
                    </el-form-item>
                  </el-form>
                  <el-button type="primary" style="float:right" @click="handleSubmitUserInfo">提交</el-button>
                </el-col>
              </el-row>
            </el-tab-pane>
            <el-tab-pane label="我上传的视频" >
              <div style="overflow-y: scroll;height:600px">
                <br>
                <br>
                <br>
                <el-row :gutter="10">
                  <el-col :span="16" :offset="4">
                    <el-table :data="userIdVideoList" style="width: 100%">
                      <el-table-column type="expand">
                        <template slot-scope="props">
                          <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="视频状态">
                              <span>{{ props.row.state }}</span>
                            </el-form-item>
                            <el-form-item label="视频封面">
                              <!-- <span>{{ props.row.shop }}</span> -->
                              <span>
                                <img :src="props.row.image" style="width:200px;" alt="">
                              </span>
                            </el-form-item>
                            <el-form-item label="视频">
                              <!-- <span>{{ props.row.shop }}</span> -->
                              <span>
                                <video :src="props.row.address" controls="controls" preload="auto" style="width:400px" alt=""/>
                              </span>
                            </el-form-item>
                          </el-form>
                        </template>
                      </el-table-column>
                      <el-table-column label="视频名称" prop="name" align="center"/>
                      <el-table-column label="视频类别" prop="categoryContent" align="center"/>
                      <el-table-column :formatter="dateFormat" label="上传时间" prop="date" align="center"/>
                      <el-table-column label="操作" align="center">
                        <template slot-scope="scope">
                          <el-button type="danger" mini icon="el-icon-delete" @click="handleDeleteVideo(scope.row,scope.row.$index)"/>
                        </template>
                      </el-table-column>

                    </el-table>
                    <mypagination type="userIdVideo" base-type="video" style="float:right"/>
                  </el-col>
                </el-row>
              </div>
            </el-tab-pane>
            <el-tab-pane label="我的评论">
              <br>
              <br>
              <br>
              <el-row :gutter="10">
                <el-col :span="16" :offset="4">
                  <div style="overflow-y: scroll;height:600px">
                    <el-table
                      :data="userIdCommentList"
                      :row-style="tableRowClassName"
                      style="width: 100%">
                      <el-table-column
                        :formatter="dateFormat"
                        prop="date"
                        align="center"
                        label="评论日期"
                        width="180"/>
                      <el-table-column
                        prop="videoName"
                        align="center"
                        label="视频名称"
                        width="180"/>
                      <el-table-column
                        align="center"
                        prop="content"
                        label="评论内容"/>
                      <el-table-column align="center" label="操作">
                        <template slot-scope="scope">
                          <el-button type="danger" mini icon="el-icon-delete" @click="handleDeleteComment(scope.row,scope.row.$index)"/>
                        </template>
                      </el-table-column>
                    </el-table>
                    <mypagination type="userIdComment" base-type="comment" style="float:right"/>
                    <br>
                    <br>

                    <br>
                    <br>
                    <br>
                    <br>
                    <br>

                  </div>
                  <!-- private String id;
                  private String videoId;
                  private String userId;
                  private String content;
                  private Date date;
                  private String userName;
                  private String videoName;
                  private String image;
                  private String introduce; -->

                </el-col>
              </el-row>
            </el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import Mypagination from '@/components/Mypagination/index'
import navMenu from '@/views/video/components/navMenu'
import Data from '@/views/video/mixin/Data'
import Vue from 'vue'
export default {
  name: 'PersonalCenter',
  components: { navMenu, Mypagination },
  mixins: [Data],
  data() {
    const validateEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请正确填写邮箱'))
      } else {
        if (value !== '') {
          var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
          if (!reg.test(value)) {
            callback(new Error('请输入有效的邮箱'))
          }
        }
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码的长度应该大于6位！'))
      } else {
        callback()
      }
    }
    const validatePasswordRepeat = (rule, value, callback) => {
      if (this.userForm.password !== this.userForm.passwordRepeat) {
        callback(new Error('两次输入的密码不一致，请检查后重试！'))
      }
      if (value.length < 6) {
        callback(new Error('密码的长度应该大于6位！'))
      } else {
        callback()
      }
    }
    return {
      uploadShow: true,
      userForm: {
        email: '',
        id: '',
        image: '',
        introduce: '',
        name: '',
        password: '',
        sex: '',
        passwordRepeat: ''
      },
      passwordType: 'password',
      rules: {
        email: [{ required: true, trigger: 'blur', validator: validateEmail }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }],
        passwordRepeat: [{ required: true, trigger: 'blur', validator: validatePasswordRepeat }],
        sex: [{ required: true, trigger: 'blur', message: '性别' }],
        name: [{ required: true, trigger: 'blur', message: '用户名不能为空' }]
      }

    }
  },
  created() {
    if (JSON.parse(sessionStorage.getItem('userInfo')) === null || JSON.parse(sessionStorage.getItem('userInfo')) === undefined) {
      this.$store.dispatch('LogOut').then(() => {
        sessionStorage.setItem('avatar', '')
        sessionStorage.setItem('userName', '')
        sessionStorage.clear()
        location.reload()// In order to re-instantiate the vue-router object to avoid bugs
        this.$router.push({ path: '/login' })
      })
    }
    this.userForm = JSON.parse(sessionStorage.getItem('userInfo'))
    this.userForm.password = ''
    Vue.set(this.userForm, 'passwordRepeat', '')
  },
  methods: {

    handleDeleteVideo(row, index) {
      this.deleteVideo(row.id).then(() => {
        this.userIdVideoList.splice(index, 1)
      })
    },
    handleDeleteComment(row, index) {
      this.deleteComment(row.id).then(() => {
        this.userIdCommentList.splice(index, 1)
      })
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 4 === 0) {
        return 'background: oldlace;'
      } else if (rowIndex % 4 === 2) {
        return 'background: #f0f9eb;'
      }
      return ''
    },
    handleTabClick(tab, event) {
      if (tab.label === '我上传的视频') {
        this.setUserIdVideoListQuery({ usingKey: true, k: 'userId', v: this.userForm.id })
        this.getUserIdVideoList()
      }
      if (tab.label === '我的评论') {
        this.setUserIdCommentListQuery({ usingKey: true, k: 'userId', v: this.userForm.id })
        this.getUserIdCommentList()
      }
    },
    changImage() { this.uploadShow = false },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
    },
    handleImageSuccess(res, file) {
      console.log(res)
      if (res.flag === true) {
        this.userForm.image = res.url
        this.uploadShow = true
      } else {
        this.$message.error('图片上传失败，请重新上传！')
      }
    },
    handleSubmitUserInfo() {
      this.$refs['userForm'].validate(valid => {
        if (valid) {
          this['setVideoUserForm']({ usingKey: true, k: 'email', v: this.userForm.email })
          this['setVideoUserForm']({ usingKey: true, k: 'password', v: this.userForm.password })
          this['setVideoUserForm']({ usingKey: true, k: 'sex', v: this.userForm.sex })
          this['setVideoUserForm']({ usingKey: true, k: 'name', v: this.userForm.name })
          this['setVideoUserForm']({ usingKey: true, k: 'image', v: this.userForm.image })
          this['setVideoUserForm']({ usingKey: true, k: 'introduce', v: this.userForm.introduce })
          this['setVideoUserForm']({ usingKey: true, k: 'id', v: this.userForm.id })
          this.getVideoList()
          this.updateVideoUser(this.userForm.id).then(() => {
            sessionStorage.setItem('userInfo', JSON.stringify(this.userForm))
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
  /* .container-tab  .el-tabs__content{
    flex-grow: 1;
    overflow-y: scroll;
  }
 .container-tab >>> .el-tabs__content{
    flex-grow: 1;
    overflow-y: scroll;
  } */
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    height:236px;width:235px;
    line-height: 235px;
    text-align: center;
  }
  .avatar {
    height:236px;width:235px;
    display: block;
  }
</style>
<style rel="stylesheet/scss" lang="scss">

/* reset element-ui css */
.edit-me-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 90%;
    input {
      border: 0px;
      border-radius: 0px;
      height: 47px;
    }
  }
  .el-form-item {
    border: 1px solid rgba(0, 0, 0, 0.1);
    border-radius: 5px;
  }
}
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
.edit-me-container {
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: #889aa4;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: #889aa4;
    cursor: pointer;
    user-select: none;
  }
}
</style>

