import { videoCommentOp, commentByUserIdOp } from '@/api/video'
import { Message, Notification } from 'element-ui'
import Vue from 'vue'

export const defaultCommentForm = {
  id: '',
  videoId: '',
  userId: '',
  content: '',
  date: ''
}
const state = {
  commentList: [],
  commentItem: {},
  commentListQuery: {
    limit: 10, // 每一页的条数
    page: 0, // 当前页的页码
    total: 0
  },
  commentForm: JSON.parse(JSON.stringify(defaultCommentForm)),

  userIdCommentList: [],
  userIdCommentItem: {},
  userIdCommentListQuery: {
    limit: 5, // 每一页的条数
    page: 0, // 当前页的页码
    total: 0,
    userId: ''
  }
}

const getters = {
  commentList: state => state.commentList,
  commentItem: state => state.commentItem,
  commentListQuery: state => state.commentListQuery,
  commentForm: state => state.commentForm,

  userIdCommentList: state => state.userIdCommentList,
  userIdCommentItem: state => state.userIdCommentItem,
  userIdCommentListQuery: state => state.userIdCommentListQuery
}
const mutations = {
  setCommentList: (state, { list, page, limit, total, needPagination = true, reset = false }) => {
    if (reset === true) {
      state.commentList = []
    } else {
      state.commentList = list
      if (needPagination) {
        Vue.set(state.commentListQuery, 'page', page)
        Vue.set(state.commentListQuery, 'total', total)
        state.commentListQuery.limit = limit
        // Vue.set(state.commentListQuery, '_total', total)
        // state.commentListQuery.offset = offset
      }
    }
  },
  setCommentItem: (state, value) => {
    state.commentItem = value
  },
  setCommentListQuery: (state, { config, usingKey = false, k, v }) => {
    if (!usingKey) {
      state.commentListQuery = { ...state.commentListQuery, ...config }
    } else {
      state.commentListQuery[k] = v
    }
  },
  setCommentForm: (state, { k, v, usingKey = false, pF, reset = false }) => {
    if (reset) {
      state.commentForm = JSON.parse(JSON.stringify(defaultCommentForm))
      return
    }
    if (usingKey) {
      Vue.set(state.commentForm, k, v)
    } else {
      state.commentForm = pF
    }
  },

  setUserIdCommentList: (state, { list, page, limit, total, needPagination = true, reset = false }) => {
    if (reset === true) {
      state.userIdCommentList = []
    } else {
      state.userIdCommentList = list
      if (needPagination) {
        Vue.set(state.userIdCommentListQuery, 'page', page)
        Vue.set(state.userIdCommentListQuery, 'total', total)
        state.userIdCommentListQuery.limit = limit
      }
    }
  },
  setUserIdCommentItem: (state, value) => {
    state.userIdCommentItem = value
  },
  setUserIdCommentListQuery: (state, { config, usingKey = false, k, v }) => {
    if (!usingKey) {
      state.userIdCommentListQuery = { ...state.userIdCommentListQuery, ...config }
    } else {
      state.userIdCommentListQuery[k] = v
    }
  }
}

const actions = {

  async getCommentList({ commit, state }) {
    await videoCommentOp.list(state.commentListQuery).then((res) => {
      res = res.data
      commit('setCommentList', {
        list: res.list,
        page: res.pageNum,
        limit: res.pageSize,
        total: res.total
      })
    })
  },
  async deleteComment({ commit, dispatch, state, rootState }, uuid) {
    await videoCommentOp.delete({ id: uuid })
    Message({
      message: '评论删除成功',
      type: 'success'

    })
  },
  createComment({ commit, dispatch, state, rootState }) {
    return videoCommentOp.create(state.commentForm).then(res => {
      if (res.data === true) {
        Notification({
          title: '成功',
          message: '评论发表成功',
          type: 'success'
        })
        commit('setCommentForm', { reset: true })
      } else {
        Notification({
          title: '失败',
          message: '评论发表失败',
          type: 'warning'
        })
      }
    })
  },
  updateComment({ commit, dispatch, state, rootState }, uuid) {
    return videoCommentOp.update(state.commentForm, uuid).then(res => {
      Notification({
        title: '成功！',
        message: '信息修改成功',
        type: 'success'
      })
      commit('setCommentForm', { reset: true })
    })
  },
  getUserIdCommentList({ commit, state }) {
    return commentByUserIdOp.list(state.userIdCommentListQuery).then(res => {
      res = res.data
      commit('setUserIdCommentList', {
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
