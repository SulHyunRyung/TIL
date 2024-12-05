package com.mokcoding.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mokcoding.ex01.domain.InfoVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/example2")
@Log4j // Lombok log4j 기능
public class ExampleController2 {

   // 페이즈 호출역활(GET)
   @GetMapping("/send")
   public void sendGET() {
      log.info("sendGET()");// log출력 가능

   }

   // 데이터 수신 역활 (POST)
   @PostMapping("/send") // RequestMapping POST
   public String sendPOST(@RequestParam("name") String name, @RequestParam("age") int age) {
      // String name = request.getParameter("name");
      // int age = Integer.parseInt(request.getParameter("age"));

      log.info("sendPOST()");
      log.info("name =" + name + "age" + age);
      return "redirect:/"; // response.sendReditrect();
   }
   // info.jsp 페이지 호출
   @GetMapping("/info")
   public void infoGET() {
      log.info("infoGET()");
   }
   // info .jsp 페이지에서 전송된 데이터 수신
   @PostMapping("/info")
   public String infoPOST(InfoVO vo) {
      //전송된 데이터는 InfoVO 에 바인딩 (Binding)
      log.info("infoPOST()");
      log.info("vo= "+ vo );
      return "redirect:/"; 
   }
}// end controller
