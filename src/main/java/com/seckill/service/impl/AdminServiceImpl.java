package com.seckill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seckill.dao.AdminDao;
import com.seckill.entity.Adlogin;
import com.seckill.entity.Admins;
import com.seckill.entity.Seckill;
import com.seckill.entity.SuccessKilled;
import com.seckill.service.AdminService;

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	// 管理员登录
	@Override
	public Integer queryAdmin(String aname, String apassword) {
		Integer result = adminDao.queryAdmin(aname, apassword);
		return result;
	}

	// 查询管理员
	@Override
	public Admins queryAdminInfo(String aname) {
		return adminDao.queryAdminInfo(aname);
	}

	// 管理员登入登出时间记录
	@Override
	public int insertDblogin(Adlogin adlogin) {
		return adminDao.insertDblogin(adlogin);
	}
	
	@Override
	// 查询单个秒杀记录
	public Seckill getSeckillById(long seckillId) {
		return adminDao.querySeckillById(seckillId);
	}
	
	@Override
	// 查询全部秒杀商品
	public List<Seckill> getSeckillList() {
		return adminDao.querySeckillAll(0, 20);
	}
	
	@Override
	// 增加秒杀商品
	public int insertSeckill(Seckill seckill) {
		return adminDao.insertSeckill(seckill);
	}

	@Override
	// 修改秒杀商品信息
	public int updateSeckill(Seckill seckill) {
		return adminDao.updateSeckill(seckill);
	}

	@Override
	// 删除秒杀商品信息（根据seckillId）
	public int deleteSeckill(long seckillId) {
		return adminDao.deleteSeckillById(seckillId);
	}

	// 查询全部秒杀成功用户
	@Override
	public List<SuccessKilled> getSeckillUserList() {
		return adminDao.querySeckillUserAll();
	}

}
