package com.netease.course.service;

import java.util.List;

import com.netease.course.meta.User;
import com.netease.course.meta.BuyList;

public interface BuyListService {

	public void buy(List<BuyList> buyList) throws Exception;
	public List<BuyList> getBuyList(User user) throws Exception;
}
