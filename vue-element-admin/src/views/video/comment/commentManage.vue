<template>
  <div v-loading="pageLoading" class="app-container">

    <el-table
      :data="videoCommentList"
      style="width: 100%">
      <el-table-column
        prop="MID"
        label="评价编号"
        align="center"
        width="180"/>
      <el-table-column
        prop="MVideoID"
        label="电影编号"
        align="center"
        width="180"/>
      <el-table-column
        prop="MContent"
        label="评价内容"
        align="center"
        width="180"/>
      <el-table-column
        prop="Mdade"
        label="评价日期"
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
import Data from '@/views/video/comment/mixin/Data'
export default {
  name: 'CommentManage',
  mixins: [Data],
  data() {
    return {
      pageLoading: false
    }
  },
  created() {
    this.pageLoading = true
    this.getVideoCommentList().then(() => {
      this.pageLoading = false
    })
  },
  methods: {
    handleDelete(row, index) {
      this.deleteVideoComment(row.id).then(() => {
        this.commentList.splice(index, 1)
      })
    }
  }
}
</script>

<style scoped>

</style>
