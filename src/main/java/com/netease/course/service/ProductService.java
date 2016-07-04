package com.netease.course.service;

import java.util.List;

import com.netease.course.meta.Product;

public interface ProductService {
	public Product getProduct(int id);
	public List<Product> getProductList();
	public boolean delete(int id);
}
