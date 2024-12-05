package com.mokcoding.ex01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// POJO(Plain Old Java Object)
@Controller // @Component로 가능
// @Controller로 선언된 객체는 Spring Container에서 관리
@RequestMapping("/example1")
public class ExampleController1 {
	private static final Logger logger = 
			LoggerFactory.getLogger(ExampleController1.class);
	
	// @RequestMapping : 특정 메소드를 요청에 대한 처리가 가능하도록 만드는
	// 어네토이션, URL, HTTP Method 등의 속성을 갖고 있음
	// @RequestMapping(value="/page1", method=RequestMethod.GET)
	@GetMapping("/page1")
	public String page1() {
		logger.info("page1()");
		return "example1/page1"; // jsp 경로 및 파일 이름(view name)
		// WEB-INF/views/example1/page1.jsp로 찾아감
		//servlet-context.xml에 설정되어 있음
	} // end page1() 
	
	@GetMapping("/page2") // short cut
	public void page2() {
		logger.info("page2*()");
		// return 타입이 void인 경우는 URL 매핑을 통해서 view를 찾음
	} // end page2()
}
