package com.wukong.examples.entity;

import javax.annotation.Generated;

public class Groups {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer groupsId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String groupName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getGroupsId() {
        return groupsId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setGroupsId(Integer groupsId) {
        this.groupsId = groupsId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getGroupName() {
        return groupName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }
}