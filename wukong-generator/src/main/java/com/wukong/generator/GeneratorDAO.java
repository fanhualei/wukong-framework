package com.wukong.generator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneratorDAO {

    public static  void main(String[] arg){
        GeneratorDAO generatorDAO=new GeneratorDAO();
        try {
            generatorDAO.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void run() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        String classPath=GeneratorDAO.class.getResource("/").getPath();
        File configFile = new File(classPath+"generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

        Context context=  config.getContexts().get(0);


        myBatisGenerator.generate(null);

        GeneratedJavaFile gj =myBatisGenerator.getGeneratedJavaFiles().get(0);


        File project = new File("log");

        int i=0;


    }

}
