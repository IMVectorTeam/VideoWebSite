import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('video', ['videoList', 'videoItem', 'videoListQuery', 'videoForm', 'videoTypeList', 'videoCommentList', 'videoCommentListQuery']),
    ...mapGetters('videoUser', ['videoUserItem'])
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
      getVideoByUserId: 'video/getVideoByUserId'
    }),
    ...mapMutations('video', ['setVideoList', 'setVideoListQuery', 'setVideoCommentListQuery']),
    ...mapMutations('videoUser', ['setVideoUserForm'])
  }
}
