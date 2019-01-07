import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import errorLog from './modules/errorLog'
import permission from './modules/permission'
import tagsView from './modules/tagsView'
import user from './modules/user'
import getters from './getters'
import videoUser from './modules/videoUser'
import video from './modules/video'
import comment from './modules/comment'
// import createPersistedState from 'vuex-persistedstate'
Vue.use(Vuex)

const store = new Vuex.Store({
  // 使用sessionStorge 加密路刷新会出现问题
  // plugins: [createPersistedState({
  //   storage: window.sessionStorage
  // })],
  // 默认使用localStorge 加密路刷新会出现问题
  // plugins: [createPersistedState()],
  modules: {
    app,
    errorLog,
    permission,
    tagsView,
    user,
    videoUser,
    video,
    comment
  },
  getters
})

export default store
