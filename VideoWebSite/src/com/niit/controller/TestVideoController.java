package com.niit.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.entity.User;
import com.niit.entity.Video;
import com.niit.entity.VideoCategory;
import com.niit.mapper.VideoCategoryMapper;
import com.niit.mapper.VideoMapper;
import com.niit.service.UserService;
import com.niit.service.VideoCategoryService;
import com.niit.service.VideoService;
import com.niit.utils.UploadUtils;

@Controller
public class TestVideoController {

	@Autowired
	private VideoService videoService;
	
	@RequestMapping("/getVideo")
	public String getVideo(String id) {
		System.out.println(videoService.getVideo(id).getName());
		return "homePage";		
	}
	@RequestMapping("/getVideoList")
	public List<Video> getVideoList(){			
		System.out.println(videoService.getVideoList());
		return videoService.getVideoList();		
	}
	
	@RequestMapping(value="/video/upload",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap uploadVideo(HttpServletRequest request) {
//		Video video2 = new Video();
//		videoService.insertVideo();
		String url=null;
		try {
			url=UploadUtils.uploadVideo(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ModelMap map=new ModelMap();
		map.addAttribute("url", url);
		map.addAttribute("flag", true);
		return map;
	}
	@RequestMapping(value="/image/upload",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap uploadImage(HttpServletRequest request) {
//		Video video2 = new Video();
//		videoService.insertVideo();
		String url=null;
		try {
			url=UploadUtils.uploadImage(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ModelMap map=new ModelMap();
		map.addAttribute("url", url);
		map.addAttribute("flag", true);
		return map;
	}
	
	@RequestMapping("/updateVideo")
	public void updateVideo(Video video){	
		Video video2 = new Video();
		video2.setId("1010");
		video2.setName("11");
		video2.setImage("/static/videolook/videolookimg/6130489-1-hd.png");
		video2.setAddress("/static/videolook/6130489-1-hd.mp4");
		video2.setTime("35");		
		video2.setCategory("1");
		video2.setState("");
		videoService.updateVideo(video2);
	}
	
	@RequestMapping("/deleteVideo")
	public void deleteVideo(String id) {
		videoService.deleteVideo(id);
	}

	@RequestMapping("/getVideoListByCategory")
	public List<Video> getVideoListByCategory(String id) {
		System.out.println(videoService.getVideoListByCategory(id));
		return 	videoService.getVideoListByCategory(id);
	}
	
	@RequestMapping("/getVideoListNumber")
	@ResponseBody
	public String getVideoListNumber(String id) {
		return ""+videoService.getVideoListNumber(id);
//		System.out.println(number);
//		return number;	
	}
}
