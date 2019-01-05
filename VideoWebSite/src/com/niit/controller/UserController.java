package com.niit.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.entity.User;
import com.niit.service.UserService;
import com.niit.utils.MD5Utils;

@Controller
public class UserController {

	@Autowired
	UserService service;

	@RequestMapping(value = "/user/valid", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap getUserByemail(@RequestBody User user) {
		System.out.println(user.getName() + " " + user.getEmail() + " " + user.getSex());
		ModelMap map = new ModelMap();
		User userLocal = service.getUserByEmail(user.getEmail());
		if (null != userLocal) {
			if (MD5Utils.md5(user.getPassword()).equals(userLocal.getPassword())) {
				map.addAttribute("data", userLocal);
				map.addAttribute("flag", true);
			} else {
				map.addAttribute("data", "密码不正确");
				map.addAttribute("flag", false);
			}
		} else {
			map.addAttribute("data", "用户不存在");
			map.addAttribute("flag", false);
		}
//		String token = UUID.randomUUID().toString().substring(0, 16);
		String token ="admin";
		map.addAttribute("token", token);
		return map;
	}
	@RequestMapping("/getUserByEmail")
	public String getUserByEmail(String email) {
		System.out.println(service.getUserByEmail(email).getName());
		return "homePage";
	}
	@RequestMapping("/getUser")
	@ResponseBody
	public String getUser(String id) {
		System.out.println(service.getUser(id).getName());
		return "homePage";

	}

	@RequestMapping("/insertUser")
	public void insertUser(User user) throws Exception {
		User user2 = new User();
		user2.setId("20154072");
		user2.setName("小陈");
		user2.setPassword("123456");
		user2.setSex("男");
		user2.setEmail("ttt@163.com");
		user2.setImage("");
		user2.setIntroduce("aaa");
		service.insertUser(user2);
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	@ResponseBody
	public boolean addUser(@RequestBody User user) {
		System.out.println(user.getName() + " " + user.getEmail() + " " + user.getSex());
		user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setPassword(MD5Utils.md5(user.getPassword()));
		boolean flag = false;
		try {
			service.insertUser(user);
			flag = true;
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}
	@RequestMapping("/user/")
	@ResponseBody
	public PageInfo getUserList(int limit, int page) {
		PageHelper.startPage(page, limit);// 第一个参数是第几页，第二个参数是每一页的数量
		List<User> uList = service.getUserList();
		PageInfo pageInfo = new PageInfo(uList);
//		ModelMap m= new ModelMap();
//		m.addAttribute("data", uList);
//		m.addAttribute("pageInfo", pageInfo);
//		return m;
		return pageInfo;
	}
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteUser(String id) {
		boolean flag = false;
		try {
			service.deleteUser(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public void updateUser(User user) {
		User user2 = new User();
		user2.setId("20154076");
		user2.setName("老张32");
		user2.setPassword("123456");
		user2.setSex("男");
		user2.setEmail("1234@qq.com");
		user2.setImage("");
		user2.setIntroduce("bbb");
		service.updateUser(user2);
	}

	@RequestMapping("/getUserByVideoId")
	@ResponseBody
	public User getUserByVideoId(String id) {
		System.out.println(service.getUserByVideoId(id).getName());
		return service.getUserByVideoId(id);
	}

}
