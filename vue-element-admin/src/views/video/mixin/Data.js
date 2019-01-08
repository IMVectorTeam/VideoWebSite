import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('video', ['videoList', 'videoItem', 'videoListQuery', 'videoForm', 'typeVideoList',
      'videoTypeList', 'videoCommentList', 'videoCommentListQuery', 'userIdVideoList']),
    ...mapGetters('videoUser', ['videoUserItem']),
    ...mapGetters('comment', ['commentList', 'commentItem', 'userIdCommentList', 'commentListQuery'])
  },
  methods: {
    ...mapActions({
      getVideoList: 'video/getVideoList',
      createVideo: 'video/createVideo',
      getVideoItem: 'video/getVideoItem',
      getVideoTypeList: 'video/getVideoTypeList',
      getTypeVideoList: 'video/getTypeVideoList',
      getVideoCommentList: 'video/getVideoCommentList',
      getUserByVideoId: 'videoUser/getUserByVideoId',
      updateVideoUser: 'videoUser/updateVideoUser',
      getUserIdVideoList: 'video/getUserIdVideoList',
      deleteVideo: 'video/deleteVideo',
      getCommentList: 'comment/getCommentList',
      getUserIdCommentList: 'comment/getUserIdCommentList',
      createComment: 'comment/createComment',
      deleteComment: 'comment/deleteComment',
      getUserById: 'videoUser/getUserById'
    }),
    ...mapMutations('video', ['setVideoList', 'setVideoListQuery', 'setVideoCommentListQuery', 'setUserIdVideoListQuery', 'setTypeVideoListQuery']),
    ...mapMutations('videoUser', ['setVideoUserForm']),
    ...mapMutations('comment', ['setCommentForm', 'setUserIdCommentListQuery'])
  }
}
