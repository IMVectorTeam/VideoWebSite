package com.niit.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.entity.VideoCategory;
import com.niit.service.VideoCategoryService;

@Controller
public class TestVideoCategoryController {

	@Autowired
	private VideoCategoryService videoCategoryService;


	@RequestMapping(value="/videoType",method=RequestMethod.POST)
	@ResponseBody
	public boolean insertVideoCategory(@RequestBody VideoCategory videoCategory) {
		videoCategory.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		boolean flag = false;
		try {
			videoCategoryService.insertVideoCategory(videoCategory);
			flag = true;
		} catch (Exception e) {
			System.out.println(e);
			flag = false;
		}
		return flag;
	}

	// 查询所有视频类别
	@RequestMapping(value = "/videoType/list", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo getVideoCategoryList(int page, int limit,String name) {
		if(name==null|| name.equals("")) {
			PageHelper.startPage(page, limit);
			PageInfo info = new PageInfo(videoCategoryService.getVideoCategoryList());
			return info;
		}else {
			PageHelper.startPage(page, limit);
			PageInfo info = new PageInfo(videoCategoryService.getVideoCategoryByName(name));
			return info;
		}

	}

	@RequestMapping(value="/videoType/{id}",method=RequestMethod.PATCH)
	@ResponseBody
	public ModelMap updateCategory(@RequestBody VideoCategory videoCategory,@PathVariable String id) {
		Boolean flag = false;
		try {
			videoCategoryService.updateVideoCategory(videoCategory);
			flag = true;
		} catch (Exception e) {
			flag = false;
			System.out.println(e);
		}
		ModelMap map = new ModelMap();
		map.addAttribute("flag", flag);
		return map;
		
	}

	@RequestMapping(value="/videoType",method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteVideoCategory(String id) {
		boolean flag = false;
		try {
			videoCategoryService.deleteVideoCategory(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

}
