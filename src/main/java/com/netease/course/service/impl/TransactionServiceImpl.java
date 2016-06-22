package com.netease.course.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.course.dao.ProductDao;
import com.netease.course.dao.TransactionDao;
import com.netease.course.meta.Product;
import com.netease.course.meta.Transaction;
import com.netease.course.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionDao trxDao;
	@Autowired
	ProductDao productDao;

	@Override
	public boolean buy(int productId) {
		try {
			Product product = productDao.getProduct(productId);
			Transaction trx = new Transaction();
			trx.setContentId(productId);
			trx.setPrice(product.getPrice());
			trx.setPersonId(0);
			Date date = new Date();
			trx.setTime(date.getTime());
			trxDao.trx(trx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
