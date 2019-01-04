import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('video', ['videoList', 'videoItem', 'videoListQuery', 'videoForm'])
  },
  methods: {
    ...mapActions({
      getVideoList: 'video/getVideoList',
      deleteVideo: 'video/deleteVideo'
    }),
    ...mapMutations('video', ['setVideoList'])
  }
}
