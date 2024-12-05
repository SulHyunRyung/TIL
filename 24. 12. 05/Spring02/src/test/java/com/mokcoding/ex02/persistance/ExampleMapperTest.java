package com.mokcoding.ex02.persistance;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mokcoding.ex02.config.RootConfig;
import com.mokcoding.ex02.persistence.ExampleMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) // Spring JUnit test 연결
@ContextConfiguration(classes = {RootConfig.class}) // 설정 파일 연결
@Log4j
public class ExampleMapperTest {
	
	@Autowired
	private ExampleMapper exampleMapper;
	
	@Test
	public void testSelectDate() {
		log.info(exampleMapper.getDate());
	}
}
