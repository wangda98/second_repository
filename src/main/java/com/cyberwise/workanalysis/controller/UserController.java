package com.cyberwise.workanalysis.controller;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.service.AllowedUserService;
import com.cyberwise.workanalysis.util.CodeAndDcode;
import com.cyberwise.workanalysis.util.DecodeUtil;
import com.cyberwise.workanalysis.util.HttpUrlConnection;
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private AllowedUserService aus;//注入服务类
	/**
	 * 登入
	 * @param account 用户名
	 * @param password 密码
	 * @param code    验证码
	 * @param request 
	 * @return
	 */
	@RequestMapping(value="/cyberLogin")
	@ResponseBody
	public CyberResultInfo login(@RequestParam(value="account", required=true)String account, @RequestParam(value="pass_hash", required=true)String password, @RequestParam(value="rand_code", required=true)String code,HttpServletRequest request) {
//		//登录密码提取算法
//		String deCode = DecodeUtil.deCode(password);
//		//base64解码
//		password= CodeAndDcode.getFromBASE64(deCode, "utf-8");
//		//调取接口验证登陆获取返回结果
//		String string = HttpUrlConnection.connection(account, password);
//		//从session中获取到的rand
//		String rand = (String) request.getSession().getAttribute("rand");
//		String allLoad = aus.findAllLoad(account);
//		//没有登陆权限
//		if(!"0".equals(allLoad)||allLoad==null){
//			CyberResultInfo resultInfo=new CyberResultInfo(9, "登陆失败,您没有登陆权限", "login.html", null);
//			return resultInfo;
//		}
//		if ("success".equals(string)&&"0".equals(allLoad) &&(rand+"").equalsIgnoreCase(code)) {
			System.out.println("登陆成功");
			request.getSession().setAttribute("account", "wangda");
			request.getSession().setAttribute("password", "123");
			CyberResultInfo resultInfo=new CyberResultInfo(0, "登陆成功", "main.html", null);
			return resultInfo;
//		}else{
//			CyberResultInfo resultInfo=new CyberResultInfo(2, "输入的账号、密码、或验证码有误，请重新输入", "login.html", null);
//			return resultInfo;
//		}
	}
	/**
	 * 登出
	 * @param account
	 * @param password
	 * @param code
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/cyberLogout")
	@ResponseBody
	public CyberResultInfo loginOut(HttpServletRequest request) {
			HttpSession session = request.getSession(); 
			session.invalidate();//强制session失效
			System.out.println("session已经失效");
			CyberResultInfo resultInfo=new CyberResultInfo(0, "你已成功退出,将返回登录页面", "login.html", null);
			return resultInfo;
	}
}




