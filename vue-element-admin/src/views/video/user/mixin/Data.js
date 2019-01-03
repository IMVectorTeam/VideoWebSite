import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('videoUser', ['videoUserList', 'videoUserItem', 'videoUserListQuery', 'videoUserForm'])
  },
  methods: {
    ...mapActions({
      getVideoUserList: 'videoUser/getVideoUserList',
      deleteUserList: 'videoUser/deleteUserList',
      createUser: 'videoUser/createUser'
    }),
    ...mapMutations('videoUser', ['setVideoUserList', 'setVideoUserForm'])
  }
}
