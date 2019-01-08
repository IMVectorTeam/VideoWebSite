<template>
  <div v-loading="pageLoading" class="app-container">

    <el-table
      :data="commentList"
      :header-cell-style="{ background: '#F5F7FA' }"
      element-loading-text="数据获取中"
      border
      fit
      style="width: 100%">
      <el-table-column
        prop="userName"
        label="评论用户"
        align="center"
        width="180"/>
      <el-table-column
        prop="videoName"
        label="视频名称"
        align="center"
        width="180"/>
      <el-table-column
        prop="content"
        label="评论内容"
        align="center"
        min-width="180"/>
      <el-table-column
        :formatter="dateFormat"
        prop="date"
        label="发表日期"
        align="center"
        width="180"/>
      <el-table-column
        align="center"
        width="100"
        label="操作">
        <template slot-scope="scope">
          <el-button type="danger" icon="el-icon-delete" @click="handleDelete(scope.row,scope.$index)"/>
        </template>
      </el-table-column>

    </el-table>
    <mypagination type="comment" base-type="comment" style="float:right"/>
  </div>
</template>

<script>
import Data from '@/views/video/comment/mixin/Data'
import Mypagination from '@/components/Mypagination/index'
export default {
  name: 'CommentManage',
  components: { Mypagination },
  mixins: [Data],
  data() {
    return {
      pageLoading: false
    }
  },
  created() {
    this.pageLoading = true
    this.getCommentList().then(() => {
      this.pageLoading = false
    })
  },
  methods: {
    handleDelete(row, index) {
      this.deleteComment(row.id).then(() => {
        this.commentList.splice(index, 1)
      })
    }
  }
}
</script>

<style scoped>

</style>
