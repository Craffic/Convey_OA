package com.craffic.convey.common.response;

import lombok.Data;

@Data
public class ResponseBody<T> {

    private static final long serialVersionUID = -4213992025937477653L;

    /*返回状态码*/
    private String code;

    /*返回数据*/
    private T obj;

    /*返回描述/消息*/
    private String message;

    /*返回是否成功*/
    private String isSuccess;

    public ResponseBody() {
        super();
    }

    public ResponseBody(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseBody(String code, T obj, String message) {
        this.code = code;
        this.obj = obj;
        this.message = message;
    }

    /*返回成功*/
    public static <T> ResponseBody<T> success(){
        return new ResponseBody("200000", "处理成功");
    }

    public static <T> ResponseBody<T> success(T data){
        return new ResponseBody("200000", data, "处理成功");
    }

    /*返回失败*/
    public static <T> ResponseBody<T> failure(String code, String errMessage){
        return new ResponseBody<>(code, errMessage);
    }
}
