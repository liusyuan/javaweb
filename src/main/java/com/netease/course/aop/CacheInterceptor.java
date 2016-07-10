package com.netease.course.aop;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import org.springframework.beans.factory.annotation.Autowired;

import com.whalin.MemCached.MemCachedClient;

import net.sf.json.JSONArray;

public class CacheInterceptor {

	@Autowired
	private MemCachedClient memCachedClient;

	public static final int TIMEOUT = 3600;// 缓存失效时间1小时
	private static final Logger logger=Logger.getLogger(CacheInterceptor.class);
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		// 获取key
		String cacheKey = getCacheKey(pjp);
		// 如果Memcached 连接失败，直接返回service
		if (memCachedClient.stats().isEmpty()) {
			logger.error("memcached连接失败");
			return pjp.proceed();
		}
		if (memCachedClient.get(cacheKey) == null) {
			// 如果没有获得key就回到service
			Object proceed = pjp.proceed();

			// 将service返回的参数放入memcached
			memCachedClient.set(cacheKey, proceed, TIMEOUT);
			// 保存key
			saveCacheKey(cacheKey, pjp);

			return proceed;
		} else {
			// 如果memcach中有数据，返回memcached中的数据
			return memCachedClient.get(cacheKey);
		}
	}

	/**
	 * 当这个类下某个方法改动数据库时，删除该类的所有缓存
	 * @param jp
	 */
	public void doAfter(JoinPoint jp) {
		String packageName = jp.getTarget().getClass().getName();
		
		if (!memCachedClient.stats().isEmpty()) {
			@SuppressWarnings("unchecked")
			List<String> list =(List<String>) memCachedClient.get(packageName);
			//遍历
			for(String key:list){
					memCachedClient.delete(key);

			}
		}else{
			logger.error("memcached连接失败");
		}

		//memCachedClient.flushAll();

	}

	// Key生成规则，包名+ 类名 + 方法名 + 参数
	public String getCacheKey(ProceedingJoinPoint pjp) {

		StringBuilder key = new StringBuilder();
		// 包名+ 类名
		String packageName = pjp.getTarget().getClass().getName();
		key.append(packageName);
		// 方法名
		String methodName = pjp.getSignature().getName();
		key.append(".").append(methodName);

		// 参数列表，转换成json格式
		Object[] args = pjp.getArgs();
		JSONArray json = JSONArray.fromObject(args);
		key.append(".").append(json);

		return key.toString();
	}
/**
 * 将key保存在memcash中
 * 以类名分类，将同类下所有方法的key保存在一起
 * @param cacheKey
 * @param pjp
 */
	public void saveCacheKey(String cacheKey, ProceedingJoinPoint pjp) {

		String packageName = pjp.getTarget().getClass().getName();

		if (memCachedClient.get(packageName) == null) {
			List<String> list =new ArrayList<String>();
			list.add(cacheKey);
			memCachedClient.set(packageName, list);

		} else {
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) memCachedClient.get(packageName);
			list.add(cacheKey);
			memCachedClient.set(packageName,list);

		}

	}

}