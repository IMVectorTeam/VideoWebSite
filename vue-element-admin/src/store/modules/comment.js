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
    limit: 20, // 每一页的条数
    page: 0 // 当前页的页码
    // offset: 0,
    // _total: 0,
  },
  commentForm: JSON.parse(JSON.stringify(defaultCommentForm))
}

const getters = {
  commentList: state => state.commentList,
  commentItem: state => state.commentItem,
  commentListQuery: state => state.commentListQuery,
  commentForm: state => state.commentForm
}
const mutations = {
  setCommentList: (state, { list, page, limit, needPagination = true, reset = false }) => {
    if (reset === true) {
      state.commentList = []
    } else {
      state.commentList = list
      if (needPagination) {
        Vue.set(state.commentListQuery, 'page', page)
        Vue.set(state.commentListQuery, 'limit', limit)
        // state.commentListQuery.limit = limit
        // Vue.set(state.commentListQuery, '_total', total)
        // state.commentListQuery.offset = offset
      }
    }
  },
  setCommentItem: (state, value) => {
    state.commentItem = value
  },
  setCommentListQuery: (state, value) => {
    state.commentListQuery = value
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
  }
}

const actions = {

  async getCommentList({ commit, state }) {
    await videoCommentOp.list(state.commentListQuery).then((res) => {
      // console.log(res)
      res = res.data
      commit('setCommentList', {
        list: res.list,
        page: res.pageNum,
        limit: res.pageSize
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
          message: '注册成功',
          type: 'success'
        })
        commit('setCommentForm', { reset: true })
      } else {
        Notification({
          title: '失败',
          message: '注册失败',
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
  getCommentByUserId({ commit, state }, { userId, page, limit }) {
    return commentByUserIdOp.list({ userId, page, limit }).then(res => {
      res = res.data
      commit('setCommentList', {
        list: res.list,
        page: res.pageNum,
        limit: res.pageSize
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
