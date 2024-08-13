package com.systcloud.entity;

import java.io.Serializable;


//用于向页面传递信息的类
public class Result<T> implements Serializable {
    private boolean success;
    private String message;
    private T data;
    public Result(boolean success,String message){
        this.success = success;
        this.message = message;

    }
    public Result(boolean success,String message,T data){
        this.success = success;
        this.message = message;
        this.data=data;

    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
