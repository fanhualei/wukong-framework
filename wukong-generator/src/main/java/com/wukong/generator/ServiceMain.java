//package com.wukong.core.generator;
//
//import com.wukong.core.generator.config.GeneratorConfig;
//import com.wukong.core.generator.service.DaoBeanInfo;
//import com.wukong.core.generator.service.ServiceImplInfo;
//import com.wukong.core.generator.service.ServiceInfo;
//import com.wukong.core.generator.service.ServiceInterfaceInfo;
//import com.wukong.core.logging.Log;
//import com.wukong.core.logging.LogFactory;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang.ArrayUtils;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * 生成service类
// * Created by fanhl on 2016/6/30.
// * 设计思路：
// * 1：通过Config文件，得到路径
// * 2：得到Dao中的文件
// * 3：解析每个Dao文件，得到DaoBeanInfo
// * 4：将DaoBeanInfo穿到ServiceGenerator，得到ServiceInterfaceInfo与ServiceImplInfo
// * 5：调用写文件类，循环写ServiceInterfaceInfo与ServiceImplInfo(其中的toString)
// */
//public class ServiceMain {
//    private Log logger = LogFactory.getLog(getClass());
//    private List<DaoBeanInfo> daoBeanInfoList=new ArrayList<>();
//    private Boolean writeFile=false;
//    private Boolean saveOldFile=true;
//
//    /**
//     * Service代码生成器
//     * @param writeFile  如果为false，那么只显示出要生成的代码
//     * @param saveOldFile  false会强行覆盖原有文件， 如果为true,生成一个文件名1的文件,原始文件保留。
//     */
//    public ServiceMain(Boolean writeFile,Boolean saveOldFile){
//        this.writeFile=writeFile;
//        this.saveOldFile=saveOldFile;
//
//
//    }
//
//    /**
//     * 执行生成文件的操作
//     * @throws Exception
//     */
//    private void execWriteFile() throws Exception{
//        File daoPath = new File(GeneratorConfig.getDaoPathName());
//        String[] extensions = {"java"};
//        Collection<File> daoFileist =FileUtils.listFiles(daoPath,extensions,false);
//        for(File e:daoFileist){
//
//            String[] ignoreDaos=GeneratorConfig.getIgnoreDao();
//            String fileName=e.getName();
//            if(ArrayUtils.contains(ignoreDaos,fileName.substring(0,fileName.indexOf(".")))){
//                logger.debug("generate service ignore:"+fileName);
//                continue;
//            }
//
//            DaoBeanInfo daoBeanInfo=new DaoBeanInfo(e);
//            daoBeanInfoList.add(daoBeanInfo);
//        }
//
//        String servicePackageName =GeneratorConfig.getServicePackageName();
//        String servicePathName =GeneratorConfig.getServicePathName();
//
//
//        for(DaoBeanInfo b:daoBeanInfoList){
//            ServiceInterfaceInfo serviceInterfaceInfo= new ServiceInterfaceInfo(b
//                                            ,servicePackageName
//                                            ,servicePathName);
//
//            logger.debug(serviceInterfaceInfo.getFileName());
//            logger.debug(serviceInterfaceInfo.toString());
//
//            ServiceImplInfo serviceImplInfo= new ServiceImplInfo(b
//                                            ,servicePackageName+".impl"
//                                            ,servicePathName+"\\impl"
//                                            ,serviceInterfaceInfo);
//
//            logger.debug(serviceImplInfo.getFileName());
//            logger.debug(serviceImplInfo.toString());
//
//            creatJavaFile(serviceInterfaceInfo); //写入接口文件
//            creatJavaFile(serviceImplInfo); //写入接口文件
//
//        }
//    }
//
//    /**
//     * 生成Java对象
//     * @param serviceInfo  传入要生成的对象
//     * @throws Exception   遇到错误抛出
//     */
//    private void creatJavaFile(ServiceInfo serviceInfo) throws Exception{
//        if(writeFile){
//            File file=FileUtils.getFile(serviceInfo.getFileName()+".java");
//            if(file.exists()&& this.saveOldFile){
//                File fileNew=FileUtils.getFile(serviceInfo.getFileName()+"1.java");
//                FileUtils.write(fileNew,serviceInfo.toString(),"UTF-8");
//            }else{
//                FileUtils.write(file,serviceInfo.toString(),"UTF-8");
//            }
//        }
//    }
//
//    public static  void main(String[] args) throws Exception{
//        System.out.println("============================");
//        ServiceMain serviceMain= new ServiceMain(true,false);
//        serviceMain.execWriteFile();
//    }
//
//
//}
