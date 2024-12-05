package com.mokcoding.ex01.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Lombok 태그들
@NoArgsConstructor//기본 생성자
@Getter//getter매소드
@Setter//setter매소드
@ToString//toString매소드
public class InfoVO {
   private String name;
   private String email;
   
   @DateTimeFormat(pattern = "yyyy-mm-dd")
   private Date birthDate;
}
