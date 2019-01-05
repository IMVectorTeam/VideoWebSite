<template>
  <div class="app-container">

    <el-row :gutter="10">

      <span class="headFont">
        <i :class="icon"/>
        <!--今日头条-->
        {{ title }}
      </span>
      <el-button style="float: right;margin-bottom: 5px;" round @click="handleMore">更多 <i class="el-icon-d-arrow-right"/></el-button>
    </el-row>
    <br>
    <el-row :gutter="10">
      <el-col v-for="item in videoData" :span="4" :key="item.video.id">
        <el-card :body-style="{ padding: '0px' }">
          <img :src="item.video.image" class="image">
          <div style="padding: 14px;">
            <span>好吃的汉堡</span>
            <div class="bottom clearfix">
              <time class="time">{{ currentDate }}</time>
              <el-button type="text" class="button">操作按钮</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>

</template>

<script>
export default {
  name: 'VideoList',
  props: {
    title: {
      type: String,
      default: '视频'
    },
    icon: {
      type: String,
      default: 'el-icon-star-on'
    },
    morePath: {
      type: String,
      default: '/classified-video'
    },
    videoData: {
      type: Array,
      default: () => {
        return []
      }
    }
  },
  data() {
    return {
      currentDate: new Date()
    }
  },
  methods: {
    handleMore() {
      this.$router.push({ path: '/classified-video' })
    }
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
    /*max-width: 300px;*/
    /*max-height: 300px;*/
    width: 300px;
    height: 300px;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
