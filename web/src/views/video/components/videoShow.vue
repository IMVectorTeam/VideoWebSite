<template xmlns:el-col="http://www.w3.org/1999/html">
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
    <!--<el-card center>-->
    <el-row :gutter="10">
      <el-col :span="16" :offset="4">
        <div style="text-align: center;">

          <el-row :gutter="10">
            <el-col :span="20">
              <!--<video class="video" src="http://www.w3school.com.cn/i/movie.ogg" controlslist="nodownload" controls="controls" preload="auto"/>-->
              <!--<video class="video" src="@/assets/video/test.mp4" controls="controls" preload="auto"/>-->
              <video class="video" src="http://localhost:8080/VideoWebSite/resources/video/20181230220720707.mp4" controls="controls" preload="auto"/>
              <!--<video style="width:100%; height:100%; object-fit: fill" src="@/assets/video/test.mp4" controls="controls" preload="auto"/>-->
            </el-col>
            <el-col :span="4">
              <ul class="list-group">
                <li class="list-group-item">HTML</li>
                <li class="list-group-item">CSS</li>
                <li class="list-group-item">javascript</li>
                <li class="list-group-item">bootstrap</li>
                <li class="list-group-item">jquery</li>
              </ul>
            </el-col>
          </el-row>

          <!--class="el-menu-demo" mode="horizontal" @select="handleSelect"-->
        </div>

      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="13" :offset="4">
        <span style="font-size: 30px;font-weight: bold">这里是视频名称</span>
        <el-rate
          v-model="value"
          style="float: right"
          disabled
          show-score
          text-color="#ff9900"
          score-template="{value}"/>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :span="16" :offset="4">

        <hr style="border: solid 0.5px #EBEEF5">
        <el-row :gutter="20">
          <el-col :span="2">
            <img class="headImage" src="@/assets/videoImage/hamburger.png" >
          </el-col>
          <el-col :span="22">
            <el-row :gutter="10">
              <span>作者昵称</span>
            </el-row>
            <br>
            <el-row :gutter="10">
              <span>自我介绍哦</span>
            </el-row>
          </el-col>
          <el-row :gutter="10"/>
          <el-row :gutter="10">
            <el-col :span="16" :offset="2">
              <el-tag>标签一</el-tag>
              <el-tag type="success">标签二</el-tag>
              <el-tag type="info">标签三</el-tag>
              <el-tag type="warning">标签四</el-tag>
              <el-tag type="danger">标签五</el-tag>
            </el-col>
          </el-row>

        </el-row>
      </el-col>
    </el-row>
    <!----------------------------------------以下内容是用户信息以及评论分页----------------------------------------->
    <!--<el-row :gutter="10">-->
    <!--<el-col :span="16" :offset="4">-->

    <!--<hr style="border: solid 0.5px #EBEEF5">-->
    <!--<el-row :gutter="20">-->
    <!--<el-col :span="2">-->
    <!--<img class="headImage" src="@/assets/videoImage/hamburger.png">-->
    <!--</el-col>-->
    <!--<el-col :span="22">-->
    <!--<el-row :gutter="10">-->
    <!--<span>昵称</span>-->
    <!--</el-row>-->
    <!--<br>-->
    <!--<el-row :gutter="10">-->
    <!--<span>自我介绍哦</span>-->
    <!--</el-row>-->
    <!--</el-col>-->
    <!--</el-row>-->

    <!--</el-col>-->
    <!--</el-row>-->
    <el-row :gutter="10">
      <el-col :span="16" :offset="4">

        <hr style="border: solid 0.5px #EBEEF5">
        <br>
        <br>
        <br>
        <span style="font-size: 20px;font-weight: bold;color: #606266;">2532评论</span>
        <el-pagination
          :page-size="20"
          :pager-count="11"
          :total="1000"
          style="float: right"
          layout="prev, pager, next"/>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-col :span="16" :offset="4">
        <hr style="border: solid 0.5px #EBEEF5">
      </el-col>
    </el-row>

    <!----------------------------------------以下内容是评论信息----------------------------------------->
    <el-row :gutter="20">
      <el-col :span="16" :offset="4">
        <el-row :gutter="10">
          <el-col :span="2">
            <img class="headImage" src="@/assets/videoImage/hamburger.png">
          </el-col>
          <el-col :span="20">
            <div contenteditable="true" class="textArea" placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。"/>
            <!--<el-input type="textarea" style="background-color: #f4f5f7;border: #e5e9ef;height: 200px" placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。" />-->
          </el-col>
          <el-col :span="2">
            <el-button style="height: 65px" type="primary">发表评论</el-button>
            <br>
            <br>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

    <el-row v-for="i in 20" :key="i" :gutter="10">
      <el-col :span="16" :offset="4">

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
              <span>评论信息</span>
            </el-row>
          </el-col>
        </el-row>

      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="16" :offset="4">
        <div class="block">
          <el-pagination
            :current-page= "currentPage4"
            :page-sizes="[100, 200, 300, 400]"
            :page-size="100"
            :total="400"
            background
            layout="total, sizes, prev, pager, next, jumper"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"/>
        </div>

      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: 'VideoShow',
  data() {
    return {
      value: 3.7,
      currentPage4: 4
    }
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
    }
  }
}
</script>

<style scoped>

.headImage{
  border-radius:50%;
  width: 50px;
}
.textArea{
  font-size: 12px;
  display: inline-block;
  box-sizing: border-box;
  background-color: #f4f5f7;
  border: 1px solid #e5e9ef;
  overflow: auto;
  border-radius: 4px;
  color: #555;
  width: 100%!important;
  height: 65px;
  transition: 0s;
  padding: 5px 10px;
  line-height: normal;
}
div:empty:before{
  content: attr(placeholder);
  color:#bbb;
}
div:focus:before{
  content:none;
}
.video{
  background-color: #000000;
  max-width: 98%;
  max-height: 600px;
  min-width: 95%;
  min-height: 550px
}
.list-group {
  padding-left: 0;
  /*margin-bottom: 20px;*/
  margin-top: 0px;
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
