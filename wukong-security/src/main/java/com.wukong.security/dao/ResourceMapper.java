package com.wukong.security.dao;

import com.wukong.security.model.Resource;
import com.wukong.security.model.ResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface ResourceMapper {
    @SelectProvider(type=ResourceSqlProvider.class, method="countByExample")
    long countByExample(ResourceExample example);

    @DeleteProvider(type=ResourceSqlProvider.class, method="deleteByExample")
    int deleteByExample(ResourceExample example);

    @Delete({
        "delete from wk_resource",
        "where resource_id = #{resourceId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer resourceId);

    @Insert({
        "insert into wk_resource (resource_name, url, ",
        "description, sort, ",
        "parent_id)",
        "values (#{resourceName,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR}, #{sort,jdbcType=INTEGER}, ",
        "#{parentId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="resourceId", before=false, resultType=Integer.class)
    int insert(Resource record);

    @InsertProvider(type=ResourceSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="resourceId", before=false, resultType=Integer.class)
    int insertSelective(Resource record);

    @SelectProvider(type=ResourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="resource_name", property="resourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER)
    })
    List<Resource> selectByExample(ResourceExample example);

    @Select({
        "select",
        "resource_id, resource_name, url, description, sort, parent_id",
        "from wk_resource",
        "where resource_id = #{resourceId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="resource_name", property="resourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER)
    })
    Resource selectByPrimaryKey(Integer resourceId);

    @UpdateProvider(type=ResourceSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    @UpdateProvider(type=ResourceSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    @UpdateProvider(type=ResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Resource record);

    @Update({
        "update wk_resource",
        "set resource_name = #{resourceName,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=INTEGER},",
          "parent_id = #{parentId,jdbcType=INTEGER}",
        "where resource_id = #{resourceId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Resource record);
}