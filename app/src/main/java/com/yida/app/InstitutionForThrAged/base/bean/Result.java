package com.yida.app.InstitutionForThrAged.base.bean;

/**
 * @author think
 * @name InstitutionForTheAged
 * @class name：com.yida.app.InstitutionForThrAged.base.bean
 * @class describe :
 * @time 2018-02-06 10:26
 */
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
