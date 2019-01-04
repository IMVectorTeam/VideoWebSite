<template>
  <div v-loading="pageLoading" class="app-container">
    <el-table
      :data="videoList"
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
        width="180"/>
      <el-table-column
        prop="address"
        label="视频URL"
        align="center"
        width="180"/>
      <el-table-column
        prop="status"
        label="状态"
        align="center"
        width="180"/>
      <el-table-column
        align="center"
        label="操作">
        <template slot-scope="scope">
          <el-button type="danger" @click="handleDelete(scope.row,scope.$index)">删除</el-button>
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
  }
}
</script>

<style scoped>

</style>
