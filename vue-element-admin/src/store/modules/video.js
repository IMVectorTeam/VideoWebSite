import { Message, Notification } from 'element-ui'
import { videoOp, videoTypeOp, videoIdCommentOp, videoByTypeOp, videoUserIdOP } from '@/api/video'
import Vue from 'vue'

export const defaultVideoForm = {
  id: '',
  name: '',
  image: '',
  address: '',
  time: '',
  category: '',
  state: '',
  userId: '',
  date: ''
}

const state = {
  videoList: [],
  videoItem: {},
  videoListQuery: {
    limit: 10, // 每一页的条数
    page: 0, // 当前页的页码
    total: 0
  },
  videoForm: JSON.parse(JSON.stringify(defaultVideoForm)),

  videoTypeList: [],
  videoTypeItem: {},
  videoTypeListQuery: {
    limit: 10, // 每一页的条数
    page: 0, // 当前页的页码
    total: 0
    // offset: 0,
    // _total: 0,
  },
  videoTypeForm: {},

  videoCommentList: [],
  videoCommentItem: {},
  videoCommentListQuery: {
    limit: 10, // 每一页的条数
    page: 0, // 当前页的页码
    total: 0
    // offset: 0,
    // _total: 0,
  },
  videoCommentForm: {},
  videoListByTypeQuery: {
    limit: 10, // 每一页的条数
    page: 0, // 当前页的页码
    videoType: ''
    // _total: 0,
  },
  userIdVideoList: [],
  userIdVideoItem: {},
  userIdVideoListQuery: {
    userId: '',
    limit: 5, // 每一页的条数
    page: 0, // 当前页的页码
    total: 0
  },
  typeVideoList: [],
  typeVideoItem: {},
  typeVideoListQuery: {
    videoType: '',
    limit: 10, // 每一页的条数
    page: 0, // 当前页的页码
    total: 0
  }
}

const getters = {
  videoList: state => state.videoList,
  videoItem: state => state.videoItem,
  videoListQuery: state => state.videoListQuery,
  videoForm: state => state.videoForm,

  videoTypeList: state => state.videoTypeList,
  videoTypeItem: state => state.videoTypeItem,
  videoTypeListQuery: state => state.videoTypeListQuery,
  videoTypeForm: state => state.videoTypeForm,

  videoCommentList: state => state.videoCommentList,
  videoCommentItem: state => state.videoCommentItem,
  videoCommentListQuery: state => state.videoCommentListQuery,
  videoCommentForm: state => state.videoCommentForm,

  userIdVideoList: state => state.userIdVideoList,
  userIdVideoItem: state => state.userIdVideoItem,
  userIdVideoListQuery: state => state.userIdVideoListQuery,

  typeVideoList: state => state.typeVideoList,
  typeVideoItem: state => state.typeVideoItem,
  typeVideoListQuery: state => state.typeVideoListQuery
}
const mutations = {
  setVideoList: (state, { list, page, limit, total, needPagination = true, reset = false }) => {
    if (reset === true) {
      state.videoList = []
    } else {
      state.videoList = list
      if (needPagination) {
        Vue.set(state.videoListQuery, 'page', page)
        Vue.set(state.videoListQuery, 'total', total)
        state.videoListQuery.limit = limit
      }
    }
  },
  setVideoItem: (state, value) => {
    state.videoItem = value
  },
  setVideoListQuery: (state, { config, usingKey = false, k, v }) => {
    if (!usingKey) {
      state.videoListQuery = { ...state.videoListQuery, ...config }
    } else {
      state.videoListQuery[k] = v
    }
  },
  setVideoForm: (state, { k, v, isEdit = false, pF, reset = false }) => {
    if (reset) {
      state.videoForm = JSON.parse(JSON.stringify(defaultVideoForm))
      return
    }
    if (!isEdit) {
      Vue.set(state.videoForm, k, v)
    } else {
      state.videoForm = pF
    }
  },

  setVideoTypeList: (state, { list, page, limit, total, needPagination = true, reset = false }) => {
    if (reset === true) {
      state.videoTypeList = []
    } else {
      state.videoTypeList = list
      if (needPagination) {
        Vue.set(state.videoTypeListQuery, 'page', page)
        Vue.set(state.videoTypeListQuery, 'total', total)
        state.videoTypeListQuery.limit = limit
      }
    }
  },
  setVideoTypeItem: (state, value) => {
    state.videoTypeItem = value
  },
  setVideoTypeListQuery: (state, { config, usingKey = false, k, v }) => {
    if (!usingKey) {
      state.videoTypeListQuery = { ...state.videoTypeListQuery, ...config }
    } else {
      state.videoTypeListQuery[k] = v
    }
  },
  setVideoTypeForm: (state, value) => {
    state.videoTypeFrom = value
  },

  setVideoCommentList: (state, { list, page, limit, total, needPagination = true, reset = false }) => {
    if (reset === true) {
      state.videoCommentList = []
    } else {
      state.videoCommentList = list
      if (needPagination) {
        Vue.set(state.videoCommentListQuery, 'page', page)
        Vue.set(state.videoCommentListQuery, 'total', total)
        state.videoCommentListQuery.limit = limit
      }
    }
  },
  setVideoCommentItem: (state, value) => {
    state.videoCommentItem = value
  },
  setVideoCommentListQuery(state, { config, usingKey = false, k, v }) {
    if (!usingKey) {
      state.videoCommentListQuery = { ...state.videoCommentListQuery, ...config }
    } else {
      state.videoCommentListQuery[k] = v
    }
  },
  setVideoCommentForm: (state, value) => {
    state.videoCommentFrom = value
  },

  setUserIdVideoList: (state, { list, page, limit, total, needPagination = true, reset = false }) => {
    if (reset === true) {
      state.userIdVideoList = []
    } else {
      state.userIdVideoList = list
      if (needPagination) {
        Vue.set(state.userIdVideoListQuery, 'page', page)
        Vue.set(state.userIdVideoListQuery, 'total', total)
        state.userIdVideoListQuery.limit = limit
      }
    }
  },
  setUserIdVideoItem: (state, value) => {
    state.userIdVideoItem = value
  },
  setUserIdVideoListQuery: (state, { config, usingKey = false, k, v }) => {
    if (!usingKey) {
      state.userIdVideoListQuery = { ...state.userIdVideoListQuery, ...config }
    } else {
      state.userIdVideoListQuery[k] = v
    }
  },

  setTypeVideoList: (state, { list, page, limit, total, needPagination = true, reset = false }) => {
    if (reset === true) {
      state.typeVideoList = []
    } else {
      state.typeVideoList = list
      if (needPagination) {
        Vue.set(state.typeVideoListQuery, 'page', page)
        Vue.set(state.typeVideoListQuery, 'total', total)
        state.typeVideoListQuery.limit = limit
      }
    }
  },
  setTypeVideoItem: (state, value) => {
    state.typeVideoItem = value
  },
  setTypeVideoListQuery: (state, { config, usingKey = false, k, v }) => {
    if (!usingKey) {
      state.typeVideoListQuery = { ...state.typeVideoListQuery, ...config }
    } else {
      state.typeVideoListQuery[k] = v
    }
  }

}

const actions = {

  async getVideoList({ commit, state }) {
    await videoOp.list(state.videoListQuery).then((res) => {
      res = res.data
      commit('setVideoList', {
        list: res.list,
        page: res.pageNum,
        limit: res.pageSize,
        total: res.total
      })
    })
  },
  async deleteVideo({ commit, dispatch, state, rootState }, uuid) {
    await videoOp.delete({ id: uuid })
    Message({
      message: '视频删除成功',
      type: 'success'
    })
  },
  async getVideoTypeList({ commit, state }) {
    await videoTypeOp.list(state.videoTypeListQuery).then((res) => {
      commit('setVideoTypeList', {
        list: res.data,
        page: res.pageNum,
        limit: res.pageSize,
        total: res.total
      })
    })
  },
  async getVideoCommentList({ commit, state }) {
    await videoIdCommentOp.list(state.videoCommentListQuery).then((res) => {
      res = res.data
      commit('setVideoCommentList', {
        list: res.list,
        page: res.pageNum,
        limit: res.pageSize,
        total: res.total
      })
    })
  },
  async deleteVideoComment({ commit, dispatch, state, rootState }, uuid) {
    await videoIdCommentOp.delete({ id: uuid })
    Message({
      message: '视频删除成功',
      type: 'success'
    })
  },
  createVideo({ commit, dispatch, state, rootState }) {
    return videoOp.create(state.videoForm).then(res => {
      res = res.data
      if (res.flag === true) {
        Notification({
          title: '成功',
          message: '视频上传成功！',
          type: 'success'
        })
      } else {
        Notification({
          title: '失败',
          message: '视频上传失败！',
          type: 'warning'
        })
      }
      commit('setVideoForm', { reset: true })
    })
  },
  async getTypeVideoList({ commit, state }) {
    // { page, limit, videoType }
    // Vue.set(state.typeVideoListQuery, 'page', page)
    // Vue.set(state.typeVideoListQuery, 'limit', limit)
    // Vue.set(state.typeVideoListQuery, 'videoType', videoType)
    const query = JSON.parse(JSON.stringify(state.typeVideoListQuery))
    await videoByTypeOp.list(query).then((res) => {
      res = res.data
      commit('setTypeVideoList', {
        list: res.list,
        page: res.pageNum,
        limit: res.pageSize,
        total: res.total
      })
    })
  },
  getVideoItem({ commit, state }, { uuid, config }) {
    return videoOp.retrieve(uuid, config).then(res => {
      res = res.data
      commit('setVideoItem', res)
    })
  },
  async getUserIdVideoList({ commit, state }) {
    await videoUserIdOP.list(state.userIdVideoListQuery).then((res) => {
      res = res.data
      commit('setUserIdVideoList', {
        list: res.list,
        page: res.pageNum,
        limit: res.pageSize,
        total: res.total
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
