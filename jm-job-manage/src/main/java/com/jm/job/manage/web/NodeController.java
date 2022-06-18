package com.jm.job.manage.web;

import com.jm.job.core.model.Node;
import com.jm.job.core.service.NodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/job/manage/node")
public class NodeController {
    @Resource
    private NodeService nodeService;

    @PostMapping("insert")
    public ResponseEntity insert(@RequestBody Node node) {
        return new ResponseEntity(nodeService.insert(node), HttpStatus.OK);
    }

    /**
     * 禁用
     * @param id 节点id
     * @return
     */
    @PostMapping("disable")
    public ResponseEntity disable(Long id){
        nodeService.disable(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 重新启用节点
     * @param id 节点id
     * @return
     */
    @PostMapping("enable")
    public ResponseEntity enable(Long id){
        nodeService.enable(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
