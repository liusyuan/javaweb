package com.netease.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.netease.course.meta.Product;

public interface ProductDao {
	@Results({
		@Result(property="image",column="icon",typeHandler=com.netease.course.utils.ConvertBlobTypeHandler.class),
		@Result(property="summary",column="abstract"),
		@Result(property="detail",column="text",typeHandler=com.netease.course.utils.ConvertBlobTypeHandler.class),
		@Result(property="id",column="id"),
		@Result(property="price",column="price"),
		@Result(property="title",column="title")		
	})
	@Select("select * from content where id= #{id}")
	public Product getProduct(int id);
	
	@Results({
		@Result(property="image",column="icon",typeHandler=com.netease.course.utils.ConvertBlobTypeHandler.class),
		@Result(property="summary",column="abstract"),
		@Result(property="detail",column="text",typeHandler=com.netease.course.utils.ConvertBlobTypeHandler.class),
		@Result(property="id",column="id"),
		@Result(property="price",column="price"),
		@Result(property="title",column="title")		
	})
	@Select("select * from content")
	public List<Product> getProductList();

	@Results({
		@Result(property="image",column="icon",typeHandler=com.netease.course.utils.ConvertBlobTypeHandler.class),
		@Result(property="summary",column="abstract"),
		@Result(property="detail",column="text",typeHandler=com.netease.course.utils.ConvertBlobTypeHandler.class),
		@Result(property="id",column="id"),
		@Result(property="price",column="price"),
		@Result(property="title",column="title")		
	})
	
	@Insert("insert into content (id,price,title,icon,abstract,text) "
			+ "values(#{id},#{price},#{title},#{image},#{summary},#{detail})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	public void addProduct(Product product);
	
	@Delete("delete from content where id=#{id}")
	public void deleteProduct(int id);
	
	@Update("update content set price=#{price},title=#{title},"
			+ "icon=#{image},abstract=#{summary},text=#{detail} where id=#{id}")
	public void updateProduct(Product product);
}
