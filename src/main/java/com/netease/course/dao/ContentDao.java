package com.netease.course.dao;

import org.apache.ibatis.annotations.Select;

import com.netease.course.meta.Content;

public interface ContentDao {
	@Select("select * from content where title= #{title}")
	public Content getContent(String title);
}
