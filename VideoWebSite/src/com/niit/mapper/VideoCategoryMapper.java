package com.niit.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.entity.VideoCategory;

@Repository
public interface VideoCategoryMapper {

    public void updateVideoCategory(VideoCategory videoCategory);

    public VideoCategory getVideoCategory(String id);
    
    //通过视频类别名称查视频类别ID
    public String getVideoCategoryIdByName(String name);
    
    public List<VideoCategory> getVideoCategoryList();
    
    public void insertVideoCategory(VideoCategory videoCategory);
    
    public void deleteVideoCategory(String id);
    	
    public List<VideoCategory> getVideoCategoryByName(String categoryName);
}
