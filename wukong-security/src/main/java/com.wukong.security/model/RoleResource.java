package com.wukong.security.model;

import java.io.Serializable;
import javax.annotation.Generated;

public class RoleResource implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long roleResourceId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long roleId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long resourceId;

    private static final long serialVersionUID = 1L;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getRoleResourceId() {
        return roleResourceId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRoleResourceId(Long roleResourceId) {
        this.roleResourceId = roleResourceId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getRoleId() {
        return roleId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getResourceId() {
        return resourceId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleResourceId=").append(roleResourceId);
        sb.append(", roleId=").append(roleId);
        sb.append(", resourceId=").append(resourceId);
        sb.append("]");
        return sb.toString();
    }
}