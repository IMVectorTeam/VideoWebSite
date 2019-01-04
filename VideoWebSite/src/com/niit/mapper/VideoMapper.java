package com.niit.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.entity.Video;

@Repository
public interface VideoMapper {

	public List<Video> getVideoList();

	public void insertVideo(Video video);

	public void updateVideo(Video video);

	public void deleteVideo(String id);

	public Video getVideo(String id);

	public List<Video> getVideoListByCategory(String id);

	public int getVideoListNumber(String id);

	public List<Video> getVideoListByCategoryPagination(String id, int offset, int pageNumber);

	// 逄锟艺
	public List<Video> getVideoListByUserId(String id);

	public Video getUserIdByVideoId(String id);

}
