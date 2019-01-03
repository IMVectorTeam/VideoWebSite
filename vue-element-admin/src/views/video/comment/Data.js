import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('Comment', ['CommentList', 'CommentItem', 'CommentListQuery', 'CommentForm'])
  },
  methods: {
    ...mapActions({
      getCommentList: 'videoUser/getCommentList',
      deleteCommentList: 'videoUser/deleteCommentList'
    }),
    ...mapMutations('Comment', ['setCommentList'])
  }
}
