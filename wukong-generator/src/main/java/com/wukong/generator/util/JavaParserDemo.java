package com.wukong.generator.util;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static com.github.javaparser.JavaParser.parse;

@Slf4j
public class JavaParserDemo {


    public static void main(String[] args){
        JavaParserDemo javaParserDemo=new JavaParserDemo();
        try{
            javaParserDemo.run();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void run()throws Exception{
        String path="/home/fan/IdeaProjects/wukong-framework/log/com/wukong/examples/dao/OrderMapper.java";
        File file=new File(path);

        System.out.println( JavaParserDemo.class.getResource(""));
        CompilationUnit compilationUnit=  JavaParser.parse(file);

        initImports(compilationUnit);
        inti(compilationUnit);

    }

    /**
     * 得到import
     * @throws Exception
     */
    private void initImports(CompilationUnit cu) throws Exception{
        NodeList<ImportDeclaration> list=  cu.getImports();
        log.debug("----------------import-----------------------");
        for(ImportDeclaration e:list){
            String str =e.toString();
            System.out.println(str);
        }
    }


    private void inti(CompilationUnit cu) throws Exception{


        NodeList<TypeDeclaration<?>> list=  cu.getTypes();
        for(TypeDeclaration e:list){
            getMembers(e);
            String str=e.getNameAsString();
            System.out.println("==============得到ClassName:"+str+"====================");
        }
    }

    private void getMembers(TypeDeclaration typeDec){
        System.out.println("---------------------函数列表----------------------------");
        NodeList<BodyDeclaration> list = typeDec.getMembers() ;
        for(BodyDeclaration e:list){
            String strMember=e.toString();
            String strParamenters=getParameters(e);

            int startIndex=strMember.indexOf("(");
            int endIndex=strMember.lastIndexOf(")");

            StringBuffer newStrMember= new StringBuffer();
            newStrMember.append(strMember.substring(0,startIndex+1) + strParamenters + strMember.substring(endIndex,strMember.length()) );
            System.out.println(strMember);//原始的函数名称
            System.out.println(newStrMember.toString());//原始的函数名称
            System.out.println(newStrMember.toString());
        }
    }


    /**
     * 得到没有注解的参数列表
     * @param bodyDec
     * @return
     */
    private String  getParameters(BodyDeclaration bodyDec){
        String ren="";
        NodeList<Parameter> list = ((MethodDeclaration) bodyDec).getParameters();
        int i=list.size();
        for(Parameter e:list){
            ren= ren + e.getType().toString()+" " ; //得到注释
            i=i-1;
            if(i>0){
                ren=ren+", ";
            }
        }
        return ren;
    }
}
