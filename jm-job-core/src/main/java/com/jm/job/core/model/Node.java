package com.jm.job.core.model;


import java.io.Serializable;

/**
 * 节点基本信息
 */
public class Node implements Serializable {
    /**
     * 节点id
     */
    private Long id;
    /**
     * 节点ip
     */
    private String ip;
    /**
     * 节点端口号
     */
    private int port = 9999;
    /**
     * 节点是否禁用
     */
    private int disable=0;
    /**
     * 节点所属分组
     */
    private Long groupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
