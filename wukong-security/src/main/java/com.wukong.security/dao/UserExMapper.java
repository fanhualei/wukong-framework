package com.wukong.security.dao;

import com.wukong.security.model.UserEx;
import com.wukong.security.model.UserExExample;
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
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserExMapper {
    @SelectProvider(type=UserExSqlProvider.class, method="countByExample")
    long countByExample(UserExExample example);

    @DeleteProvider(type=UserExSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserExExample example);

    @Delete({
        "delete from wk_user_ex",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into wk_user_ex (user_id, nickname, ",
        "signature, qq, weixin, ",
        "weibo, avatar)",
        "values (#{userId,jdbcType=INTEGER}, #{nickname,jdbcType=VARCHAR}, ",
        "#{signature,jdbcType=VARCHAR}, #{qq,jdbcType=VARCHAR}, #{weixin,jdbcType=VARCHAR}, ",
        "#{weibo,jdbcType=VARCHAR}, #{avatar,jdbcType=VARCHAR})"
    })
    int insert(UserEx record);

    @InsertProvider(type=UserExSqlProvider.class, method="insertSelective")
    int insertSelective(UserEx record);

    @SelectProvider(type=UserExSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="signature", property="signature", jdbcType=JdbcType.VARCHAR),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="weixin", property="weixin", jdbcType=JdbcType.VARCHAR),
        @Result(column="weibo", property="weibo", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR)
    })
    List<UserEx> selectByExample(UserExExample example);

    @Select({
        "select",
        "user_id, nickname, signature, qq, weixin, weibo, avatar",
        "from wk_user_ex",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="signature", property="signature", jdbcType=JdbcType.VARCHAR),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="weixin", property="weixin", jdbcType=JdbcType.VARCHAR),
        @Result(column="weibo", property="weibo", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR)
    })
    UserEx selectByPrimaryKey(Integer userId);

    @UpdateProvider(type=UserExSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserEx record, @Param("example") UserExExample example);

    @UpdateProvider(type=UserExSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserEx record, @Param("example") UserExExample example);

    @UpdateProvider(type=UserExSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserEx record);

    @Update({
        "update wk_user_ex",
        "set nickname = #{nickname,jdbcType=VARCHAR},",
          "signature = #{signature,jdbcType=VARCHAR},",
          "qq = #{qq,jdbcType=VARCHAR},",
          "weixin = #{weixin,jdbcType=VARCHAR},",
          "weibo = #{weibo,jdbcType=VARCHAR},",
          "avatar = #{avatar,jdbcType=VARCHAR}",
        "where user_id = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserEx record);
}