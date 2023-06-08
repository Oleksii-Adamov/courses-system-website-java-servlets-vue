// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })
module.exports = {
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://localhost:3000/Lab1',
        changeOrigin: true
      },
    }
  }
}