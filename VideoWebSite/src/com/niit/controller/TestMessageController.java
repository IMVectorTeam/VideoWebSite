package com.niit.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.entity.Message;
import com.niit.entity.User;
import com.niit.service.MessageService;

@Controller
public class TestMessageController {

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/videoComment/list", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo getMessageByVideoId(String videoId, int page, int limit) {
		PageHelper.startPage(page, limit);
		List<Message> list = messageService.getMessageByVideoId(videoId);
		PageInfo pageInfo = new PageInfo(list);
		return pageInfo;
	}

	// 根据关键字模糊查询所有评论内容
	@RequestMapping("/getMessageByLikeContent")
	@ResponseBody
	public List<Message> getMessageByLikeContent(String content) {
		System.out.println("aaa");
		return messageService.getMessageByLikeContent(content);
	}

	@RequestMapping("/getAllMessage")
	@ResponseBody
	public List<Message> getAllMessage() {
		return messageService.getAllMessage();
	}

	@RequestMapping("/insertMessage")
	@ResponseBody
	public void insertMessage(Message message) {
		Message message2 = new Message();
		message2.setId("2003");
		message2.setVideoId("1001");
		message2.setUserId("20154071");
		message2.setContent("真好看！");
		message2.setDate(new Date());
		messageService.insertMessage(message2);
	}

	// http://localhost:8081/api/VideoWebSite/videoComment/?id=2002
	@RequestMapping(value = "/videoComment/", method = RequestMethod.DELETE)
	@ResponseBody
	public ModelMap deleteMessage(String id) {
		Boolean flag = false;
		try {
			messageService.deleteMessage(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
			System.out.println(e);
		}
		ModelMap map = new ModelMap();
		map.addAttribute("flag", flag);
		return map;
	}

	@RequestMapping(value="/comment/userId/list/",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo getMessageByUserId(String userId, int page, int limit) {
		PageHelper.offsetPage(page, limit);
		List<Message> list = messageService.getMessageByUserId(userId);
		PageInfo info = new PageInfo(list);
		return info;
	}
}
