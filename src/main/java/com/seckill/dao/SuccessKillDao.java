package com.seckill.dao;

import org.apache.ibatis.annotations.Param;

import com.seckill.entity.SuccessKilled;

public interface SuccessKillDao {

	//插入购买明细，课过滤重复
	int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone")long userPhone);
	
	//根据秒杀商品的id查询明细SuccessKilled对象（该对象携带了Seckill秒杀产品对象）
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckilled,@Param("userPhone") long userPhone);

}
