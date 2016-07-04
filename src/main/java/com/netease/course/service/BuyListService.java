package com.netease.course.service;

import java.util.List;

import com.netease.course.meta.User;
import com.netease.course.meta.BuyList;

public interface BuyListService {

	public boolean buy(List<BuyList> buyList);
	public List<BuyList> getBuyList(User user);
}
