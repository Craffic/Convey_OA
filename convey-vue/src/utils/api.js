import axios from 'axios'
import {Message} from "element-ui";

/**
 * 封装响应拦截器
 */
axios.interceptors.response.use(
    // 请求成功：http.status == 200，包括请求成功，和业务上500错误
    success => {
        // 处理业务报错
        if (success.status && success.status == 200 && success.data.status == 500) {
            Message.error({message: success.data.msg});
            return;
        }
        // 处理成功返回RespBean.data
        if (success.data.msg){
            Message.success({message: success.data.msg})
        }
        return success.data;
    }, error => {
        if (error.response.status == 504 || error.response.status == 404) {
            Message.error({message: '找不到服务器'})
        } else if (error.response.status == 403) {
            Message.error({message: '权限不足，请联系管理员'})
        } else if (error.response.status == 401) {
            Message.error({message: '未登录，请先登录'})
        } else {
            if (error.response.data.msg) {
                Message.error({message: error.response.data.msg})
            } else {
                Message.error({message: '未知错误'})
            }
        }
        return;
    }
)

/**
 * 封装key-value的post请求
 */
let base = '';
export const postKeyValueRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        // 如果以data: params形式的话就会以json的形式传到服务器，在key-value请求中是不支持的
        // 所以在下面方法做一下转换
        transformRequest:[
            function (data) {
                let ret = '';
                for (let i in data){
                    console.log(i + " " + data[i]);
                    ret += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&';
                }
                console.log(ret);
                return ret;
            }],
        header: {
            'content-Type': 'application/x-www-form-urlencoded'
        }
    })
}

export const getRequest = (url, params) => {
    return axios({
        method: 'get',
        url: `${base}${url}`,
        data: params
    })
}

/**
 * 封装post请求
 */
export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params
    })
}

/*封装删除请求*/
export const deleteRequest = (url, params) => {
    return axios({
        method: 'delete',
        url: `${base}${url}`,
        params: params
    })
}

/*封装put请求*/
export const putRequest = (url, params) => {
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params
    })
}