package com.netease.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netease.course.service.ProductService;
import com.netease.course.dao.ProductDao;
import com.netease.course.dao.BuyListDao;
import com.netease.course.meta.BuyList;
import com.netease.course.meta.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	BuyListDao buyListDao;
	@Autowired
	ProductDao productDao;

	// 获取商品
	@Transactional(readOnly = true)
	public Product getProduct(int id){

			Product product = productDao.getProduct(id);
			if (product != null) {
				BuyList trx = buyListDao.getOrder(product.getId());
				if (trx.getNumber() != 0) {
					product.setIsBuy(true);
					product.setIsSell(true);
					product.setBuyPrice(trx.getBuyPrice());
				} else {
					product.setIsBuy(false);
					product.setIsSell(false);
				}
			}
			return product;
	}

	// 获取商品列表
	@Transactional(readOnly = true)
	public List<Product> getProductList() {
		try {
			List<Product> productList = productDao.getProductList();
			for (Product product : productList) {
				if (product != null) {

					if (buyListDao.getOrder(product.getId()).getNumber() != 0) {
						// 已购买
						product.setIsBuy(true);
						//已卖出
						product.setIsSell(true);
					} else {
						//未购买
						product.setIsBuy(false);
						//未卖出
						product.setIsSell(false);
					}
				}
			}
			return productList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// 删除产品
	@Override
	public void delete(int id){
		productDao.delete(id);
	}

	// 发布产品
	@Override
	public void publish(Product product){
		productDao.add(product);		
	}

	// 更新产品
	@Override
	public void update(Product product){
		productDao.update(product);
	}

}
