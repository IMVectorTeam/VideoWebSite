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
            <el-tab-pane label="我上传的视频">
              <br>
              <br>
              <br>
              <el-row :gutter="10">
                <el-col :span="16" :offset="4">
                  <!-- address: "http://127.0.0.1:8080/VideoWebSite/resources/video/xiaoyuan.mp4"
                    category: "电影"
                    date: null
                    id: "125"
                    image: "http://127.0.0.1:8080/VideoWebSite/resources/images/test5.png"
                    name: "是的发生大咖啡"
                    state: null
                    time: null
                    userId: "王小红" -->
                  <div style="overflow-y: auto">
                    <el-table :data="videoList" style="width: 100%">
                      <el-table-column type="expand">
                        <template slot-scope="props">
                          <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="视频状态">
                              <span>{{ props.row.state }}</span>
                            </el-form-item>
                            <el-form-item label="视频封面">
                              <!-- <span>{{ props.row.shop }}</span> -->
                              <span>
                                <img :src="props.row.image" style="width:100px;" alt="">
                              </span>
                            </el-form-item>
                            <el-form-item label="视频">
                              <!-- <span>{{ props.row.shop }}</span> -->
                              <span>
                                <video :src="props.row.address" controls="controls" preload="auto" style="width:400px" alt=""/>
                              </span>
                            </el-form-item>
                          <!-- <el-form-item label="商品 ID">
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
                          </el-form-item> -->
                          </el-form>
                        </template>
                      </el-table-column>
                      <el-table-column label="视频名称" prop="name"/>
                      <el-table-column label="视频类别" prop="category"/>
                      <el-table-column label="上传时间" prop="date"/>
                    </el-table>
                  </div>
                </el-col>
              </el-row>
            </el-tab-pane>
            <el-tab-pane label="我的评论">
              <br>
              <br>
              <br>
              <el-row :gutter="10">
                <el-col :span="16" :offset="4">
                  <el-row v-for="(item, index) in 10" :key="index" :gutter="10">
                    <el-col>
                      <hr style="border: solid 0.5px #EBEEF5">
                      <el-row :gutter="20">
                        <el-col :span="2">
                          <img class="headImage" src="@/assets/videoImage/hamburger.png">
                        </el-col>
                        <el-col :span="22">
                          <el-row :gutter="10">
                            <span>昵称</span>
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
import navMenu from '@/views/video/components/navMenu'
import Data from '@/views/video/mixin/Data'
export default {
  name: 'PersonalCenter',
  components: { navMenu },
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
      },
      tableData5: [
        {
          id: '12987122',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        },
        {
          id: '12987123',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        },
        {
          id: '12987125',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        },
        {
          id: '12987126',
          name: '好滋好味鸡蛋仔',
          category: '江浙小吃、小吃零食',
          desc: '荷兰优质淡奶，奶香浓而不腻',
          address: '上海市普陀区真北路',
          shop: '王小虎夫妻店',
          shopId: '10333'
        }
      ]
    }
  },
  created() {
    this.userForm = JSON.parse(sessionStorage.getItem('userInfo'))
    this.userForm.password = ''
    this.userForm.passwordRepeat = ''
  },
  methods: {
    handleTabClick(tab, event) {
      // console.log(tab.label, event)
      if (tab.label === '我上传的视频') {
        this.getVideoByUserId({ userId: this.userForm.id, page: 1, limit: 10 })
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
          this['setVideoUserForm']({
            usingKey: true,
            k: 'email',
            v: this.userForm.email
          })
          this['setVideoUserForm']({
            usingKey: true,
            k: 'password',
            v: this.userForm.password
          })
          this['setVideoUserForm']({
            usingKey: true,
            k: 'sex',
            v: this.userForm.sex
          })
          this['setVideoUserForm']({
            usingKey: true,
            k: 'name',
            v: this.userForm.name
          })
          this['setVideoUserForm']({
            usingKey: true,
            k: 'image',
            v: this.userForm.image
          })
          this['setVideoUserForm']({
            usingKey: true,
            k: 'introduce',
            v: this.userForm.introduce
          })
          this['setVideoUserForm']({
            usingKey: true,
            k: 'id',
            v: this.userForm.id
          })
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
  .container-tab  .el-tabs__content{
    flex-grow: 1;
    overflow-y: scroll;
  }
 .container-tab >>> .el-tabs__content{
    flex-grow: 1;
    overflow-y: scroll;
  }
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

