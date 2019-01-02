package com.niit.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.entity.Administrator;
import com.niit.entity.User;

@Repository
public interface AdministratorMapper {
	   
    public Administrator getAdministrator(String id);
    
    public void updateAdministrator(Administrator administrator);
}