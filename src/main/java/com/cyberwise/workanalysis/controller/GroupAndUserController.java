package com.cyberwise.workanalysis.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyberwise.workanalysis.bean.Group;
import com.cyberwise.workanalysis.bean.GroupAndUserValue;
import com.cyberwise.workanalysis.bean.ZtUser;
import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.service.GroupAndUserService;
import com.cyberwise.workanalysis.service.GroupService;
import com.cyberwise.workanalysis.service.MainMenuService;
import com.cyberwise.workanalysis.service.ZtUserService;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.mysql.fabric.xmlrpc.base.Array;
import net.sf.jsqlparser.statement.create.table.Index;
@Controller
@RequestMapping("/groupAndUser")
public class GroupAndUserController {
	@Resource
	private GroupAndUserService gus;
	@Resource
	private GroupService gs;
	@Resource
	private ZtUserService zs;
	//根据工作组的id进行组员查询
	@RequestMapping(value="/queryByGroupId")
	@ResponseBody
	public CyberResultInfo queryByGroupId(Integer id) {
		List<GroupAndUserValue> list =gus.queryByGroupId(id);
		CyberResultInfo resultInfo=new CyberResultInfo(0, "", "", list);
			return resultInfo;
	}
	@RequestMapping(value="/queryAll")
	@ResponseBody
	public CyberResultInfo queryAll(HttpServletRequest request) {
		//获取所有的项目组
		List<Group> list  = gs.queryAll();
		CyberResultInfo resultInfo=new CyberResultInfo(0, "", "", list);
			return resultInfo;
	}
	@RequestMapping(value="/likeFind")
	@ResponseBody
	public CyberResultInfo likeFind(String inputName) {
		List<ZtUser> list =gus.likequery(inputName);
		for (ZtUser groupAndUserValue : list) {
		}
		//获取所有的项目组
		CyberResultInfo resultInfo=new CyberResultInfo(0, "", "", list);
			return resultInfo;
	}
	@RequestMapping(value="/add")
	@ResponseBody
	public CyberResultInfo add(Integer groupName,Integer id,Integer isLeader,String date) throws ParseException {
		int status=0;
		if(groupName!=null&&id!=null&&isLeader!=null&&date!=null&&!date.isEmpty()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date1= formatter.parse(date); //time为你前台得到的值
			System.out.println("后台接收到的参数:"+"     用户ID:"+id+"     groupID:"+groupName+"     isLeader:"+isLeader+"     date:"+date);
 				int insert = gus.insert(groupName, id, isLeader,date1);
 				System.out.println("插入数据后返回的结果:"+insert);
				CyberResultInfo resultInfo=new CyberResultInfo(0, "添加成功", "",  null);
				return resultInfo;
			}
		System.out.println("添加信息不完整,添加失败");
		CyberResultInfo resultInfo=new CyberResultInfo(1,  "添加失败,添加信息不完整", "", null);
		return resultInfo;
	}
	@RequestMapping(value="/update")
	@ResponseBody
	public CyberResultInfo update(Integer groupName,Integer memberName,Integer isLeader,Integer isRemove,Integer id,String date,String isChange) throws Exception {
		int res=0;
		if(id!=null){
		res =gus.update(groupName, memberName, isLeader,isRemove,id,date,isChange);
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
		if (id!=null) {
			res =gus.delete(id);
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




