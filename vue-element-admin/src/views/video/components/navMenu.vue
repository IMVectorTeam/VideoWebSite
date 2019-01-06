<template>
  <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect">
    <el-menu-item index="1" @click="handleIndex">主页</el-menu-item>
    <!-- <el-submenu index="2">
      <template slot="title">我的工作台</template>
      <el-menu-item index="2-1">选项1</el-menu-item>
      <el-submenu index="2-4">
        <template slot="title">选项4</template>
        <el-menu-item index="2-4-1">选项1</el-menu-item>
      </el-submenu>
    </el-submenu> -->
    <el-menu-item index="2" @click="handleClassfiedVideo">视频</el-menu-item>
    <el-menu-item index="3" @click="handleUpload">视频上传</el-menu-item>
    <el-menu-item index="4" style="float: right">
      <img :src="avatar" style="height: 50px;border-radius: 50%;">
    </el-menu-item>
    <el-submenu style="float: right" index="5">
      <template slot="title">{{ userName }}</template>
      <el-menu-item index="5-1" @click="handlePersonalCenter">个人中心</el-menu-item>
      <el-menu-item v-if="userName===''" index="5-1" @click="handleLogin">登录</el-menu-item>
      <el-menu-item v-else index="5-3" @click="logout">退出登录</el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>

import Data from '@/views/video/mixin/Data'
// import axios from 'axios'
import store from '@/store'
export default {
  name: 'NavMenu',
  mixins: [Data],
  props: {
    activeIndex: {
      type: String,
      default: '1'
    }
  },
  data() {
    return {
      pageLoading: false,
      //   activeIndex: '1',
      avatar: store.getters.userInfo.image,
      userName: store.getters.userInfo.name
    }
  },
  created() {
    console.log(store.getters.token)
    console.log(store.getters.userInfo)
    if (this.avatar && this.userName) {
      sessionStorage.setItem('avatar', store.getters.userInfo.image)
      sessionStorage.setItem('userName', store.getters.userInfo.name)
    }

    this.avatar = sessionStorage.getItem('avatar')
    this.userName = sessionStorage.getItem('userName')
    // this.pageLoading = true
    // this.pageLoading = false
    // console.log(this.videoList)
    // Promise.all([])
    // this.getVideoList().then(() => {
    //   this.pageLoading = false
    // })
    // axios({
    //   method: 'get',
    //   url: '/api/VideoWebSite/test11',
    //   params: {
    //     id: '1001'
    //   }
    // }).then(function(resp) {
    //   console.log(resp.data)
    // }).catch(resp => {
    //   console.log('请求失败：' + resp.status + ',' + resp.statusText)
    // })
  },
  methods: {
    handleLogin() {
      this.$router.push({ path: '/login' })
    },
    logout() {
      this.$store.dispatch('LogOut').then(() => {
        location.reload()// In order to re-instantiate the vue-router object to avoid bugs
        this.$router.push({ path: '/login' })
      })
    },
    handleClassfiedVideo() {
      this.$router.push({ path: `/classified-video/今日焦点` })
    },
    handleIndex() {
      this.$router.push({ path: '/index' })
    },
    handlePersonalCenter() {
      this.$router.push({ path: '/personal-center' })
    },
    handleSelect() {},
    handleUpload() {
      this.$router.push({ path: '/video-upload' })
    }
  }
}
</script>

<style scoped>

</style>
