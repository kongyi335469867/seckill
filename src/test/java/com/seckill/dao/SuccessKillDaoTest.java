package com.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.entity.SuccessKilled;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {

	@Resource
	private SuccessKillDao successKillDao;
	
	@Test
	public void insertSuccessKilled(){
		long seckillId = 1000;
		long userPhone = 17875511945L;
		int insertResult = successKillDao.insertSuccessKilled(seckillId, userPhone);
		System.out.println(insertResult);
	}
	
	@Test
	public void queryByIdWithSeckill(){
		long seckillId = 1000;
		long userPhone = 17875511945L;
		SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(seckillId, userPhone);
		System.out.println(successKilled);
		System.out.println(successKilled.getSeckillId());
	}
}
