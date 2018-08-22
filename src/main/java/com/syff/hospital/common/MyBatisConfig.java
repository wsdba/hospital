package com.syff.hospital.common;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * springboot集成mybatis的基本入口
 * 1）创建数据源
 * 2）创建SqlSessionFactory
 * 
    类上边添加两个
        @Configuration注解（该注解类似于spring的配置文件）(用@Configuration注解该类，等价 与XML中配置beans；用@Bean标注方法等价于XML中配置bean)
        @MapperScan注解，指定扫描的mapper接口所在的包
    在该类中，注入了Environment实例，使用该实例可以去读取类路径下application.properties文件中的内容，读取文件内容的三种方式，见第二章 第二个spring-boot程序
    在该类中，使用druid数据源定义了数据源Bean，spring-boot默认使用的是tomcat-jdbc数据源，这是springboot官方推荐的数据源（性能和并发性都很好）
    根据数据源生成SqlSessionFactory
        值得注意的是，数据源是必须指定的，否则springboot启动不了
        typeAliasesPackage和mapperLocations不是必须的，如果整个项目不需要用到*Mapper.xml来写SQL的话（即只用注解就可以搞定），那么不需要配
    @Primary注解：指定在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@Autowire注解报错（一般用于多数据源的情况下）
	@autowired:Spring 2.5 引入了 @Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 通过 @Autowired的使用来消除 set ，get方法。
这样之后，在项目中再使用springboot就和在ssm中（配置完成后）使用一样了。
 */
@Configuration    //该注解类似于spring配置文件
@MapperScan(basePackages="com.syff.mapper")
public class MyBatisConfig {
    
    @Autowired
    private Environment env;
    
    /**
     * 创建数据源
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错 
     */
    @Bean
    @Primary
    public DataSource getDataSource() throws Exception{
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("jdbc.driverClassName"));
        props.put("url", env.getProperty("jdbc.url"));
        props.put("username", env.getProperty("jdbc.username"));
        props.put("password", env.getProperty("jdbc.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);//指定数据源(这个必须有，否则报错)
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置
        
        return fb.getObject();
    }

}