package com.jm.job.core.model;


import com.jm.job.core.common.utils.CronParser;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务基本信息
 */
public class Task implements Serializable {

    private Long id;

    /**
     * 调度名称
     */
    private String name;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * @see TaskStatus
     */
    private Integer status = TaskStatus.NotStarted.getVal();

    /**
     * 成功次数
     */
    private Integer successCount;

    /**
     * 失败次数
     */
    private Integer failCount;

    /**
     * job的bean名
     */
    private String beanName;

    /**
     * 所属分组id
     */
    private Long groupId;

    /**
     * 是否暂停：0否，1是
     */
    private int disable=0;

    /**
     * 手动执行：0否，1是
     * 用于手动重试
     */
    private int retry=0;

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public int getDisable() {
        return disable;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public Date getNextStartTime(){
        if(this.retry==1) return new Date();

        return CronParser.getNextDate(cron);
    }
}
