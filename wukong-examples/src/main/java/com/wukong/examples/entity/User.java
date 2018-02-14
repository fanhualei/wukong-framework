package com.wukong.examples.entity;

import java.util.Date;
import javax.annotation.Generated;

public class User {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String userName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer age;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer groupsId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getUserId() {
        return userId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUserName() {
        return userName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getAge() {
        return age;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAge(Integer age) {
        this.age = age;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCreateDate() {
        return createDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getGroupsId() {
        return groupsId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setGroupsId(Integer groupsId) {
        this.groupsId = groupsId;
    }
}