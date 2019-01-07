package com.niit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.entity.Administrator;
import com.niit.entity.User;
import com.niit.service.AdministratorService;
import com.niit.service.UserService;
import com.niit.utils.MD5Utils;

@Controller
public class UserController {

	@Autowired
	UserService service;

	@RequestMapping(value = "/user/infomation", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap getUserById(String token) {
		User userLocal = service.getUser(token);
//		Administrator adminLocal= service.getAdmin(token);
		if (null != userLocal) {
			ModelMap map = new ModelMap();
			map.addAttribute("data", userLocal);
			map.addAttribute("token", userLocal.getId());
			if (userLocal.getRoles().equals("admin")) {
				List<String> list = new ArrayList();
				list.add("admin");
				list.add("user");
				map.addAttribute("roles", list);
				return map;
			} else {
				List<String> list = new ArrayList();
				list.add("user");
				map.addAttribute("roles", list);
				return map;
			}

		} else {
			ModelMap map = new ModelMap();
			map.addAttribute("token", token);
			List<String> list = new ArrayList();
			list.add("user");
			map.addAttribute("roles", list);
			return map;
		}
	}

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
		map.addAttribute("token", userLocal.getId());
		List<String> list = new ArrayList();
		list.add("user");
		map.addAttribute("roles", list);
		return map;
	}

	// 根据用户名模糊查询用户
	@RequestMapping("/getUserByLikeName")
	@ResponseBody
	public List<User> getUserByLikeName(String name) {
		System.out.println(service.getUserByLikeName(name).get(0).getName());
		return service.getUserByLikeName(name);
	}

	// 添加用户
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

	// 获取用户列表
	@RequestMapping(value = "/user/list/", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo getUserList(int limit, int page) {
		PageHelper.startPage(page, limit);// 第一个参数是第几页，第二个参数是每一页的数量
		List<User> uList = service.getUserList();
		PageInfo pageInfo = new PageInfo(uList);
		return pageInfo;
	}

	// 删除用户
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

	// 更新用户
//	http://127.0.0.1:9080/api/VideoWebSite/user/20154070/
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PATCH)
	@ResponseBody
	public ModelMap updateUser(@RequestBody User user, @PathVariable String id) {
		Boolean flag = false;
		try {
			user.setPassword(MD5Utils.md5(user.getPassword()));
			user.setRoles(service.getUser(id).getRoles());
			service.updateUser(user);
			flag = true;
		} catch (Exception e) {
			flag = false;
			System.out.println(e);
		}
		ModelMap map = new ModelMap();
		map.addAttribute("flag", flag);
		return map;
	}
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User updateUser(@PathVariable String id) {
		User user=service.getUser(id);
		return user;
	}
	// 根据videoId获取用户信息
	@RequestMapping(value = "/user/videoId/{videoId}", method = RequestMethod.GET)
	@ResponseBody
	public User getUserByVideoId(@PathVariable String videoId) {
		return service.getUserByVideoId(videoId);
	}

}
