import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('videoUser', ['videoUserList', 'videoUserItem', 'videoUserListQuery', 'videoUserForm'])
  },
  methods: {
    ...mapActions({
      getVideoUserList: 'videoUser/getVideoUserList'
    }),
    ...mapMutations('videoUser', ['setVideoUserList'])
  }
}
