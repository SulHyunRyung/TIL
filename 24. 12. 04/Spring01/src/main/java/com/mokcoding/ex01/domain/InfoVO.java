package com.mokcoding.ex01.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Lombok �±׵�
@NoArgsConstructor//�⺻ ������
@Getter//getter�żҵ�
@Setter//setter�żҵ�
@ToString//toString�żҵ�
public class InfoVO {
   private String name;
   private String email;
   
   @DateTimeFormat(pattern = "yyyy-mm-dd")
   private Date birthDate;
}
