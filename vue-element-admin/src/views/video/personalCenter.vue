<template>
  <div>
    <nav-menu :active-index="'5'"/>
    <div style="height: 160px;width: 100%;background: url('src/assets/videoImage/head.png') no-repeat;background-size: 100% 100%;"/>
    <div class="app-container">
      <el-row>
        <el-col :span="20" :offset="2">
          <el-tabs :tab-position="'left'" style="height:500px;">
            <el-tab-pane label="信息修改">
              <br>
              <br>
              <br>
              <el-row :gutter="10">
                <el-col :span="4" :offset="4" >
                  <img src="src/assets/videoImage/hamburger.png" style="border-radius: 10%" alt="">
                  <el-button type="info" style="margin-left: 60px">更换头像</el-button>
                </el-col>
                <el-col :span="12" >
                  <el-form ref="form" label-width="80px">
                    <el-form-item label="用户名">
                      <el-input v-model="userForm.name" placeholder="请输入用户名"/>
                    </el-form-item>
                    <el-form-item label="签名">
                      <el-input placeholder="请输入签名"/>
                    </el-form-item>
                    <el-form-item label="性别">
                      <el-select v-model="userForm.sex" value="" style="width: 100%" placeholder="请选择性别">
                        <el-option value="男"/>
                        <el-option value="女"/>
                        <el-option value="保密"/>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="邮箱">
                      <!-- <span class="svg-container">
                        <svg-icon icon-class="user"/>
                      </span> -->
                      <el-input
                        v-model="userForm.email"
                        placeholder="请输入邮箱"
                        name="email"
                        type="text"
                        auto-complete="on"
                      />
                    </el-form-item>
                    <el-form-item label="密码">
                      <el-input placeholder="请输入密码"/>
                    </el-form-item>
                    <el-form-item label="重复密码">
                      <el-input placeholder="请重复输入上述密码"/>
                    </el-form-item>
                  </el-form>
                  <el-button type="primary" style="float:right">提交</el-button>
                </el-col>
              </el-row>

            </el-tab-pane>
            <el-tab-pane label="我上传的视频">
              <br>
              <br>
              <br>
              <el-row :gutter="10">
                <el-col :span="16" :offset="4" >
                  <el-table
                    :data="tableData5"
                    style="width: 100%">
                    <el-table-column type="expand">
                      <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
                          <el-form-item label="商品名称">
                            <span>{{ props.row.name }}</span>
                          </el-form-item>
                          <el-form-item label="所属店铺">
                            <span>{{ props.row.shop }}</span>
                          </el-form-item>
                          <el-form-item label="商品 ID">
                            <span>{{ props.row.id }}</span>
                          </el-form-item>
                          <el-form-item label="店铺 ID">
                            <span>{{ props.row.shopId }}</span>
                          </el-form-item>
                          <el-form-item label="商品分类">
                            <span>{{ props.row.category }}</span>
                          </el-form-item>
                          <el-form-item label="店铺地址">
                            <span>{{ props.row.address }}</span>
                          </el-form-item>
                          <el-form-item label="商品描述">
                            <span>{{ props.row.desc }}</span>
                          </el-form-item>
                        </el-form>
                      </template>
                    </el-table-column>
                    <el-table-column
                      label="商品 ID"
                      prop="id"/>
                    <el-table-column
                      label="商品名称"
                      prop="name"/>
                    <el-table-column
                      label="描述"
                      prop="desc"/>
                  </el-table>
                </el-col>
              </el-row>
            </el-tab-pane>
            <el-tab-pane label="我的评论">
              <br>
              <br>
              <br>
              <el-row :gutter="10">
                <el-col :span="16" :offset="4" >
                  <el-row v-for="(item, index) in 10" :key="index" :gutter="10">
                    <el-col>
                      <hr style="border: solid 0.5px #EBEEF5">
                      <el-row :gutter="20">
                        <el-col :span="2">
                          <img class="headImage" src="@/assets/videoImage/hamburger.png">
                        </el-col>
                        <el-col :span="22">
                          <el-row :gutter="10">
                            <span>昵称</span>
                          </el-row>
                          <br>
                          <el-row :gutter="10">
                            <!--private String id;-->
                            <!--private String videoId ;-->
                            <!--private String userId;-->
                            <!--private String content;-->
                            <!--private Date date;-->
                            <span>{{ item.content }}</span>
                          </el-row>
                        </el-col>
                      </el-row>

                    </el-col>
                  </el-row>
                </el-col>
              </el-row>

            </el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>

    </div>
  </div>
</template>

<script>
import navMenu from '@/views/video/components/navMenu'
export default {
  name: 'PersonalCenter',
  components: { navMenu },
  data() {
    return {
      userForm: {},
      tableData5: [{
        id: '12987122',
        name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }, {
        id: '12987123',
        name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }, {
        id: '12987125',
        name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }, {
        id: '12987126',
        name: '好滋好味鸡蛋仔',
        category: '江浙小吃、小吃零食',
        desc: '荷兰优质淡奶，奶香浓而不腻',
        address: '上海市普陀区真北路',
        shop: '王小虎夫妻店',
        shopId: '10333'
      }]
    }
  },
  methods: {
    handleSubmitUserInfo() {

    }
  }
}
</script>

<style scoped>

</style>
