package com.jm.job.core.model;


/**
 * 任务状态
 */
public enum TaskStatus {

    //未开始
    NotStarted(0),
    //待执行
    Waiting(1),
    //执行中
    Doing(2),
    //异常
    Error(3),
    //已完成
    Done(4),
    //已停止
    Stop(5);

    int val;

    TaskStatus(int id) {
        this.val = id;
    }

    public int getVal() {
        return val;
    }

    public  static TaskStatus  valueOf(int id) {
        switch (id) {
            case 1:
                return Waiting;
            case 2:
                return Doing;
            case 3:
                return Error;
            case 4:
                return Done;
            case 5:
                return Stop;
            default:
                return NotStarted;
        }
    }

}
