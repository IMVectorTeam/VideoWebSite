import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('video', ['videoList', 'videoItem', 'videoListQuery', 'videoForm', 'videoTypeList'])
  },
  methods: {
    ...mapActions({
      getVideoList: 'video/getVideoList',
      createVideo: 'video/createVideo',
      getVideoTypeList: 'video/getVideoTypeList',
      getVideoListByType: 'video/getVideoListByType'
    }),
    ...mapMutations('videoIndex', ['setVideoList'])
  }
}
