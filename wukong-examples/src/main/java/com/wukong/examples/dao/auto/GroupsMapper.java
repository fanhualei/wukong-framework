package com.wukong.examples.dao.auto;

import com.wukong.examples.entity.Groups;
import org.apache.ibatis.annotations.*;
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

import javax.annotation.Generated;
import java.util.List;

import static com.wukong.examples.dao.auto.GroupsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface GroupsMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Groups> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("GroupsResult")
    Groups selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="GroupsResult", value = {
        @Result(column="groups_id", property="groupsId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR)
    })
    List<Groups> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(groups);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, groups);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer groupsId_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, groups)
                .where(groupsId, isEqualTo(groupsId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Groups record) {
        return insert(SqlBuilder.insert(record)
                .into(groups)
                .map(groupsId).toProperty("groupsId")
                .map(groupName).toProperty("groupName")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Groups record) {
        return insert(SqlBuilder.insert(record)
                .into(groups)
                .map(groupsId).toPropertyWhenPresent("groupsId", record::getGroupsId)
                .map(groupName).toPropertyWhenPresent("groupName", record::getGroupName)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Groups>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, groupsId, groupName)
                .from(groups);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Groups>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, groupsId, groupName)
                .from(groups);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Groups selectByPrimaryKey(Integer groupsId_) {
        return SelectDSL.selectWithMapper(this::selectOne, groupsId, groupName)
                .from(groups)
                .where(groupsId, isEqualTo(groupsId_))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(Groups record) {
        return UpdateDSL.updateWithMapper(this::update, groups)
                .set(groupsId).equalTo(record::getGroupsId)
                .set(groupName).equalTo(record::getGroupName);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(Groups record) {
        return UpdateDSL.updateWithMapper(this::update, groups)
                .set(groupsId).equalToWhenPresent(record::getGroupsId)
                .set(groupName).equalToWhenPresent(record::getGroupName);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Groups record) {
        return UpdateDSL.updateWithMapper(this::update, groups)
                .set(groupName).equalTo(record::getGroupName)
                .where(groupsId, isEqualTo(record::getGroupsId))
                .build()
                .execute();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Groups record) {
        return UpdateDSL.updateWithMapper(this::update, groups)
                .set(groupName).equalToWhenPresent(record::getGroupName)
                .where(groupsId, isEqualTo(record::getGroupsId))
                .build()
                .execute();
    }
}