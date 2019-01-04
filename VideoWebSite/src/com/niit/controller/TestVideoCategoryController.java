package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.entity.User;
import com.niit.entity.VideoCategory;
import com.niit.service.UserService;
import com.niit.service.VideoCategoryService;


@Controller
public class TestVideoCategoryController {

	@Autowired
	private VideoCategoryService videoCategoryService;
	
	@RequestMapping("/getVideoCategory")
	public String getVideoCategory(String id) {
		System.out.println(videoCategoryService.getVideoCategory(id).getName());
		return "homePage";
		
	}
	
	@RequestMapping("/insertVideoCategory")
	public void insertVideoCategory(VideoCategory videoCategory){
		VideoCategory videoCategory2 = new VideoCategory();
		videoCategory2.setId("2");
		videoCategory2.setName("纪录片");
		videoCategory2.setRemark("以真实生活为创作素材，以真人真事为表现对象");
		videoCategoryService.insertVideoCategory(videoCategory2);		
	}
	
	@RequestMapping(value="/videoType",method=RequestMethod.GET)
	@ResponseBody
	public List<VideoCategory> getVideoCategoryList(){	
		
		return videoCategoryService.getVideoCategoryList();		
	}
	
	@RequestMapping("/updateCategory")
	public void updateCategory(VideoCategory videoCategory){	
		VideoCategory videoCategory2 = new VideoCategory();
		videoCategory2.setId("1");
		videoCategory2.setName("大型演唱会");
		videoCategory2.setRemark("亚洲");
		videoCategoryService.updateVideoCategory(videoCategory2);
	}
	
}
