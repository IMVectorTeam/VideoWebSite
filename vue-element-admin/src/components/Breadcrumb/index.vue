<template>
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <transition-group name="breadcrumb">
      <el-breadcrumb-item v-for="(item,index) in levelList" v-if="item.meta.title" :key="item.path">
        <span v-if="item.redirect==='noredirect'||index==levelList.length-1" class="no-redirect">{{ generateTitle(item.meta.title) }}</span>
        <a v-else @click.prevent="handleLink(item)">{{ generateTitle(item.meta.title) }}</a>
      </el-breadcrumb-item>
      <!--面包屑导航-->
      <!--基于位置(Location-based)的面包屑向用户显示他们在网站的哪一个级别页面-->
      <!--。它们一般典型的用于有多个级别（一般是多于两级）的导航方案。-->
    </transition-group>
  </el-breadcrumb>
</template>

<script>
import { generateTitle } from '@/utils/i18n'
import pathToRegexp from 'path-to-regexp'

export default {
  data() {
    return {
      levelList: null
    }
  },
  watch: {
    $route() {
      this.getBreadcrumb()
    }
  },
  created() {
    this.getBreadcrumb()
  },
  methods: {
    generateTitle,
    getBreadcrumb() {
      let matched = this.$route.matched.filter(item => {
        if (item.name) {
          return true
        }
      })
      const first = matched[0]
      if (first && first.name.trim().toLocaleLowerCase() !== 'Dashboard'.toLocaleLowerCase()) {
        matched = [{ path: '/dashboard', meta: { title: 'dashboard' }}].concat(matched)
      }
      this.levelList = matched
    },
    pathCompile(path) {
      // To solve this problem https://github.com/PanJiaChen/vue-element-admin/issues/561
      const { params } = this.$route
      var toPath = pathToRegexp.compile(path)
      return toPath(params)
    },
    handleLink(item) {
      const { redirect, path } = item
      if (redirect) {
        this.$router.push(redirect)
        return
      }
      this.$router.push(this.pathCompile(path))
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .app-breadcrumb.el-breadcrumb {
    display: inline-block;
    font-size: 14px;
    line-height: 50px;
    margin-left: 10px;
    .no-redirect {
      color: #97a8be;
      cursor: text;
    }
  }
</style>
