package com.netease.course.service;


import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
	public String save(MultipartFile pic,String path) throws IllegalStateException, IOException;

}
