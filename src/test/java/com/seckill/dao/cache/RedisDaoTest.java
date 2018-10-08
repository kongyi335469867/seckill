package com.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dao.SeckillDao;
import com.seckill.entity.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {

	@Autowired
	private RedisDao redisDao;

	@Autowired
	private SeckillDao seckillDao;
	
	private long seckillId = 1001;
	
	@Test
	public void testSeckill(){
		Seckill	seckill = redisDao.getSeckill(seckillId);
		System.out.println("redis中拿取的数据：" + seckill);
		if(seckill == null){
			seckill = seckillDao.querySeckillById(seckillId);
			if(seckill != null){
				String result = redisDao.putSeckill(seckill);
				System.out.println(result);
				seckill = redisDao.getSeckill(seckillId);
				System.out.println("redis数据拿取成功,从数据库当中拿取：" + seckill);
			} else{
				System.out.println("redis数据拿取成功,数据库查询不到此商品信息！");
			}
		} else {
			System.out.println("redis数据拿取成功！");
		}
	}
	
	
}
