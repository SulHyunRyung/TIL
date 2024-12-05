package com.mokcoding.ex01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// POJO(Plain Old Java Object)
@Controller // @Component�� ����
// @Controller�� ����� ��ü�� Spring Container���� ����
@RequestMapping("/example1")
public class ExampleController1 {
	private static final Logger logger = 
			LoggerFactory.getLogger(ExampleController1.class);
	
	// @RequestMapping : Ư�� �޼ҵ带 ��û�� ���� ó���� �����ϵ��� �����
	// ������̼�, URL, HTTP Method ���� �Ӽ��� ���� ����
	// @RequestMapping(value="/page1", method=RequestMethod.GET)
	@GetMapping("/page1")
	public String page1() {
		logger.info("page1()");
		return "example1/page1"; // jsp ��� �� ���� �̸�(view name)
		// WEB-INF/views/example1/page1.jsp�� ã�ư�
		//servlet-context.xml�� �����Ǿ� ����
	} // end page1() 
	
	@GetMapping("/page2") // short cut
	public void page2() {
		logger.info("page2*()");
		// return Ÿ���� void�� ���� URL ������ ���ؼ� view�� ã��
	} // end page2()
}
