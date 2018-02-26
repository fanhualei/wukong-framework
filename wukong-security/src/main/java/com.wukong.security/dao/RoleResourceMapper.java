package com.wukong.security.dao;

import static com.wukong.security.dao.RoleResourceDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.wukong.security.model.RoleResource;
import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
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
public interface RoleResourceMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.roleResourceId", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<RoleResource> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("RoleResourceResult")
    RoleResource selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="RoleResourceResult", value = {
        @Result(column="role_resource_id", property="roleResourceId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.BIGINT)
    })
    List<RoleResource> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(roleResource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, roleResource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long roleResourceId_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, roleResource)
                .where(roleResourceId, isEqualTo(roleResourceId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(RoleResource record) {
        return insert(SqlBuilder.insert(record)
                .into(roleResource)
                .map(roleId).toProperty("roleId")
                .map(resourceId).toProperty("resourceId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(RoleResource record) {
        return insert(SqlBuilder.insert(record)
                .into(roleResource)
                .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                .map(resourceId).toPropertyWhenPresent("resourceId", record::getResourceId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RoleResource>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, roleResourceId, roleId, resourceId)
                .from(roleResource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<RoleResource>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, roleResourceId, roleId, resourceId)
                .from(roleResource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default RoleResource selectByPrimaryKey(Long roleResourceId_) {
        return SelectDSL.selectWithMapper(this::selectOne, roleResourceId, roleId, resourceId)
                .from(roleResource)
                .where(roleResourceId, isEqualTo(roleResourceId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(RoleResource record) {
        return UpdateDSL.updateWithMapper(this::update, roleResource)
                .set(roleId).equalTo(record::getRoleId)
                .set(resourceId).equalTo(record::getResourceId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(RoleResource record) {
        return UpdateDSL.updateWithMapper(this::update, roleResource)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(resourceId).equalToWhenPresent(record::getResourceId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(RoleResource record) {
        return UpdateDSL.updateWithMapper(this::update, roleResource)
                .set(roleId).equalTo(record::getRoleId)
                .set(resourceId).equalTo(record::getResourceId)
                .where(roleResourceId, isEqualTo(record::getRoleResourceId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(RoleResource record) {
        return UpdateDSL.updateWithMapper(this::update, roleResource)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(resourceId).equalToWhenPresent(record::getResourceId)
                .where(roleResourceId, isEqualTo(record::getRoleResourceId))
                .build()
                .execute();
    }
}