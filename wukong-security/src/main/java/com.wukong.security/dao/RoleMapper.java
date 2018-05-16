package com.wukong.security.dao;

import com.wukong.security.model.Role;
import com.wukong.security.model.RoleExample;
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
public interface RoleMapper {
    @SelectProvider(type=RoleSqlProvider.class, method="countByExample")
    long countByExample(RoleExample example);

    @DeleteProvider(type=RoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(RoleExample example);

    @Delete({
        "delete from wk_role",
        "where role_id = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roleId);

    @Insert({
        "insert into wk_role (rolename, description, ",
        "sort)",
        "values (#{rolename,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{sort,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roleId", before=false, resultType=Integer.class)
    int insert(Role record);

    @InsertProvider(type=RoleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roleId", before=false, resultType=Integer.class)
    int insertSelective(Role record);

    @SelectProvider(type=RoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rolename", property="rolename", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<Role> selectByExample(RoleExample example);

    @Select({
        "select",
        "role_id, rolename, description, sort",
        "from wk_role",
        "where role_id = #{roleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rolename", property="rolename", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    Role selectByPrimaryKey(Integer roleId);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update wk_role",
        "set rolename = #{rolename,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where role_id = #{roleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Role record);

    @Select({
            "select",
            "role_id, rolename, description, sort",
            "from wk_role",
            "where role_id = #{roleId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="rolename", property="rolename", jdbcType=JdbcType.VARCHAR),
            @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<Role> selectByRoleId(Integer roleId);//仅仅为了统一返回值
}