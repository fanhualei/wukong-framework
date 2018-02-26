package com.wukong.security.model;

import java.io.Serializable;
import javax.annotation.Generated;

public class UserEx implements Serializable {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long userId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String nickname;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String signature;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String qq;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String weixin;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String weibo;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String avatar;

    private static final long serialVersionUID = 1L;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getUserId() {
        return userId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getNickname() {
        return nickname;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getSignature() {
        return signature;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getQq() {
        return qq;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getWeixin() {
        return weixin;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getWeibo() {
        return weibo;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setWeibo(String weibo) {
        this.weibo = weibo == null ? null : weibo.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAvatar() {
        return avatar;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", nickname=").append(nickname);
        sb.append(", signature=").append(signature);
        sb.append(", qq=").append(qq);
        sb.append(", weixin=").append(weixin);
        sb.append(", weibo=").append(weibo);
        sb.append(", avatar=").append(avatar);
        sb.append("]");
        return sb.toString();
    }
}