package com.wukong.generator.util;

import lombok.Getter;
import org.apache.commons.lang.ArrayUtils;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.io.*;
import java.sql.*;
import java.util.Map;

public class TablesContext {
    private Context context;
    private String driver;
    private String url;
    private String userid;
    private String password;

    private String prefix;//表的前缀
    private String[] ignores; //忽略的表
    private String tableSchema;

    @Getter
    private Map<String,TableInfo> tableList=new HashMap<String, TableInfo>();
//
    public TablesContext(Context a_context){
        context=a_context;

        driver= a_context.getJdbcConnectionConfiguration().getDriverClass();
        url=a_context.getJdbcConnectionConfiguration().getConnectionURL();
        userid=a_context.getJdbcConnectionConfiguration().getUserId();
        password=a_context.getJdbcConnectionConfiguration().getPassword();

        prefix=a_context.getProperty("prefix");

        String strIgnores=a_context.getProperty("ignores");
        if( strIgnores!=null){
            ignores=strIgnores.split(",");
        }

        tableSchema=getTableSchema(url);
        try {
            initTables();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * get Schema
     * @param aUrl
     * @return
     */
    private String getTableSchema(String aUrl){
        int stratPos=aUrl.lastIndexOf("/");
        int endPos=aUrl.lastIndexOf("?");
        String ren="";

        if(endPos>0){
            ren = aUrl.substring(stratPos + 1, endPos);
        }else {
            ren = aUrl.substring(stratPos + 1, aUrl.length());
        }
        return ren;
    }


    private void initTables() throws Exception {
        StringBuffer ren= new StringBuffer();
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userid, password);
            Statement statement = connection.createStatement();

            String getTableNameSql = "select distinct  table_name  from information_schema.`COLUMNS`  where table_schema='"+tableSchema+"'";
            ResultSet tableRt = statement.executeQuery(getTableNameSql);
            while (tableRt.next()) {
                String tabelName = tableRt.getString("table_name");
                //判断如果以prefix开头，生成的对象去掉前缀
                if(ArrayUtils.contains(ignores,tabelName)
                        || ArrayUtils.contains(ignores,tabelName.substring(prefix.length(),tabelName.length()))){
                    continue; //ingore talbe 不执行
                }

                TableInfo tableInfo=new TableInfo();
                tableInfo.setTableName(tabelName);

                if (tabelName.startsWith(prefix)) {
                    String domainObjectName = tabelName.substring(prefix.length(), tabelName.length());
                    domainObjectName = JavaBeansUtil.getCamelCaseString(domainObjectName, true);
                    tableInfo.setDomainObjectName(domainObjectName);
                } else {
                    String domainObjectName = JavaBeansUtil.getCamelCaseString(tabelName, true);
                    tableInfo.setDomainObjectName(domainObjectName);
                }

                String pkeyFieldName = getPKeyFieldName(connection, tabelName);
                if (pkeyFieldName.length() > 0) {
                    tableInfo.setPKeyFieldName(pkeyFieldName);
                }
                tableList.put(tabelName,tableInfo);
            }

            tableRt.close();
            closeStatement(statement);
            //connection.commit();
        }catch (Exception e){
            throw e;
        }
        finally {
            closeConnection(connection);
        }
    }


    /**
     * get PKeyFieldName
     * @param connection
     * @param tabelName
     * @return
     * @throws Exception
     */
    private String getPKeyFieldName(Connection connection, String tabelName) throws Exception {
        String pkeyFieldName = "";
        Statement statement = connection.createStatement();
        String getAutoPkSql = "select column_name from information_schema.`COLUMNS`  " +
                "where table_schema='"+tableSchema+"'  and extra='auto_increment'  " +
                "and table_name='" + tabelName + "'";
        ResultSet resultSet = statement.executeQuery(getAutoPkSql);

        int i=0;
        while (resultSet.next()) {
            if(i==0)
                pkeyFieldName=resultSet.getString("column_name");
            else
                pkeyFieldName=pkeyFieldName+","+resultSet.getString("column_name");
            i=i+1;
        }
        resultSet.close();
        return pkeyFieldName;
    }

    /**
     * close connection
     * @param connection
     */
    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * close statement
     * @param statement
     */
    private void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
