package com.mokcoding.ex02.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mokcoding.ex02.config.WebConfig;

import lombok.extern.log4j.Log4j;
import oracle.jdbc.OracleDriver;

@RunWith(SpringJUnit4ClassRunner.class) // Spring JUnit test ����
@ContextConfiguration(classes = {WebConfig.class}) // ���� ���� ����
@Log4j
public class OracleJDBCTest {
	String URL = "jdbc:oracle:thin:@192.168.0.126:1521:xe";
	String USER = "STUDY";
	String PASSWORD = "1234";
	
	@Test // �޼ҵ忡 �׽�Ʈ ����� ����
	public void testConnection() {
		Connection conn = null;
		try {
			DriverManager.registerDriver(new OracleDriver());
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			log.info("Oracle ���� ���� : conn = " + conn);
		} catch (SQLException e) {
			log.info("Oracle ���� ���� : " + e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
