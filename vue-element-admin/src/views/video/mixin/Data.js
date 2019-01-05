import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('video', ['videoList', 'videoItem', 'videoListQuery', 'videoForm', 'videoTypeList', 'videoCommentListQuery'])
  },
  methods: {
    ...mapActions({
      getVideoList: 'video/getVideoList',
      createVideo: 'video/createVideo',
      getVideoItem: 'video/getVideoItem',
      getVideoTypeList: 'video/getVideoTypeList',
      getVideoListByType: 'video/getVideoListByType',
      getVideoCommentList: 'video/getVideoCommentList'
    }),
    ...mapMutations('video', ['setVideoList', 'setVideoListQuery', 'setVideoCommentListQuery'])
  }
}
