import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import '@/styles/index.scss' // global css

import App from './App'
import router from './router'
import store from './store'

import i18n from './lang' // Internationalization
import './icons' // icon
import './errorLog' // error log
import './permission' // permission control 在这里引入了permission
import './mock' // simulation data

import * as filters from './filters' // global filters

Vue.use(Element, {
  size: Cookies.get('size') || 'medium', // set element-ui default size
  i18n: (key, value) => i18n.t(key, value)
})

// register global utility filters.
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})
// console.log('main' + new Date().getUTCDate().toString())
Vue.config.productionTip = false

Vue.mixin({
  data() {
    const convertNullToBlack = (obj) => {
      return obj === undefined || obj === null ? {} : obj
    }
    return {
      convertNullToBlack
    }
  },
  methods: {
    dateFormat(row, column, cellValue, index) {
      const daterc = row[column.property]
      if (daterc != null) {
        const dateMat = new Date(parseInt(daterc, 10))
        const year = dateMat.getFullYear()
        const month = dateMat.getMonth() + 1
        const day = dateMat.getDate()
        const hh = dateMat.getHours()
        const mm = dateMat.getMinutes()
        const ss = dateMat.getSeconds()
        const timeFormat = year + '/' + month + '/' + day + ' ' + hh + ':' + mm + ':' + ss
        return timeFormat
      }
    },
    textDateFormat(val) {
      if (val != null) {
        const dateMat = new Date(parseInt(val, 10))
        const year = dateMat.getFullYear()
        const month = dateMat.getMonth() + 1
        const day = dateMat.getDate()
        const hh = dateMat.getHours()
        const mm = dateMat.getMinutes()
        const ss = dateMat.getSeconds()
        const timeFormat = year + '/' + month + '/' + day + ' ' + hh + ':' + mm + ':' + ss
        return timeFormat
      }
    },
    changeTagsTitle(title) {
      const idx = this.$store.state.tagsView.visitedViews.findIndex(v => v.fullPath === document.location.hash.replace('#', ''))
      this.$set(this.$store.state.tagsView.visitedViews[idx].meta, 'title', title)
      this.$set(this.$store.state.tagsView.visitedViews[idx], 'title', title)
    }
  }
})
// 在这里注册router
new Vue({
  el: '#app',
  router,
  store,
  i18n,
  render: h => h(App)
})
