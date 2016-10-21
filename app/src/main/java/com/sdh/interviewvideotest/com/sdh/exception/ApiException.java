package com.sdh.interviewvideotest.com.sdh.exception;

/**
 * Created by sdh on 2016/10/21.
 */

public class ApiException extends RuntimeException {
    public ApiException(String msg){
        super(msg);

    }

    public ApiException(int code){
       this(getErrMessage(code));

    }

    private static  String getErrMessage(int code) {
        return "只要start不为0,就会认为响应不正确偶";
    }
}
