import { Message } from 'element-ui'
import { videoOp, videoTypeOp } from '@/api/video'
import Vue from 'vue'

const state = {
  videoList: [],
  videoItem: {},
  videoListQuery: {
    limit: 20, // 每一页的条数
    page: 0 // 当前页的页码
    // offset: 0,
    // _total: 0,
  },
  videoForm: {},

  videoTypeList: [],
  videoTypeItem: {},
  videoTypeListQuery: {
    limit: 20, // 每一页的条数
    page: 0 // 当前页的页码
    // offset: 0,
    // _total: 0,
  },
  videoTypeForm: {}
}

const getters = {
  videoList: state => state.videoList,
  videoItem: state => state.videoItem,
  videoListQuery: state => state.videoListQuery,
  videoForm: state => state.videoForm,

  videoTypeList: state => state.videoTypeList,
  videoTypeItem: state => state.videoTypeItem,
  videoTypeListQuery: state => state.videoTypeListQuery,
  videoTypeForm: state => state.videoTypeForm
}
const mutations = {
  setVideoList: (state, { list, page, limit, needPagination = true, reset = false }) => {
    if (reset === true) {
      state.videoList = []
    } else {
      state.videoList = list
      if (needPagination) {
        Vue.set(state.videoListQuery, 'page', page)
        Vue.set(state.videoListQuery, 'limit', limit)
      }
    }
  },
  setVideoItem: (state, value) => {
    state.videoItem = value
  },
  setVideoListQuery: (state, value) => {
    state.videoListQuery = value
  },
  setVideoForm: (state, value) => {
    state.videoFrom = value
  },

  setVideoTypeList: (state, { list, page, limit, needPagination = true, reset = false }) => {
    if (reset === true) {
      state.videoTypeList = []
    } else {
      state.videoTypeList = list
      if (needPagination) {
        Vue.set(state.videoTypeListQuery, 'page', page)
        Vue.set(state.videoTypeListQuery, 'limit', limit)
      }
    }
  },
  setVideoTypeItem: (state, value) => {
    state.videoTypeItem = value
  },
  setVideoTypeListQuery: (state, value) => {
    state.videoTypeListQuery = value
  },
  setVideoTypeForm: (state, value) => {
    state.videoTypeFrom = value
  }
}

const actions = {

  async getVideoList({ commit, state }) {
    await videoOp.list(state.videoListQuery).then((res) => {
      // console.log(res)
      res = res.data
      commit('setVideoList', {
        list: res.list,
        page: res.pageNum,
        limit: res.pageSize
      })
    })
  },
  async deleteList({ commit, dispatch, state, rootState }, uuid) {
    await videoOp.delete({ id: uuid })
    Message({
      message: '视频删除成功',
      type: 'success'
    })
  },
  async getVideoTypeList({ commit, state }) {
    await videoTypeOp.list(state.videoTypeListQuery).then((res) => {
      commit('setVideoTypeList', {
        list: res.data
      })
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
