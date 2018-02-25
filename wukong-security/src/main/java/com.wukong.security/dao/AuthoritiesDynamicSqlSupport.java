package com.wukong.security.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class AuthoritiesDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Authorities authorities = new Authorities();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> username = authorities.username;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> authority = authorities.authority;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Authorities extends SqlTable {
        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> authority = column("authority", JDBCType.VARCHAR);

        public Authorities() {
            super("wk_authorities");
        }
    }
}