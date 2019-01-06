'use strict'

// using personal dev ip and dev ip.
// 该方法能解决静态资源跨域问题
module.exports = {
  dev: {
    proxyTable: {
      '/api': {
        target: 'http://127.0.0.1:8080',
        changeOrigin: true, //改变源
        pathRewrite: { '^/api': 'http://127.0.0.1:8080' } // 路径重写
      }
    }
    // ,
    // host: '127.0.0.1',
    // port: 9080
  }
}
// 通过这中方法去解决跨域，打包部署时还按这种方法会出问题。解决方法如下：
// let serverUrl = '/api/'  //本地调试时
// // let serverUrl = 'http://f.apiplus.cn/'  //打包部署上线时
// export default {
//   dataUrl: serverUrl + 'bj11x5.json'
// }



//  该方法静态资源跨域无法解决
// 'use strict'

// // using personal dev ip and dev ip.
// module.exports = {
//   dev: {
//     proxyTable: {
//       '/api': {
//         target: 'http://127.0.0.1:8080',
//         pathRewrite: { '^/api': '' }
//       }
//     },
//     host: '127.0.0.1',
//     port: 9080
//   }
// }