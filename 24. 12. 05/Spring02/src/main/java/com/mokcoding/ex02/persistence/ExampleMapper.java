package com.mokcoding.ex02.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper // Mapper ������̼�
public interface ExampleMapper {

	@Select("SELECT SYSDATE FROM DUAL")
	// ���� ����� �޼ҵ忡 �����ϴ� ������̼�
	public String getDate();
}
