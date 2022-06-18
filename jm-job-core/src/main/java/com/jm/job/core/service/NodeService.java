package com.jm.job.core.service;

import com.jm.job.core.model.Node;


public interface NodeService {
    /**
     * 新增一个节点
     * @param node
     * @return
     */
    Node insert(Node node);

    /**
     * 禁用
     * @param id 节点id
     * @return
     */
    void disable(Long id);


    /**
     * 重新启用节点
     * @param id 节点id
     * @return
     */
    void enable(Long id);
}
