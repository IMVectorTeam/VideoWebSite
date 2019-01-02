package com.niit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.entity.Message;
import com.niit.entity.User;
import com.niit.mapper.MessageMapper;
import com.niit.mapper.UserMapper;

@Service
public class MessageService {

	@Autowired
	private MessageMapper messageDao;
	
	public List<Message> getMessageByVideoId(String id){		
		return messageDao.getMessageByVideoId(id);	
	}
	
	public void insertMessage(Message message) {		
		 messageDao.insertMessage(message);
	}
	
	public void deleteMessage(String id) {
		messageDao.deleteMessage(id);
	}
	
}
