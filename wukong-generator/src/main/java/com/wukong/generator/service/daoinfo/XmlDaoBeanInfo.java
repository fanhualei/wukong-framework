package com.wukong.generator.service.daoinfo;

import java.io.File;

public class XmlDaoBeanInfo extends DaoBeanInfo{

    public XmlDaoBeanInfo(File aDaoFile) throws Exception{
        super(aDaoFile);
    }


    /**
     * 动态sql 只生成一部分函数， 如果是xml或注解的，要生成全部的函数
     */
    @Override
    protected void initMethods(){

        //不指定 outPutMethods，那么就生成全部的函数
        outPutMethods=new String[]{};
    }

}
