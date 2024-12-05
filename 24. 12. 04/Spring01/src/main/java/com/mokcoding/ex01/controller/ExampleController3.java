package com.mokcoding.ex01.controller;


import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokcoding.ex01.domain.InfoVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/example3")
@Log4j // Lombok log4j ���
public class ExampleController3 {

   // info.jsp ������ ȣ��
   @GetMapping("/info")
   public void infoGET(Model model) { //Model ��ü ���
	   // Model : Map �������� �����͸� �����ϴ� ��ü
      log.info("infoGET()");
      InfoVO vo = new InfoVO();
      vo.setName("������");
      vo.setEmail("test@test.com");
      vo.setBirthDate(new Date());
      
      model.addAttribute("vo", vo); //model�� name="vo", object="vo" ����
   }
   
   // page3.jsp ������ ȣ�� �� ������ ����
   @GetMapping("/page3")
   public void page3(@ModelAttribute("name") String name,
		   @ModelAttribute("age") int age) {
	   // @ModelAttribute() : request parameter�� ���޹��� �����͸�
	   // Model ��ü�� ��Ƽ� View�� �������ִ� ����
	   log.info("page3()");
   }
   
   // result() �޼ҵ�� �̵�(redirect)
   @GetMapping("/redirect")
   public String redirect(RedirectAttributes reAttr) {
	   log.info("redirect()");
	   // RedirectAttributes : ��ȸ�� ������ ���� ��ü. attribute ���
	   reAttr.addFlashAttribute("result", "success");
	   // FlashAttribute : ���ǿ� ����Ǿ� ��� �� �ڵ� ����.
	   // url �Ķ���Ϳ� ǥ�õ��� ����.
	   return "redirect:/example3/result";
   }

   
   // result.jsp ������ ȣ��
   @GetMapping("/result")
   public void result() {
	   log.info("result()");
   }
}// end controller
