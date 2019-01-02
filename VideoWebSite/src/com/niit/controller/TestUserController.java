package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.entity.User;
import com.niit.service.UserService;

@Controller
public class TestUserController {
	
	@Autowired
	private UserService testService;
	
	@RequestMapping("/getUser")
	public String getUser(String id) {
		System.out.println(testService.getUser(id).getName());
		return "homePage";
		
	}
	
	@RequestMapping("/insertUser")
	public void insertUser(User user) throws Exception{
		User user2 = new User();
		user2.setId("20154072");
		user2.setName("小陈");
		user2.setPassword("123456");
		user2.setSex("男");
		user2.setEmail("ttt@163.com");
		testService.insertUser(user2);		
	}
	
	@RequestMapping("/getUserList")
	public List<User> getUserList(){			
		System.out.println(testService.getUserList());
		return testService.getUserList();		
	}
	
	@RequestMapping("/updateUser")
	public void updateUser(User user){	
		User user2 = new User();
		user2.setId("20154071");
		user2.setName("老张3");
		user2.setPassword("123456");
		user2.setSex("男");
		user2.setEmail("1234@qq.com");
		testService.updateUser(user2);	
	}
	
	@RequestMapping("/deleteUser")
	public void deleteUser(String id) {
		testService.deleteUser(id);
	}

}
