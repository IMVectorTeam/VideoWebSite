<template>
  <section>
    <div class="app-container">
      <div class="filter-container">
        <el-form :inline="true">
          <el-form-item label="查询：">
            <el-input v-model.trim="listQuery.name" clearable />
          </el-form-item>
          <el-form-item>
            <el-button type="default" round icon="el-icon-search" @click="handleFilter">查 询</el-button>
            <el-button type="success" round icon="el-icon-plus" @click="handleCreate">新增视频类别</el-button>
          </el-form-item>
        </el-form>
      </div>
      <el-table v-loading="listLoading" :data="list" :header-cell-style="{ background: '#F5F7FA' }" border fit highlight-current-row height="650px">
        <el-table-column align="center" width="80px" type="index" label="序号" />
        <el-table-column align="center" min-width="300px" prop="name" label="视频类别名称" />
        <el-table-column align="center" min-width="300px" prop="remark" label="视频类别描述" />
        <el-table-column align="center" width="200px" label="操作">
          <template slot-scope="scope">
            <el-tooltip placement="top" content="修改">
              <el-button type="primary" icon="el-icon-edit" size="mini" @click="handleUpdate(scope.row)" />
            </el-tooltip>
            <el-tooltip placement="top" content="删除">
              <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDelete(scope.row)" />
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <pagination :total="listQuery.total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="initData"/>
    </div>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="40%">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="150px" @submit.native.prevent>
        <el-form-item label="视频类别名称" prop="name">
          <el-input v-model="temp.name" placeholder="视频类别名称" style="width:300px"/>
        </el-form-item>
        <el-form-item label="视频类别描述" prop="remark">
          <el-input v-model="temp.remark" placeholder="视频类别描述" style="width:300px"/>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">确 认</el-button>
      </div>
    </el-dialog>
  </section>

</template>

<script>
import Pagination from '@/components/Pagination/index'
import { videoTypeOp } from '@/api/video'
export default {
  name: 'VideoTypeManage',
  components: { Pagination },
  data() {
    return {
      dialogFormVisible: false,
      dialogStatus: '',
      listLoading: false,
      total: 0,
      list: [],
      listQuery: {
        page: 0,
        limit: 20,
        total: 0,
        name: ''
      },
      temp: {
        name: '',
        remark: ''
      },
      textMap: {
        update: '编辑视频类别',
        create: '新增视频类别'
      },
      rules: {
        name: [{ required: true, message: '请输入视频类别名称', trigger: 'blur' }]
      }
    }
  },
  computed: {},
  created() {
    this.initData()
  },
  methods: {
    initData() {
      this.listLoading = true
      videoTypeOp.list(this.listQuery).then(res => {
        this.list = res.data.list.map(v => {
          this.$set(v, 'isLoading', false)
          return v
        })
        this.listQuery.total = res.data.total
        this.listLoading = false
      })
    },
    handleFilter() {
      this.initData()
    },
    resetTemp() {
      this.temp = {
        name: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          videoTypeOp.create(this.temp).then((res) => {
            if (res.data === true) {
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '创建成功',
                type: 'success',
                duration: 2000
              })
              this.initData()
            } else {
              this.$notify({
                title: '失败',
                message: '创建失败',
                type: 'danger',
                duration: 2000
              })
            }
          })
        }
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          videoTypeOp.update(tempData, tempData.id).then((res) => {
            if (res.data.flag === true) {
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
              this.initData()
            } else {
              this.$notify({
                title: '失败',
                message: '更新失败',
                type: 'danger',
                duration: 2000
              })
            }
          })
        }
      })
    },
    handleDelete(row) {
      this.$confirm('您确定删除此条目吗?', '提示', {
        type: 'warning',
        confirmButtonText: '是',
        cancelButtonText: '否'
      }).then(() => {
        videoTypeOp.delete({ id: row.id }).then(() => {
          this.$notify({
            title: '成功',
            message: '删除成功',
            type: 'success',
            duration: 2000
          })
          this.initData()
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
