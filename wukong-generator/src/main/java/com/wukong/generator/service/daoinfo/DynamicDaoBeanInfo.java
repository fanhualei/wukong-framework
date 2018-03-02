package com.wukong.generator.service.daoinfo;

import java.io.File;

/**
 * 这个类是用来生成 动态sql语句的DaoInfo对象
 */
public class DynamicDaoBeanInfo extends DaoBeanInfo{

    public DynamicDaoBeanInfo(File aDaoFile) throws Exception{
        super(aDaoFile);
    }


    /**
     * 动态sql 只生成一部分函数， 如果是xml或注解的，要生成全部的函数
     */
    @Override
    protected void initMethods(){
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

        //自己想要追加的函数
        for(String s:outPutMethods){
            if(s.equals("count"))
                methods.put("count","long count()");
            else if(s.equals("selectAll"))
                methods.put("selectAll","List<"+getModelName()+"> selectAll()");
            else
                methods.put(s,"");
        }
    }





}
