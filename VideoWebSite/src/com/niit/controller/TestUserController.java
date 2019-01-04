package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
	

	@RequestMapping("/getUserByEmail")
	public String getUserByEmail(String email) {
		System.out.println(testService.getUserByEmail(email).getName());
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
	
	@RequestMapping("/user/list")
	@ResponseBody
	public PageInfo getUserList(int limit,int page){	
		PageHelper.startPage(page, limit);//第一个参数是第几页，第二个参数是每一页的数量
		List<User> uList=testService.getUserList();
		PageInfo pageInfo=new PageInfo(uList);
//		ModelMap m= new ModelMap();
//		m.addAttribute("data", uList);
//		m.addAttribute("pageInfo", pageInfo);
//		return m;
		return pageInfo;
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
	
	@RequestMapping(value="/user/list",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteUser(String id) {
		boolean flag=false;
		try {
			testService.deleteUser(id);
			flag=true;
		}catch (Exception e) {
			flag=false;
		}
		return flag;
	}
	
}
