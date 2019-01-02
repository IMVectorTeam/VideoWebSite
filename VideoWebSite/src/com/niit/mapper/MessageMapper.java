package com.niit.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.entity.Message;

@Repository
public interface MessageMapper {
	
	public List<Message> getMessageByVideoId(String id);
	
	public void insertMessage(Message message);

	public void deleteMessage(String id);
}
