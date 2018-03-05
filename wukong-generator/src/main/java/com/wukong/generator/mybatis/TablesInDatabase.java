package com.wukong.generator.mybatis;

import com.wukong.generator.service.ServiceConfig;
import lombok.Getter;
import org.apache.commons.lang.ArrayUtils;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.springframework.util.StringUtils;

import java.util.*;

import java.sql.*;

public class TablesInDatabase {
    //private Context context;
    private String driver;
    private String url;
    private String userid;
    private String password;

    private String prefix;//表的前缀
    private String[] ignores; //忽略的表
    private String   effectsStr;//要生成的表名例如:  " and table_name in('columns','collations')"
    private String tableSchema;

    @Getter
    private Map<String,TableInfo> tableList=new HashMap<String, TableInfo>();



    public TablesInDatabase(Context a_context, ServiceConfig serviceConfig){
        driver= a_context.getJdbcConnectionConfiguration().getDriverClass();
        url=a_context.getJdbcConnectionConfiguration().getConnectionURL();
        userid=a_context.getJdbcConnectionConfiguration().getUserId();
        password=a_context.getJdbcConnectionConfiguration().getPassword();
        prefix=serviceConfig.getPrefix();

        initEffectsStr(serviceConfig);

        String strIgnores=serviceConfig.getIgnores();
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

    private void initEffectsStr(ServiceConfig serviceConfig){

        String str=serviceConfig.getEffects();
        if(StringUtils.isEmpty(str)){
            effectsStr="";
        }else{

            StringBuffer strb=new StringBuffer();
            strb.append(" and  table_name in(");
            String[] strs=str.split(",");
            int i=0;
            for (String s : strs) {
                i=i+1;
                if(i>1) strb.append(",");
                strb.append("'"+s+"'");
            }

            strb.append(")");

            effectsStr=strb.toString();
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


            if(effectsStr.length()>5){
                getTableNameSql=getTableNameSql+ " " + effectsStr;
            }

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
