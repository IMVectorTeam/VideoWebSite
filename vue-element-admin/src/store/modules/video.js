import { Message, Notification } from 'element-ui'
import { videoOp, videoTypeOp, videoCommentOp, videoByTypeOp } from '@/api/video'
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
    limit: 20, // 每一页的条数
    page: 0 // 当前页的页码
    // offset: 0,
    // _total: 0,
  },
  videoForm: JSON.parse(JSON.stringify(defaultVideoForm)),

  videoTypeList: [],
  videoTypeItem: {},
  videoTypeListQuery: {
    limit: 20, // 每一页的条数
    page: 0 // 当前页的页码
    // offset: 0,
    // _total: 0,
  },
  videoTypeForm: {},

  videoCommentList: [],
  videoCommentItem: {},
  videoCommentListQuery: {
    limit: 20, // 每一页的条数
    page: 0 // 当前页的页码
    // offset: 0,
    // _total: 0,
  },
  videoCommentForm: {},
  videoListByTypeQuery: {
    limit: 20, // 每一页的条数
    page: 0, // 当前页的页码
    videoType: ''
    // _total: 0,
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
  videoCommentForm: state => state.videoCommentForm
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
  },

  setVideoCommentList: (state, { list, page, limit, needPagination = true, reset = false }) => {
    if (reset === true) {
      state.videoCommentList = []
    } else {
      state.videoCommentList = list
      if (needPagination) {
        Vue.set(state.videoCommentListQuery, 'page', page)
        Vue.set(state.videoCommentListQuery, 'limit', limit)
      }
    }
  },
  setVideoCommentItem: (state, value) => {
    state.videoCommentItem = value
  },
  setVideoCommentListQuery(state, { config, usingKey = false, k, v }) {
    console.log(config)
    if (!usingKey) {
      state.videoCommentListQuery = { ...state.videoCommentListQuery, ...config }
    } else {
      state.videoCommentListQuery[k] = v
    }
  },
  setVideoCommentForm: (state, value) => {
    state.videoCommentFrom = value
  }
}

const actions = {

  async getVideoList({ commit, state }) {
    await videoOp.list(state.videoListQuery).then((res) => {
      // console.log(res)
      console.log(res)
      res = res.data
      commit('setVideoList', {
        list: res.list,
        page: res.pageNum,
        limit: res.pageSize
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
        list: res.data
      })
    })
  },
  async getVideoCommentList({ commit, state }) {
    await videoCommentOp.list(state.videoCommentListQuery).then((res) => {
      // console.log(res)
      console.log(res)
      res = res.data
      commit('setVideoCommentList', {
        list: res.list,
        page: res.pageNum,
        limit: res.pageSize
      })
    })
  },
  async deleteVideoComment({ commit, dispatch, state, rootState }, uuid) {
    await videoCommentOp.delete({ id: uuid })
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
  async getVideoListByType({ commit, state }, { page, limit, videoType }) {
    Vue.set(state.videoListByTypeQuery, 'page', page)
    Vue.set(state.videoListByTypeQuery, 'limit', limit)
    Vue.set(state.videoListByTypeQuery, 'videoType', videoType)
    const query = JSON.parse(JSON.stringify(state.videoListByTypeQuery))

    await videoByTypeOp.list(query).then((res) => {
      res = res.data
      commit('setVideoList', {
        list: res.list,
        page: res.pageNum,
        limit: res.pageSize
      })
      console.log(state.videoList)
    })
  },
  getVideoItem({ commit, state }, { uuid, config }) {
    console.log(uuid)
    return videoOp.retrieve(uuid, config).then(res => {
      res = res.data
      res.is_valid = true
      console.log(res)
      commit('setVideoItem', res)
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
