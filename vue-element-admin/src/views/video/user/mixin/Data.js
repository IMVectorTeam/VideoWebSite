import { mapGetters, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters('videoUser', ['videoUserList', 'videoUserItem', 'videoUserListQuery', 'videoUserForm'])
  },
  methods: {
    ...mapActions({
      getVideoUserList: 'videoUser/getVideoUserList',
      deleteUser: 'videoUser/deleteUser',
      createUser: 'videoUser/createUser'
    }),
    ...mapMutations('videoUser', ['setVideoUserList', 'setVideoUserForm'])
  }
}
