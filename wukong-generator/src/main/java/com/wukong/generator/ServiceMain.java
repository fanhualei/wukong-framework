package com.wukong.generator;


import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;
import com.wukong.generator.service.DaoBeanInfo;
import com.wukong.generator.service.ServiceImplInfo;
import org.apache.commons.io.FileUtils;


import java.io.File;

public class ServiceMain {

    private Boolean writeFile=true;
    private Boolean saveOldFile=true;

    public static void main(String[] args) throws Exception {

        ServiceMain serviceMain=new ServiceMain();
        serviceMain.run();


    }


    private void run()throws Exception{
        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(ServiceMain.class).resolve(".."));
        System.out.printf(sourceRoot.getRoot().toString());
        File daoFile = new File(sourceRoot.getRoot().toString()+"/log/com/wukong/examples/dao/"+"OrderMapper.java");

        DaoBeanInfo daoBeanInfo=new DaoBeanInfo(daoFile);

        String aPath= sourceRoot.getRoot().toString()+"/log/com/wukong/examples/servic/";
        ServiceImplInfo serviceImplInfo=new ServiceImplInfo(daoBeanInfo,"com.wukong.examples.service",aPath);

        System.out.printf(serviceImplInfo.toString());
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

    public static String getIndent(){
        return "    ";
    }

}


