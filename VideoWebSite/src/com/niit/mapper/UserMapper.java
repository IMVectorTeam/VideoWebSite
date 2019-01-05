package com.niit.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.entity.User;
@Repository
public interface UserMapper {
	
    public List<User> getUserList();

    public void insertUser(User user) throws Exception;

    public void updateUser(User user);

    public void deleteUser(String userId);

    public User getUser(String id);
    
    public User getUserByEmail(String email);
    
    //根据videoId查询该上传用户的信息
    public User getUserByVideoId(String id);
}