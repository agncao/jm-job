package com.jm.job.core.service.impl;

import com.jm.job.core.common.enums.Bool;
import com.jm.job.core.model.Group;
import com.jm.job.core.model.Node;
import com.jm.job.core.model.Task;
import com.jm.job.core.repository.GroupRepository;
import com.jm.job.core.repository.NodeRepository;
import com.jm.job.core.repository.TaskRepository;
import com.jm.job.core.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Resource
    TaskRepository taskRepository;
    @Resource
    NodeRepository nodeRepository;
    @Resource
    GroupRepository groupRepository;

    @Override
    public Task insert(Task task) {
        taskRepository.insert(task);
        return task;
    }

    @Override
    public void disable(Long id) {
        taskRepository.disable(id, Bool.Yes.val());
    }

    @Override
    public void enable(Long id) {
        taskRepository.disable(id,Bool.No.val());
    }

    @Override
    public void retry(Long id) {
        taskRepository.retry(id);
    }

    @Override
    public void finish(Long taskId) {
        taskRepository.finish(taskId);
    }

    @Override
    public List<Task> getNotStartedList(String ip) {
        List<Task> res = new ArrayList<>();

        //根据ip查询任务运行节点
        Node node=nodeRepository.getByIp(ip);
        if(null==node || node.getDisable()==1) return res;
        //根据节点查询所属群组
        List<Group> groups=groupRepository.findListByNodeId(node.getGroupId());
        if(CollectionUtils.isEmpty(groups)) return res;
        Set<Long> groupIds = groups.stream().map(Group::getId).collect(Collectors.toSet());

        //查找是否有手动立刻执行的任务
        res.addAll(taskRepository.findRetriedTasks(groupIds));
        //根据群组查询所有代执行的任务
        res.addAll(taskRepository.findTasksByGroupIds(groupIds));

        return res;
    }

    @Override
    public int updateStatus(Long id,int status){
        return taskRepository.updateStatus(id,status);
    }
}
