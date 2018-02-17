//package com.wukong.generator.service;
//
//import com.github.javaparser.JavaParser;
//import com.github.javaparser.ast.CompilationUnit;
//import com.github.javaparser.ast.ImportDeclaration;
//import com.github.javaparser.ast.PackageDeclaration;
//import com.github.javaparser.ast.body.BodyDeclaration;
//import com.github.javaparser.ast.body.MethodDeclaration;
//import com.github.javaparser.ast.body.Parameter;
//import com.github.javaparser.ast.body.TypeDeclaration;
//import lombok.extern.slf4j.Slf4j;
//
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
///**
// * Created by fanhl on 2016/6/30.
// */
//@Slf4j
//public  class DaoBeanInfo {
//    private String className="";
//    private List<String> imports = new ArrayList<String>();
//    private List<String> methods = new ArrayList<String>();
//    private File daoFile ;
//    private CompilationUnit cu;
//
//
//    public DaoBeanInfo(File aDaoFile) throws Exception{
//        daoFile=aDaoFile;
//        cu = JavaParser.parse(daoFile);
//        inti();
//
//    }
//
//    protected String getName(){
//        return className;
//    }
//
//    protected List<String> getImports(){
//        return imports;
//    }
//
//    protected List<String> getMethods(){
//        return methods;
//    }
//
//    public String getPackageName(){
//        Optional<PackageDeclaration> optional= cu.getPackageDeclaration();
//
//        String pachageName=optional.get().getName();
//        return cu.getPackage().getName().toStringWithoutComments();
//    }
//
//    public String getModelName(){
//        String str= getName();
//        str=str.substring(0,str.length()-"Mapper".length());
//        return str;
//    }
//
//
//
//    /**
//     * 得到import
//     * @throws Exception
//     */
//    private void initImports() throws Exception{
//        List<ImportDeclaration> list=  cu.getImports();
//        log.debug("----------------import-----------------------");
//        for(ImportDeclaration e:list){
//            String str =e.toStringWithoutComments();
//            if(str.indexOf("org.apache.ibatis.annotations.Param")<=0){
//                log.debug(str);
//
//
//                imports.add(str);
//            }
//
//        }
//    }
//
//
//    private void inti() throws Exception{
//        initImports();
//        List<TypeDeclaration> list=  cu.getTypes();
//        for(TypeDeclaration e:list){
//            getMembers(e);
//            String str=e.getName();
//            className=str;
//            logger.debug("==============得到ClassName:"+str+"====================");
//        }
//    }
//
//    private void getMembers(TypeDeclaration typeDec){
//        logger.debug("---------------------函数列表----------------------------");
//        List<BodyDeclaration> list = typeDec.getMembers() ;
//        for(BodyDeclaration e:list){
//            String strMember=e.toStringWithoutComments();
//            String strParamenters=getParameters(e);
//
//            int startIndex=strMember.indexOf("(");
//            int endIndex=strMember.lastIndexOf(")");
//
//            StringBuffer newStrMember= new StringBuffer();
//            newStrMember.append(strMember.substring(0,startIndex+1) + strParamenters + strMember.substring(endIndex,strMember.length()) );
//            logger.debug(strMember);//原始的函数名称
//            logger.debug(newStrMember.toString());//原始的函数名称
//            methods.add(newStrMember.toString());
//        }
//    }
//
//    /**
//     * 得到没有注解的参数列表
//     * @param bodyDec
//     * @return
//     */
//    private String  getParameters(BodyDeclaration bodyDec){
//        String ren="";
//        List<Parameter> list = ((MethodDeclaration) bodyDec).getParameters();
//        int i=list.size();
//        for(Parameter e:list){
//            ren= ren + e.getType().toStringWithoutComments()+" " + e.getId().toStringWithoutComments();
//            i=i-1;
//            if(i>0){
//                ren=ren+", ";
//            }
//        }
//        return ren;
//    }
//
//
//
//}
