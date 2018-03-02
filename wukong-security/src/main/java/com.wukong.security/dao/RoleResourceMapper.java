package com.wukong.security.dao;

import com.wukong.security.model.RoleResource;
import com.wukong.security.model.RoleResourceExample;
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
public interface RoleResourceMapper {
    @SelectProvider(type=RoleResourceSqlProvider.class, method="countByExample")
    long countByExample(RoleResourceExample example);

    @DeleteProvider(type=RoleResourceSqlProvider.class, method="deleteByExample")
    int deleteByExample(RoleResourceExample example);

    @Delete({
        "delete from wk_role_resource",
        "where role_resource_id = #{roleResourceId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roleResourceId);

    @Insert({
        "insert into wk_role_resource (role_id, resource_id)",
        "values (#{roleId,jdbcType=INTEGER}, #{resourceId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roleResourceId", before=false, resultType=Integer.class)
    int insert(RoleResource record);

    @InsertProvider(type=RoleResourceSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roleResourceId", before=false, resultType=Integer.class)
    int insertSelective(RoleResource record);

    @SelectProvider(type=RoleResourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="role_resource_id", property="roleResourceId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER)
    })
    List<RoleResource> selectByExample(RoleResourceExample example);

    @Select({
        "select",
        "role_resource_id, role_id, resource_id",
        "from wk_role_resource",
        "where role_resource_id = #{roleResourceId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="role_resource_id", property="roleResourceId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER)
    })
    RoleResource selectByPrimaryKey(Integer roleResourceId);

    @UpdateProvider(type=RoleResourceSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    @UpdateProvider(type=RoleResourceSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    @UpdateProvider(type=RoleResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RoleResource record);

    @Update({
        "update wk_role_resource",
        "set role_id = #{roleId,jdbcType=INTEGER},",
          "resource_id = #{resourceId,jdbcType=INTEGER}",
        "where role_resource_id = #{roleResourceId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(RoleResource record);
}