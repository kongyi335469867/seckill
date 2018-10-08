package com.seckill.ctrl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.dto.SeckillResult;
import com.seckill.entity.Seckill;
import com.seckill.entity.SeckillStateEnum;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.service.SeckillService;

//对于spring框架，默然会将项目加入url，但springboot框架的则需要加入下面这句
//@RequestMapping(value = "/seckill")
@Controller
public class SeckillController {

	@Autowired
	private SeckillService seckillService;
	
	// 商品列表信息
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}
	
	// 商品信息详情页
	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
		if (seckillId == null) {
			//return "redirect:/seckill/list";
			return "redirect:/list";
		}
		Seckill seckill = seckillService.getSeckillById(seckillId);
		if (seckill == null) {
			//return "forward:/seckill/list";
			return "redirect:/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	
	// 暴露秒杀接口地址
	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
		SeckillResult<Exposer> result;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			return new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			e.printStackTrace();
			result = new SeckillResult<Exposer>(false, e.getMessage());
		}
		return result;
	}

	// 执行秒杀
	@RequestMapping(value = "/{seckillId}/{md5}/execute", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
			@CookieValue(value = "userPhone", required = false) Long userPhone, @PathVariable("md5") String md5) {
		if (userPhone == null) {
			return new SeckillResult<SeckillExecution>(false, "手机号未注册！");
		}
		try {
			// 原先的方式，在java代码执行事务
			//SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
			
			// 优化的方式，在MySQL数据库调用过程执行事务
			SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, userPhone, md5);
			return new SeckillResult<SeckillExecution>(true, execution);
			
		} catch (RepeatKillException e1) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(true, execution);
		} catch (SeckillCloseException e2) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.END);
			return new SeckillResult<SeckillExecution>(true, execution);
		} catch (Exception e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true, execution);
		}
	}

	// 获取系统时间
	@RequestMapping(value = "/time/now")
	@ResponseBody
	public SeckillResult<Long> nowTime() {
		Date nowTime = new Date();
		return new SeckillResult<Long>(true, nowTime.getTime());
	}
	
}
