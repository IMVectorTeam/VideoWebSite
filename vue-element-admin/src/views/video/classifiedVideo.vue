<template>
  <div>
    <nav-menu :active-index = "'2'"/>
    <div class="app-container">
      <el-row :gutter="10">
        <el-col :offset="4">
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">活动管理</a></el-breadcrumb-item>
            <el-breadcrumb-item>活动列表</el-breadcrumb-item>
            <el-breadcrumb-item>活动详情</el-breadcrumb-item>
          </el-breadcrumb>
        </el-col>
      </el-row>
      <br>

      <el-row :gutter="10">
        <el-col :offset="4">
          <el-tag >标签一</el-tag>
          <el-tag type="success">标签二</el-tag>
          <el-tag type="info">标签三</el-tag>
          <el-tag type="warning">标签四</el-tag>
          <el-tag type="danger">标签五</el-tag>
        </el-col>
      </el-row>
      <br>
      <el-row :gutter="10">
        <el-col :span="16" :offset="4">
          <el-row :gutter="10">
            <!-----------------------------------视频列表----------------------------------- -->
            <el-col :span="16">
              <el-row v-for="(item, index) in list" :key="index" :gutter="10">
                <el-col v-for="(children,cIndex) in item" :key="cIndex" :span="12">

                  <el-card :body-style="{ padding: '0px' }">
                    <el-row :gutter="10">
                      <el-col :span="8">
                        <img :src="children.image" class="image" @click="showVideo(children.id)">
                        <br>
                        <br>
                      </el-col>
                      <el-col :span="16">
                        <div style="padding: 14px;">
                          <span @click="showVideo(children.id)">视频名称:{{ children.name }}</span>
                        </div>
                        <div style="padding: 14px;">
                          <span>视频介绍: {{ children.describe }}</span>
                        </div>
                        <div style="padding: 14px;color: #909399;">
                          <span>上传时间：{{ textDateFormat(children.date) }}</span>
                          <!-- <span>视频作者昵称{{ index +'-'+ cIndex }}</span> -->
                        <!--private String id;-->
                        <!--private String name;-->
                        <!--private String image;-->
                        <!--private String address;-->
                        <!--private String time;-->
                        <!--private String category;-->
                        <!--private String state;-->
                        <!--private String userId;-->
                        <!--private Date   date;-->

                        </div>
                      </el-col>
                    </el-row>
                  </el-card>

                </el-col>

              </el-row>
            </el-col>
            <!-- ---------------------------------视频列表结束----------------------------------- -->
            <!-- ------------------------------更多信息列表------------------------------ -->
            <el-col :span="8">
              <ul class="list-group">
                <li v-for="(item,index) in videoList" :key="index" class="list-group-item" @click="showVideo(item.id)">{{ item.name }}</li>
              </ul>
            </el-col>
          <!-- ------------------------------更多信息列表结束------------------------------ -->
          </el-row>
          <br>
          <br>
          <mypagination type="typeVideo" base-type="video"/>
        </el-col>
      </el-row>
    </div>

  </div>
</template>

<script>
import Data from '@/views/video/mixin/Data'
import navMenu from '@/views/video/components/navMenu'
import Mypagination from '@/components/Mypagination/index'
export default {
  name: 'ClassifiedVideo',
  components: { navMenu, Mypagination },
  mixins: [Data],
  data() {
    return {

    }
  },
  computed: {
    list: function() {
      var i = 0
      var list = [[]]
      this.typeVideoList.forEach((item, index) => {
        if (index % 2 === 0 && index !== 0) {
          i++
        }
        if (list[i]) {
          list[i].push(item)
        } else {
          list[i] = []
          list[i].push(item)
        }
      })
      return list
    }
  },
  created() {
    this.getVideoList()
    this.setTypeVideoListQuery({ usingKey: true, k: 'videoType', v: this.$route.params.title })
    this.setTypeVideoListQuery({ usingKey: true, k: 'limit', v: 10 })
    this.setTypeVideoListQuery({ usingKey: true, k: 'page', v: 1 })
    this.getTypeVideoList()
  },
  methods: {
    showVideo(id) {
      this.$router.push({ path: `/video-show/${id}` })
    },
    handleSelect() {

    },
    handleSizeChange() {},
    handleCurrentChange() {}
  }
}
</script>

<style scoped>
  .headFont {
    font-size: 30px;
    font-weight: bold;
  }
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    /*width: 100%;*/
    width:120px;
    height:120px;
    display: block;
    border-radius: 15%;
    padding: 10px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
  .list-group {
    padding-left: 0;
    margin-top: 0px;
    /*margin-bottom: px;*/
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
