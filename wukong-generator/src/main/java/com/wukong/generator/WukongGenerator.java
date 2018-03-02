package com.wukong.generator;

import com.wukong.generator.mybatis.TableInfo;
import com.wukong.generator.mybatis.TablesInDatabase;
import com.wukong.generator.service.ServiceConfig;
import com.wukong.generator.service.ServiceGenerator;
import com.wukong.generator.util.*;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WukongGenerator {

    public static  void main(String[] arg){
        WukongGenerator wukongGenerator=new WukongGenerator();
        try {
            wukongGenerator.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void run() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String classPath=WukongGenerator.class.getResource("/").getPath();
        File configFile = new File(classPath+"generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);


        ServiceConfig serviceConfig=  initServiceConfig(config);

        if (serviceConfig.isRunWukongRule()) {
            addTableConfigByWK(config,serviceConfig);
        }

        myBatisGenerator.generate(null);

        GeneratorUtil generatorUtil=new GeneratorUtil();

        //genarate service
        if(serviceConfig.isRunWukongRule()) {
            List<GeneratedJavaFile>  lists=  myBatisGenerator.getGeneratedJavaFiles();
            for(GeneratedJavaFile file:lists){
                if( file.getFileName().lastIndexOf("Mapper.java")!=-1){
                    File daoDirFile=  generatorUtil.getDirectory(file.getTargetProject(),file.getTargetPackage());
                    File daoFile=new File(daoDirFile,file.getFileName());
                    ServiceGenerator serviceGenerator=new ServiceGenerator(serviceConfig.isDynamicService());

                    File serviceDirFile=generatorUtil.getDirectory(serviceConfig.getTargetProject(),serviceConfig.getTargetPackage());
                    serviceGenerator.run(daoFile.getAbsolutePath(),serviceDirFile.getAbsolutePath(),serviceConfig.getTargetPackage());

                }
            }
        }
    }

    //get serviceConfig
    public ServiceConfig initServiceConfig(Configuration config){
        Context context=config.getContexts().get(0);
        Properties javaClientProperties=  context.getJavaClientGeneratorConfiguration().getProperties();
        ServiceConfig serviceConfig=new ServiceConfig();


        serviceConfig.setTargetRuntime(context.getTargetRuntime());

        for(Object key:javaClientProperties.keySet()) {
            String value=(String) javaClientProperties.get(key);
            if ("prefix".equals(key))
                serviceConfig.setPrefix(value);
            else if ("ignores".equals(key))
                serviceConfig.setIgnores( value);
            else if("targetPackage".equals(key))
                serviceConfig.setTargetPackage(value);
            else if("targetProject".equals(key))
                serviceConfig.setTargetProject(value);
            else if("runWukongRule".equals(key)) {
                if(value.equalsIgnoreCase("true"))
                    serviceConfig.setRunWukongRule(true);
                else
                    serviceConfig.setRunWukongRule(false);
            }
        }
        return serviceConfig;
    }

    /**
     * clear config TableList and setNewTable
     * @param config
     */
    public void addTableConfigByWK(Configuration config,ServiceConfig serviceConfig){
        Context context=  config.getContexts().get(0);
        List<TableConfiguration> tableList=  context.getTableConfigurations();
        tableList.clear();
        TablesInDatabase tablesInDatabase =new TablesInDatabase(context,serviceConfig);

        //set table  get table List
        for(TableInfo tableInfo: tablesInDatabase.getTableList().values()) {
            TableConfiguration tableConfig = new TableConfiguration(context);
            tableConfig.setTableName(tableInfo.getTableName());
            tableConfig.setDomainObjectName(tableInfo.getDomainObjectName());

            if(tableInfo.getPKeyFieldName()!=null&&tableInfo.getPKeyFieldName().length()>0) {
                GeneratedKey generatedKey = new GeneratedKey(tableInfo.getPKeyFieldName()
                        , "Mysql", true, null);
                tableConfig.setGeneratedKey(generatedKey);//add key
            }
            context.addTableConfiguration(tableConfig);
        }
    }


}
