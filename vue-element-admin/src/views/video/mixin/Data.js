import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('video', ['videoList', 'videoItem', 'videoListQuery', 'videoForm', 'videoTypeList', 'videoCommentList', 'videoCommentListQuery']),
    ...mapGetters('videoUser', ['videoUserItem']),
    ...mapGetters('comment', ['commentList', 'commentItem'])
  },
  methods: {
    ...mapActions({
      getVideoList: 'video/getVideoList',
      createVideo: 'video/createVideo',
      getVideoItem: 'video/getVideoItem',
      getVideoTypeList: 'video/getVideoTypeList',
      getVideoListByType: 'video/getVideoListByType',
      getVideoCommentList: 'video/getVideoCommentList',
      getUserByVideoId: 'videoUser/getUserByVideoId',
      updateVideoUser: 'videoUser/updateVideoUser',
      getVideoByUserId: 'video/getVideoByUserId',
      deleteVideo: 'video/deleteVideo',
      getCommentList: 'comment/getCommentList',
      getCommentByUserId: 'comment/getCommentByUserId',
      createComment: 'comment/createComment',
      deleteComment: 'comment/deleteComment',
      getUserById: 'videoUser/getUserById'
    }),
    ...mapMutations('video', ['setVideoList', 'setVideoListQuery', 'setVideoCommentListQuery']),
    ...mapMutations('videoUser', ['setVideoUserForm']),
    ...mapMutations('comment', ['setCommentForm'])
  }
}
