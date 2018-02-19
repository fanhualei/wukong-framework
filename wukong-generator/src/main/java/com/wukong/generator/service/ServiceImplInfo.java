package com.wukong.generator.service;


import com.wukong.generator.util.GeneratorUtil;
import org.apache.commons.lang.StringUtils;

/**
 * Created by fanhl on 2018/2/19.
 */
public class ServiceImplInfo {
    private DaoBeanInfo daoBeanInfo;
    private String packageName;
    private String path;


    public ServiceImplInfo(DaoBeanInfo aDaoBeanInfo,String aPackageName
            ,String aPath){
        daoBeanInfo=aDaoBeanInfo;
        packageName=aPackageName;
        path=aPath;
    }

    public String getBeanName(){
        String str= daoBeanInfo.getName();
        str=str.substring(0,str.length()-"Mapper".length());
        return str+"Service";
    }

    /**
     * 不包含.java后缀的名称
     * @return
     */
    public String getFileName(){
        return path+"/"+getBeanName();
    }

    public String toString(){
        StringBuffer sb= new StringBuffer();
        sb.append(getPackgeStr());
        sb.append(getImportStr());
        sb.append(getClassStr());
        sb.append(getClassVariablesStr());
        sb.append(getMethodsStr());
        sb.append(getEndStr());
        return sb.toString();
    }


    //////////////////////////////////下面都是私有函数/////////////////////////////////////////////////////
    /**
     * 得到packgae语句
     * @return
     */
    private  String getPackgeStr(){
        String str= "package "+packageName +";\n\n";
        return str;
    }

    /**
     * 得到import语句
     * @return
     */
    private  String getImportStr(){
        StringBuffer sb= new StringBuffer();
        for(String e:this.daoBeanInfo.getImports()){
            sb.append(e);
        }
        //生成Service专用的包
        sb.append("import "+daoBeanInfo.getPackageName()+"."+daoBeanInfo.getName()+";");
        sb.append("\n");sb.append("\n");

        sb.append("import org.springframework.beans.factory.annotation.Autowired;");
        sb.append("\n");
        sb.append("import org.springframework.stereotype.Service;");
        sb.append("\n");
        sb.append("import org.springframework.transaction.annotation.Transactional;");
        sb.append("\n");sb.append("\n");

        sb.append("import java.util.List;");
        sb.append("\n");
        sb.append("import lombok.extern.slf4j.Slf4j;");
        sb.append("\n\n");
        return sb.toString();
    }

    /**
     * 得到Class的定义
     * @return
     */
    private String getClassStr(){
        StringBuffer sb= new StringBuffer();
        String springBeanName= StringUtils.uncapitalize(this.daoBeanInfo.getModelName()) + "Service";
        sb.append("@Service");
        sb.append("\n");
        sb.append("@Slf4j");
        sb.append("\n");
        sb.append("public class "+getBeanName()+" {");
        sb.append("\n");
        return sb.toString();
    }

    /**
     * 得到Class的成员变量
     * @return  例如下面代码
            //利用SpingIOC注入DAO变量
            @@Autowired
            private UsersMapper usersMapper;
     */
    private String getClassVariablesStr(){
        StringBuffer sb= new StringBuffer();
        String s= GeneratorUtil.getIndent();
        String spingDaoName= StringUtils.uncapitalize(this.daoBeanInfo.getName()) ;
        sb.append(s).append("//利用SpingIOC注入DAO变量").append("\n");
        sb.append(s).append("@Autowired").append("\n");
        sb.append(s).append("private "+daoBeanInfo.getName()+" "+spingDaoName+";").append("\n");
        sb.append("\n");
        return sb.toString();
    }



    /**
     * 得到函数定义
     * @return  例如下面
                public int countByExample(UsersExample example) {
                    return usersMapper.countByExample(example);
                }
     */
    private String getMethodsStr(){

        String s= GeneratorUtil.getIndent();
        StringBuffer sb= new StringBuffer();
        for(String methodName:this.daoBeanInfo.getOutPutMethods()){
            //sb.append(getMethodComment());  //函数注释
            sb.append(getMethodAnnotation(methodName)); //函数注解释

            String e=this.daoBeanInfo.getMethods().get(methodName);
            String tempStr=e.substring(e.indexOf(" "),e.length()).trim();
            String funStr="public "+e.substring(0,e.length())+" {";

            sb.append(s).append(funStr).append("\n"); //函数头
            sb.append(s).append(s).append(getDaoFunStr(tempStr)).append("\n"); //函数体
            sb.append(s).append("}").append("\n").append("\n");
        }
        return sb.toString();
    }



    /**
     * 得到函数的注释
     * @return  @Transactional
     * @param methodName :例如int deleteByExample(UsersExample example);
     */
    private String getMethodAnnotation(String methodName){

//        String methodName = methodStr.substring(methodStr.indexOf(" "),methodStr.indexOf("(")).trim();
        //如果是查询语句，那么可以将事务声明称只读
        String transStr="@Transactional";
        if(methodName.startsWith("select")
                || methodName.startsWith("count")
                ){
            transStr="@Transactional(readOnly=true)";
        }
        StringBuffer sb= new StringBuffer();
        String s= GeneratorUtil.getIndent();
        sb.append(s).append(transStr).append("\n");
        return sb.toString();
    }

    /**
     * 得到函数的注释
     * @return
     */
    private String getMethodComment(){
        StringBuffer sb= new StringBuffer();
        String s= GeneratorUtil.getIndent();
        sb.append(s).append("/**").append("\n");
        sb.append(s).append(" * ").append("This method was generated by ServiceInterfaceInfo Generator.").append("\n");
        sb.append(s).append(" * ").append("@wkgenerated").append("\n");
        sb.append(s).append(" */").append("\n");
        return sb.toString();
    }


    /**
     * 得到Dao函数的调用
     * @param fun  例如: selectByExample(UsersExample example)
     * @return     例如：return usersMapper.selectByExample(example);
     */
    private String getDaoFunStr(String fun){
        String paraStr= fun.substring(fun.indexOf("(")+1,fun.lastIndexOf(")"));
        String[] paras=paraStr.split(",");
        String  variates= "";
        int i = paras.length;
        for(String e:paras){
            if(e.length()>0) {
                e = e.trim();
                String temp = e.substring(e.indexOf(" "), e.length()).trim();
                variates = variates + temp;
                i = i - 1;
                if (i > 0) {
                    variates = variates + ",";
                }
            }
        }

        String funName=fun.substring(0,fun.indexOf("(")).trim();

        String daoFunstr="return "+ StringUtils.uncapitalize(this.daoBeanInfo.getName())
                          + "." + funName
                          + "("+variates+")"
                          +";";

        if(funName.equals("count")){
            daoFunstr="return "+ StringUtils.uncapitalize(this.daoBeanInfo.getName())
                    + ".countByExample().build().execute();";
        }else if(funName.equals("selectAll")){
            daoFunstr="return "+ StringUtils.uncapitalize(this.daoBeanInfo.getName())
                    + ".selectByExample().build().execute()";
        }


        return daoFunstr;
    }


    /**
     * 得到结尾
     * @return  }
     */
    private String getEndStr(){
        return "}";
    }


}
