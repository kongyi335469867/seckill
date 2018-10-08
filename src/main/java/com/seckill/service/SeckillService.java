package com.seckill.service;

import java.util.List;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;

public interface SeckillService {

	//查询单个秒杀记录
	Seckill getSeckillById(long seckillId);
	
	//查询全部秒杀记录
	List<Seckill> getSeckillList();
	
	//暴露秒杀地址（在秒杀开启时输出秒杀地址，否则输出系统时间和秒杀时间）
	Exposer exportSeckillUrl(long seckillId);
	
	//执行秒杀操作，要对相应的异常做处理
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
	
	//执行秒杀操作：性能响应优化方式，使用数据库存储过程
	SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);
	
}
