package com.d.dao.eventbus_learning_second.bean;

/**
 * Created by dao on 7/11/16.
 */
public class PostMessage {
    String msg;

    public PostMessage(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
