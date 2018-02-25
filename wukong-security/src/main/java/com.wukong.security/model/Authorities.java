package com.wukong.security.model;

import java.io.Serializable;
import javax.annotation.Generated;

public class Authorities implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String username;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String authority;

    private static final long serialVersionUID = 1L;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUsername() {
        return username;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAuthority() {
        return authority;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", username=").append(username);
        sb.append(", authority=").append(authority);
        sb.append("]");
        return sb.toString();
    }
}