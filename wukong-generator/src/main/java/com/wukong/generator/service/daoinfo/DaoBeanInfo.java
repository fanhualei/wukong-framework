package com.wukong.generator.service.daoinfo;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;


import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by fanhl on 2018/2/19.
 */
@Slf4j
public abstract class DaoBeanInfo {
    private String className;
    private List<String> imports;
    private CompilationUnit cu;
    private String packageName;
    protected Map<String,String> methods = new LinkedHashMap<String,String>();
    protected String[] outPutMethods;

    public DaoBeanInfo(File aDaoFile) throws Exception{
        cu = JavaParser.parse(aDaoFile);
        packageName=cu.getPackageDeclaration().get().getNameAsString();
        className=cu.getTypes().get(0).getNameAsString();
        imports=initImports();

        initMethods();

        cu.accept(new DaoBeanInfo.MethodVisitor(), null);
        methods.put("count","long count()");//  dao has count method
   }


    /**
     * 动态sql 只生成一部分函数， 如果是xml或注解的，要生成全部的函数
     */
    protected abstract void initMethods();


    //init methods
    private  class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {

            String methodStr;

            String typeStr=n.getType().asString();
            String nameStr=n.getNameAsString();
            String parametersStr= StringUtils.join(n.getParameters().toArray(),",");
            methodStr=typeStr + " " +nameStr+"("+parametersStr+")";

            //如果没有指定要生成的函数，就生成全部
            if(outPutMethods!=null && outPutMethods.length>0) {
                for (String s : outPutMethods) {
                    if (s.equals(nameStr))
                        methods.put(s, methodStr);
                }
            }else{
                methods.put(nameStr, methodStr);
            }

            super.visit(n, arg);
        }
    }

    public String getName(){
        return className;
    }

    public List<String> getImports(){
        return imports;
    }

    public Map<String,String> getMethods(){
        return methods;
    }

    private String[] getOutPutMethods(){
//        if(outPutMethods!=null&&outPutMethods.length>0){
//            return outPutMethods; //表示有默认值
//        }
//
//        outPutMethods =new String[methods.size()];
//        for (Integer key : map.keySet()) {
//
//            System.out.println("Key = " + key);
//
//        }


        return outPutMethods;
    }

    public String getPackageName(){
        return cu.getPackageDeclaration().get().getName().toString();
    }


    public String getModelName(){
        String str= getName();
        str=str.substring(0,str.length()-"Mapper".length());
        return str;
    }

    /**
     * 得到 model import
     * @throws Exception
     */
    private List<String> initImports() throws Exception{
        NodeList<ImportDeclaration> list=  cu.getImports();
        List<String> tempImports =new ArrayList<String>();
        for(ImportDeclaration e:list){
            String str =e.toString();
            if(str.lastIndexOf("."+getName()+";")!=-1
                ||  str.lastIndexOf("."+getModelName()+";")!=-1 ){
                tempImports.add(str);
            }
        }
        return tempImports;
    }


}
