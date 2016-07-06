package com.netease.course.service;

import java.util.List;

import com.netease.course.meta.Product;

public interface ProductService {
	public Product getProduct(int id);
	public List<Product> getProductList();
	public void delete(int id);
	public void publish(Product product);
	public void update(Product product);
}
