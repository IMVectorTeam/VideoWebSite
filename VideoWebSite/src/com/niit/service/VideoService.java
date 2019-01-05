package com.niit.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.entity.User;
import com.niit.entity.Video;
import com.niit.entity.VideoC;
import com.niit.mapper.UserMapper;
import com.niit.mapper.VideoCategoryMapper;
import com.niit.mapper.VideoMapper;

@Service
public class VideoService {

	@Autowired
	private VideoMapper videoDao;

	
	public Video getVideo(String id) {		
		return videoDao.getVideo(id);	
	}
	
	//查询所有视频
	public List<Video> getVideoList(){		
		return videoDao.getVideoList();
	}	
	
	//模糊查询
	public List<Video> getVideoListByLikeName(String name){
		System.out.println(videoDao.getVideoListByLikeName(name).get(0).getCategory());
		return videoDao.getVideoListByLikeName(name);
	}
	
	public void insertVideo(Video video){		
		 videoDao.insertVideo(video);
	}
		
	
	public void updateVideo(Video video) {
		videoDao.updateVideo(video);
	}
	
	public void deleteVideo(String id) {
		videoDao.deleteVideo(id);
	}
	
	//通过视频类别ID得到分类视频list
	public List<Video> getVideoListByCategory(String id){	
		return videoDao.getVideoListByCategory(id);
	}
	
	public Integer getVideoListNumber(String id) {
		return videoDao.getVideoListNumber(id);
	}
	
	//通过视频类别名称查视频
	public List<Video> getVideoListByCategoryName(String name){
		List<Video> list =videoDao.getVideoByCategoryName(name); 
		return list;			
	}
	
	//根据用户id查询该用户的所有的上传的视频
	public List<Video> getVideoListByUserId(String id){
		return videoDao.getVideoListByUserId(id);
	}
	

}
