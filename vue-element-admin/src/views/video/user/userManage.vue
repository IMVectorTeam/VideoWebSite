<template>
  <div class="app-container">
    <el-row>
      <el-col :span="18" :offset="3">
        <el-table
          :data="videoUserList"
          :header-cell-style="{ background: '#F5F7FA' }"
          element-loading-text="数据获取中"
          border
          fit
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
            min-width="180"/>
          <el-table-column
            prop="sex"
            label="性别"
            align="center"
            width="180"/>
          <el-table-column
            prop="email"
            label="邮箱"
            align="center"
            min-width="180"/>
          <el-table-column
            label="用户头像"
            align="center"
            width="300">
            <template slot-scope="scope">
              <img :src="scope.row.image" style="width:100px;max-height:110px" alt="用户头像">
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="200"
            label="操作">
            <template slot-scope="scope">
              <el-button icon="el-icon-info" @click="handleUserInfo(scope.row.id)"/>
              <el-button type="danger" icon="el-icon-delete" @click="handleDelete(scope.row,scope.$index)"/>
            </template>
          </el-table-column>

        </el-table>
        <mypagination type="videoUser" base-type="videoUser" style="float:right"/>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import Mypagination from '@/components/Mypagination/index'
import Data from '@/views/video/user/mixin/Data'
export default {
  name: 'UserManage',
  components: { Mypagination },
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
      this.deleteUser(row.id).then(() => {
        this.videoUserList.splice(index, 1)
      })
    },
    handleUserInfo(id) {
      this.$router.push({ path: `/admin/user-info/${id}` })
    }
  }
}
</script>

<style scoped>

</style>
