<template>
  <div class="app-container">
    <el-menu :default-active="activeIndex" style="margin-top: -20px;" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">处理中心</el-menu-item>
      <el-submenu index="2">
        <template slot="title">我的工作台</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-menu-item index="2-2">选项2</el-menu-item>
        <el-menu-item index="2-3">选项3</el-menu-item>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项1</el-menu-item>
          <el-menu-item index="2-4-2">选项2</el-menu-item>
          <el-menu-item index="2-4-3">选项3</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="3" disabled>消息中心</el-menu-item>

      <el-menu-item index="4" style="float: right">
        <img src="@/assets/videoImage/hamburger.png" style="height: 50px;border-radius: 50%;">
      </el-menu-item>
      <el-submenu style="float: right" index="5">
        <template slot="title">昵称</template>
        <el-menu-item index="5-1">个人中心</el-menu-item>
        <el-menu-item index="5-2">退出登录</el-menu-item>
      </el-submenu>
    </el-menu>

    <div style="height: 160px;width: 100%;background: url('src/assets/videoImage/head.png') no-repeat;background-size: 100% 100%;"/>
    <!--<img src="src/assets/videoImage/head.png" style="margin: 0;" alt="">-->
    <br>
    <br>
    <el-row :gutter="10">
      <el-col :span="4" :offset="4">

        <img src="src/assets/videoImage/hamburger.png" style="border-radius: 10%" alt="">
        <el-button type="info" style="margin-left: 60px">更换头像</el-button>
      </el-col>
      <el-col :span="12" >
        <el-form ref="form" label-width="80px">
          <el-form-item label="用户名">
            <el-input />
          </el-form-item>
          <el-form-item label="签名">
            <el-input />
          </el-form-item>
          <el-form-item label="性别">
            <el-input />
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input />
          </el-form-item>
          <el-form-item label="密码">
            <el-input />
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: 'PersonalCenter'
}
</script>

<style scoped>

</style>
