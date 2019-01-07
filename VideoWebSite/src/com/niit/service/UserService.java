package com.niit.service;

import java.io.DataOutput;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.mapper.AdministratorMapper;
import com.niit.mapper.UserMapper;
import com.niit.entity.Administrator;
import com.niit.entity.User;


@Service
public class UserService {
	@Autowired
	private UserMapper userDao;

	public User getUser(String id) {		
		return userDao.getUser(id);	
	}
	
	public void insertUser(User user) throws Exception {		
		 userDao.insertUser(user);
	}
	
	public List<User> getUserList(){		
		return userDao.getUserList();		
	}
	
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
	public void deleteUser(String userId) {
		userDao.deleteUser(userId);
	}
	
	public User getUserByEmail(String email) {		
		return userDao.getUserByEmail(email);
	}
	
	public User getUserByVideoId(String id) {
		return userDao.getUserByVideoId(id);
	}
	
	//根据用户名模糊查询用户
	public List<User> getUserByLikeName(String name){
		return userDao.getUserByLikeName(name);
	}
	public void updateAdministrator(User administrator) {		
		userDao.insertAdmin(administrator);
	}
	
	public User getAdministratorByEmail(String email) {		
		return userDao.getAdminByEmail(email);
	}
}
