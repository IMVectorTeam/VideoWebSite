package com.niit.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.entity.User;
import com.niit.service.UserService;
import com.niit.utils.MD5Utils;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping(value="/user/list", method=RequestMethod.POST)
	@ResponseBody
	public boolean addUser(@RequestBody User user) {
		System.out.println(user.getName()+" " +user.getEmail()+" "+user.getSex());
		user.setId(UUID.randomUUID().toString().replaceAll("-",""));
		user.setPassword(MD5Utils.md5(user.getPassword()));
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
	
	@RequestMapping(value="/user/valid", method=RequestMethod.POST)
	@ResponseBody
	public ModelMap getUserByemail(@RequestBody User user) {
		//		System.out.println(user.getName()+" " +user.getEmail()+" "+user.getSex());
		//		user.setId(UUID.randomUUID().toString().replaceAll("-",""));
		ModelMap map=new ModelMap();
		User userLocal=service.getUser(user.getId());
		if(null!= userLocal) {
			if(MD5Utils.md5(user.getPassword()).equals(userLocal.getPassword())) {
				map.addAttribute("data" , userLocal);
				map.addAttribute("flag", true);
			}else {
				map.addAttribute("data" , "密码不正确");
				map.addAttribute("flag", false);
			}
		}else {
			map.addAttribute("data", "用户不存在");
			map.addAttribute("flag",false);
		}
		return map;
	}
}
