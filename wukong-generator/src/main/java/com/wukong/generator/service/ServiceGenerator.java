package com.wukong.generator.service;


import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;
import com.wukong.generator.service.daoinfo.DaoBeanInfo;
import com.wukong.generator.service.daoinfo.DynamicDaoBeanInfo;
import com.wukong.generator.service.daoinfo.XmlDaoBeanInfo;
import com.wukong.generator.service.serviceInfo.DynamicServiceInfo;
import com.wukong.generator.service.serviceInfo.ServiceInfo;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.text.SimpleDateFormat;

public class ServiceGenerator {

    private Boolean writeFile=true;
    private Boolean saveOldFile=true;

    private boolean isDynamicService;


    public ServiceGenerator(boolean isDynamicService_){
        isDynamicService=isDynamicService_;
    }


    //这个测试程序可能有错误
    public static void main(String[] args) throws Exception {
        /*
        ServiceGenerator serviceMain=new ServiceGenerator(true);
        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(ServiceGenerator.class).resolve(".."));
        System.out.printf(sourceRoot.getRoot().toString());

        String daoFilePath=sourceRoot.getRoot().toString()+"/log/com/wukong/examples/dao/"+"OrderMapper.java";
        String servicePath=sourceRoot.getRoot().toString()+"/log/com/wukong/examples/servic/";
        String servicePackageName="com.wukong.examples.service";

        serviceMain.run(daoFilePath,servicePath,servicePackageName);
        */
    }


    public void run(String daoFilePath,String servicePath,String servicePackageName)throws Exception{
        File daoFile = new File(daoFilePath);
        DaoBeanInfo daoBeanInfo;
        ServiceInfo serviceInfo;

        if(isDynamicService){
            daoBeanInfo=new DynamicDaoBeanInfo(daoFile);
            serviceInfo =new DynamicServiceInfo(daoBeanInfo,servicePackageName,servicePath);
        }else {
            daoBeanInfo=new XmlDaoBeanInfo(daoFile);
            serviceInfo =new DynamicServiceInfo(daoBeanInfo,servicePackageName,servicePath);
        }

        creatJavaFile(serviceInfo);
    }

    private void creatJavaFile(ServiceInfo serviceInfo) throws Exception{

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dateStr=df.format(System.currentTimeMillis());


        if(writeFile){
            File file=FileUtils.getFile(serviceInfo.getFileName()+".java");
            if(file.exists()&& this.saveOldFile){
                File fileNew= FileUtils.getFile(serviceInfo.getFileName()+dateStr+".java");
                FileUtils.write(fileNew,serviceInfo.toString(),"UTF-8");
            }else{
                FileUtils.write(file,serviceInfo.toString(),"UTF-8");
            }
        }
    }



}


