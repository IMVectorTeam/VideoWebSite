package com.niit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.entity.Administrator;
import com.niit.entity.User;
import com.niit.service.AdministratorService;
import com.niit.service.UserService;
import com.niit.utils.MD5Utils;

@Controller
public class TestAdministratorController {

	@Autowired
	private UserService adminService;
	
	@RequestMapping(value="/admin/valid", method=RequestMethod.POST)
	@ResponseBody
	public ModelMap getUserByemail(@RequestBody User admin) {
		System.out.println(admin.getName()+" +++++++++"+admin.getEmail()+"++++++ ");
		ModelMap map=new ModelMap();
		User administratorLocal=adminService.getAdministratorByEmail(admin.getEmail());
		if(null!= administratorLocal) {
			if(MD5Utils.md5(admin.getPassword()).equals(administratorLocal.getPassword())) {
				map.addAttribute("data" , administratorLocal);
				map.addAttribute("flag", true);
			}else {
				map.addAttribute("data" , "密码不正确");
				map.addAttribute("flag", false);
			}
		}else {
			map.addAttribute("data", "该管理账户不存在");
			map.addAttribute("flag",false);
		}
		map.addAttribute("token", administratorLocal.getId());
		List<String> list=new ArrayList();
		list.add("admin,user");
		map.addAttribute("roles", list);
		return map;
	}

//	@RequestMapping("/getAdministrator")
//	@ResponseBody
//	public String getAdministrator(String id) {
//		System.out.println(administratorService.getAdministrator(id).getName());
//		return "homePage";
//		
//	}
//	
//	@RequestMapping("/updateAdministrator")
//	@ResponseBody
//	public void updateAdministrator(Administrator administrator) {
//		Administrator administrator2 = new Administrator();
//		administrator2.setId("0001");
//		administrator2.setName("猪小明");
//		administrator2.setPassword("123456");
//		administrator2.setSex("女");
//		administrator2.setEmail("zhuzhu@163.com");
//		administrator2.setImage("/cc/cc");
//		administratorService.updateAdministrator(administrator2);
//	}
//	
//	@RequestMapping("/getAdministratorByEmail")
//	@ResponseBody
//	public String getAdministratorByEmail(String email) {
//		System.out.println(administratorService.getAdministratorByEmail(email).getName());
//		return "homePage";	
//	}
}
