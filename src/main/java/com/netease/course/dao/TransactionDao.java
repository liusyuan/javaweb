package com.netease.course.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.netease.course.meta.Transaction;

public interface TransactionDao {
	@Insert("insert into trx (id,contentId,personId,price,time) "
			+ "values(#{id},#{contentId},#{personId},#{price},#{time})")
	public void trx(Transaction trx);
	
	@Select("select * from trx where contentid = #{id}")
	public Transaction getOrder(int id);
}
