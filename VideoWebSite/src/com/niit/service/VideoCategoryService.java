package com.niit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.entity.User;
import com.niit.entity.VideoCategory;
import com.niit.mapper.UserMapper;
import com.niit.mapper.VideoCategoryMapper;

@Service
public class VideoCategoryService {

	@Autowired
	private VideoCategoryMapper videoCategoryDao;

	public VideoCategory getVideoCategory(String id) {		
		return videoCategoryDao.getVideoCategory(id);
	}
	
	public void insertVideoCategory(VideoCategory videoCategory){		
		 videoCategoryDao.insertVideoCategory(videoCategory);
	}
	
	public List<VideoCategory> getVideoCategoryList(){		
		return videoCategoryDao.getVideoCategoryList();		
	}
	
	public void updateVideoCategory(VideoCategory videoCategory) {
		videoCategoryDao.updateVideoCategory(videoCategory);
	}
	
	public void deleteVideoCategory(String id) {
		videoCategoryDao.deleteVideoCategory(id);
	}
}
