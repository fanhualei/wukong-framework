package com.wukong.generator.service;


import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;
import org.apache.commons.io.FileUtils;


import java.io.File;

public class ServiceGenerator {

    private Boolean writeFile=true;
    private Boolean saveOldFile=true;

    public static void main(String[] args) throws Exception {
        ServiceGenerator serviceMain=new ServiceGenerator();
        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(ServiceGenerator.class).resolve(".."));
        System.out.printf(sourceRoot.getRoot().toString());

        String daoFilePath=sourceRoot.getRoot().toString()+"/log/com/wukong/examples/dao/"+"OrderMapper.java";
        String servicePath=sourceRoot.getRoot().toString()+"/log/com/wukong/examples/servic/";
        String servicePackageName="com.wukong.examples.service";

        serviceMain.run(daoFilePath,servicePath,servicePackageName);
    }


    public void run(String daoFilePath,String servicePath,String servicePackageName)throws Exception{
        File daoFile = new File(daoFilePath);
        DaoBeanInfo daoBeanInfo=new DaoBeanInfo(daoFile);
        ServiceImplInfo serviceImplInfo=new ServiceImplInfo(daoBeanInfo,servicePackageName,servicePath);
        creatJavaFile(serviceImplInfo);
    }

    private void creatJavaFile(ServiceImplInfo serviceInfo) throws Exception{
        if(writeFile){
            File file=FileUtils.getFile(serviceInfo.getFileName()+".java");
            if(file.exists()&& this.saveOldFile){
                File fileNew= FileUtils.getFile(serviceInfo.getFileName()+"1.java");
                FileUtils.write(fileNew,serviceInfo.toString(),"UTF-8");
            }else{
                FileUtils.write(file,serviceInfo.toString(),"UTF-8");
            }
        }
    }



}


