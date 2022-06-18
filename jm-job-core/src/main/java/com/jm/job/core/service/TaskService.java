package com.jm.job.core.service;

import com.jm.job.core.model.Task;

import java.util.List;

public interface TaskService {

    /**
     * 新增一个任务
     * @param task
     * @return
     */
    Task insert(Task task);

    /**
     * 禁用
     * @param id 任务id
     * @return
     */
    void disable(Long id);

    /**
     * 重新启用任务
     * @param id 任务id
     * @return
     */
    void enable(Long id);

    /**
     * 手动重试,手动执行
     * @param id 任务id
     * @return
     */
    void retry(Long id);

    /**
     * 任务完成
     * @param taskId
     * @return
     */
    void finish(Long taskId);

    /**
     * 查询当前节点未运行的任务
     * @param ip
     * @return
     */
    List<Task> getNotStartedList(String ip);

    /**
     * 更新任务
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Long id,int status);
}
