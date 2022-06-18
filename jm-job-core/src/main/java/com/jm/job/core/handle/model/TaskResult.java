package com.jm.job.core.handle.model;

import java.io.Serializable;

/**
 * 任务执行结果
 */
public class TaskResult implements Serializable {
    public static final int SUCCESS_CODE = 200;
    public static final int FAIL_CODE = 500;
    public static final TaskResult SUCCESS = new TaskResult(200,null);
    public static final TaskResult FAIL = new TaskResult(500, null);
    private int code;
    private String msg;

    public TaskResult() {
    }

    public TaskResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
