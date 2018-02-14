package com.wukong.examples.dao.auto;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class GroupsDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final Groups groups = new Groups();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> groupsId = groups.groupsId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> groupName = groups.groupName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class Groups extends SqlTable {
        public final SqlColumn<Integer> groupsId = column("groups_id", JDBCType.INTEGER);

        public final SqlColumn<String> groupName = column("group_name", JDBCType.VARCHAR);

        public Groups() {
            super("groups");
        }
    }
}