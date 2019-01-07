<template>
  <div v-loading="pageLoading" class="app-container">
    <el-table
      :data="videoList"
      :header-cell-style="{ background: '#F5F7FA' }"
      element-loading-text="数据获取中"
      border
      fit
      style="width: 100%">
      <el-table-column
        prop="name"
        label="视频名"
        align="center"
        width="180"/>
      <el-table-column
        prop="date"
        label="上传时间"
        align="center"
        width="180"/>
      <el-table-column
        prop="category"
        label="视频类别"
        align="center"
        width="180"/>
      <el-table-column
        prop="image"
        label="封面图像URL"
        align="center"
        min-width="180"/>
      <el-table-column
        prop="address"
        label="视频URL"
        align="center"
        min-width="180"/>
      <el-table-column
        prop="state"
        label="状态"
        align="center"
        width="100"/>
      <el-table-column
        width="200"
        align="center"
        label="操作">
        <template slot-scope="scope">
          <el-button icon="el-icon-info" @click="handleVideoInfo(scope.row.id)"/>
          <el-button type="danger" icon="el-icon-delete" @click="handleDelete(scope.row,scope.$index)"/>
        </template>
      </el-table-column>

    </el-table>

  </div>
</template>

<script>
import Data from '@/views/video/video/mixin/Data'
export default {
  name: 'VideoManage',
  mixins: [Data],
  data() {
    return {
      pageLoading: false
    }
  },
  created() {
    this.pageLoading = true
    this.getVideoList().then(() => {
      this.pageLoading = false
    })
  },
  methods: {
    handleVideoInfo(id) {
      this.$router.push({ path: `/admin/video-info/${id}` })
    }
  }
}
</script>

<style scoped>

</style>
