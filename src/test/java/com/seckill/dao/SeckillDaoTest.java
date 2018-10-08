package com.seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.entity.Seckill;

@RunWith(SpringJUnit4ClassRunner.class) //spring和junit的整合类启动
@ContextConfiguration({"classpath:spring/spring-dao.xml"})  //junit加载spring的配置文件
public class SeckillDaoTest {

	@Resource
	private SeckillDao seckillDao;
	
	@Test
	public void queryById() throws Exception {
		long seckillId = 1001;
		Seckill seckill = seckillDao.querySeckillById(seckillId);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}
	
	@Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillDao.querySeckillAll(0,100);
        for (Seckill seckill : seckills)
        {
            System.out.println(seckill);
        }
    }
	
	@Test
	public void reduceNumberTest(){
		long seckillId = 1000;
		Date date = new Date();
		int updateCount = seckillDao.reduceSeckillNumber(seckillId, date);
		System.out.println(updateCount);
	}
	
}
