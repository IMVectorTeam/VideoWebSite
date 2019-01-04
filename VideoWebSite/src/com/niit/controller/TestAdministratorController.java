package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.entity.Administrator;
import com.niit.entity.User;
import com.niit.service.AdministratorService;
import com.niit.service.UserService;

@Controller
public class TestAdministratorController {

	@Autowired
	private AdministratorService administratorService;
	
	@RequestMapping("/getAdministrator")
	public String getAdministrator(String id) {
		System.out.println(administratorService.getAdministrator(id).getName());
		return "homePage";
		
	}
	
	@RequestMapping("/updateAdministrator")
	public void updateAdministrator(Administrator administrator) {
		Administrator administrator2 = new Administrator();
		administrator2.setId("0001");
		administrator2.setName("猪小明");
		administrator2.setPassword("123456");
		administrator2.setSex("女");
		administrator2.setEmail("zhuzhu@163.com");
		administrator2.setImage("/cc/cc");
		administratorService.updateAdministrator(administrator2);
	}
	
	@RequestMapping("/getAdministratorByEmail")
	public String getAdministratorByEmail(String email) {
		System.out.println(administratorService.getAdministratorByEmail(email).getName());
		return "homePage";	
	}
}
