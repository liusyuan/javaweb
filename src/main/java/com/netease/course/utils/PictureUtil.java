package com.netease.course.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class PictureUtil {


	public static String save(MultipartFile pic,String path) throws IllegalStateException, IOException {
		String picName=pic.getOriginalFilename();
		picName=getNewName(picName);
		String savePath=path+"image/";
		File file=new File(savePath);
		if  (!file .exists()  && !file .isDirectory())      
		{        
		    file .mkdir();
		} 
		pic.transferTo(new File(savePath+picName));
		return "/image/"+picName;
	}	

	public static String getNewName(String oldName) {
		String picType=oldName.substring(oldName.lastIndexOf("."));
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String format = df.format(new Date());		
		Random random = new Random();
		int end3 = random.nextInt(999);
		String str = String.format("%03d", end3);
		String newName=format+str+picType;
		return newName;
	}

}

