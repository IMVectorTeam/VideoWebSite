import { videoUserOp } from '@/api/video'
const state = {
  videoUserList: [],
  videoUserItem: {},
  videoUserListQuery: {
    limit: 20, // 每一页的条数
    // offset: 0,
    // _total: 0,
    _page: 0
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
  setVideoUserList: (state, value) => {
    state.videoUserList = value
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
      // res = res.data
      console.log(res)
      console.log(res.data)
      commit('setVideoUserList', res.data)
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
