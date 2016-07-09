package com.netease.course.test.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.netease.course.dao.ProductDao;
import com.netease.course.dao.BuyListDao;
import com.netease.course.meta.BuyList;
import com.netease.course.meta.User;
import com.netease.course.test.service.TestBuyListService;

@Service
@Transactional
public class TestBuyListServiceImpl implements TestBuyListService {

	@Autowired
	BuyListDao trxDao;
	@Autowired
	ProductDao productDao;

	@Override
	public boolean buy(List<BuyList> buyList) {

		try {
			for (BuyList order : buyList) {
				int contentId = order.getId();
				double buyPrice = productDao.getProduct(contentId).getPrice();

				order.setPersonId(0);
				Date date = new Date();
				order.setBuyTime(date.getTime());
				order.setBuyPrice(buyPrice);
				trxDao.addBuyList(order);

			}

			throw new RuntimeException("ERROR");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return true;

	}

	@Transactional(readOnly = true)
	@Override
	public List<BuyList> getBuyList(User user) {

		List<BuyList> buyLists = user.getBuyList();
		for (BuyList buyList : buyLists) {
			int number = trxDao.getBuyList(buyList.getId()).getNumber();
			buyList.setBuyNum(number);
			buyList.setTotal(buyList.getBuyPrice() * number);
		}

		return buyLists;
	}

}
