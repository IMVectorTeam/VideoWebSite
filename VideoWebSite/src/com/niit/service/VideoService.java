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
	
	//模糊查询
	public List<VideoC> getVideoListByLikeName(String name){
		List<Video> list = videoDao.getVideoListByLikeName(name);
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
	
	//通过视频类别ID得到分类视频list
	public List<VideoC> getVideoListByCategory(String id){	
		List<Video> list = videoDao.getVideoListByCategory(id);
		List<VideoC> listC = new ArrayList<VideoC>();
		for (int i = 0; i < list.size(); i++) {
			VideoC videoC = new VideoC();
			videoC.setVideo(list.get(i));
			videoC.setVideoCategory(videoCategoryDao.getVideoCategory(list.get(i).getCategory()));
			listC.add(videoC);
		}		
		return listC;	
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
	public List<VideoC> getVideoListByUserId(String id){
		List<Video> list = videoDao.getVideoListByUserId(id);
		List<VideoC> listC = new ArrayList<VideoC>();
		for (int i = 0; i < list.size(); i++) {
			VideoC videoC = new VideoC();
			videoC.setVideo(list.get(i));
			videoC.setVideoCategory(videoCategoryDao.getVideoCategory(list.get(i).getCategory()));
			listC.add(videoC);
		}		
		return listC;	
	}
	
	public User getUserByVideoId(String id){
		Video video=videoDao.getUserIdByVideoId(id);
		User user = userDao.getUser(video.getUserId());
		return user;
	}	

}
