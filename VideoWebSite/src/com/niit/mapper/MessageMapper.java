package com.niit.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.entity.Message;

@Repository
public interface MessageMapper {
	
	public List<Message> getMessageByVideoId(String id);
	
	//根据关键字模糊查询所有评论内容
	public List<Message> getMessageByLikeContent(String content);
	
	public List<Message> getAllMessage();
	
	public void insertMessage(Message message);

	public void deleteMessage(String id);
}
