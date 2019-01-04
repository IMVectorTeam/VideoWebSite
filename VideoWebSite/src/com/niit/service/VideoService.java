package com.niit.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.entity.User;
import com.niit.entity.Video;
import com.niit.mapper.UserMapper;
import com.niit.mapper.VideoMapper;

@Service
public class VideoService {

	@Autowired
	private VideoMapper videoDao;
	
	@Autowired
	private UserMapper userDao;

	public Video getVideo(String id) {		
		return videoDao.getVideo(id);	
	}
	
	public List<Video> getVideoList(){		
		return videoDao.getVideoList();	
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
	
	public List<Video> getVideoListByCategory(String id){		
		return videoDao.getVideoListByCategory(id);
	}
	
	public Integer getVideoListNumber(String id) {
		return videoDao.getVideoListNumber(id);
	}
	
	//pky
	public List<Video> getVideoListByUserId(String id){
		return videoDao.getVideoListByUserId(id);
	}
	
	public User getUserByVideoId(String id){
		Video video=videoDao.getUserIdByVideoId(id);
		User user = userDao.getUser(video.getUserId());
		return user;
	}	

}
