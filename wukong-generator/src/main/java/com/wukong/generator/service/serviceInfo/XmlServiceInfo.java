package com.wukong.generator.service.serviceInfo;

import com.wukong.generator.service.daoinfo.DaoBeanInfo;
import org.apache.commons.lang.StringUtils;

public class XmlServiceInfo extends ServiceInfo {

    public XmlServiceInfo(DaoBeanInfo aDaoBeanInfo, String aPackageName
            , String aPath){
        super(aDaoBeanInfo,aPackageName,aPath);
    }


    /**
     * 得到import语句
     * @return
     */
    protected String getImportStr(){
        StringBuffer sb= new StringBuffer();
        for(String e:this.daoBeanInfo.getImports()){
            sb.append(e);
        }
        //生成Service专用的包
        sb.append("import "+daoBeanInfo.getPackageName()+"."+daoBeanInfo.getName()+";");
        sb.append("\n");
        sb.append("import "+daoBeanInfo.getPackageName()+"."+daoBeanInfo.getName()+"Example;");
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

    protected String getCustomizeFunstr(String funName){
        return null;
    }



}
