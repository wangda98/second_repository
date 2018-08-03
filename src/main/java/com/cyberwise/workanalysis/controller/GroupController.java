package com.cyberwise.workanalysis.controller;
import java.util.ArrayList;
import java.util.Date;
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

import com.cyberwise.workanalysis.bean.Group;
import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.service.GroupAndUserService;
import com.cyberwise.workanalysis.service.GroupService;
import com.cyberwise.workanalysis.service.MainMenuService;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.mysql.fabric.xmlrpc.base.Array;
import net.sf.jsqlparser.statement.create.table.Index;
@Controller
@RequestMapping("/group")
public class GroupController {
	@Resource
	private GroupService gs;
	@Resource
	private GroupAndUserService gus;
	@RequestMapping(value="/queryAll")
	@ResponseBody
	public CyberResultInfo queryAll(HttpServletRequest request) {
		List<Group> list =gs.queryAll( ) ;
		CyberResultInfo resultInfo=new CyberResultInfo(0, "", "", list);
			return resultInfo;
	}
	@RequestMapping(value="/add")
	@ResponseBody
	public CyberResultInfo add(HttpServletRequest request,String groupName,Integer groupType) {
		int status=0;
		if(groupName!=null&&!groupName.isEmpty()&&groupType!=null){
			status=	gs.insert(groupName, groupType);
		}
		if(status>0){
			CyberResultInfo resultInfo=new CyberResultInfo(0, "添加成功", "",  null);
			return resultInfo;
		}else {
			CyberResultInfo resultInfo=new CyberResultInfo(1,  "添加失败", "", null);
			return resultInfo;
		}
	}
	@RequestMapping(value="/update")
	@ResponseBody
	public CyberResultInfo update(HttpServletRequest request,String groupName,Integer groupType,Integer status,Integer id) {
		int res=0;
		if(id!=null){
			res =gs.update(groupName, groupType, status,id);
		}
		if(res>0){
			CyberResultInfo resultInfo=new CyberResultInfo(0, "更新成功", "",  null);
			return resultInfo;
		}else {
			CyberResultInfo resultInfo=new CyberResultInfo(1,  "更新失败", "", null);
			return resultInfo;
		}
	}
	@RequestMapping(value="/delete")
	@ResponseBody
	public CyberResultInfo delete(HttpServletRequest request,Integer id) {
		int res=0;
		if(id!=null){
			res =gs.delete(id);//删除组
			 gus.deleteByGroupId(id);//同时删除组员
		}
		if(res>0){
			CyberResultInfo resultInfo=new CyberResultInfo(0, "删除成功", "",  null);
			return resultInfo;
		}else {
			CyberResultInfo resultInfo=new CyberResultInfo(1,  "删除失败", "", null);
			return resultInfo;
		}
	}
}




