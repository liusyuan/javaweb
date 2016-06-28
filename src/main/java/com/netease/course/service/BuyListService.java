package com.netease.course.service;

import java.util.List;

import com.netease.course.meta.BuyList;
import com.netease.course.meta.User;

public interface BuyListService {
	public boolean buy(List<BuyList> buyList);
	public List<BuyList> getBuyList(User user);
}
