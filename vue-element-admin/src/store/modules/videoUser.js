import { videoUserOp, videoUserByVideoIdOp } from '@/api/video'
import { Message, Notification } from 'element-ui'
import Vue from 'vue'

export const defaultVideoUserForm = {
  email: '',
  password: '',
  name: '',
  sex: '',
  id: ''// 这一个加上与不加后台只要在参数列表中使用 @requestBody就可以封装成一个对象
}
const state = {
  videoUserList: [],
  videoUserItem: {},
  videoUserListQuery: {
    limit: 20, // 每一页的条数
    page: 0 // 当前页的页码
    // offset: 0,
    // _total: 0,
  },
  videoUserForm: JSON.parse(JSON.stringify(defaultVideoUserForm))
}

const getters = {
  videoUserList: state => state.videoUserList,
  videoUserItem: state => state.videoUserItem,
  videoUserListQuery: state => state.videoUserListQuery,
  videoUserForm: state => state.videoUserForm
}
const mutations = {
  setVideoUserList: (state, { list, page, limit, needPagination = true, reset = false }) => {
    if (reset === true) {
      state.videoUserList = []
    } else {
      state.videoUserList = list
      if (needPagination) {
        Vue.set(state.videoUserListQuery, 'page', page)
        Vue.set(state.videoUserListQuery, 'limit', limit)
        // state.videoUserListQuery.limit = limit
        // Vue.set(state.videoUserListQuery, '_total', total)
        // state.videoUserListQuery.offset = offset
      }
    }
  },
  setVideoUserItem: (state, value) => {
    state.videoUserItem = value
  },
  setVideoUserListQuery: (state, value) => {
    state.videoUserListQuery = value
  },
  setVideoUserForm: (state, { k, v, usingKey = false, pF, reset = false }) => {
    if (reset) {
      state.videoUserForm = JSON.parse(JSON.stringify(defaultVideoUserForm))
      return
    }
    if (usingKey) {
      Vue.set(state.videoUserForm, k, v)
    } else {
      state.videoUserForm = pF
    }
  }
}

const actions = {

  async getVideoUserList({ commit, state }) {
    await videoUserOp.list(state.videoUserListQuery).then((res) => {
      // console.log(res)
      res = res.data
      commit('setVideoUserList', {
        list: res.list,
        page: res.pageNum,
        limit: res.pageSize
      })
    })
  },
  async deleteUser({ commit, dispatch, state, rootState }, uuid) {
    await videoUserOp.delete({ id: uuid })
    Message({
      message: '用户删除成功',
      type: 'success'

    })
  },
  createUser({ commit, dispatch, state, rootState }) {
    return videoUserOp.create(state.videoUserForm).then(res => {
      if (res.data === true) {
        Notification({
          title: '成功',
          message: '注册成功',
          type: 'success'
        })
        commit('setVideoUserForm', { reset: true })
      } else {
        Notification({
          title: '失败',
          message: '注册失败',
          type: 'warning'
        })
      }
    })
  },
  updateVideoUser({ commit, dispatch, state, rootState }, uuid) {
    // if (uuid === undefined) uuid = state.videoUser.uuid
    return videoUserOp.update(state.videoUserForm, uuid).then(res => {
      Notification({
        title: '成功！',
        message: '信息修改成功',
        type: 'success'
      })
      commit('setVideoUserForm', { reset: true })
    })
  },
  getUserByVideoId({ commit, state }, { videoId, config }) {
    console.log(videoId)
    return videoUserByVideoIdOp.retrieve(videoId, config).then(res => {
      res = res.data
      res.is_valid = true
      console.log(res)
      commit('setVideoUserItem', res)
    })
  },
  getUserById({ commit, state }, { userId, config }) {
    console.log(userId)
    return videoUserOp.retrieve(userId, config).then(res => {
      res = res.data
      res.is_valid = true
      console.log(res)
      commit('setVideoUserItem', res)
    })
  }

}

export default {
  namespaced: true,
  state,
  mutations,
  getters,
  actions
}
