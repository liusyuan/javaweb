package com.netease.course.service;

import java.util.List;
import com.netease.course.meta.BuyList;

public interface BuyListService {

	public void addBuyList(List<BuyList> buyList) throws Exception;
	public List<BuyList> getBuyList(String userName) throws Exception;
}
