package com.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.entity.Admins;
import com.seckill.entity.SuccessKilled;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class AdminServiceImplTest {

	@Autowired
	private AdminService adminService;
	
	//查找所有成功秒杀记录
	@Test
	public void querySeckillUserAll(){
		List<SuccessKilled> successKilleds = adminService.getSeckillUserList();
		for (SuccessKilled successKilled : successKilleds) {
			System.out.println(successKilled);
		}
	}
	
	//查询管理员信息
	@Test
	public void queryAdminInfo(){
		String aname = "admin";
		Admins admin = adminService.queryAdminInfo(aname);
		System.out.println(admin);
	}
}
