package com.niit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.entity.Administrator;
import com.niit.entity.User;
import com.niit.mapper.AdministratorMapper;
import com.niit.mapper.UserMapper;

@Service
public class AdministratorService {

	@Autowired
	private AdministratorMapper administratorDao;

	public Administrator getAdministrator(String id) {		
		return administratorDao.getAdministrator(id);
	}
	
	public void updateAdministrator(Administrator administrator) {		
		 administratorDao.updateAdministrator(administrator);
	}
	
}


