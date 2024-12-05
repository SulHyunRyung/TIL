package com.mokcoding.ex02.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper // Mapper 어노테이션
public interface ExampleMapper {

	@Select("SELECT SYSDATE FROM DUAL")
	// 쿼리 결과를 메소드에 적용하는 어노테이션
	public String getDate();
}
