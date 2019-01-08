import { loginByUsername, getUserInfo, loginByAdminEmail } from '@/api/login' // logout,
import { getToken, setToken, removeToken } from '@/utils/auth'
import { Message } from 'element-ui'
const userForm = {
  id: '',
  name: '',
  password: '',
  sex: '',
  email: '',
  image: '',
  introduce: ''
}

const user = {
  state: {
    id: '',
    email: '',
    user: '',
    status: '',
    code: '',
    token: getToken(),
    name: '',
    avatar: '',
    introduction: '',
    roles: [],
    setting: {
      articlePlatform: []
    },
    userInfo: JSON.parse(JSON.stringify(userForm))
  },

  mutations: {
    SET_CODE: (state, code) => {
      state.code = code
    },
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_INTRODUCTION: (state, introduction) => {
      state.introduction = introduction
    },
    SET_SETTING: (state, setting) => {
      state.setting = setting
    },
    SET_STATUS: (state, status) => {
      state.status = status
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_EMAIL: (state, email) => {
      state.email = email
    },
    SET_ID: (state, id) => {
      state.id = id
    },
    SET_USER_INFO: (state, userInfo) => {
      state.userInfo = userInfo
    }
  },

  actions: {
    // 用户名登录
    LoginByUsername({ commit }, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        loginByUsername(username, userInfo.password).then(response => {
          const map = response.data
          console.log('++++++++++++++++++用户信息+++++++++++++++++')
          console.log(map)
          console.log('++++++++++++++++++用户信息+++++++++++++++++')
          if (map.flag === true) {
            sessionStorage.setItem('roles', map.roles)
            commit('SET_TOKEN', map.token)
            // commit('SET_TOKEN', 'admin')
            setToken(response.data.token)
            commit('SET_ID', map.data.id)
            commit('SET_NAME', map.data.name)
            commit('SET_AVATAR', map.data.image)
            commit('SET_INTRODUCTION', map.data.introduction)
            commit('SET_USER_INFO', JSON.parse(JSON.stringify(map.data)))
            sessionStorage.setItem('avatar', map.data.image)
            sessionStorage.setItem('userName', map.data.name)
            sessionStorage.setItem('userId', map.data.id)
            sessionStorage.setItem('userEmail', map.data.email)
            sessionStorage.setItem('userInfo', JSON.stringify(map.data))
            resolve()
          } else {
            Message({
              message: map.data,
              type: 'warning'
            })
            reject(map.data)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 用户名登录
    LoginByAdminEmail({ commit }, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        loginByAdminEmail(username, userInfo.password).then(response => {
          const map = response.data
          console.log('++++++++++++++++++用户信息+++++++++++++++++')
          console.log(map)
          console.log('++++++++++++++++++用户信息+++++++++++++++++')
          if (map.flag === true) {
            sessionStorage.setItem('roles', map.roles)
            commit('SET_TOKEN', map.token)
            // commit('SET_TOKEN', 'admin')
            setToken(response.data.token)
            commit('SET_ID', map.data.id)
            commit('SET_NAME', map.data.name)
            commit('SET_AVATAR', map.data.image)
            commit('SET_INTRODUCTION', map.data.introduction)
            commit('SET_USER_INFO', JSON.parse(JSON.stringify(map.data)))
            resolve()
          } else {
            Message({
              message: map.data,
              type: 'warning'
            })
            reject(map.data)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 获取用户信息
    GetUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getUserInfo(state.token).then(response => {
          if (!response.data) { // 由于mockjs 不支持自定义状态码只能这样hack
            reject('error')
          }
          const data = response.data
          console.log(response.data)

          if (data.roles && data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', data.roles)
          } else {
            reject('getInfo: roles must be a non-null array !')
          }
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.avatar)
          commit('SET_INTRODUCTION', data.introduction)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 第三方验证登录
    // LoginByThirdparty({ commit, state }, code) {
    //   return new Promise((resolve, reject) => {
    //     commit('SET_CODE', code)
    //     loginByThirdparty(state.status, state.email, state.code).then(response => {
    //       commit('SET_TOKEN', response.data.token)
    //       setToken(response.data.token)
    //       resolve()
    //     }).catch(error => {
    //       reject(error)
    //     })
    //   })
    // },

    // 登出
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
        removeToken()
        resolve()
        // logout(state.token).then(() => {
        //   commit('SET_TOKEN', '')
        //   commit('SET_ROLES', [])
        //   removeToken()
        //   resolve()
        // }).catch(error => {
        //   reject(error)
        // })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },

    // 动态修改权限
    ChangeRoles({ commit, dispatch }, role) {
      return new Promise(resolve => {
        commit('SET_TOKEN', role)
        setToken(role)
        getUserInfo(role).then(response => {
          const data = response.data
          commit('SET_ROLES', data.roles)
          commit('SET_NAME', data.name)
          commit('SET_AVATAR', data.avatar)
          commit('SET_INTRODUCTION', data.introduction)
          dispatch('GenerateRoutes', data) // 动态修改权限后 重绘侧边菜单
          resolve()
        })
      })
    }
  }
}

export default user
