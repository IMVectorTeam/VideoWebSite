package com.niit.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.entity.User;
import com.niit.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping(value="/user/list", method=RequestMethod.POST)
	@ResponseBody
	public boolean addUser(@RequestBody User user) {
		System.out.println(user.getName()+" " +user.getEmail()+" "+user.getSex());
		user.setId(UUID.randomUUID().toString().replaceAll("-",""));
		boolean flag=false;
		try {
			service.insertUser(user);
			flag=true;
		} catch (Exception e) {
			System.out.println(e);
			flag=false;
		}
		
		return flag;
	}

}
