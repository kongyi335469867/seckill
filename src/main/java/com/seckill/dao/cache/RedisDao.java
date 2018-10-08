package com.seckill.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.seckill.entity.Seckill;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {
	
	//日志对象
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	//jedis地址池对象
	private final JedisPool jedisPool;
	//构造方法，通过数据注入方式连接redis缓存数据库
	public RedisDao(String ip, int port){
		jedisPool = new JedisPool(ip, port);
	}
	//protostuff中api：运行期字节码转换模式
	private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
	
	/* 从redis获取信息 ，相当于get方式*/
	public Seckill getSeckill(long seckillId){
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckill:" + seckillId;
				//需要反序列化方式拿取信息：get -> btye[] -> 反序列化 -> Object(Seckill)
				//jedis中并没有实现内部序列化操作，引入protostuff
				byte[] bytes = jedis.get(key.getBytes());
				if(bytes != null){
					//空对象
					Seckill seckill = schema.newMessage();
					//将seckill反序列化
					ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
					return seckill;
				}
			} finally {
				jedis.close();   //关闭连接
			}
 		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	/* redis中存放数据，相当于set方式 */
	public String putSeckill(Seckill seckill){
		// set -> Object(Seckill) -> 序列化 -> byte[] -> 
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckill:" + seckill.getSeckillId();
				byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				//超时缓存,一小时
				int timeout = 60 * 60;
				String result = jedis.setex(key.getBytes(), timeout, bytes);
				return result;
			} finally {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
