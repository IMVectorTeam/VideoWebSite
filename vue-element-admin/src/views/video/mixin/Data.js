import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('videoIndex', ['videoList', 'videoItem', 'videoListQuery', 'videoForm']),
    ...mapGetters('video', ['videoTypeList'])
  },
  methods: {
    ...mapActions({
      getVideoList: 'videoIndex/getVideoList',
      getVideoTypeList: 'video/getVideoTypeList'
    }),
    ...mapMutations('videoIndex', ['setVideoList'])
  }
}
