package com.netease.course.utils;
/**
 * 价格转换工具，用于价格元和分之间的互换
 * @author acer
 *
 */
public class PriceUtil {
	/**
	 * 元转换成分
	 */
	public static int toFen(double price){
		int newPrice=(int) (price*1000);
		int units=newPrice%10;
		if(units>=5){
			newPrice=newPrice/10+1;
		}else
		{
			newPrice=newPrice/10;
		}
		return newPrice;
	}
	
	public static double toYuan(double price){
		return price/100;
	}
	
	public static void main(String[] args) {
		System.out.println(toYuan(1012142)*2);
	}
}
