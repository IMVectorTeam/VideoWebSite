import { videoOp } from '@/api/video'
const state = {
  videoList: [],
  videoItem: {},
  videoListQuery: {
    // limit: 20,
    // offset: 0,
    // _total: 0,
    // _page: 0
  },
  videoForm: {}
}

const getters = {
  videoList: state => state.videoList,
  videoItem: state => state.videoItem,
  videoListQuery: state => state.videoListQuery,
  videoForm: state => state.videoForm
}
const mutations = {
  setVideoList: (state, value) => {
    state.videoList = value
  },
  setVideoItem: (state, value) => {
    state.videoItem = value
  },
  setVideoListQuery: (state, value) => {
    state.videoListQuery = value
  },
  setVideoForm: (state, value) => {
    state.videoFrom = value
  }
}

const actions = {

  async getVideoList({ commit, state }) {
    await videoOp.list(state.videoListQuery).then((res) => {
      res = res.data
      commit('setVideoList', {
        list: res.results,
        total: res.count,
        limit: res.limit,
        offset: res.offset,
        needPagination: true
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
