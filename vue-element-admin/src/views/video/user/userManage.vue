<template>
  <div class="app-container">

    <el-table
      :data="videoUserList"
      style="width: 100%">
      <el-table-column
        prop="name"
        label="日期"
        width="180"/>
      <el-table-column
        prop="name"
        label="姓名"
        width="180"/>
      <el-table-column
        prop="address"
        label="地址"/>
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
    console.log(this.videoUserList)
  }
}
</script>

<style scoped>

</style>
