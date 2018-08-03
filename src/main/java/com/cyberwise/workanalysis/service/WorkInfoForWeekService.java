package com.cyberwise.workanalysis.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberwise.workanalysis.mapper.WorkInfoForWeekMapper;

@Service
public class WorkInfoForWeekService {
	@Autowired
	private WorkInfoForWeekMapper workInfo;
	private List<Map<Object, Object>> list;
	private List<Object> collect;

	public List<Map<Object, Object>> selectByExample(String str) {
		List<Date> dateList = new ArrayList<>();
		String[] arr = str.split(" ");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].indexOf("-") >= 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date data = new Date();
				try {
					data = sdf.parse(arr[i]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				dateList.add(data);
			}
		}

		Date beginDate = dateList.get(0);
		Date endDate = dateList.get(1);
		list = workInfo.findIndex(beginDate, endDate);
		// List<Object> collect =
		// list.stream().map(x->x.get("项目")).distinct().collect(Collectors.toList());
		// collect.forEach(System.out::println);
		list = sumWeekTime(list);
		return list;
	}

	// 获取指定周内所有项目名称
	public List<Object> findObject() {
		while (true) {
			if (list != null) {
				collect = list.stream().map(x -> x.get("项目")).distinct().collect(Collectors.toList());
				return collect;
			}

		}
	}

	// 获取指定周内所有项目名称
	// public List<Object> findName() {
	//
	// }
	// 计算汇总周工时
	public List<Map<Object,Object>> sumWeekTime(List<Map<Object,Object>> sList) {
		List<Object> nlist = new ArrayList<>();
		Object name;
		Double etime = 0.0;
		int t = 0;
		int i = 0;
		//获取集合中所有姓名(不重复的)
		nlist = sList.stream().map(x -> x.get("姓名")).distinct().collect(Collectors.toList());
		for (i = 0; i < sList.size(); i++) {// Map<Object,Object> map:list
			name = sList.get(i).get("姓名");
			Double time = Double.parseDouble(sList.get(i).get("总耗费工时").toString());
			if (nlist.get(t).equals(name)) {
				etime += time;
			} else {
					sList.get(i - 1).put("汇总周工时", etime);
				etime = (double) 0;
				etime += time;
				t++;
			}
		}
		//判断筛选后集合中是否有元素
		if(sList.size()>1) {
			sList.get(i - 1).put("汇总周工时", etime);
		}else if(sList.size()==1){
			sList.get(0).put("汇总周工时", etime);
		}
		return sList;
	}

	public List<Map<Object, Object>> findScreeningObject(String objectName, String names, boolean checkBox) {
		List<Map<Object, Object>> copyList = list;
		// List<Map<Object, Object>> list2 =
		/*Iterator<Map<Object, Object>> it = copyList.iterator();
		while (it.hasNext()) {
			Map<Object, Object> o = it.next();
			if (checkBox) {
				if ((Double.parseDouble(o.get("预计总工时").toString()) >= Double.parseDouble(o.get("总耗费工时").toString()))) {
					it.remove();
				}
			}
			
		}*/
		if(checkBox) {
			copyList = copyList.stream().filter(x->Double.parseDouble(x.get("预计总工时").toString()) < Double.parseDouble(x.get("总耗费工时").toString())).collect(Collectors.toList());
		}
		if(!objectName.equals("选择项目")) {
			copyList = copyList.stream().filter(x->x.get("项目").equals(objectName)).collect(Collectors.toList());
		}
		if(names!="") {
			copyList = copyList.stream().filter(x -> names.contains((String) x.get("姓名"))).collect(Collectors.toList());
		}
		
/*		List<Map<Object, Object>> copyList = list;
		
		while (it.hasNext()) {
			Map<Object, Object> o = it.next();
			if (objectName != null && objectName != "选择项目") {
				if (!o.get("项目").equals(objectName)) {
					it.remove();
					continue;
				}
			}
			if (names != null) {
				if (o.get("姓名").equals(objectName)) {
					it.remove();
					continue;
				}
			}
			if (checkBox) {
				if ((Double.parseDouble(o.get("预计总工时").toString()) < Double.parseDouble(o.get("总耗费工时").toString()))) {
					it.remove();
				}
			}
		}*/
		/*
		 * for(Map<Object,Object> m:copyList) { System.out.println(m); }
		 */
		
		return sumWeekTime(copyList);
	}

}
