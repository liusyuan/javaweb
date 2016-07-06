package com.netease.course.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.netease.course.meta.BuyList;


public interface BuyListDao {
	@Insert("insert into trx (contentId,personId,price,time) "
			+ "values(#{id},#{personId},#{buyPrice},#{buyTime})")
	public void trx(BuyList buyList);
	
	
	
	@Results({
		@Result(property="id",column="contentId"),
		@Result(property="personId",column="personId"),
		@Result(property="buyPrice",column="price"),
		@Result(property="buyTime",column="time"),		
		@Result(property="number",column="count(contentId)")		
	})
	@Select("select contentId,personId,price,time,count(contentId) from trx where contentid = #{id}")
	public BuyList getOrder(int id);
}
