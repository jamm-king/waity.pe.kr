package com.waity.api.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import com.waity.api.mapper.tagMapper;
import com.waity.api.mapper.channelMapper;
import com.waity.api.mapper.kingtagMapper;
import com.waity.api.mapper.videoMapper;

@Configuration
@MapperScan(value="com.waity.api.mapper")
public class DBConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() throws Exception {
		return new HikariConfig();
	}

	@Bean
	public DataSource dataSource() throws Exception {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		System.out.println(dataSource.toString());
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	    sqlSessionFactoryBean.setDataSource(dataSource);
	    sqlSessionFactoryBean.setMapperLocations(
	        resolver.getResources("classpath:/mapper/*.xml")
	    );
	    sqlSessionFactoryBean.setConfiguration(mybatisConfig());
	    return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
	    return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() throws Exception {
	    return new org.apache.ibatis.session.Configuration();		
	}

}