package com.seckill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.seckill.dao.SeckillDao;
import com.seckill.dao.SuccessKillDao;
import com.seckill.dao.cache.RedisDao;
import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.entity.SeckillStateEnum;
import com.seckill.entity.SuccessKilled;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;

@Service(value = "seckillService")
public class SeckillServiceImpl implements SeckillService {
	// 日志对象
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	// md5加密信息盐值
	private final String SALT = "zs*an&#@an'aj$ga3a.ga131_dna4#9$angfa11";

	@Autowired
	// @Resource(name="seckillDao")
	private SeckillDao seckillDao;

	@Autowired
	private SuccessKillDao successKillDao;

	@Autowired
	private RedisDao redisDao;

	@Override
	// 查询单个秒杀记录
	public Seckill getSeckillById(long seckillId) {
		return seckillDao.querySeckillById(seckillId);
	}

	@Override
	// 查询全部秒杀记录
	public List<Seckill> getSeckillList() {
		return seckillDao.querySeckillAll(0, 20);
	}

	@Override
	// 暴露秒杀地址（在秒杀开启时输出秒杀地址，否则输出系统时间和秒杀时间）
	public Exposer exportSeckillUrl(long seckillId) {
		// 优化：通过redis缓存数据
		Seckill seckill = redisDao.getSeckill(seckillId);
		if (seckill == null) { // redis缓存中没有数据，写进缓存
			seckill = getSeckillById(seckillId);
			if (seckill == null) { // 访问数据库，数据不存在，返回不存在
				return new Exposer(false, seckillId);
			} else { // 存在数据库，同时写进缓存
				redisDao.putSeckill(seckill);
			}
		}
		// 拿取到的seckill具体数据
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		// 当前时间不在秒杀商品开始时间和结束时间范围内，则不暴露接口，返回秒杀商品开始时间和结束时间
		if (startTime.getTime() > nowTime.getTime() || endTime.getTime() < nowTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		// 秒杀接口暴露，秒杀活动开启
		String md5 = getMD5(seckillId);
		return new Exposer(true, seckillId, md5);
	}

	// MD5是不可逆加密
	public String getMD5(long seckillId) {
		String base = seckillId + "/" + SALT;
		// spring框架提供了md5加密转换字符串方法
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Override
	@Transactional
	// 执行秒杀操作：成功则相应减库存并增加明细，失败则抛出异常并进行事务回滚
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		// 秒杀操作失败
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("秒杀数据被重写！");
		}
		// 执行秒杀逻辑，即减库存并增加明细
		Date nowTime = new Date();
		// 先进行插入用户的秒杀记录，一个用户对应一个商品只能秒杀一次，再进行减库存操作
		int insertCount = successKillDao.insertSuccessKilled(seckillId, userPhone);
		try {
			if (insertCount <= 0) {
				throw new RepeatKillException("秒杀重复！");
			} else {
				// update事务操作会进行锁持有，消耗一定的持锁时间，所以先insert再update更高效于先update再insert
				int updateCount = seckillDao.reduceSeckillNumber(seckillId, nowTime);
				if (updateCount <= 0) {
					throw new SeckillCloseException("秒杀已关闭！");
				} else {
					SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
				}
			}
		} catch (SeckillCloseException e1) {
			throw e1;
		} catch (RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// 编译异常转换为运行异常
			throw new SeckillException("秒杀内部异常：" + e.getMessage());
		}
	}

	// 执行秒杀操作：优化方案，使用数据库存储过程（将事务交给数据库去执行）
	@Override
	public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5) {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			return new SeckillExecution(seckillId, SeckillStateEnum.DATA_REWRITE); // 数据被篡改
		}
		Date killTime = new Date(); // 当前系统时间
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seckillId", seckillId);
		map.put("userPhone", userPhone);
		map.put("killTime", killTime);
		map.put("result", null);
		try {
			seckillDao.seckillBYProcedure(map);
			int result = MapUtils.getInteger(map, "result", -2); // 返回key"result"的值没有的话就返回-2
			if (result == 1) { // 成功
				SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(seckillId, userPhone);
				return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
			} else { // 根据返回的result数值判断抛出什么异常
				return new SeckillExecution(seckillId, SeckillStateEnum.stateOf(result));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR); // 出错，返回内部异常
		}
	}

}
