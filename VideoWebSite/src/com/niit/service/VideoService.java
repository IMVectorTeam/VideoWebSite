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
	
	@Autowired
	private UserMapper userDao;
	
	@Autowired
	private VideoCategoryMapper videoCategoryDao;

	
	public VideoC getVideo(String id) {		
//		videoDao.getVideo(id).setVideoCategory(videoCategoryDao.getVideoCategory(videoDao.getVideo(id).getCategory()));  
		VideoC videoC = new VideoC();
		videoC.setVideo(videoDao.getVideo(id));
		videoC.setVideoCategory(videoCategoryDao.getVideoCategory(videoDao.getVideo(id).getCategory()));
		
		return videoC;	
	}
	
	public List<VideoC> getVideoList(){	
		
		List<Video> list = videoDao.getVideoList();
		List<VideoC> listC = new ArrayList<VideoC>();
		for (int i = 0; i < list.size(); i++) {
			VideoC videoC = new VideoC();
			videoC.setVideo(list.get(i));
			videoC.setVideoCategory(videoCategoryDao.getVideoCategory(list.get(i).getCategory()));
			listC.add(videoC);
		}		
		return listC;	
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
	
	//通过视频类别名称查视频
	public List<Video> getVideoListByCategoryName(String name){
		List<Video> list =videoDao.getVideoListByCategory(videoCategoryDao.getVideoCategoryIdByName(name)); 
		return list;
		
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
