package com.mokcoding.ex02.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = {"com.mokcoding.ex02.persistence"})
// 패키지 경로로 Mapper 스캐닝
public class RootConfig {

	@Bean // Spring bean으로 설정, xml의 <bean> 태그와 동일
	public DataSource dataSource() { // DataSource 객체 리턴 메소드
		// HikariConfig : DBCP 라이브러리
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("oracle.jdbc.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@192.168.0.126:1521:xe");
		config.setUsername("STUDY");
		config.setPassword("1234");
		
		config.setMaximumPoolSize(10); // 최대 Pool Size 10
		config.setConnectionTimeout(30000); // Connection TimeOut 30s
		HikariDataSource ds = new HikariDataSource(config);
		
		return ds;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
				
	}
}
