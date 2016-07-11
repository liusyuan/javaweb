package com.netease.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netease.course.service.ProductService;
import com.netease.course.utils.PriceUtil;
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
				
				product.setPrice(PriceUtil.toYuan(product.getPrice()));
				//设置Product已购买已售属性
				BuyList buyList = buyListDao.getBuyList(product.getId());
				if (buyList.getNumber() != 0) {
					product.setIsBuy(true);
					product.setIsSell(true);
					product.setBuyPrice(PriceUtil.toYuan(buyList.getBuyPrice()));
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

			List<Product> productList = productDao.getProductList();
			for (Product product : productList) {
				if (product != null) {
					product.setPrice(PriceUtil.toYuan(product.getPrice()));
					if (buyListDao.getBuyList(product.getId()).getNumber() != 0) {
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

		
	}

	// 删除产品
	@Override
	public boolean deleteProduct(int id){
		//如果删除产品时恰好买家购买了，则删除失败
		if(buyListDao.getBuyList(id).getNumber()>0){
			return false;
		}else{
			productDao.deleteProduct(id);
			return true;
		}
		
	}

	// 发布产品
	@Override
	public void addProduct(Product product){
		product.setPrice(PriceUtil.toFen(product.getPrice()));
		productDao.addProduct(product);		
	}

	// 更新产品
	@Override
	public void updateProduct(Product product){
		
		product.setPrice(PriceUtil.toFen(product.getPrice()));
		productDao.updateProduct(product);
	}

}
