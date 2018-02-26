package com.wukong.security.dao;

import static com.wukong.security.dao.UserExDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.wukong.security.model.UserEx;
import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

@Mapper
public interface UserExMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<UserEx> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserExResult")
    UserEx selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserExResult", value = {
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="signature", property="signature", jdbcType=JdbcType.VARCHAR),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="weixin", property="weixin", jdbcType=JdbcType.VARCHAR),
        @Result(column="weibo", property="weibo", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR)
    })
    List<UserEx> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(userEx);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, userEx);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long userId_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, userEx)
                .where(userId, isEqualTo(userId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(UserEx record) {
        return insert(SqlBuilder.insert(record)
                .into(userEx)
                .map(userId).toProperty("userId")
                .map(nickname).toProperty("nickname")
                .map(signature).toProperty("signature")
                .map(qq).toProperty("qq")
                .map(weixin).toProperty("weixin")
                .map(weibo).toProperty("weibo")
                .map(avatar).toProperty("avatar")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(UserEx record) {
        return insert(SqlBuilder.insert(record)
                .into(userEx)
                .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                .map(nickname).toPropertyWhenPresent("nickname", record::getNickname)
                .map(signature).toPropertyWhenPresent("signature", record::getSignature)
                .map(qq).toPropertyWhenPresent("qq", record::getQq)
                .map(weixin).toPropertyWhenPresent("weixin", record::getWeixin)
                .map(weibo).toPropertyWhenPresent("weibo", record::getWeibo)
                .map(avatar).toPropertyWhenPresent("avatar", record::getAvatar)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<UserEx>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, userId, nickname, signature, qq, weixin, weibo, avatar)
                .from(userEx);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<UserEx>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, userId, nickname, signature, qq, weixin, weibo, avatar)
                .from(userEx);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UserEx selectByPrimaryKey(Long userId_) {
        return SelectDSL.selectWithMapper(this::selectOne, userId, nickname, signature, qq, weixin, weibo, avatar)
                .from(userEx)
                .where(userId, isEqualTo(userId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(UserEx record) {
        return UpdateDSL.updateWithMapper(this::update, userEx)
                .set(userId).equalTo(record::getUserId)
                .set(nickname).equalTo(record::getNickname)
                .set(signature).equalTo(record::getSignature)
                .set(qq).equalTo(record::getQq)
                .set(weixin).equalTo(record::getWeixin)
                .set(weibo).equalTo(record::getWeibo)
                .set(avatar).equalTo(record::getAvatar);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(UserEx record) {
        return UpdateDSL.updateWithMapper(this::update, userEx)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(nickname).equalToWhenPresent(record::getNickname)
                .set(signature).equalToWhenPresent(record::getSignature)
                .set(qq).equalToWhenPresent(record::getQq)
                .set(weixin).equalToWhenPresent(record::getWeixin)
                .set(weibo).equalToWhenPresent(record::getWeibo)
                .set(avatar).equalToWhenPresent(record::getAvatar);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(UserEx record) {
        return UpdateDSL.updateWithMapper(this::update, userEx)
                .set(nickname).equalTo(record::getNickname)
                .set(signature).equalTo(record::getSignature)
                .set(qq).equalTo(record::getQq)
                .set(weixin).equalTo(record::getWeixin)
                .set(weibo).equalTo(record::getWeibo)
                .set(avatar).equalTo(record::getAvatar)
                .where(userId, isEqualTo(record::getUserId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(UserEx record) {
        return UpdateDSL.updateWithMapper(this::update, userEx)
                .set(nickname).equalToWhenPresent(record::getNickname)
                .set(signature).equalToWhenPresent(record::getSignature)
                .set(qq).equalToWhenPresent(record::getQq)
                .set(weixin).equalToWhenPresent(record::getWeixin)
                .set(weibo).equalToWhenPresent(record::getWeibo)
                .set(avatar).equalToWhenPresent(record::getAvatar)
                .where(userId, isEqualTo(record::getUserId))
                .build()
                .execute();
    }
}