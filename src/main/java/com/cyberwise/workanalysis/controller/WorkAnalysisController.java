package com.cyberwise.workanalysis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.service.WorkAnalysisService;
import com.cyberwise.workanalysis.service.WorkInfoForWeekService;

/**
 * 周负载分析
 */
@Controller
// @RequestMapping("/service/workanalysis")
@RequestMapping("/workanalysis")
public class WorkAnalysisController {
	
	
	@Autowired
	private WorkInfoForWeekService workInfoForWeekService;
	/**
	 * 查询当前时间段记录
	 * @return 
	 */
	@RequestMapping("/getWorkInfoForWeek")
	@ResponseBody
	public CyberResultInfo selectByExample(String date) {
		if (date == null||date.trim()=="") {
			return new CyberResultInfo(0,"请选择有效时间！",null);
		}
		List<Map<Object,Object>> list=workInfoForWeekService.selectByExample(date);
		return CyberResultInfo.ok(list);
	}
	/**
	 * 获取项目名
	 * @return
	 */
	@RequestMapping("/getWorkInfoForWeekObject")
	@ResponseBody
	public CyberResultInfo findObject() {
		List<Object> list=workInfoForWeekService.findObject();
		return CyberResultInfo.ok(list);
	}
	/**
	 * 执行筛选
	 * @param date
	 * @return
	 */
	@RequestMapping("/getWorkInfoForWeekscreening")
	@ResponseBody
	public CyberResultInfo findScreeningObject(String objectName,String names,boolean checkBox) {
		List<Map<Object,Object>> list=workInfoForWeekService.findScreeningObject(objectName, names, checkBox);
		return CyberResultInfo.ok(list);
	}
	
//--------------------------------------------------------------------------------------------------	
	

	@Autowired
	private WorkAnalysisService weekWorkLoadService;

	/**
	 * 获取所有统计周/service/workanalysis/getWeeksOfAnalysis
	 */
	@RequestMapping("/getWeeksOfAnalysis")
	@ResponseBody
	public CyberResultInfo getWeeksOfAnalysis() {
		return weekWorkLoadService.getWeeksOfAnalysis();
	}

	/**
	 * 获取周工作负载率
	 */
	@RequestMapping("/getWeekCapacity")
	@ResponseBody
	public CyberResultInfo getWeekCapacity(String date) {
		if (date == null) {
			return new CyberResultInfo(-1, "暂无查询数据", null);
		}
		date = date.substring(0, 10);
		return weekWorkLoadService.getWeekCapacity(date);
	}

	/**
	 * 获取所有岗位分类接口
	 */
	@RequestMapping("/getOperatingPost")
	@ResponseBody
	public CyberResultInfo getOperatingPost(Integer post_id, String keyword) {
		return weekWorkLoadService.getOperatingPost(post_id, keyword);
	}

	/**
	 * 获取个人工作表现趋势接口
	 */
	@RequestMapping("/getPersonalWorkAnalysis")
	@ResponseBody
	public CyberResultInfo getPersonalWorkAnalysis(String start_date, String end_date, Integer post_id, String names,
			Integer report_type) {
		// System.out.println(start_date + "--" + end_date + "--" + post_id +
		// "--" + names + "--" + report_type);
		if (start_date == null || end_date == null || post_id == null || names == null || report_type == null) {
			return new CyberResultInfo(-1, "无效的输入参数！", null);
		}
		// System.out.println(post_id);
		return weekWorkLoadService.getPersonalWorkAnalysis(start_date, end_date, post_id, names, report_type);
	}

	/**
	 * 获取员工岗位信息
	 */
	@RequestMapping("/getUserPostInfo")
	@ResponseBody
	public CyberResultInfo getUserPostInfo() {
		return weekWorkLoadService.getUserPostInfo();
	}

	/**
	 * 过滤员工信息
	 */
	@RequestMapping("/FilterUserPostInfo")
	@ResponseBody
	public CyberResultInfo FilterUserPostInfo(String filterName) {
		return weekWorkLoadService.FilterUserPostInfo(filterName);
	}

	/**
	 * 获取分项目工作量占比趋势数据接口
	 */
	@RequestMapping("/getPersonalWorkCapacityInProject")
	@ResponseBody
	public CyberResultInfo getPersonalWorkCapacityInProject(String start_date, String end_date, String names) {
		if (start_date == null || end_date == null || names == null) {
			return new CyberResultInfo(-1, "无效的输入参数！", null);
		}
		return weekWorkLoadService.getPersonalWorkCapacityInProject(start_date, end_date, names);
	}

	/**
	 * 获取组周工作负载率
	 */
	@RequestMapping("/getGroupWeekCapacity")
	@ResponseBody
	public CyberResultInfo getGroupWeekCapacity(String date) {
		if (date == null) {
			return new CyberResultInfo(-1, "暂无查询数据", null);
		}
		date = date.substring(0, 10);
		return weekWorkLoadService.getGroupWeekCapacity(date);
	}

	/**
	 * 获取组工作表现趋势接口
	 */
	@RequestMapping("/getGroupWorkAnalysis")
	@ResponseBody
	public CyberResultInfo getGroupWorkAnalysis(String start_date, String end_date, Integer post_id, String ids,
			Integer report_type) {
		// System.out.println(start_date + "--" + end_date + "--" + post_id +
		// "--" + names + "--" + report_type);
		if (start_date == null || end_date == null || post_id == null || ids == null || report_type == null) {
			return new CyberResultInfo(-1, "无效的输入参数！", null);
		}
		// System.out.println(post_id);
		return weekWorkLoadService.getGroupWorkAnalysis(start_date, end_date, post_id, ids, report_type);
	}

	/**
	 * 获取组信息
	 */
	@RequestMapping("/getGroupPostInfo")
	@ResponseBody
	public CyberResultInfo getGroupPostInfo() {
		return weekWorkLoadService.getGroupPostInfo();
	}

	/**
	 * 过滤组信息
	 */
	@RequestMapping("/FilterGroupPostInfo")
	@ResponseBody
	public CyberResultInfo FilterGroupPostInfo(String filterName) {
		return weekWorkLoadService.FilterGroupPostInfo(filterName);
	}
}
