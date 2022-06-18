package com.jm.job.client;

import com.jm.job.core.common.utils.IpUtil;
import com.jm.job.core.handle.IJobHandler;
import com.jm.job.core.handle.model.DelayItem;
import com.jm.job.core.model.Task;
import com.jm.job.core.model.TaskStatus;
import com.jm.job.core.service.TaskService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Component
public class JmJobExecutor  implements ApplicationContextAware,ApplicationListener<ContextRefreshedEvent> {
    private ApplicationContext applicationContext;
    @Resource
    private TaskService taskService;

    //创建任务到期延时队列
    private DelayQueue<DelayItem> taskQueue = new DelayQueue<>();

    //存放所有等待执行的线程
    private ExecutorService bossPool = Executors.newFixedThreadPool(1);

    //运行任务的线程池
    private ThreadPoolExecutor workerPool;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        workerPool = new ThreadPoolExecutor(8, 512, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024));

        //加载待处理的任务
        bossPool.execute(new TaskLoader());

        //任务调度
        workerPool.execute(new Worker());

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }


    class  TaskLoader implements Runnable{
        public TaskLoader(){}

        @Override
        public void run() {
            for(;;) {
                try {
                    //查询当前节点的所有任务
                    List<Task> taskList = taskService.getNotStartedList(IpUtil.getIp());
                    if(taskList == null || taskList.isEmpty()) {
                        Thread.sleep(200);
                        continue;
                    }

                    //找到还有10分钟才执行的任务
                    long expectedTime=System.currentTimeMillis()+10*60*1000;
                    taskList=taskList.stream().filter(ele->ele.getNextStartTime().getTime()<=expectedTime).collect(Collectors.toList());
                    if(taskList == null || taskList.isEmpty()) {
                        continue;
                    }

                    for(Task task:taskList) {
                        //将任务设置为待执行
                        task.setStatus(TaskStatus.Waiting.getVal());

                        DelayItem delayTask = new DelayItem(task);
                        taskQueue.offer(delayTask);

                    }

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
    class Worker implements Runnable {
        @Override
        public void run() {
            for (;;) {
                try {
                    //时间到了就可以从延时队列拿出任务对象
                    DelayItem item = taskQueue.take();
                    if(item != null && item.getItem() != null) {
                        Task task = item.getItem();

                        //将任务设置成执行中
                        taskService.updateStatus(task.getId(),TaskStatus.Doing.getVal());
                        String beanName=task.getBeanName();
                        IJobHandler jobHandler=applicationContext.getBean(beanName,IJobHandler.class);
                        jobHandler.execute();

                        //任务执行完成
                        taskService.updateStatus(task.getId(),TaskStatus.Done.getVal());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
