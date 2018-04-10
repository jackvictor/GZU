package com.gzu.su.manager.common.response;

/**
 * 数据接口返回封包
 *
 * @author mike.peng
 * @date 2018-03-27
 */
public class Result {

    /**
     * 接口返回码
     * 1、请求成功
     * 0、请求参数错误
     * -1、请求失败
     */
    public int code = 1;

    /**
     * 接口请求情况描述
     */
    public String msg = "请求成功";

    /**
     * 接口数据
     */
    public Object data ;

    private Result (Object data){
        this.code = 1;
        this.msg = "请求成功";
        this.data = data;
    }

    private Result (int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static Result SUCCESS(Object data){
        return new Result(data);
    }

    public static Result ERROR(String ... params){
        return new Result(0, "接口参数错误：" + params);
    }

    public static Result EXCEPTION(String msg){
        msg = msg == null ? "服务器异常" : msg;
        return new Result(-1, msg);
    }
}
