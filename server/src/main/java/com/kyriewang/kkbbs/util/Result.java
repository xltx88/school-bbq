//package com.kyriewang.kkbbs.util;
//
//import java.io.Serializable;
//
//public class Result<T> implements Serializable {
//    private static final long serialVersionUID = 1L;
//    private int code;
//    private String message;
//    private T data;
//
//    public Result() {
//    }
//
//    public Result(int code, String message, T data) {
//        this.code = code;
//        this.message = message;
//        this.data = data;
//    }
//
//    public static <T> Result<T> success(T data) {
//        return new Result<>(200, "操作成功", data);
//    }
//
//    public static <T> Result<T> error(int code, String message) {
//        return new Result<>(code, message, null);
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//}
//