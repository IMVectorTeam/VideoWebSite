package com.niit.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.entity.VideoCategory;

@Repository
public interface VideoCategoryMapper {

    public void updateVideoCategory(VideoCategory videoCategory);

    public VideoCategory getVideoCategory(String id);
    
    public List<VideoCategory> getVideoCategoryList();
    
    public void insertVideoCategory(VideoCategory videoCategory);
    
}
