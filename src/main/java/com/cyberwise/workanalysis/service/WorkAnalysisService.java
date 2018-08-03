package com.cyberwise.workanalysis.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cyberwise.workanalysis.bean.WorkCapacity2;
import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.common.GroupUser;
import com.cyberwise.workanalysis.common.PersonalPerformInProject;
import com.cyberwise.workanalysis.common.PersonalWorkAnalysis;
import com.cyberwise.workanalysis.common.PostInfo;
import com.cyberwise.workanalysis.common.User;
import com.cyberwise.workanalysis.common.UserPostInfo;
import com.cyberwise.workanalysis.mapper.WorkCapacity2Mapper;
import com.cyberwise.workanalysis.util.FormatUtil;

@Service
public class WorkAnalysisService {
	@Autowired
	private WorkCapacity2Mapper capacity2Mapper;
	@Value("${WEEKS_ANALYSIS}")
	private String WEEKS_ANALYSIS;
	// 作为筛选缓存
	private List<UserPostInfo> personList;
	private List<GroupUser> groups;

	// 从数据库查询周数据
	public CyberResultInfo getWeeksOfAnalysis() {
		List<WorkCapacity2> list = capacity2Mapper.findWeekData();
		List<String> weekDate = new ArrayList<>();
		for (WorkCapacity2 w : list) {
			weekDate.add(FormatUtil.formateDate(w.getStartDate()) + " ~ " + FormatUtil.formateDate(w.getEndDate()));
		}
		return CyberResultInfo.ok(weekDate);
	}

	// 模拟周数据
	public CyberResultInfo getWeeksOfAnalysis1() {
		List<String> weekDate = new ArrayList<>();
		// 当前周周一与周日
		LocalDate currentMonday = LocalDate.now().with(DayOfWeek.MONDAY);
		LocalDate currentSunday = currentMonday.plusDays(6);
		// 计算开始的周一
		LocalDate startMonday = LocalDate.parse(WEEKS_ANALYSIS);
		LocalDate startSunday = startMonday.plusDays(6);
		// 添加时间
		while (!currentMonday.equals(startMonday)) {
			weekDate.add(startMonday.toString() + " ~ " + startSunday.toString());
			startMonday = startMonday.plusWeeks(1);
			startSunday = startSunday.plusWeeks(1);
		}
		weekDate.add(currentMonday.toString() + " ~ " + currentSunday.toString());
		// weekDate.forEach(System.out::println);
		return CyberResultInfo.ok(weekDate);
	}

	// 获取周工作负载率
	public CyberResultInfo getWeekCapacity(String date) {
		// 获取工程ID与工程名字
		List<WorkCapacity2> list = capacity2Mapper.findAllProject(date);
		date = "'" + date + "'";
		// int k = 0;
		// 该查询中文不能有任何符号存在
		for (WorkCapacity2 l : list) {
			// l.setProjectName("'" + l.getProjectName() + "'");
			l.setProjectName(l.getProjectName().replace("(", "").replace(")", "").replace("[", "").replace("]", "")
					.replace("（", "").replace("）", "").replace("{", "").replace("}", ""));
			// k++;
			// l.setProjectName("呵呵呵" + k);
		}
		// 获取数据表
		StringBuilder sb = new StringBuilder("select a.realname");
		for (WorkCapacity2 l : list) {
			sb.append(",ifnull(").append(l.getProjectName()).append(",0)").append(l.getProjectName());
		}
		sb.append(" from(select distinct realname from cb_st_work_capacity2 where start_date =").append(date)
				.append(")a ");
		for (int i = 0; i < list.size(); i++) {
			sb.append(" left outer join( select realname,capacity as ").append(list.get(i).getProjectName())
					.append(" from cb_st_work_capacity2 where start_date = ").append(date).append(" and project= ")
					.append(list.get(i).getProject()).append(")").append("a" + i).append(" on a.realname = ")
					.append("a" + i + ".realname");
		}
		sb.append(" order by ");
		for (int i = 0; i < list.size(); i++) {
			if (i != list.size() - 1) {
				sb.append("ifnull(").append(list.get(i).getProjectName()).append(",0)+");
			} else {
				sb.append("ifnull(").append(list.get(i).getProjectName()).append(",0) ");
			}
		}
		sb.append(" desc");

		// System.out.println(sb.toString());
		// 查询负载率
		List<Map<String, Object>> list2;
		try {
			list2 = capacity2Mapper.findWeekCapacity(sb.toString());
		} catch (Exception e) {
			return new CyberResultInfo(-1, "暂无查询数据", null);
		}
		// System.out.println(list.size() + "--" + list2.size());

		Object ds_work_capacity_record[][] = new Object[list2.size() + 1][list.size() + 2];
		// 填充二维数组表头
		ds_work_capacity_record[0][0] = "姓名";
		for (int i = 0; i < list.size(); i++) {
			ds_work_capacity_record[0][i + 1] = list.get(i).getProjectName();
		}
		ds_work_capacity_record[0][list.size() + 1] = "合计";

		// 填充二维数组内容
		for (int i = 0; i < list2.size(); i++) {
			ds_work_capacity_record[i + 1][0] = list2.get(i).get("realname");
			for (int j = 0; j < list.size(); j++) {
				ds_work_capacity_record[i + 1][j + 1] = list2.get(i).get(list.get(j).getProjectName());
			}
		}
		// 合计
		for (int i = 1; i < list2.size() + 1; i++) {
			double total = 0;
			for (int j = 1; j < list.size() + 1; j++) {
				total += (Double) ds_work_capacity_record[i][j];
			}
			ds_work_capacity_record[i][list.size() + 1] = total;
		}

		// 优化double
		for (int x = 0; x < ds_work_capacity_record.length; x++) {
			for (int y = 0; y < ds_work_capacity_record[x].length; y++) {
				Object object = ds_work_capacity_record[x][y];
				if (object instanceof Double) {
					double d = (Double) object;
					ds_work_capacity_record[x][y] = FormatUtil.parseDouble(d);
				}
			}
		}

		return CyberResultInfo.ok(ds_work_capacity_record);
	}

	// 获取所有岗位分类接口
	public CyberResultInfo getOperatingPost(Integer post_id, String keyword) {
		// 入参为空,查询所有岗位
		if (post_id == null && keyword == null) {
			List<PostInfo> list = capacity2Mapper.findOperatingPost();
			return CyberResultInfo.ok(list);
		}
		return new CyberResultInfo(-1, "暂无岗位相关信息", null);
	}

	// 获取个人工作表现趋势接口
	public CyberResultInfo getPersonalWorkAnalysis(String startDate, String endDate, Integer positionId, String names,
			Integer chartType) {
		// 解析名字
		names = names.replace(",", "','");
		names = "'" + names + "'";
		// 处理positionId
		positionId -= 1;
		// 获取选中员工的数据
		List<PersonalWorkAnalysis> list = capacity2Mapper.findPersonalWorkAnalysis(startDate, endDate, names);
		// list中也要出去null的名字的数据,这句要放在添加参考线前面，不然参考线没名字也会去掉
		list = list.stream().filter(x -> x.getRealname() != null).collect(Collectors.toList());
		// 获取参考线的数据
		List<PersonalWorkAnalysis> lineList = capacity2Mapper.findAverageline(startDate, endDate, positionId);
		for (PersonalWorkAnalysis p : lineList) {
			list.add(p);
		}

		// 这里需要去数据库查询日期，前面的日期不靠谱，会有缺失
		List<PersonalWorkAnalysis> list2 = capacity2Mapper.findWeekOfPersonalWorkAnalysis(startDate, endDate);
		// 去除重复的周与重复的姓名
		List<Date> weekList = list2.stream().map(x -> x.getStartDate()).distinct().collect(Collectors.toList());
		// 姓名不能为null,null也算在名字里面(出了个bug)
		List<String> nameList = list.stream().map(x -> x.getRealname()).filter(x -> x != null).distinct()
				.collect(Collectors.toList());
		nameList.add("参考线");

		List<Object[][]> reportList = Arrays.asList(getReportArray(list, weekList, nameList, 1),
				getReportArray(list, weekList, nameList, 2), getReportArray(list, weekList, nameList, 3),
				getReportArray(list, weekList, nameList, 4));

		// Object[][] ds_personal_work_report = reportList.get(3);
		// for (int x = 0; x < ds_personal_work_report.length; x++) {
		// for (int y = 0; y < ds_personal_work_report[x].length; y++) {
		// System.out.print(ds_personal_work_report[x][y] + "\t");
		// }
		// System.out.println("");
		// }
		return CyberResultInfo.ok(reportList);
	}

	private Object[][] getReportArray(List<PersonalWorkAnalysis> list, List<Date> weekList, List<String> nameList,
			int type) {
		// nameList.forEach(System.out::println);
		Object ds_personal_work_report[][] = new Object[nameList.size() + 1][weekList.size() + 1];
		// 填充头
		ds_personal_work_report[0][0] = "姓名";
		for (int i = 1; i <= weekList.size(); i++) {
			ds_personal_work_report[0][i] = weekList.get(i - 1);
		}
		// 填充姓名
		for (int i = 1; i < ds_personal_work_report.length; i++) {
			ds_personal_work_report[i][0] = nameList.get(i - 1);
		}
		// 填充内容
		int count = 0;
		// PS这方法需要保证参考线都有数据(目前全有)，不然有可能抛出下标越界异常
		for (int i = 1; i < ds_personal_work_report.length; i++) {
			for (int j = 1; j <= weekList.size(); j++) {
				// 判断取出来的时间是否和头时间对等，不对等则表示没数据，填充0
				if (list.get(count).getStartDate().equals(ds_personal_work_report[0][j])) {
					if (type == 1) {
						ds_personal_work_report[i][j] = FormatUtil.parseDouble(list.get(count).getWeekCapacity());
					} else if (type == 2) {
						ds_personal_work_report[i][j] = FormatUtil
								.parseDouble(list.get(count).getConsumedWeekCapacity());
					} else if (type == 3) {
						ds_personal_work_report[i][j] = FormatUtil.parseDouble(list.get(count).getCpl());
					} else if (type == 4) {
						ds_personal_work_report[i][j] = FormatUtil.parseDouble(list.get(count).getPtt());
					}
					count++;
				} else {
					ds_personal_work_report[i][j] = 0.00;
				}
			}
		}
		// 改变头信息
		for (int i = 1; i <= weekList.size(); i++) {
			ds_personal_work_report[0][i] = "第" + i + "周";
		}
		return ds_personal_work_report;
	}

	// 获取员工岗位信息
	public CyberResultInfo getUserPostInfo() {
		personList = capacity2Mapper.findUserPostInfo();
		return CyberResultInfo.ok(personList);
	}

	// 过滤员工信息
	public CyberResultInfo FilterUserPostInfo(String filterName) {
		String name = filterName.trim();
		List<UserPostInfo> list = personList.stream().filter(x -> x.getRealname().contains(name))
				.collect(Collectors.toList());
		return CyberResultInfo.ok(list);
	}

	// 获取分项目工作量占比趋势数据接口
	public CyberResultInfo getPersonalWorkCapacityInProject(String startDate, String endDate, String names) {
		// 解析名字
		names = names.replace(",", "','");
		names = "'" + names + "'";
		// 获取个人项目信息
		List<PersonalPerformInProject> list = capacity2Mapper.findPersonalWorkCapacityInProject(startDate, endDate,
				names);
		list = list.stream().filter(x -> x.getRealname() != null).collect(Collectors.toList());
		// System.out.println(list.size());
		// 获取时间
		List<PersonalPerformInProject> weekList = capacity2Mapper.findWeekOfPersonalWorkCapacityInProject(startDate,
				endDate);

		List<Map<String, Object>> ds_person_work_capacity_in_project = new ArrayList<>();
		List<Integer> projectList = list.stream().map(x -> x.getProject()).distinct().collect(Collectors.toList());
		for (int i = 0; i < projectList.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			Integer projectId = projectList.get(i);
			map.put("project_id", projectId);
			map.put("project_name", list.stream().filter(x -> x.getProject() == projectId).map(x -> x.getProjectName())
					.findFirst().get());
			Object[][] capacityInProjectArray = getCapacityInProjectArray(
					list.stream().filter(x -> x.getProject() == projectId).collect(Collectors.toList()), weekList);
			map.put("records", capacityInProjectArray);
			ds_person_work_capacity_in_project.add(map);
		}
		return CyberResultInfo.ok(ds_person_work_capacity_in_project);
	}

	private Object[][] getCapacityInProjectArray(List<PersonalPerformInProject> list,
			List<PersonalPerformInProject> weekList) {
		List<String> nameList = list.stream().map(x -> x.getRealname()).filter(x -> x != null).distinct()
				.collect(Collectors.toList());
		Object ds_personal_work_report[][] = new Object[nameList.size() + 1][weekList.size() + 1];
		// 填充头
		ds_personal_work_report[0][0] = "姓名";
		for (int i = 1; i <= weekList.size(); i++) {
			ds_personal_work_report[0][i] = weekList.get(i - 1).getStatiTime();
		}
		// 填充姓名
		for (int i = 1; i < ds_personal_work_report.length; i++) {
			ds_personal_work_report[i][0] = nameList.get(i - 1);
		}
		// 填充内容
		int count = 0;
		for (int i = 1; i < ds_personal_work_report.length; i++) {
			for (int j = 1; j <= weekList.size(); j++) {
				if (list.get(count).getStatiTime().equals(ds_personal_work_report[0][j])) {
					ds_personal_work_report[i][j] = FormatUtil.parseDouble(list.get(count++).getPerProjectCapacity());
					// 有可能中间断了数据
					if (list.size() == count) {
						count--;
					}
				} else {
					ds_personal_work_report[i][j] = 0.00;
				}
			}
		}

		// 改变头信息
		for (int i = 1; i <= weekList.size(); i++) {
			ds_personal_work_report[0][i] = "第" + weekList.get(i - 1).getWeekNo() + "周";
		}

		// for (int x = 0; x < ds_personal_work_report.length; x++) {
		// for (int y = 0; y < ds_personal_work_report[x].length; y++) {
		// System.out.print(ds_personal_work_report[x][y] + "\t");
		// }
		// System.out.println("");
		// }
		return ds_personal_work_report;
	}

	// 获取组周工作负载率
	public CyberResultInfo getGroupWeekCapacity(String date) {
		// 获取个人数据
		Object ds_work_capacity_record[][] = (Object[][]) getWeekCapacity(date).getResult();
		List<GroupUser> lists = null;
		if (groups != null && !groups.isEmpty()) {
			lists = groups;
		} else {
			lists = capacity2Mapper.findGroups();
		}
		// 去除没有员工的组,不然显示会乱码
		lists = lists.stream().filter(x -> x.getUsers().size() > 0).collect(Collectors.toList());
		// System.out.println(groups);
		// 团队数据
		Object ds_group_work_capacity_record[][] = new Object[lists.size() + 1][ds_work_capacity_record[0].length];
		// 填充头
		for (int i = 0; i < ds_work_capacity_record[0].length; i++) {
			ds_group_work_capacity_record[0][i] = ds_work_capacity_record[0][i];
		}
		ds_group_work_capacity_record[0][0] = "组名";
		// 填充数据
		for (int x = 1; x < ds_group_work_capacity_record.length; x++) {
			ds_group_work_capacity_record[x][0] = lists.get(x - 1).getGroupName();
			for (int y = 1; y < ds_group_work_capacity_record[x].length; y++) {
				ds_group_work_capacity_record[x][y] = getGroupData(lists.get(x - 1).getUsers(), y,
						ds_work_capacity_record);
			}

		}

		// for (int x = 0; x < ds_group_work_capacity_record.length; x++) {
		// for (int y = 0; y < ds_group_work_capacity_record[x].length; y++) {
		// System.out.print(ds_group_work_capacity_record[x][y] + "\t");
		// }
		// System.out.println("");
		// }
		return CyberResultInfo.ok(ds_group_work_capacity_record);
	}

	// 获取组数据
	private String getGroupData(List<User> users, int j, Object[][] src) {
		double data = 0;
		for (User user : users) {
			for (int i = 1; i < src.length; i++) {
				if (user.getRealname().equals(src[i][0])) {
					// System.out.println(src[i][y].getClass().getName());
					// 格式化以后又成了string类型
					if (src[i][j] instanceof String) {
						data += Double.parseDouble((String) src[i][j]);
					} else if (src[i][j] instanceof Double) {
						data += (double) src[i][j];
					}
					break;
				}
			}
		}
		data /= users.size();
		return FormatUtil.formatDouble(data);
	}

	// 获取组信息
	public CyberResultInfo getGroupPostInfo() {
		groups = capacity2Mapper.findGroups();
		return CyberResultInfo.ok(groups);
	}

	// 过滤组信息
	public CyberResultInfo FilterGroupPostInfo(String filterName) {
		String name = filterName.trim();
		List<GroupUser> list = groups.stream().filter(x -> x.getGroupName().contains(name))
				.collect(Collectors.toList());
		return CyberResultInfo.ok(list);
	}

	// 获取组工作表现趋势接口
	public CyberResultInfo getGroupWorkAnalysis(String start_date, String end_date, Integer post_id, String ids,
			Integer report_type) {
		// groups = capacity2Mapper.findGroups();
		String[] idsStr = ids.split(",");
		List<GroupUser> lists = new ArrayList<>();
		// 过滤组
		for (String s : idsStr) {
			lists.add(groups.stream().filter(x -> x.getId() == Integer.parseInt(s)).findFirst().get());
		}
		// 获取组员
		List<String> namesList = new ArrayList<>();
		for (GroupUser g : lists) {
			for (User u : g.getUsers()) {
				namesList.add(u.getRealname());
			}
		}
		// 名字需要去重
		String names = namesList.stream().distinct().collect(Collectors.joining(","));
		// System.out.println(names);
		// System.out.println(lists);
		// 解析数据
		List<Object[][]> groupReportList = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<Object[][]> reportList = (List<Object[][]>) getPersonalWorkAnalysis(start_date, end_date, post_id, names,
				report_type).getResult();
		for (Object[][] objs : reportList) {
			// 团队数据
			Object groupRecord[][] = new Object[lists.size() + 2][objs[0].length];
			// 填充头
			for (int i = 0; i < objs[0].length; i++) {
				groupRecord[0][i] = objs[0][i];
			}
			// 填充数据,不填充参考线，所以长度要-1，不然会抛出异常
			for (int x = 1; x < groupRecord.length - 1; x++) {
				groupRecord[x][0] = lists.get(x - 1).getGroupName();
				for (int y = 1; y < groupRecord[x].length; y++) {
					groupRecord[x][y] = getGroupData(lists.get(x - 1).getUsers(), y, objs);
				}

			}
			// 填充参考线
			for (int i = 0; i < objs[0].length; i++) {
				groupRecord[lists.size() + 1][i] = objs[objs.length - 1][i];
			}
			groupReportList.add(groupRecord);
		}
		return CyberResultInfo.ok(groupReportList);
	}
}
