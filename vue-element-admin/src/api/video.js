import request from '@/utils/request'

const baseApi = function(url) {
  return {
    list: (query) => request({ url: `${url}list/`, method: 'get', params: query }),
    update: (data, uuid) => request({ url: `${url}${uuid}/`, method: 'patch', data }),
    create: (data) => request({ url: url, method: 'post', data }),
    delete: (uuid) => request({ url: `${url}`, params: uuid, method: 'delete' }),
    retrieve: (uuid, config = {}) => request({ url: `${url}${uuid}`, method: 'get', params: config })
  }
}
// 视频
const VIDEO_URI = 'VideoWebSite/video/'
const VIDEO_URI_TYPE = 'VideoWebSite/video/type/'
const VIDEO_URI_USER_ID = 'VideoWebSite/video/userId/'

// 用户
const VIDEO_USER_URI = 'VideoWebSite/user/'
const VIDEO_UESR_VIDEOID_URL = 'VideoWebSite/user/videoId/'

// 视频分类
const VIDEO_TYPE = 'VideoWebSite/videoType/'

// 视频评论
const VIDEO_COMMENT = 'VideoWebSite/videoComment/'
const VIDEO_COMMENT_USER_ID = 'VideoWebSite/comment/userId/'

export const videoUserOp = baseApi(VIDEO_USER_URI)
export const videoTypeOp = baseApi(VIDEO_TYPE)
export const videoOp = baseApi(VIDEO_URI)
export const videoUserIdOP = baseApi(VIDEO_URI_USER_ID)
export const videoByTypeOp = baseApi(VIDEO_URI_TYPE)
export const videoCommentOp = baseApi(VIDEO_COMMENT)
export const commentByUserIdOp = baseApi(VIDEO_COMMENT_USER_ID)
export const videoUserByVideoIdOp = baseApi(VIDEO_UESR_VIDEOID_URL)
