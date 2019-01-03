<template>
  <div class="app-container">
    <el-table
      :data="videoList"
      style="width: 100%">
      <el-table-column
        prop="name"
        label="名字"
        align="center"
        width="180"/>
      <el-table-column
        prop="id"
        label="id"
        align="center"
        width="180"/>
      <el-table-column
        prop="time"
        label="时间"
        align="center"
        width="180"/>
      <el-table-column
        prop="category"
        label="类别"
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
export default {
  name: 'VideoManage'
}
</script>

<style scoped>

</style>
