package com.jm.job.core.service.impl;

import com.jm.job.core.model.Group;
import com.jm.job.core.repository.GroupRepository;
import com.jm.job.core.service.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupRepository groupRepository;

    @Override
    public Group insert(Group group) {
        groupRepository.insert(group);
        return group;
    }

    @Override
    public List<Group> findListByNodeId(Long nodeId) {
        return groupRepository.findListByNodeId(nodeId);
    }
}
