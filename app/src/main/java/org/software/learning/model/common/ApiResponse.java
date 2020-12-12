package org.software.learning.model.common;

public class ApiResponse<T> {
    private int code;
    private String summary;
    private String msg;
    private T data;

    public ApiResponse(ResponseToken token, String msg){
        this(token);
        this.msg = msg;
    }

    public ApiResponse(ResponseToken token, String msg, T data){
        this(token, msg);
        this.data = data;
    }

    public ApiResponse(ResponseToken token){
        this.code = token.getCode();
        this.summary = token.getSummary();
        this.msg = token.getDefaultMsg();
    }

    public ApiResponse(ResponseToken token, T data) {
        this(token);
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
