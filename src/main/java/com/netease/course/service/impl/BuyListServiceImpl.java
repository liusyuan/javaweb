package com.netease.course.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netease.course.dao.ProductDao;
import com.netease.course.dao.TransactionDao;
import com.netease.course.meta.BuyList;
import com.netease.course.meta.User;
import com.netease.course.service.BuyListService;


@Service
public class BuyListServiceImpl implements BuyListService {

	@Autowired
	TransactionDao trxDao;
	@Autowired
	ProductDao productDao;

	@Transactional
	@Override
	public boolean buy(List<BuyList> buyList) {
		System.out.println("doBuylist");
		try {
			for (BuyList order : buyList) {

				for (int i = order.getNumber(); i > 0;i--) {
					System.out.println("doBuy");
					order.setPersonId(0);
					Date date = new Date();
					order.setBuyTime(date.getTime());
					trxDao.trx(order);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

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
