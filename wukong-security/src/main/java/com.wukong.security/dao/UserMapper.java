package com.wukong.security.dao;

import com.wukong.security.model.User;
import com.wukong.security.model.UserExample;
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
public interface UserMapper {
    @SelectProvider(type=UserSqlProvider.class, method="countByExample")
    long countByExample(UserExample example);

    @DeleteProvider(type=UserSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserExample example);

    @Delete({
        "delete from wk_user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into wk_user (username, password, ",
        "enabled, phone, email, ",
        "pwResetDate)",
        "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{enabled,jdbcType=BIT}, #{phone,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
        "#{pwresetdate,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userId", before=false, resultType=Integer.class)
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userId", before=false, resultType=Integer.class)
    int insertSelective(User record);

    @SelectProvider(type=UserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="enabled", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="pwResetDate", property="pwresetdate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> selectByExample(UserExample example);

    @Select({
        "select",
        "user_id, username, password, enabled, phone, email, pwResetDate",
        "from wk_user",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="enabled", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="pwResetDate", property="pwresetdate", jdbcType=JdbcType.TIMESTAMP)
    })
    User selectByPrimaryKey(Integer userId);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update wk_user",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "enabled = #{enabled,jdbcType=BIT},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "pwResetDate = #{pwresetdate,jdbcType=TIMESTAMP}",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);


    /**
     * 得到一个用户
     * @param account，可以是用户名，邮箱，电话 不能为空
     * @return
     */
    @Select({
            "select",
            "*",
            "from wk_user",
            "where username = #{account} or phone=#{account} or email=#{account}"
    })
    @Results({
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="enabled", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="pwResetDate", property="pwresetdate", jdbcType=JdbcType.TIMESTAMP)
    })
    User selectUserByAccount( String account);

    /**
     * 依据电话号码检查用户是否已注册
     * @param cellphone 电话号码
     */
    @Select({
            "select",
            "count(*)",
            "from wk_user",
            "where  phone=#{cellphone} "
    })
    int isUserExistByCellphone(String cellphone);


    /**
     * 依据email检查用户是否已注册
     * @param email
     */
    @Select({
            "select",
            "count(*)",
            "from wk_user",
            "where  email=#{email} "
    })
    int isUserExistByEmail(String email);

    /**
     * 依据用户名检查用户是否已注册
     * @param username 用户名
     */
    @Select({
            "select",
            "count(*)",
            "from wk_user",
            "where  username=#{username} "
    })
    int isUserExistByUsername(String username);

    /**
     * 依据电话号码查询用户
     * @param cellphone 电话号码
     */
    @Select({
            "select",
            "*",
            "from wk_user",
            "where  phone=#{cellphone} "
    })
    @Results({
            @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="enabled", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="pwResetDate", property="pwresetdate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.INTEGER)
    })
    User selectByCellphone(String cellphone);


    /**
     * 依据用户id更改密码
     * @param userid 用户id
     */
    @Update({
            "update wk_user",
            "set password=#{password}",
            "where  user_id=#{userid} "
    })
    int updatePasswordbyUserid(@Param("userid") Integer userid,@Param("password") String password);


//    /**
//     * 依据uid获取role id
//     * @param uid
//     * */
//    @Select({
//            "select",
//            "role_id",
//            "from wk_user",
//            "where  user_id=#{uid} "
//    })
//    Integer selectRoleidByUserid(Integer uid);

}