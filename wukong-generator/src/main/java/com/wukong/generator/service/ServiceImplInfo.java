//package com.wukong.core.generator.service;
//
//import com.wukong.core.generator.config.GeneratorConfig;
//import org.apache.commons.lang3.StringUtils;
//
//
///**
// * Created by fanhl on 2016/7/1.
// */
//public class ServiceImplInfo implements ServiceInfo{
//    private DaoBeanInfo daoBeanInfo;
//    private String packageName;
//    private String path;
//    private ServiceInterfaceInfo serviceInterfaceInfo;
//
//    public ServiceImplInfo(DaoBeanInfo aDaoBeanInfo,String aPackageName
//            ,String aPath,ServiceInterfaceInfo aServiceInterfaceInfo){
//        daoBeanInfo=aDaoBeanInfo;
//        packageName=aPackageName;
//        path=aPath;
//        serviceInterfaceInfo=aServiceInterfaceInfo;
//    }
//
//    public String getBeanName(){
//        String str= daoBeanInfo.getName();
//        str=str.substring(0,str.length()-"Mapper".length());
//        return str+"ServiceImpl";
//    }
//
//    /**
//     * 不包含.java后缀的名称
//     * @return
//     */
//    public String getFileName(){
//        return path+"\\"+getBeanName();
//    }
//
//    public String toString(){
//        StringBuffer sb= new StringBuffer();
//        sb.append(getPackgeStr());
//        sb.append(getImportStr());
//        sb.append(getClassStr());
//        sb.append(getClassVariablesStr());
//        sb.append(getMethodsStr());
//        sb.append(getEndStr());
//        return sb.toString();
//    }
//
//
//    //////////////////////////////////下面都是私有函数/////////////////////////////////////////////////////
//    /**
//     * 得到packgae语句
//     * @return
//     */
//    private  String getPackgeStr(){
//        String str= "package "+packageName +";\n\n";
//        return str;
//    }
//
//    /**
//     * 得到import语句
//     * @return
//     */
//    private  String getImportStr(){
//        StringBuffer sb= new StringBuffer();
//        for(String e:this.daoBeanInfo.getImports()){
//            sb.append(e);
//        }
//        sb.append("\n");
//
//        //生成Service专用的包
//
//        sb.append("import "+daoBeanInfo.getPackageName()+"."+daoBeanInfo.getName()+";");
//        sb.append("\n");
//        sb.append("import "+serviceInterfaceInfo.getPackageName()+"."+serviceInterfaceInfo.getBeanName()+";");
//        sb.append("\n");
//        sb.append("import org.springframework.transaction.annotation.Transactional;");
//        sb.append("\n");
//        sb.append("import org.springframework.stereotype.Service;");
//        sb.append("\n");
//        sb.append("import javax.annotation.Resource;");
//        sb.append("\n");
//        sb.append("import com.wukong.core.logging.Log;");
//        sb.append("\n");
//        sb.append("import com.wukong.core.logging.LogFactory;");
//        sb.append("\n\n");
//        return sb.toString();
//    }
//
//    /**
//     * 得到Class的定义
//     * @return
//     */
//    private String getClassStr(){
//        StringBuffer sb= new StringBuffer();
//        String springBeanName=StringUtils.uncapitalize(this.daoBeanInfo.getModelName()) + "Service";
//        sb.append("@Service(\""+springBeanName+"\")");
//        sb.append("\n");
//        sb.append("public class "+getBeanName()+" implements "+ this.serviceInterfaceInfo.getBeanName()+" {");
//        sb.append("\n");
//        return sb.toString();
//    }
//
//    /**
//     * 得到Class的成员变量
//     * @return  例如下面代码
//            private Log logger = LogFactory.getLog(getClass());
//            //利用SpingIOC注入DAO变量
//            @Resource(name="usersMapper")
//            private UsersMapper usersMapper;
//     */
//    private String getClassVariablesStr(){
//        StringBuffer sb= new StringBuffer();
//        String s=GeneratorConfig.getIndent();
//        String spingDaoName= StringUtils.uncapitalize(this.daoBeanInfo.getName()) ;
//        sb.append(s).append("private Log logger = LogFactory.getLog(getClass());").append("\n");
//        sb.append(s).append("//利用SpingIOC注入DAO变量").append("\n");
//        sb.append(s).append("@Resource(name =\""+spingDaoName+"\")").append("\n");
//        sb.append(s).append("private "+daoBeanInfo.getName()+" "+spingDaoName+";").append("\n");
//        sb.append("\n");
//        return sb.toString();
//    }
//
//
//
//    /**
//     * 得到函数定义
//     * @return  例如下面
//                public int countByExample(UsersExample example) {
//                    return usersMapper.countByExample(example);
//                }
//     */
//    private String getMethodsStr(){
//
//        String s=GeneratorConfig.getIndent();
//        StringBuffer sb= new StringBuffer();
//        for(String e:this.daoBeanInfo.getMethods()){
//            sb.append(getMethodComment());  //函数注释
//            sb.append(getMethodAnnotation(e)); //函数注解释
//
//            String tempStr=e.substring(e.indexOf(" "),e.length()-1).trim();
//            String funStr="public "+e.substring(0,e.length()-1)+" {";
//
//            sb.append(s).append(funStr).append("\n"); //函数头
//            sb.append(s).append(s).append(getDaoFunStr(tempStr)).append("\n"); //函数体
//            sb.append(s).append("}").append("\n");
//        }
//        return sb.toString();
//    }
//
//
//
//    /**
//     * 得到函数的注释
//     * @return  @Transactional
//     * @param methodStr :例如int deleteByExample(UsersExample example);
//     */
//    private String getMethodAnnotation(String methodStr){
//
//        String methodName = methodStr.substring(methodStr.indexOf(" "),methodStr.indexOf("(")).trim();
//        //如果是查询语句，那么可以将事务声明称只读
//        String transStr="@Transactional";
//        if(methodName.startsWith("selectBy")
//                || methodName.startsWith("countBy")
//                ){
//            transStr="@Transactional(readOnly=true)";
//        }
//        StringBuffer sb= new StringBuffer();
//        String s=GeneratorConfig.getIndent();
//        sb.append(s).append(transStr).append("\n");
//        return sb.toString();
//    }
//
//    /**
//     * 得到函数的注释
//     * @return
//     */
//    private String getMethodComment(){
//        StringBuffer sb= new StringBuffer();
//        String s=GeneratorConfig.getIndent();
//        sb.append(s).append("/**").append("\n");
//        sb.append(s).append(" * ").append("This method was generated by ServiceInterfaceInfo Generator.").append("\n");
//        sb.append(s).append(" * ").append("@wkgenerated").append("\n");
//        sb.append(s).append(" */").append("\n");
//        return sb.toString();
//    }
//
//
//    /**
//     * 得到Dao函数的调用
//     * @param fun  例如: selectByExample(UsersExample example)
//     * @return     例如：return usersMapper.selectByExample(example);
//     */
//    private String getDaoFunStr(String fun){
//        String paraStr= fun.substring(fun.indexOf("(")+1,fun.lastIndexOf(")"));
//        String[] paras=paraStr.split(",");
//        String  variates= "";
//        int i = paras.length;
//        for(String e:paras){
//            e=e.trim();
//            String temp=e.substring(e.indexOf(" "),e.length()).trim();
//            variates=variates+temp;
//            i=i-1;
//            if(i>0){
//                variates=variates+",";
//            }
//        }
//
//        String funName=fun.substring(0,fun.indexOf("(")).trim();
//
//        String daoFunstr="return "+ StringUtils.uncapitalize(this.daoBeanInfo.getName())
//                          + "." + funName
//                          + "("+variates+")"
//                          +";";
//        return daoFunstr;
//    }
//
//
//    /**
//     * 得到结尾
//     * @return  }
//     */
//    private String getEndStr(){
//        return "}";
//    }
//
//
//}
