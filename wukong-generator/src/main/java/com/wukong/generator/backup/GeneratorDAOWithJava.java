package com.wukong.generator.backup;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;


/**
 * 这是一个例子，可以先保留
 */
public class GeneratorDAOWithJava {
    public static  void main(String[] arg){
        GeneratorDAOWithJava generatorDAO=new GeneratorDAOWithJava();
        try {
            generatorDAO.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void run() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        Configuration config = initConfig();
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

    }

    public Configuration initConfig(){
        Configuration config=new Configuration();
//        //set classPathEntry
//        String location="/home/fan/IdeaProjects/wukong-framework/wukong-generator/src/main/resources/mysql-connector-java-5.1.45-bin.jar";
//        config.addClasspathEntry(location);
//
//
//
//        //set context
//        Context context = new Context(ModelType.CONDITIONAL);//flat = 为每个表生成一个类
//        context.setId("wukong-generator");
//        context.setTargetRuntime("MyBatis3DynamicSql");
//
//        //set pulgins
//        PluginConfiguration toStringPlugin = new PluginConfiguration();
//        toStringPlugin.addProperty("type", "org.mybatis.generator.plugins.ToStringPlugin");
//        toStringPlugin.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");
//        context.addPluginConfiguration(toStringPlugin);
//
//        //set commentGenerator
//        CommentGeneratorConfiguration commentConfig = new CommentGeneratorConfiguration();
//        commentConfig.addProperty("suppressAllComments","false");
//        commentConfig.addProperty("suppressDate","false");
//        commentConfig.addProperty("addRemarkComments","true");//include table and column remarks from db table
//        context.setCommentGeneratorConfiguration(commentConfig);
//
//        //set property
//        context.addProperty("prefix","wk_");
//        context.addProperty("ignores","user");
//
//
//
//
//        //set jdbcConnection
//        JDBCConnectionConfiguration jdbcConfig = new JDBCConnectionConfiguration();
//        jdbcConfig.setDriverClass("com.mysql.jdbc.Driver");
//        jdbcConfig.setConnectionURL("jdbc:mysql://127.0.0.1:3306/wukong_read?useSSL=false");
//        jdbcConfig.setUserId("root");
//        jdbcConfig.setPassword("rootmysql");
//        context.setJdbcConnectionConfiguration(jdbcConfig);
//
//
//        //set javaTypeResolver
//        JavaTypeResolverConfiguration javaTypeResolverConfig=new JavaTypeResolverConfiguration();
//        javaTypeResolverConfig.addProperty("forceBigDecimals","false");
//        context.setJavaTypeResolverConfiguration(javaTypeResolverConfig);
//
//        //set javaModel
//        JavaModelGeneratorConfiguration  javaModelGeneratorConfig =new JavaModelGeneratorConfiguration();
//        javaModelGeneratorConfig.setTargetPackage("com.wukong.examples.entity");
//        javaModelGeneratorConfig.setTargetProject("log");
//        javaModelGeneratorConfig.addProperty("enableSubPackages","true");
//        javaModelGeneratorConfig.addProperty("trimStrings","true");
//        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfig);
//
//
//        //set sqlMap  but if MyBatis3DynamicSql ,this not genrator xml
//        SqlMapGeneratorConfiguration sqlMapGeneratorConfig=new SqlMapGeneratorConfiguration();
//        sqlMapGeneratorConfig.setTargetPackage("com.wukong.examples.mapper");
//        sqlMapGeneratorConfig.setTargetProject("log");
//        sqlMapGeneratorConfig.addProperty("enableSubPackages","true");
//        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfig);
//
//
//        //set JavaClient
//        JavaClientGeneratorConfiguration javaClientGeneratorConfig=new JavaClientGeneratorConfiguration();
//        javaClientGeneratorConfig.setTargetPackage("com.wukong.examples.dao");
//        javaClientGeneratorConfig.setTargetProject("log");
//        javaClientGeneratorConfig.setConfigurationType("XMLMAPPER");
//        javaClientGeneratorConfig.addProperty("enableSubPackages","true");
//        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfig);
//
//
//        TablesInDatabase tablesContext=new TablesInDatabase(context);
//
//        //set table  get table List
//        for(TableInfo tableInfo:tablesContext.getTableList().values()) {
//            TableConfiguration tableConfig = new TableConfiguration(context);
//            tableConfig.setTableName(tableInfo.getTableName());
//            tableConfig.setDomainObjectName(tableInfo.getDomainObjectName());
//
//            if(tableInfo.getPKeyFieldName()!=null&&tableInfo.getPKeyFieldName().length()>0) {
//                GeneratedKey generatedKey = new GeneratedKey(tableInfo.getPKeyFieldName()
//                        , "Mysql", true, null);
//                tableConfig.setGeneratedKey(generatedKey);//add key
//            }
//            context.addTableConfiguration(tableConfig);
//        }
//        //set context
//        config.addContext(context);
        return config;


    }





}
