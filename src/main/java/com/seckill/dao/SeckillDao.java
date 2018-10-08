package com.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.seckill.entity.Seckill;

public interface SeckillDao {
	
	// 根据id查询秒杀的商品信息
	Seckill querySeckillById(long seckillId);
	
	// 根据偏移量查询秒杀商品列表
	List<Seckill> querySeckillAll(@Param("offset") int offset, @Param("limit") int limit);
	
	// 减库存
	int reduceSeckillNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
	
	// 使用存储方式执行秒杀过程 
	void seckillBYProcedure(Map<String, Object> paramMap);
	
}
