import request from '@/utils/request'

export function loginByUsername(email, password) {
  const data = {
    email,
    password,
    name: '',
    sex: '',
    id: ''// 这一个加上与不加后台只要在参数列表中使用 @requestBody就可以封装成一个对象
  }
  return request({
    url: '/VideoWebSite/user/valid',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/login/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

