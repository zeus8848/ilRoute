package com.github.ilRoute.result;


import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * @author lwx
 */
public class Result<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    public Result() {
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(String message, T data){
        return new Result(HttpStatus.SC_OK,message,data);
    }

    public static <T> Result<T> success(String message){
        return new Result<>(HttpStatus.SC_OK,message,null);
    }

    public static <T> Result<T> fail(String message, T data){
        return new Result<>(HttpStatus.SC_NOT_FOUND,message,data);
    }

    public static <T> Result<T> fail(String message){
        return new Result<>(HttpStatus.SC_NOT_FOUND,message,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
