package com.cyberwise.workanalysis.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.scripting.xmltags.IfSqlNode;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyberwise.workanalysis.bean.MainMenu;
import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.service.MainMenuService;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.mysql.fabric.xmlrpc.base.Array;
import net.sf.jsqlparser.statement.create.table.Index;
@Controller
@RequestMapping("/main")
public class MainController {
	@Resource
	private MainMenuService mms;
	
	@RequestMapping(value="/cyberMainMenu")
	@ResponseBody
	public CyberResultInfo cyberMainMenu(HttpServletRequest request) {
		List<MainMenu> list = mms.queryAll();
		String account=(String) request.getSession().getAttribute("account");
		System.out.println("测试成功,用户名为:"+account);
		Object[] objects={account,list};
		CyberResultInfo resultInfo=new CyberResultInfo(0, "", "", objects);
			return resultInfo;
	}
	
	@RequestMapping(value="/cyberHeart")
	@ResponseBody
	public CyberResultInfo cyberHeart(HttpServletRequest request) {
		CyberResultInfo resultInfo=new CyberResultInfo(0, "", "", null);
			return resultInfo;
	}
}




