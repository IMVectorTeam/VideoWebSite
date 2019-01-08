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
      <el-col v-for="item in videoData" :span="4" :key="item.id">
        <el-card :body-style="{ padding: '0px' }">
          <img :src="item.image" class="image" @click="showVideo(item.id)">
          <div style="padding: 14px;">
            <span>{{ item.name }}</span>
            <div class="bottom clearfix">
              <!--private String id;-->
              <!--private String name;-->
              <!--private String image;-->
              <!--private String address;-->
              <!--private String time;-->
              <!--private String category;-->
              <!--private String state;-->
              <!--private String userId;-->
              <!--private Date   date;-->
              <time v-if="item.date" class="time">{{ textDateFormat(item.date) }}</time>
              <time v-else class="time">上传时间记不清了~</time>
              <!--<el-button type="text" class="button">操作按钮</el-button>-->
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
      this.$router.push({ path: `/classified-video/${this.title}` })
    },
    showVideo(id) {
      this.$router.push({ path: `/video-show/${id}` })
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
