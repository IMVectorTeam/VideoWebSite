import request from '@/utils/request'

const baseApi = function(url) {
  return {
    list: (query) => request({ url: url, method: 'get', params: query }),
    update: (data, uuid) => request({ url: `${url}${uuid}/`, method: 'patch', data }),
    create: (data) => request({ url: url, method: 'post', data }),
    delete: (uuid) => request({ url: `${url}${uuid}/`, method: 'delete' }),
    retrieve: (uuid, config = {}) => request({ url: `${url}${uuid}/`, method: 'get', params: config })
  }
}
// 供应商
const VIDEO_URI = 'VideoWebSite/video/list'

export const videoOp = baseApi(VIDEO_URI)
