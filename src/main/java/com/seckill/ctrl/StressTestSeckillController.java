package com.seckill.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;
import com.seckill.utils.RandomUtil;

/* 对商品秒杀这一功能做压力测试 */
@Controller
public class StressTestSeckillController {

	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@RequestMapping(value = "/{seckillId}/stressTestDetail")
	public String stressTestDetail(@PathVariable("seckillId") Long seckillId, Model model){
		Seckill seckill = seckillService.getSeckillById(seckillId);
		model.addAttribute("seckill", seckill);
		return "stressTestDetail";
	}
	
	@RequestMapping(value = "/{seckillId}/stressTestExecute")
	public String stressTestExecute(@PathVariable("seckillId") Long seckillId, Model model){
		Exposer exposer = seckillService.exportSeckillUrl(seckillId);
		Long userPhone = Long.parseLong(RandomUtil.getRandomEleven(11));
		if(exposer.isExposed()){
			System.out.println("秒杀已开启，地址信息可以暴露：" + exposer);
			String md5 = exposer.getMd5();
			try {
				System.out.println("seckillId: " + seckillId + ", md5: " + md5 + ", userPhone: " + userPhone);
				// 原先的方式，在java代码执行事务
				//SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
				
				// 优化的方式，在MySQL数据库调用过程执行事务
				SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, userPhone, md5);
				System.out.println(execution.getStateInfo());
				System.out.println("用户秒杀成功，商品明细信息：" + execution);
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
		return "redirect:/{seckillId}/stressTestDetail";
	}
	
}
