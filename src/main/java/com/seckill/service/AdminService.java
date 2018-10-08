package com.seckill.service;

import java.util.List;

import com.seckill.entity.Adlogin;
import com.seckill.entity.Admins;
import com.seckill.entity.Seckill;
import com.seckill.entity.SuccessKilled;

public interface AdminService {

	// 管理员登录
	Integer queryAdmin(String aname, String apassword);
	
	// 查询管理员信息
	Admins queryAdminInfo(String aname);
	
	// 管理员登入登出时间记录
	int insertDblogin(Adlogin adlogin);
	
	// 查询单个秒杀记录
	Seckill getSeckillById(long seckillId);
	
	// 查询全部秒杀记录
	List<Seckill> getSeckillList();
	
	// 增加秒杀商品
	int insertSeckill(Seckill seckill);

	// 修改秒杀商品信息
	int updateSeckill(Seckill seckill);

	// 删除秒杀商品信息（根据seckillId）
	int deleteSeckill(long seckillId);

	// 查询全部秒杀成功用户记录
	List<SuccessKilled> getSeckillUserList();

}
