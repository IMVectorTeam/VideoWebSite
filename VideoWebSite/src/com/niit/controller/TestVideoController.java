package com.niit.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.entity.User;
import com.niit.entity.Video;
import com.niit.entity.VideoC;
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
	

	@RequestMapping(value ="/video/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Video getVideoByID(@PathVariable String id) {
		System.out.println(id);
		return videoService.getVideo(id);
	}

	@RequestMapping(value = "/video/", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo getVideoList(int page, int limit) {
		PageHelper.startPage(page, limit);// 第一个参数是第几页，第二个参数是每一页的数量
		List<Video> videoList = videoService.getVideoList();
		PageInfo pageInfo = new PageInfo(videoList);
		return pageInfo;
	}

	@RequestMapping(value = "/video/upload", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap uploadVideo(HttpServletRequest request) {
//		Video video2 = new Video();
//		videoService.insertVideo();
		String url = null;
		try {
			url = UploadUtils.uploadVideo(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ModelMap map = new ModelMap();
		map.addAttribute("url", url);
		map.addAttribute("flag", true);
		return map;
	}

	@RequestMapping("/getVideoList")
	public List<Video> getVideoList() {
		System.out.println(videoService.getVideoList().get(0).getName());// 打印的视频的视频类别名称
		return videoService.getVideoList();
	}

	//模糊查询视频
	@RequestMapping("/getVideoListByLikeName")
	public List<Video> getVideoListByLikeName(String name) {
		System.out.println(videoService.getVideoListByLikeName(name).get(0).getCategory());// 打印查询到的视频类别名称
		return (List<Video>) videoService.getVideoListByLikeName(name);
	}

	@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap uploadImage(HttpServletRequest request) {
//		Video video2 = new Video();
//		videoService.insertVideo();
		String url = null;
		try {
			url = UploadUtils.uploadImage(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ModelMap map = new ModelMap();
		map.addAttribute("url", url);
		map.addAttribute("flag", true);
		return map;
	}

	@RequestMapping(value = "/video/", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap insertVideo(@RequestBody Video video) {
		video.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		video.setDate(new Date());

		ModelMap map = new ModelMap();
		boolean flag = false;
		try {
			videoService.insertVideo(video);
			flag = true;
		} catch (Exception e) {
			flag = false;
			System.out.println(e);
		}
		map.addAttribute("flag", flag);
		map.addAttribute("data", video);
		return map;
	}

	@RequestMapping("/updateVideo")
	@ResponseBody
	public void updateVideo(Video video) {
		Video video2 = new Video();
		video2.setId("1010");
		video2.setName("11");
		video2.setImage("/static/videolook/videolookimg/6130489-1-hd.png");
		video2.setAddress("/static/videolook/6130489-1-hd.mp4");
		video2.setTime("35");
		video2.setCategory("1");
		video2.setState("");
		video2.setUserId("1");
		video2.setDate(new Date());
		videoService.updateVideo(video2);
	}

	@RequestMapping("/deleteVideo")
	@ResponseBody
	public void deleteVideo(String id) {
		videoService.deleteVideo(id);
	}

	@RequestMapping("/getVideoListByCategory")
	@ResponseBody
	public List<Video> getVideoListByCategory(String id) {
		System.out.println(videoService.getVideoListByCategory(id).get(0).getName());
		return videoService.getVideoListByCategory(id);
	}

	@RequestMapping("/getVideoListNumber")
	@ResponseBody
	public String getVideoListNumber(String id) {
		return "" + videoService.getVideoListNumber(id);
//		System.out.println(number);
//		return number;	
	}

	// 根据视频类别名称查询视频
	@RequestMapping(value="/video/type/", method=RequestMethod.GET)
	@ResponseBody
	public PageInfo getVideoListByCategoryName(int page,int limit, String videoType) {
		PageHelper.startPage(page, limit);// 第一个参数是第几页，第二个参数是每一页的数量
		List<Video> list=videoService.getVideoListByCategoryName(videoType);
		PageInfo pageInfo = new PageInfo(list);
		System.out.println(videoType+" " +page+" "+ limit);
		return pageInfo;
	}

	// pky
	// 根据用户id查询该用户的所有的上传的视频
	@RequestMapping("/getVideoListByUserId")
	public List<Video> getVideoListByUserId(String id) {
		return videoService.getVideoListByUserId(id);
	}


}
