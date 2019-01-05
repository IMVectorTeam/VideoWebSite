package com.niit.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.entity.Message;
import com.niit.entity.User;
import com.niit.service.MessageService;

@Controller
public class TestMessageController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/getMessageByVideoId")
	@ResponseBody
	public List<Message> getMessageByVideoId(String id){			
		System.out.println(messageService.getMessageByVideoId(id).get(0).getContent());
		return 	messageService.getMessageByVideoId(id);
	}
	
	//根据关键字模糊查询所有评论内容
	@RequestMapping("/getMessageByLikeContent")
	@ResponseBody
	public List<Message> getMessageByLikeContent(String content){
		System.out.println("aaa");
		return messageService.getMessageByLikeContent(content);
	}
	
	@RequestMapping("/getAllMessage")
	@ResponseBody
	public List<Message> getAllMessage(){
		return messageService.getAllMessage();
	}
	
	@RequestMapping("/insertMessage")
	@ResponseBody
	public void insertMessage(Message message){
		Message message2 = new Message();
		message2.setId("2003");
		message2.setVideoId("1001");
		message2.setUserId("20154071");
		message2.setContent("真好看！");
		message2.setDate(new Date());
		messageService.insertMessage(message2);
	}
	
	@RequestMapping("/deleteMessage")
	@ResponseBody
	public void deleteMessage(String id) {
		messageService.deleteMessage(id);
	}
}
