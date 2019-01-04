import request from '@/utils/request'

const baseApi = function(url) {
  return {
    list: (query) => request({ url: url, method: 'get', params: query }),
    update: (data, uuid) => request({ url: `${url}${uuid}/`, method: 'patch', data }),
    create: (data) => request({ url: url, method: 'post', data }),
    delete: (uuid) => request({ url: `${url}`, params: uuid, method: 'delete' }),
    retrieve: (uuid, config = {}) => request({ url: `${url}${uuid}/`, method: 'get', params: config })
  }
}
// 视频
const VIDEO_URI = 'VideoWebSite/video/list'

// 用户
const VIDEO_USER_URI = 'VideoWebSite/user/list'

// 视频分类
const VIDEO_TYPE = 'VideoWebSite/videoType/'

export const videoUserOp = baseApi(VIDEO_USER_URI)
export const videoTypeOp = baseApi(VIDEO_TYPE)
export const videoOp = baseApi(VIDEO_URI)
