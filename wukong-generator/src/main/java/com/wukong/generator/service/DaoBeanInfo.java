package com.wukong.generator.service;

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
public  class DaoBeanInfo {
    private String className;
    private List<String> imports;
    private CompilationUnit cu;
    private String packageName;
    private Map<String,String> methods = new LinkedHashMap<String,String>();
    private String[] outPutMethods;

    public DaoBeanInfo(File aDaoFile) throws Exception{
        cu = JavaParser.parse(aDaoFile);
        packageName=cu.getPackageDeclaration().get().getNameAsString();
        className=cu.getTypes().get(0).getNameAsString();
        imports=initImports();
        // create 8 methods for service
        outPutMethods=new String[]{
                "deleteByPrimaryKey",
                "insert",
                "insertSelective",
                "updateByPrimaryKey",
                "updateByPrimaryKeySelective",
                "count",/*not in dao*/
                "selectByPrimaryKey",
                "selectAll",/*not in dao */
                };

        for(String s:outPutMethods){
            if(s.equals("count"))
                methods.put("count","long count()");
            else if(s.equals("selectAll"))
                methods.put("selectAll","List<"+getModelName()+"> selectAll()");
            else
                methods.put(s,"");
        }

        cu.accept(new DaoBeanInfo.MethodVisitor(), null);
        methods.put("count","long count()");//  dao has count method

   }

    //init methods
    private  class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {

            String methodStr;

            String typeStr=n.getType().asString();
            String nameStr=n.getNameAsString();
            String parametersStr= StringUtils.join(n.getParameters().toArray(),",");
            methodStr=typeStr + " " +nameStr+"("+parametersStr+")";
            for(String s:outPutMethods){
                if(s.equals(nameStr))
                    methods.put(s,methodStr);
            }
            super.visit(n, arg);
        }
    }

    protected String getName(){
        return className;
    }

    protected List<String> getImports(){
        return imports;
    }

    protected Map<String,String> getMethods(){
        return methods;
    }

    protected String[] getOutPutMethods(){
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
