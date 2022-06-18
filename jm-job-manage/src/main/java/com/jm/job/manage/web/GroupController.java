package com.jm.job.manage.web;

import com.jm.job.core.model.Group;
import com.jm.job.core.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/job/manage/group")
public class GroupController {
    @Resource
    private GroupService groupService;

    @PostMapping("insert")
    public ResponseEntity insert(@RequestBody Group group) {
        return new ResponseEntity(groupService.insert(group), HttpStatus.OK);
    }

    @PostMapping("findListByNodeId")
    public ResponseEntity<List<Group>> findListByNodeId(@RequestBody Long nodeId) {
        return new ResponseEntity<>(groupService.findListByNodeId(nodeId), HttpStatus.OK);
    }
}
