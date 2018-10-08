package com.seckill.utils;

import java.util.Random;

public class RandomUtil {

	// 获取随机数
	public static String getRandomEleven(int length){
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}
	
}
