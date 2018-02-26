package com.wukong.security.dao;

import static com.wukong.security.dao.ResourceDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.wukong.security.model.Resource;
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
public interface ResourceMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.resourceId", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Resource> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ResourceResult")
    Resource selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ResourceResult", value = {
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="resource_name", property="resourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT)
    })
    List<Resource> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(resource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, resource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long resourceId_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, resource)
                .where(resourceId, isEqualTo(resourceId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Resource record) {
        return insert(SqlBuilder.insert(record)
                .into(resource)
                .map(resourceName).toProperty("resourceName")
                .map(url).toProperty("url")
                .map(description).toProperty("description")
                .map(sort).toProperty("sort")
                .map(parentId).toProperty("parentId")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Resource record) {
        return insert(SqlBuilder.insert(record)
                .into(resource)
                .map(resourceName).toPropertyWhenPresent("resourceName", record::getResourceName)
                .map(url).toPropertyWhenPresent("url", record::getUrl)
                .map(description).toPropertyWhenPresent("description", record::getDescription)
                .map(sort).toPropertyWhenPresent("sort", record::getSort)
                .map(parentId).toPropertyWhenPresent("parentId", record::getParentId)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Resource>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, resourceId, resourceName, url, description, sort, parentId)
                .from(resource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Resource>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, resourceId, resourceName, url, description, sort, parentId)
                .from(resource);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Resource selectByPrimaryKey(Long resourceId_) {
        return SelectDSL.selectWithMapper(this::selectOne, resourceId, resourceName, url, description, sort, parentId)
                .from(resource)
                .where(resourceId, isEqualTo(resourceId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Resource record) {
        return UpdateDSL.updateWithMapper(this::update, resource)
                .set(resourceName).equalTo(record::getResourceName)
                .set(url).equalTo(record::getUrl)
                .set(description).equalTo(record::getDescription)
                .set(sort).equalTo(record::getSort)
                .set(parentId).equalTo(record::getParentId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Resource record) {
        return UpdateDSL.updateWithMapper(this::update, resource)
                .set(resourceName).equalToWhenPresent(record::getResourceName)
                .set(url).equalToWhenPresent(record::getUrl)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(sort).equalToWhenPresent(record::getSort)
                .set(parentId).equalToWhenPresent(record::getParentId);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Resource record) {
        return UpdateDSL.updateWithMapper(this::update, resource)
                .set(resourceName).equalTo(record::getResourceName)
                .set(url).equalTo(record::getUrl)
                .set(description).equalTo(record::getDescription)
                .set(sort).equalTo(record::getSort)
                .set(parentId).equalTo(record::getParentId)
                .where(resourceId, isEqualTo(record::getResourceId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Resource record) {
        return UpdateDSL.updateWithMapper(this::update, resource)
                .set(resourceName).equalToWhenPresent(record::getResourceName)
                .set(url).equalToWhenPresent(record::getUrl)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(sort).equalToWhenPresent(record::getSort)
                .set(parentId).equalToWhenPresent(record::getParentId)
                .where(resourceId, isEqualTo(record::getResourceId))
                .build()
                .execute();
    }
}