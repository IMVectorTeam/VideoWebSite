package com.niit.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
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

	// 根据videoId查询视频
	@RequestMapping(value = "/videoIdComment/list", method = RequestMethod.GET)
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
		return messageService.getMessageByLikeContent(content);
	}
	
	@RequestMapping(value="/videoCommentlist",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo getAllMessage( int page, int limit) {
		PageHelper.startPage(page, limit);
		List<Message> list=messageService.getAllMessage();
		PageInfo pageInfo = new PageInfo(list);
		return pageInfo;
	}

	@RequestMapping(value = "/videoComment/", method = RequestMethod.POST)
	@ResponseBody
	public Boolean insertMessage(@RequestBody Message message) {
		message.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		message.setDate(new Date());
		boolean flag = false;
		try {
			messageService.insertMessage(message);
			flag = true;
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	// http://localhost:8081/api/VideoWebSite/videoComment/?id=2002
	@RequestMapping(value = "/videoComment", method = RequestMethod.DELETE)
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
	// 根据用户id查询所有该用户的评论
	@RequestMapping(value="/comment/userId/list/",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo getMessageByUserId(String userId, int page, int limit) {
		PageHelper.startPage(page, limit);// 第一个参数是第几页，第二个参数是每一页的数量
		List<Message> list = messageService.getMessageByUserId(userId);
		PageInfo info = new PageInfo(list);
		return info;
	}
}
