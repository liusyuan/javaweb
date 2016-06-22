package com.netease.course.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.course.dao.ProductDao;
import com.netease.course.dao.TransactionDao;
import com.netease.course.meta.Product;

@Service
public class getIsBuyProduct {
	@Autowired
	TransactionDao trxDao;
	@Autowired
	ProductDao productDao;

	public Product set(int id) {
		try {
			Product product = productDao.getProduct(id);
			if (trxDao.getOrder(product.getId()) != null) {
				product.setIsBuy(true);
				product.setIsSell(true);
			} else {
				product.setIsBuy(false);
				product.setIsSell(false);
			}
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Product> setProductList() {
		try {
			List<Product> productList = productDao.getProductList();
			for (Product product : productList) {
				if (product != null) {

					if (trxDao.getOrder(product.getId()) != null) {
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
