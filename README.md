# jm-job整体架构

jm-job是一个分布式任务执行器，分为管理端Master和执行节点Node两部分。管理端可对任务进行管理，可注册任务、禁用任务、重试任务、重启(恢复)任务。其中，禁用任务可达到删除任务的效果、即能让任务立刻从执行队列中移除，同时还能具备恢复任务的功能，以防止任务意外删除的错误操作。

整体架构如下图所示

![jm-job架构](/Users/caojm/jd/离职和简历/review/jm-job架构.png)

注：因受限于环境与时间，未能完全调试代码。代码只能作为设计参考，但以足够丰富完整。

# 管理端Master

com.jm.job.manage.web.TaskController

```java
    /**
     * 新增一个任务
     * @param task
     * @return
     */
    Task insert(Task task);

    /**
     * 禁用,相当于删除效果
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
```

对任务的管理粒度

```
1、以组作为最小管理单元
2、一个组可以有多个任务，一个组可以对应多个节点。表示同一个组的任务在多个节点运行
```



# 执行端Node

拉取任务

```java
com.jm.job.client.JmJobExecutor.TaskLoader
```

执行任务

```
com.jm.job.client.JmJobExecutor.Worker
```

