package com.seckill.ctrl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seckill.entity.Adlogin;
import com.seckill.entity.Admins;
import com.seckill.entity.Seckill;
import com.seckill.entity.SuccessKilled;
import com.seckill.service.AdminService;
import com.seckill.utils.DateTimeUtil;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/adminLogin")
	public String adminLogin(HttpServletRequest request) {
		return "backstageLogin";
	}

	// 登录登录验证
	@RequestMapping(value = "/adminValidate")
	public String adminValidate(@RequestParam String aname, @RequestParam String apassword, HttpServletRequest request,
			HttpSession session, RedirectAttributes attr) {
		Integer astate = adminService.queryAdmin(aname, apassword);
		if (null != astate) {
			if (astate == 1) {   //管理员登录成功
				System.out.println("session.getAttribute('admin'):" + session.getAttribute("admin"));
				if (null == session.getAttribute("admin")) {
					Admins admin = adminService.queryAdminInfo(aname);
					session.setAttribute("admin", admin);
				}
				// 登入时间记录,保存至session
				Date aditime = new Date();
				session.setAttribute("aditime", aditime);
				return "redirect:/backstageIndex";
			} else {
				//转发方式,地址但不变化,体验不好
				//request.setAttribute("error", 0);  //登录失败:此账号无权限登入！（被限制）
				//return "forward:/adminLogin";
				//重定向方式
				attr.addFlashAttribute("error", 0);  //登录失败:此账号无权限登入！
				return "redirect:/adminLogin";
			}
		} else {
			attr.addFlashAttribute("error", -1);  //登录失败:账号或密码错误！
			return "redirect:/adminLogin";
		}
	}

	// 后台主页
	@RequestMapping(value = "/backstageIndex")
	public String backstageIndex(HttpServletRequest request, Model model) {
		// 管理员查询秒杀商品列表
		List<Seckill> list = adminService.getSeckillList();
		model.addAttribute("list", list);
		return "backstageIndex";
	}

	// 添加秒杀商品信息
	@RequestMapping(value = "/seckillInsert")
	public String seckillInsert(HttpServletRequest request) {
		return "backstageInsertPage";
	}

	@RequestMapping(value = "/seckillInsertActive")
	public String seckillInsertActive(String name, int number, @RequestParam String startTime, 
			@RequestParam String endTime, HttpServletRequest request, RedirectAttributes attr) {
		Seckill seckill = new Seckill();
		seckill.setSeckillId(0);
		seckill.setName(name);
		seckill.setNumber(number);
		seckill.setStartTime(DateTimeUtil.getStringFormat(startTime));
		seckill.setEndTime(DateTimeUtil.getStringFormat(endTime));
		seckill.setCreateTime(new Date());
		int result = adminService.insertSeckill(seckill);
		if (result == 1) {
			attr.addFlashAttribute("insertmsg", 1);  //添加成功！
		} else {
			attr.addFlashAttribute("insertmsg", 0);  //添加失败！
		}
		return "redirect:/seckillInsert";
	}

	// 修改秒杀商品信息
	@RequestMapping(value = "/{seckillId}/seckillUpdate")
	public String seckillUpdate(@PathVariable("seckillId") Long seckillId, HttpServletRequest request, Model model) {
		Seckill seckill = adminService.getSeckillById(seckillId);
		// System.out.println(dateFormat(seckill.getStartTime()));
		model.addAttribute("seckill", seckill);
		return "backstageUpdatePage";
	}

	@RequestMapping(value = "/{seckillId}/seckillUpdateActive")
	public String seckillUpdateActive(@PathVariable("seckillId") Long seckillId, @RequestParam String name, 
			@RequestParam int number, @RequestParam String startTime, @RequestParam String endTime, 
			HttpServletRequest request, RedirectAttributes attr) {
		Seckill seckill = adminService.getSeckillById(seckillId);
		// System.out.println(name + "__" + number + "__" + startTime + "__" +
		// stringFormat(endTime));
		seckill.setName(name);
		seckill.setNumber(number);
		seckill.setStartTime(DateTimeUtil.getStringFormat(startTime));
		seckill.setEndTime(DateTimeUtil.getStringFormat(endTime));
		int result = adminService.updateSeckill(seckill);
		System.out.println("seckillUpdate result:" + result);
		if (result == 1) {
			attr.addFlashAttribute("updatemsg", 1);  //修改成功！
		} else {
			attr.addFlashAttribute("updatemsg", 0);  //修改失败！
		}
		return "redirect:/backstageIndex";
	}

	// 删除秒杀商品
	@RequestMapping(value = "/{seckillId}/seckillDelete")
	public String seckillDelete(@PathVariable("seckillId") Long seckillId, HttpServletRequest request, 
			RedirectAttributes attr) {
		Integer result = adminService.deleteSeckill(seckillId);
		if (result == 1) {
			attr.addFlashAttribute("deletemsg", 1);   //删除成功！
		} else {
			attr.addFlashAttribute("deletemsg", 0);  //删除失败！
		}
		return "redirect:/backstageIndex";
	}

	// 用户管理（查询成功秒杀到商品的用户的所有记录）
	@RequestMapping(value = "/userManagement")
	public String userRecords(Model model) {
		List<SuccessKilled> userskList = adminService.getSeckillUserList();
		model.addAttribute("userskList", userskList);
		return "backstageUserManagement";
	}

	// 退出登录，删除session
	@RequestMapping(value = "/adminExit")
	public String adminExit(HttpSession session) {
		Admins admin = (Admins) session.getAttribute("admin");
		long aid = admin.getAid();  // 管理员ID号
		Date aditime = (Date) session.getAttribute("aditime");  // 管理员登入时间
		Date adotime = new Date();  // 管理员登出时间
		Adlogin adlogin = new Adlogin();
		adlogin.setAid(aid);
		adlogin.setAditime(aditime);
		adlogin.setAdotime(adotime);
		int result = adminService.insertDblogin(adlogin);
		System.out.println("ID:" + aid + "管理员登入时间：" + aditime + ",登出时间：" + adotime + ";记录添加result=" + result);
		// 删除session值
		session.removeAttribute("aditime");
		System.out.println("------管理员 "+ admin.getAname() +" 已退出登录-------");
		session.removeAttribute("admin");
		return "redirect:/adminLogin";
	}

	// 非正常登录状态界面
	@RequestMapping(value = "/loginError")
	public String loginError() {
		return "backstageError";
	}

}
