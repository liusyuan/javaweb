package com.netease.course.test.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netease.course.dao.ProductDao;
import com.netease.course.dao.TransactionDao;
import com.netease.course.meta.BuyList;
import com.netease.course.meta.User;
import com.netease.course.test.service.TestBuyListService;

@Service
@Transactional
public class TestBuyListServiceImpl implements TestBuyListService {

	@Autowired
	TransactionDao trxDao;
	@Autowired
	ProductDao productDao;

	
	@Override
	public boolean buy(List<BuyList> buyList) {

		try {
			for (BuyList order : buyList) {			
				int contentId=order.getId();
				int buyPrice =productDao.getProduct(contentId).getPrice();
				
					order.setPersonId(0);
					Date date = new Date();
					order.setBuyTime(date.getTime());
					order.setBuyPrice(buyPrice);
					trxDao.trx(order);
					throw new RuntimeException();
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;//如果不抛出异常，事务将不会生效
		}
		return true;
	}

	@Transactional(readOnly=true)
	@Override
	public List<BuyList> getBuyList(User user) {

		List<BuyList> buyLists=user.getBuyList();
		for(BuyList buyList:buyLists){
			int number=trxDao.getOrder(buyList.getId()).getNumber();
			buyList.setBuyNum(number);
			buyList.setTotal(buyList.getBuyPrice()*number);
		}
		
		return buyLists;
	}

}
