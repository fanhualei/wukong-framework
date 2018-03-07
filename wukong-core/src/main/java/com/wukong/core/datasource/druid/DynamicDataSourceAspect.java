package com.wukong.core.datasource.druid;

import com.wukong.core.datasource.DatasourceAnno;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Multiple DataSource Aspect
 *
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    //@TODO  配置需要拦截的只读操作，这里拦截的是dao
    private final String[] UPDATE_PREFIX = {"insert","update","delete","drop","remove"};

    /**
     * Dao aspect. @TODO 这个拦截器配置最好配置到文件中
     */
     //@Pointcut("execution( * com.wukong.examples.dao.*.*(..))")
    @Pointcut("execution( * com.wukong.examples.service.*.*(..))")
    public void daoAspect() {
    }

    /**
     * Switch DataSource
     *
     * @param point the point
     */
    @Before("daoAspect()")
    public void switchDataSource(JoinPoint point) {
        DatasourceAnno datasourceAnno=
                ((MethodSignature)point.getSignature()).getMethod().getAnnotation(DatasourceAnno.class);

        Boolean isQueryMethod = isQueryMethod(point.getSignature().getName(),datasourceAnno);
        if (isQueryMethod) {
            DynamicDataSourceContextHolder.useSlaveDataSource();
            logger.debug("Switch DataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
        }
    }

    /**
     * Restore DataSource
     *
     * @param point the point
     */
    @After("daoAspect())")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSourceContextHolder.clearDataSourceKey();
        logger.debug("Restore DataSource to [{}] in Method [{}]",
                DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
    }


    /**
     * Judge if method start with update prefix
     *
     * @param methodName
     * @return
     */
    private Boolean isQueryMethod(String methodName,DatasourceAnno datasourceAnno) {
        if(datasourceAnno!=null){
            if(datasourceAnno.name().equals("master"))
                return false;
            else if (datasourceAnno.name().equals("slave"))
                return true;

        }


        for (String prefix : UPDATE_PREFIX) {
            if (methodName.startsWith(prefix)) {
                return false;
            }
        }
        return true;
    }

}
