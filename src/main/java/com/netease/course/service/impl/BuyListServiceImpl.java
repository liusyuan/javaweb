package com.netease.course.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.netease.course.meta.User;
import com.netease.course.service.BuyListService;
import com.netease.course.dao.ProductDao;
import com.netease.course.dao.UserDao;
import com.netease.course.dao.BuyListDao;
import com.netease.course.meta.BuyList;


@Service
@Transactional
public class BuyListServiceImpl implements BuyListService {

	@Autowired
	BuyListDao buyListDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	UserDao userDao;
	
	@Override
	public void addBuyList(List<BuyList> buyList) throws Exception{


			for (BuyList order : buyList) {			
				int contentId=order.getId();
				int buyPrice =productDao.getProduct(contentId).getPrice();
				for (int i = order.getNumber(); i > 0;i--) {
				
					order.setPersonId(0);
					Date date = new Date();
					order.setBuyTime(date.getTime());
					order.setBuyPrice(buyPrice);
					buyListDao.addBuyList(order);
					
				}
			}

	}

	@Transactional(readOnly=true)
	@Override
	public List<BuyList> getBuyList(String userName) throws Exception{
		User user=userDao.getUser(userName);
		List<BuyList> buyLists=user.getBuyList();
		for(BuyList buyList:buyLists){
			int number=buyListDao.getBuyList(buyList.getId()).getNumber();
			buyList.setBuyNum(number);
			buyList.setTotal(buyList.getBuyPrice()*number);
		}
		
		return buyLists;
	}

}
