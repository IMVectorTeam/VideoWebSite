<template>
  <div class="app-container">

    <el-table
      :data="videoUserList"
      style="width: 100%">
      <el-table-column
        prop="name"
        label="姓名"
        align="center"
        width="180"/>
      <el-table-column
        prop="id"
        label="id"
        align="center"
        width="180"/>
      <el-table-column
        prop="sex"
        label="性别"
        align="center"
        width="180"/>
      <el-table-column
        prop="email"
        label="邮箱"
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
import Data from '@/views/video/user/mixin/Data'
export default {
  name: 'UserManage',
  mixins: [Data],
  data() {
    return {

    }
  },
  created() {
    this.getVideoUserList()
  },
  methods: {
    handleDelete(row, index) {
      this.deleteUserList(row.id).then(() => {
        this.videoUserList.splice(index, 1)
      })
    }
  }
}
</script>

<style scoped>

</style>
