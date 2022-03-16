let proxyobj = {}
proxyobj['/'] = {
    ws: false,
    target: 'http://localhost:8091',
    changeOrigin: true,
    pathRewrite: {
        '^/': ''
    }
}

module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        proxy: proxyobj
    }
}