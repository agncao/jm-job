package com.jm.job.core.handle.model;

import com.jm.job.core.model.Task;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列中的任务
 */
public class DelayItem implements Delayed {

    private final long delay;
    private final Task task;

    public DelayItem(Task task) {
        this.task=task;
        this.delay=task.getNextStartTime().getTime()-new Date().getTime();
    }


    /**
     * 需要实现的接口，获得延迟时间   用过期时间-当前时间
     * @param unit
     * @return
     */
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.delay , TimeUnit.MILLISECONDS);
    }

    /**
     * 用于延迟队列内部比较排序   当前时间的延迟时间 - 比较对象的延迟时间
     * @param o
     * @return
     */
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
    }

    public Task getItem(){
        return this.task;
    }
}
