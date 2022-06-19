# jm-job整体架构

jm-job是一个分布式任务执行器，分为管理端Master和执行节点Node两部分。管理端可对任务进行管理，可注册任务、禁用任务、重试任务、重启(恢复)任务。其中，禁用任务可达到删除任务的效果、即能让任务立刻从执行队列中移除，同时还能具备恢复任务的功能，以防止任务意外删除的错误操作。

整体架构如下图所示

![jm-job架构](https://github.com/agncao/jm-job/blob/master/jm-job%E6%9E%B6%E6%9E%84.png)

注：因受限于环境与时间，未能完全调试代码。代码只能作为设计参考，但以足够丰富完整。

# 持久层设计
## 优化设计
持久层采用mysql存储任务调度所需的基本信息，包括分组、节点、任务等基本信息。当分布式达到一定规模，可对数据访问进行优化设计，由于此场景是写入少、查询多，故优化可遵照如下思路：  
1、各个节点从数据库查询要执行的任务信息，管理端写入任务相关数据，所以查询多写入少，数据库存储采用读写分离。  
2、对从数据库查询到等待执行任务可放入redis缓存中。当管理端变更任务数据时，可同步缓存中的待执行的任务。  

## 数据库设计
1、Group，分组基本信息

| 字段 | 字段注释 | 字段类型 | 备注         |
| ---- | -------- | -------- | ------------ |
| id   | 关键字   | bigint   | 自增长       |
| name | 组名     | varchar  |              |

2、Node，节点基本信息

| 字段    | 字段注释     | 字段类型 | 备注            |
| ------- | ------------ | -------- | --------------- |
| id      | 关键字       | bigint   | 自增长          |
| ip      | 节点ip地址   | varchar  | not null        |
| port    | 节点端口号   | varchar  |                 |
| disable | 节点是否禁用 | int      | 0否1是,not null |
| groupId | 所属分组     | bigint   |                 |

3、Task，节点基本信息

| 字段     | 关键字   | 字段类型 | 备注                                 |
| -------- | -------- | -------- | ------------------------------------ |
| id       | 关键字   | bigint   | 自增长                               |
| name     | 任务名   | varchar  | not null                             |
| beanName | bean的名 | varchar  | not null                             |
| status   | 状态     | int      | 0未开始1待执行2执行中3异常, not null |
| disable  | 是否禁用 | int      | 0否1是,not null                      |
| retry    | 是否重试 | int      | 0否1是,not null                      |
| groupId  | 所属分组 | bigint   |                                      |


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

1、以组作为最小管理单元  
2、一个组可以有多个任务，一个组可以对应多个节点。表示同一个组的任务在多个节点运行
![jm-job类图](https://github.com/agncao/jm-job/blob/master/jm-job%20%E7%B1%BB%E5%9B%BE.png)


# 执行端Node

拉取任务

```java
com.jm.job.client.JmJobExecutor.TaskLoader
```

执行任务

```
com.jm.job.client.JmJobExecutor.Worker
```
