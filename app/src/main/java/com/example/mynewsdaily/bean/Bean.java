package com.example.mynewsdaily.bean;

import java.util.List;

/**
 * Created by wangzhenkai on 2016/6/12.
 */
public class Bean <T>{

    private String message;
    private String status;
    private T data;

    public Bean() {
        // TODO Auto-generated constructor stub
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }
}
