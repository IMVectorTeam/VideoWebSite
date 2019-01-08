<template>
  <div class="app-container">
    <el-row class="el-title">
      <i class="el-icon-d-arrow-right"/>
      <span>基本信息</span>
    </el-row>
    <el-form ref="form" style="padding-left: 25px" inline>
      <!--判断是否为空，如果为空不加载-->
      <el-row type="flex" justify="space-around">
        <el-col :span="8">
          <el-form-item label="昵称：">
            <span>{{ convertNullToBlack(videoUserItem).name }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="性别：">
            <span>{{ convertNullToBlack(videoUserItem).sex }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="邮箱：">
            <span>{{ convertNullToBlack(videoUserItem).email }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="签名：">
            <span style="height:30px;">{{ convertNullToBlack(videoUserItem).introduce }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="头像：">
            <img :src="videoUserItem.image" alt="用户头像" style="width:50px;border-radius:10%">
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <el-row class="el-title">
      <i class="el-icon-d-arrow-right"/>
      <span>已上传视频</span>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="20" :offset="2">
        <el-table
          :data="userIdVideoList"
          :header-cell-style="{ background: '#F5F7FA' }"
          element-loading-text="数据获取中"
          border
          fit
          style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-row>
                  <el-col :span="6">
                    <el-form-item label="视频状态">
                      <span>{{ props.row.state }}</span>
                    </el-form-item>

                  </el-col>
                  <el-col :span="7">
                    <el-form-item label="视频封面">
                      <span>
                        <img :src="props.row.image" style="width:200px;" alt="">
                      </span>
                    </el-form-item>
                  </el-col>
                  <el-col :span="9">
                    <el-form-item label="视频">
                      <span>
                        <video :src="props.row.address" controls="controls" preload="auto" style="width:400px" alt=""/>
                      </span>
                    </el-form-item>
                  </el-col>
                </el-row>

              </el-form>
            </template>
          </el-table-column>
          <el-table-column label="视频名称" prop="name" align="center"/>
          <el-table-column label="视频类别" prop="categoryContent" align="center"/>
          <el-table-column :formatter="dateFormat" label="上传时间" prop="date" align="center"/>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="danger" mini icon="el-icon-delete" @click="handleDeleteVideo(scope.row,scope.$index)"/>
            </template>
          </el-table-column>
        </el-table>
        <mypagination type="userIdVideo" base-type="video" style="float:right"/>
      </el-col>
    </el-row>
    <el-row class="el-title">
      <i class="el-icon-d-arrow-right"/>
      <span>已发表评论</span>
    </el-row>
    <el-row :gutter="10">
      <el-col :span="20" :offset="2">
        <el-table
          :data="userIdCommentList"
          :header-cell-style="{ background: '#F5F7FA' }"
          element-loading-text="数据获取中"
          border
          fit
          style="width: 100%">
          <el-table-column
            :formatter="dateFormat"
            prop="date"
            align="center"
            label="评论日期"
            width="180"/>
          <el-table-column
            prop="videoName"
            align="center"
            label="视频名称"
            width="180"/>
          <el-table-column
            align="center"
            prop="content"
            label="评论内容"/>
          <el-table-column align="center" label="操作" width="340">
            <template slot-scope="scope">
              <el-button type="danger" mini icon="el-icon-delete" @click="handleDeleteComment(scope.row,scope.$index)"/>
            </template>
          </el-table-column>
        </el-table>
        <mypagination type="userIdComment" base-type="comment" style="float:right"/>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import Data from '@/views/video/mixin/Data'
import Mypagination from '@/components/Mypagination/index'
export default {
  name: 'Info',
  components: { Mypagination },
  mixins: [Data],
  data() {
    return {
      detailsModal: false,
      detailsItem: {}
    }
  },
  created() {
    this.getUserById({ userId: this.$route.params.id }).then(() => {
      this.setUserIdVideoListQuery({ usingKey: true, k: 'userId', v: this.$route.params.id })
      this.getUserIdVideoList().then(() => {
        this.setUserIdCommentListQuery({ usingKey: true, k: 'userId', v: this.$route.params.id })
        this.setUserIdCommentListQuery({ usingKey: true, k: 'page', v: 1 })
        this.setUserIdCommentListQuery({ usingKey: true, k: 'limit', v: 10 })
        this.getUserIdCommentList()
      })

      // this.getCommentByUserId({ userId: this.$route.params.id, page: 1, limit: 10 })
    })
    // console.log(this.$route.params.uuid)
    // this.getHiddenDangersItem({ uuid: this.$route.params.uuid, config: { expand: 'entries,shift,hazard_classification,work_shop,team,entries.trying_to_identify_people,entries.measure_principal,entries.acceptor' }})
    // console.log(this.hiddenDangersItem)
  },
  methods: {
    handleDeleteComment(row, index) {
      this.deleteComment(row.id).then(() => {
        console.log(index)
        this.userIdCommentList.splice(index, 1)
      })
    },
    handleDetails(row) {
      this.detailsModal = true
      this.detailsItem = JSON.parse(JSON.stringify(row))
    },
    handleDeleteVideo(row, index) {
      this.deleteVideo(row.id).then(() => {
        this.userIdVideoList.splice(index, 1)
      })
    }
  }
}
</script>

<style scoped>
  .form-boder{
    border: #F2F6FC 0.5px solid;
    border-collapse: collapse;
  }
  .el-title {
    margin: 10px 0px 10px 0px;
    color: #606266;
    background-color: #F5F7FA;
    line-height: 40px;
    font-size: 16px;
    font-weight: 600;
  }

  .el-form-item {
    color: #666666;
    margin-bottom: 20px;
  }
</style>
