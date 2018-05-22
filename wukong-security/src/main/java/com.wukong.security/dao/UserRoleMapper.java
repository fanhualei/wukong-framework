package com.wukong.security.dao;

import com.wukong.security.model.UserRole;
import com.wukong.security.model.UserRoleExample;
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
public interface UserRoleMapper {
    @SelectProvider(type=UserRoleSqlProvider.class, method="countByExample")
    long countByExample(UserRoleExample example);

    @DeleteProvider(type=UserRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserRoleExample example);

    @Delete({
        "delete from wk_user_role",
        "where user_role_id = #{userRoleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userRoleId);

    @Insert({
        "insert into wk_user_role (user_id, role_id)",
        "values (#{userId,jdbcType=INTEGER}, #{roleId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userRoleId", before=false, resultType=Integer.class)
    int insert(UserRole record);

    @InsertProvider(type=UserRoleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userRoleId", before=false, resultType=Integer.class)
    int insertSelective(UserRole record);

    @SelectProvider(type=UserRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="user_role_id", property="userRoleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER)
    })
    List<UserRole> selectByExample(UserRoleExample example);

    @Select({
        "select",
        "user_role_id, user_id, role_id",
        "from wk_user_role",
        "where user_role_id = #{userRoleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="user_role_id", property="userRoleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER)
    })
    UserRole selectByPrimaryKey(Integer userRoleId);

    @UpdateProvider(type=UserRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    @UpdateProvider(type=UserRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    @UpdateProvider(type=UserRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserRole record);

    @Update({
        "update wk_user_role",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "role_id = #{roleId,jdbcType=INTEGER}",
        "where user_role_id = #{userRoleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserRole record);


}