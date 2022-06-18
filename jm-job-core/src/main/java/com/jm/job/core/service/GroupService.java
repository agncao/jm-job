package com.jm.job.core.service;

import com.jm.job.core.model.Group;

import java.util.List;

public interface GroupService {
    /**
     * 新增一个应用
     * @param group
     * @return
     */
    Group insert(Group group);

    /**
     * 得到当前节点所属的分组
     * @param nodeId
     * @return
     */
    List<Group> findListByNodeId(Long nodeId);
}
