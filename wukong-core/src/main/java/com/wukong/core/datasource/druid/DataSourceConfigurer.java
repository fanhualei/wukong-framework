//package com.wukong.core.datasource.druid;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Multiple DataSource Configurer
// *
// */
//@Configuration
//public class DataSourceConfigurer {
//
//    /**
//     * master DataSource
//     *
//     * @return data source
//     */
//    @Bean("master")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
//    public DataSource master() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * Slave alpha data source.
//     *
//     * @return the data source
//     */
//    @Bean("slave1")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.slave1")
//    public DataSource slave1() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * Slave beta data source.
//     *
//     * @return the data source
//     */
//    @Bean("slave2")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.slave2")
//    public DataSource slave2() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * Slave gamma data source.
//     *
//     * @return the data source
//     */
//    @Bean("slave3")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.slave3")
//    public DataSource slave3() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * Dynamic data source.
//     *
//     * @return the data source
//     */
//    @Bean("dynamicDataSource")
//    public DataSource dynamicDataSource() {
//        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
//        Map<Object, Object> dataSourceMap = new HashMap<>(4);
//        dataSourceMap.put(DataSourceKey.master.name(), master());
//        dataSourceMap.put(DataSourceKey.slave1.name(), slave1());
//        dataSourceMap.put(DataSourceKey.slave2.name(), slave2());
//        dataSourceMap.put(DataSourceKey.slave3.name(), slave3());
//
//
//
//        // Set master datasource as default
//        dynamicRoutingDataSource.setDefaultTargetDataSource(master());
//        // Set master and slave datasource as target datasource
//        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
//
//        // To put datasource keys into DataSourceContextHolder to judge if the datasource is exist
//        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());
//
//        // To put slave datasource keys into DataSourceContextHolder to load balance
//        DynamicDataSourceContextHolder.slaveDataSourceKeys.addAll(dataSourceMap.keySet());
//        DynamicDataSourceContextHolder.slaveDataSourceKeys.remove(DataSourceKey.master.name());
//        return dynamicRoutingDataSource;
//    }
//
//    /**
//     * Sql session factory bean.
//     * Here to config datasource for SqlSessionFactory
//     * <p>
//     * You need to add @{@code @ConfigurationProperties(prefix = "mybatis")}, if you are using *.xml file,
//     * the {@code 'mybatis.type-aliases-package'} and {@code 'mybatis.mapper-locations'} should be set in
//     * {@code 'application.properties'} file, or there will appear invalid bond statement exception
//     *
//     * @return the sql session factory bean
//     */
//    @Bean
//    @ConfigurationProperties(prefix = "mybatis")
//    public SqlSessionFactoryBean sqlSessionFactoryBean() {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        // Here is very important, if don't config this, will can't switch datasource
//        // put all datasource into SqlSessionFactoryBean, then will autoconfig SqlSessionFactory
//        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
//        return sqlSessionFactoryBean;
//    }
//
//    /**
//     * Transaction manager platform transaction manager.
//     *
//     * @return the platform transaction manager
//     */
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dynamicDataSource());
//    }
//}
//
