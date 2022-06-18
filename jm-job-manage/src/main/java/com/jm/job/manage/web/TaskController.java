package com.jm.job.manage.web;

import com.jm.job.core.model.Task;
import com.jm.job.core.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/job/manage/task")
public class TaskController {
    @Resource
    private TaskService taskService;

    @PostMapping("insert")
    public ResponseEntity insert(@RequestBody Task task) {
        return new ResponseEntity(taskService.insert(task), HttpStatus.OK);
    }

    /**
     * 禁用
     * @param id 节点id
     * @return
     */
    @PostMapping("disable")
    public ResponseEntity disable(Long id){
        taskService.disable(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 重新启用
     * @param id 任务id
     * @return
     */
    @PostMapping("enable")
    public ResponseEntity enable(Long id){
        taskService.enable(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 重新启用
     * @param id 任务id
     * @return
     */
    @PostMapping("retry")
    public ResponseEntity retry(Long id){
        taskService.retry(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
