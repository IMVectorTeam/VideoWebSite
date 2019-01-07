<template>
  <div v-loading="pageLoading">
    <nav-menu/>
    <div class="app-container">

      <el-carousel indicator-position="outside" height="500px">
        <el-carousel-item v-for="(item, index) in carouselImage" :key="index">
          <img :src="item.url" alt="闪剧">
          <!-- <h3>{{ item }}</h3> -->
        </el-carousel-item>
      </el-carousel>
      <el-card>
        <video-list :title="'今日焦点'" :icon="'el-icon-star-off'" :more-path="'hotSpot'" :video-data="hotSpotList"/>
      </el-card>
      <el-card>
        <video-list :title="'综艺娱乐'" :icon="'el-icon-picture-outline'" :more-path="'entertainment'" :video-data="entertainmentList"/>
      </el-card>
      <el-card>
        <video-list :title="'电视剧'" :icon="'el-icon-news'" :more-path="'TVSeries'" :video-data="TVSeriesList"/>
      </el-card>
      <el-card>
        <video-list :title="'电影'" :icon="'el-icon-menu'" :more-path="'movies'" :video-data="moviesList"/>
      </el-card>
      <!--<el-table-->
      <!--:data="datalist"-->
      1<!--style="width: 100%">-->
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

import videoList from '@/views/video/components/videoList'
import navMenu from '@/views/video/components/navMenu'
import Data from '@/views/video/mixin/Data'

// import axios from 'axios'
import store from '@/store'
export default {
  name: 'Index',
  components: { videoList, navMenu },
  mixins: [Data],
  data() {
    return {
      pageLoading: false,
      carouselImage: [{ url: 'http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/0.jpeg' },
        { url: 'http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/1.jpeg' },
        { url: 'http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/2.jpeg' },
        { url: 'http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/3.jpeg' },
        { url: 'http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/4.jpeg' },
        { url: 'http://127.0.0.1:8080/VideoWebSite/resources/carouselImage/5.jpeg' }],
      datalist: [],
      hotSpotList: [],
      entertainmentList: [],
      TVSeriesList: [],
      moviesList: [],
      activeIndex: '1',
      avatar: store.getters.userInfo.image,
      userName: store.getters.userInfo.name
    }
  },
  created() {
    console.log(store.getters.userInfo)
    // this.pageLoading = true
    // this.pageLoading = false
    this.getVideoListByType({ page: 1, limit: 6, videoType: '今日焦点' }).then(() => {
      console.log(this.videoList)
      this.videoList.forEach(item => {
        this.hotSpotList.push(item)
      })
    })
    this.getVideoListByType({ page: 1, limit: 6, videoType: '综艺娱乐' }).then(() => {
      this.videoList.forEach(item => {
        this.entertainmentList.push(item)
      })
    })
    this.getVideoListByType({ page: 1, limit: 6, videoType: '电视剧' }).then(() => {
      this.videoList.forEach(item => {
        this.TVSeriesList.push(item)
      })
    })
    this.getVideoListByType({ page: 1, limit: 6, videoType: '电影' }).then(() => {
      this.videoList.forEach(item => {
        this.moviesList.push(item)
      })
    })
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
