import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('video', ['videoList', 'videoItem', 'videoListQuery', 'videoForm'])
  },
  methods: {
    ...mapActions({
      getVideoUserList: 'video/getVideoList',
      deleteUserList: 'video/deleteList'
    }),
    ...mapMutations('video', ['setVideoList'])
  }
}
