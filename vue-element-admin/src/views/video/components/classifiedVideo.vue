<template>
  <div class="app-container">
    <el-menu :default-active="activeIndex" style="margin-top: -20px;" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">处理中心</el-menu-item>
      <el-submenu index="2">
        <template slot="title">我的工作台</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-menu-item index="2-2">选项2</el-menu-item>
        <el-menu-item index="2-3">选项3</el-menu-item>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项1</el-menu-item>
          <el-menu-item index="2-4-2">选项2</el-menu-item>
          <el-menu-item index="2-4-3">选项3</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="3" disabled>消息中心</el-menu-item>

      <el-menu-item index="4" style="float: right">
        <img src="@/assets/videoImage/hamburger.png" style="height: 50px;border-radius: 50%;">
      </el-menu-item>
      <el-submenu style="float: right" index="5">
        <template slot="title">昵称</template>
        <el-menu-item index="5-1">个人中心</el-menu-item>
        <el-menu-item index="5-2">退出登录</el-menu-item>
      </el-submenu>
    </el-menu>
    <br>
    <br>
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
          <!-----------------------------------视频列表------------------------------------->
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
                        <span>视频介绍: {{ children.status }}</span>
                      </div>
                      <div style="padding: 14px;color: #909399;">
                        <span>视频作者昵称{{ children.userId }}</span>
                        <span>视频作者昵称{{ index +'-'+ cIndex }}</span>
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
          <!-----------------------------------视频列表结束------------------------------------->
          <!--------------------------------更多信息列表-------------------------------->
          <el-col :span="8">
            <ul class="list-group">
              <li v-for="i in 10" :key="i" class="list-group-item">HTML</li>
              <li class="list-group-item">CSS</li>
              <li class="list-group-item">javascript</li>
              <li class="list-group-item">bootstrap</li>
              <li class="list-group-item">jquery</li>
            </ul>
          </el-col>
          <!--------------------------------更多信息列表结束-------------------------------->
        </el-row>
        <br>
        <br>
        <el-row :gutter="10">
          <el-col :span="16">
            <el-pagination
              :current-page="4"
              :page-sizes="[100, 200, 300, 400]"
              :page-size="100"
              :total="400"
              layout="total, sizes, prev, pager, next, jumper"
              style="float: right"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
            />
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Data from '@/views/video/mixin/Data'
export default {
  name: 'ClassifiedVideo',
  mixins: [Data],
  data() {
    return {
      activeIndex: '1',
      list: [[]]

    }
  },
  created() {
    this.getVideoListByType({ page: 1, limit: 10, videoType: this.$route.params.title }).then(() => {
      console.log(this.videoList)
      var i = 0

      this.videoList.forEach((item, index) => {
        if (index % 2 === 0 && index !== 0) {
          i++
        }
        if (this.list[i]) {
          this.list[i].push(item)
        } else {
          this.list[i] = []
          this.list[i].push(item)
        }
      })
      console.log(this.list)
    })
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
