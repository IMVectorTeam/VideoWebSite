import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('videoIndex', ['videoList', 'videoItem', 'videoListQuery', 'videoForm'])
  },
  methods: {
    ...mapActions({
      getVideoList: 'videoIndex/getVideoList'
    }),
    ...mapMutations('videoIndex', ['setVideoList'])
  }
}
