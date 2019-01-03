import { videoUserOp } from '@/api/video'
import { Message } from 'element-ui'
import Vue from 'vue'
const state = {
  videoUserList: [],
  videoUserItem: {},
  videoUserListQuery: {
    limit: 20, // 每一页的条数
    page: 0 // 当前页的页码
    // offset: 0,
    // _total: 0,
  },
  videoUserForm: {}
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
  setVideoUserForm: (state, value) => {
    state.videoFrom = value
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
  async deleteUserList({ commit, dispatch, state, rootState }, uuid) {
    await videoUserOp.delete({ id: uuid })
    Message({
      message: '用户删除成功',
      type: 'success'

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
