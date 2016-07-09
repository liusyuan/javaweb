package com.netease.course.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netease.course.meta.User;
import com.netease.course.service.BuyListService;
import com.netease.course.utils.BuyException;
import com.netease.course.utils.PriceUtil;
import com.netease.course.dao.ProductDao;
import com.netease.course.dao.UserDao;
import com.netease.course.dao.BuyListDao;
import com.netease.course.meta.BuyList;

@Service
@Transactional
public class BuyListServiceImpl implements BuyListService {

	@Autowired
	BuyListDao buyListDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	UserDao userDao;

	@Override
	public void addBuyList(List<BuyList> buyLists) throws Exception, BuyException {
		// 遍历购物车
		for (BuyList buyList : buyLists) {
			int contentId = buyList.getId();

			// 查询交易记录表，如果已有记录表名已经购买过
			if (buyListDao.getBuyList(contentId).getNumber()!= 0) {
				throw new BuyException("重复购买");
			}
			double buyPrice = productDao.getProduct(contentId).getPrice();
			//查询商品表，如果商品价格与购物车不同，说明商品价格有变动，另外，购物车中单位为元，数据库中为分
			if(buyPrice!=(buyList.getBuyPrice()*100)){
				throw new BuyException("商品价格有变动");
			}
			buyList.setPersonId(0);
			Date date = new Date();
			buyList.setBuyTime(date.getTime());
			buyList.setBuyPrice(buyPrice);
			for (int i = buyList.getNumber(); i > 0; i--) {
				buyListDao.addBuyList(buyList);
			}
		}

	}

	@Transactional(readOnly = true)
	@Override
	public List<BuyList> getBuyList(String userName) throws Exception {
		User user = userDao.getUser(userName);
		List<BuyList> buyLists = user.getBuyList();
		for (BuyList buyList : buyLists) {

			// 分转换为元
			buyList.setBuyPrice(PriceUtil.toYuan(buyList.getBuyPrice()));
			int number = buyListDao.getBuyList(buyList.getId()).getNumber();
			buyList.setBuyNum(number);
			buyList.setTotal(buyList.getBuyPrice() * number);
		}

		return buyLists;
	}

}
