package com.mokcoding.ex02.persistance;

import java.sql.Connection;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mokcoding.ex02.config.RootConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) // Spring JUnit test ����
@ContextConfiguration(classes = {RootConfig.class}) // ���� ���� ����
@Log4j
public class SqlSessionFactoryTest {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	// try-resource-catch(){} : �ڵ� ���ҽ� ����(close) try-catach
	@Test
	public void testSessionFactory() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();
				Connection conn = sqlSession.getConnection()) {
			log.info("SqlSession ��ü : " + sqlSession);
			log.info("Connection ��ü : " + conn);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
