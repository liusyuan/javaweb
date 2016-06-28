package com.netease.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.course.dao.ProductDao;
import com.netease.course.dao.TransactionDao;
import com.netease.course.meta.BuyList;
import com.netease.course.meta.Product;
import com.netease.course.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	TransactionDao trxDao;
	@Autowired
	ProductDao productDao;

	public Product getProduct(int id) {
		try {
			Product product = productDao.getProduct(id);
			if (product != null) {
				BuyList trx = trxDao.getOrder(product.getId());
				if (trx.getNumber() != 0) {
					product.setIsBuy(true);
					product.setIsSell(true);
					product.setBuyPrice(trx.getBuyPrice());
				}else{
					product.setIsBuy(false);
					product.setIsSell(false);
				}
			}
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Product> getProductList() {
		try {
			List<Product> productList = productDao.getProductList();
			for (Product product : productList) {
				if (product != null) {

					if (trxDao.getOrder(product.getId()).getNumber() != 0) {
						product.setIsBuy(true);
						product.setIsSell(true);
					} else {
						product.setIsBuy(false);
						product.setIsSell(false);
					}
				}
			}
			return productList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
