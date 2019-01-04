package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niit.entity.User;
import com.niit.entity.Video;
import com.niit.entity.VideoC;
import com.niit.entity.VideoCategory;
import com.niit.mapper.VideoCategoryMapper;
import com.niit.mapper.VideoMapper;
import com.niit.service.UserService;
import com.niit.service.VideoCategoryService;
import com.niit.service.VideoService;

@Controller
public class TestVideoController {

	@Autowired
	private VideoService videoService;
	
	@RequestMapping("/getVideo")
	public String getVideo(String id) {
		System.out.println(videoService.getVideo(id).getVideoCategory().getName());//打印的视频的视频类别名称
		return "homePage";		
	}
	@RequestMapping("/getVideoList")
	public List<VideoC> getVideoList(){			
		System.out.println(videoService.getVideoList().get(0).getVideoCategory().getName());//打印的视频的视频类别名称
		return videoService.getVideoList();		
	}
	
	@RequestMapping("/insertVideo")
	public void insertVideo(Video video) {
		Video video2 = new Video();
		video2.setId("1010");
		video2.setName("迪迦奥特曼大电影");
		video2.setImage("/static/videolook/videolookimg/6130489-1-hd.png");
		video2.setAddress("/videolookimg");
		video2.setTime("35");	
		video2.setCategory("1");
		video2.setState("");
		video2.setUserId("1");
		video2.setDate(new Date());
		videoService.insertVideo(video2);	
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
	
	//根据视频类别名称查询视频
	@RequestMapping("/getVideoListByCategoryName")
	public List<Video> getVideoListByCategoryName(String name){
		System.out.println(videoService.getVideoListByCategoryName(name));
		return videoService.getVideoListByCategoryName(name);
	}
	
	//pky
	//根据用户id查询该用户的所有的上传的视频
		@RequestMapping("/getVideoListByUserId")
		public List<Video> getVideoListByUserId(String id){
			List<Video> videoList=videoService.getVideoListByUserId(id);
			for(int i=0;i<videoList.size();i++){
				System.out.println(videoList.get(i));
			}
			return videoList;
		}
		//根据videoId查询该上传用户的信息
		@RequestMapping("/getUserByVideoId")
		public User getUserByVideoId(String id){
			User user=videoService.getUserByVideoId(id);
			System.out.println(user);
			return user;
		}

}
