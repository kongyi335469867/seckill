package com.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.seckill.entity.Adlogin;
import com.seckill.entity.Admins;
import com.seckill.entity.Seckill;
import com.seckill.entity.SuccessKilled;

public interface AdminDao {

	// 管理员登录
	Integer queryAdmin(@Param("aname") String aname, @Param("apassword") String apassword);
	
	// 查询管理员信息
	Admins queryAdminInfo(String aname);

	// 后台自动添加记录管理员登入登出时间
	int insertDblogin(Adlogin adloogin);
	
	// 根据id查询秒杀的商品信息
	Seckill querySeckillById(long seckillId);
	
	// 根据偏移量查询秒杀商品列表
	List<Seckill> querySeckillAll(@Param("offset") int offset, @Param("limit") int limit);
	
	//后台管理查看所有成功秒杀记录
	List<SuccessKilled> querySeckillUserAll();
	
	// 后台管理员增加秒杀商品
	int insertSeckill(Seckill seckill);

	// 后台管理员修改秒杀商品信息
	int updateSeckill(Seckill seckill);

	// 后台管理员删除秒杀商品记录
	int deleteSeckillById(long seckillId);
	
}
