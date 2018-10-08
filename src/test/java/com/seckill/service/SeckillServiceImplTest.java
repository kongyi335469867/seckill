package com.seckill.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillException;
import com.seckill.utils.RandomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class SeckillServiceImplTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillService seckillService;

	@Autowired
	private AdminService adminService;
	
	@Test
	public void testGetSeckillList() throws Exception {
		List<Seckill> seckills = seckillService.getSeckillList();
		for (Seckill seckill : seckills) {
			//logger.info("seckill={} ", seckill);
			 System.out.println(seckill);
		}
	}

	@Test
	public void testGetSeckillById() throws Exception {
		long seckillId = 1000;
		Seckill seckill = seckillService.getSeckillById(seckillId);
		logger.info("seckill={} ", seckill);
		// System.out.println(seckill);
	}

	// 测试：暴露商品秒杀地址
	@Test
	public void testExportSeckillUrl() throws Exception {
		long seckillId = 1002;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		logger.info("exposer={} " + exposer);
		// 1002商品ID对应的暴露的地址中的md5加密值：fb50283c4766ba734abf7cd2a787f0df
		// System.out.println(exposer);
	}

	@Test
	public void testExecuteSeckill() throws Exception {
		long seckillId = 1002;
		long userPhone = 17875511946L;
		String md5 = "fb50283c4766ba734abf7cd2a787f0df";
		SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
		logger.info("executeSeckill={} " + seckillExecution);
		//System.out.println(seckillExecution);
		//打印出的信息：
		//executeSeckill={} 
		//SeckillExecution [seckillId=1002, state=1, stateInfo=秒杀成功, 
		//successKilled=SuccessKilled {seckillId=1002, userPhone=17875511946, state=0, createTime=Wed Oct 03 17:50:16 CST 2018, 
		//seckill=Seckill { seckillId=1002, name=1000元秒杀魅族3, number=3499, startTime=Wed Oct 03 17:50:17 CST 2018, endTime=Thu Oct 04 00:00:00 CST 2018, createTime=Sun Sep 30 00:00:00 CST 2018}}]
	}
	
	//综合测试
	@Test
	public void testSeckillLogic() throws Exception {
		long seckillId = 1003;
		long userPhone = 17875511947L;
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		if(exposer.isExposed()){
			System.out.println("秒杀已开启，地址信息可以暴露：" + exposer);
			String md5 = exposer.getMd5();
			try {
				SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
				System.out.println("用户秒杀成功，商品明细信息：" + seckillExecution);
			} catch (RepeatKillException e1) {
				e1.printStackTrace();
			} catch (SeckillException e2) {
				e2.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("秒杀未开启：" + exposer);
		}
	}

	@Test
	public void testInsertSeckill() throws Exception {
		Seckill seckill = new Seckill();
		Date date = new Date();
		seckill.setName("2000元魅族6");
		seckill.setNumber(200);
		seckill.setStartTime(date);
		seckill.setEndTime(date);
		seckill.setCreateTime(date);
		int insertCount = adminService.insertSeckill(seckill);
		Seckill sec = seckillService.getSeckillById(seckill.getSeckillId());
		System.out.println(insertCount + " -- " + sec);
	}

	@Test
	public void testUpdateSeckill() throws Exception {
		long seckillId = 1006;
		Date nowTime = new Date();
		Seckill seckill = seckillService.getSeckillById(seckillId);
		seckill.setName("修改2000元魅族6");
		seckill.setNumber(1000);
		seckill.setEndTime(nowTime);
		int updateCount = adminService.updateSeckill(seckill);
		Seckill seckillAfter = seckillService.getSeckillById(seckillId);
		System.out.println(updateCount + " -- " + seckillAfter);
	}

	@Test
	public void testDeleteSeckillById() throws Exception {
		long seckillId = 1006;
		int deleteCount = adminService.deleteSeckill(seckillId);
		Seckill seckill = seckillService.getSeckillById(seckillId);
		System.out.println(deleteCount + " -- " + seckill);
	}
	
	/* 测试：使用存储过程方式执行的秒杀商品  */
	@Test
	public void testExecuteSeckillProcedure(){
		long seckillId = 1003;
		long userPhone = Long.parseLong(RandomUtil.getRandomEleven(11));
		System.out.println("随机11位数字：" + userPhone);
		Seckill seckill = seckillService.getSeckillById(seckillId);
		System.out.println("seckill: " + seckill);
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		System.out.println("exposer: " + exposer);
		if(exposer.isExposed()){
			System.out.println("成功获取暴露地址");
			String md5 = exposer.getMd5();
			SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
			logger.info(execution.getStateInfo());
		}else{
			System.out.println("获取暴露地址失败！...");
		}
		
	}

}
