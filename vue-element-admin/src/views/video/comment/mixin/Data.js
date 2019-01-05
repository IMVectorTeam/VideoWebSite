import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('Comment', ['videoCommentList', 'videoCommentItem', 'videoCommentListQuery', 'videoCommentForm'])
  },
  methods: {
    ...mapActions({
      getVideoCommentList: 'video/getVideoCommentList',
      deleteVideoComment: 'video/deleteVideoComment'
    }),
    ...mapMutations('Comment', ['setCommentList'])
  }
}
