package com.jm.job.core.model;

import java.io.Serializable;
import java.util.List;

/**
 * 任务分组
 */
public class Group implements Serializable {
    /**
     * 分组id
     */
    private Long id;
    /**
     * 分组名
     */
    private String name;
    /**
     * 同组的节点
     */
    private List<Node> nodeSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getNodeSet() {
        return nodeSet;
    }

    public void setNodeSet(List<Node> nodeSet) {
        this.nodeSet = nodeSet;
    }
}
