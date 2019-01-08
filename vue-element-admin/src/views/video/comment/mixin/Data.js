import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('comment', ['commentList', 'commentItem', 'commentListQuery', 'commentForm'])
  },
  methods: {
    ...mapActions({
      getCommentList: 'comment/getCommentList',
      deleteComment: 'comment/deleteComment'
    }),
    ...mapMutations('comment', ['setCommentListQuery'])
  }
}
