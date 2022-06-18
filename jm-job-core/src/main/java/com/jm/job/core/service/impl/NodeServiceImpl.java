package com.jm.job.core.service.impl;

import com.jm.job.core.common.enums.Bool;
import com.jm.job.core.model.Node;
import com.jm.job.core.repository.NodeRepository;
import com.jm.job.core.service.NodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NodeServiceImpl implements NodeService {
    @Resource
    NodeRepository nodeRepository;

    @Override
    public Node insert(Node node) {
        nodeRepository.insert(node);
        return node;
    }

    @Override
    public void disable(Long id) {
        nodeRepository.disable(id, Bool.Yes.val());
    }

    @Override
    public void enable(Long id) {
        nodeRepository.disable(id,Bool.No.val());
    }
}
