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

			
			BuyList product=buyListDao.getBuyList(contentId);//查询交易记录表
			Date time=new Date();
			Long nowTime=time.getTime();//现在的时间
			//如果商品在交易记录表中数量大于0，已经购买过
			if (product.getNumber()>0) {
				Long buyTime=product.getBuyTime();//产品购买日期
				
				//如果现在的时间和购买时间小于20秒，则判定为重复提交
				if(nowTime-buyTime<20000){
					throw new BuyException("重复提交");
				}else{
					throw new BuyException("重复购买");//若需求为可以重复购买，去掉此行
				}
				
			}
			double buyPrice = productDao.getProduct(contentId).getPrice();
//			//查询商品表，如果商品价格与购物车不同，说明商品价格有变动
//			if(buyPrice!=(PriceUtil.toFen(buyList.getBuyPrice()))){
//				throw new BuyException("商品价格有变动");
//			}
			buyList.setPersonId(0);

			buyList.setBuyTime(nowTime);
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
