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
    <div class="app-container">
      <el-button type="primary" @click="handleTest">跳转测试</el-button>
      <el-button type="primary" @click="handleTest1">跳转测试上传</el-button>
      <el-carousel indicator-position="outside" height="500px">
        <el-carousel-item v-for="item in 4" :key="item">
          <h3>{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
      <el-card>
        <video-list :title="'今日焦点'" :icon="'el-icon-star-off'" :more-path="'hotSpot'"/>
      </el-card>
      <el-card>
        <video-list :title="'综艺娱乐'" :icon="'el-icon-picture-outline'" :more-path="'entertainment'"/>
      </el-card>
      <el-card>
        <video-list :title="'电视剧'" :icon="'el-icon-news'" :more-path="'TVSeries'"/>
      </el-card>
      <el-card>
        <video-list :title="'电影'" :icon="'el-icon-menu'" :more-path="'movies'"/>
      </el-card>
      <!--<el-table-->
      <!--:data="datalist"-->
      <!--style="width: 100%">-->
      <!--<el-table-column-->
      <!--prop="date"-->
      <!--label="日期"-->
      <!--width="180"/>-->
      <!--<el-table-column-->
      <!--prop="name"-->
      <!--label="姓名"-->
      <!--width="180"/>-->
      <!--<el-table-column-->
      <!--prop="address"-->
      <!--label="地址"/>-->
      <!--</el-table>-->
    </div>

  </div>

</template>

<script>

import videoList from './components/videoList'
import Data from '@/views/video/mixin/Data'
// import axios from 'axios'
export default {
  name: 'Index',
  components: { videoList },
  mixins: [Data],
  data() {
    return {
      pageLoading: false,
      hello: 'hello world!',
      datalist: [],
      hotSpotList: [],
      entertainmentList: [],
      TVSeriesList: [],
      moviesList: [],
      activeIndex: '1'
    }
  },
  created() {
    this.pageLoading = true

    Promise.all([this.getVideoListByType('今日焦点').then(() => {
      this.hotSpotList = JSON.parse(JSON.stringify(this.videoList))
      console.log(this.hotSpotList)
    }),
    this.getVideoListByType('综艺娱乐'),
    this.getVideoListByType('电视剧'),
    this.getVideoListByType('电影')]).then(() => {
      this.pageLoading = false
    })
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
    logout() {
      this.$store.dispatch('LogOut').then(() => {
        location.reload()// In order to re-instantiate the vue-router object to avoid bugs
        this.$router.push({ path: '/login' })
      })
    },
    handlePersonalCenter() {
      this.$router.push({ path: '/personal-center' })
    },
    handleSelect() {},
    handleTest() {
      this.$router.push({ path: '/video-show' })
    },
    handleTest1() {
      this.$router.push({ path: '/video-upload' })
    }
  }
}
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 500px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
