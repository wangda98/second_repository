package com.cyberwise.workanalysis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberwise.workanalysis.bean.PersonSkill;
import com.cyberwise.workanalysis.bean.PersonSkillBigItem;
import com.cyberwise.workanalysis.bean.PersonSkillSmallItem;
import com.cyberwise.workanalysis.bean.Skill;
import com.cyberwise.workanalysis.bean.SkillBigItem;
import com.cyberwise.workanalysis.bean.SkillSmallItem;
import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.mapper.PersonSkillMapper;
import com.cyberwise.workanalysis.util.FormatUtil;

@Service
public class PersonSkillService {
	@Autowired
	private PersonSkillMapper skillMapper;
	@Autowired
	private SkillManageService manageService;
	private List<PersonSkill> personSkillList = new ArrayList<>();

	// 按名字岗位查询
	public List<PersonSkill> findPersonSkillByName(String name, Integer postId) {
		// 需要按updatetime排序
		List<PersonSkill> lists = personSkillList.stream().filter(x -> x.getName().equals(name))
				.filter(x -> x.getPostId() == postId).sorted((x, y) -> y.getUpdateTime().compareTo(x.getUpdateTime()))
				.collect(Collectors.toList());
		if (lists == null || lists.isEmpty()) {
			lists = skillMapper.findPersonSkillByNameAndPostId(name, postId);
			if (lists.size() > 0) {
				lists = fillPersonSkillList(lists);
				personSkillList.addAll(lists);
			}
		}
		// System.out.println(lists);
		return lists;
	}

	// 填充personSkillList信息
	private List<PersonSkill> fillPersonSkillList(List<PersonSkill> skills) {
		for (PersonSkill skill : skills) {
			fillSkill(skill);
		}
		return skills;
	}

	// 填充skill信息
	// private PersonSkill fillSkill(PersonSkill ps) {
	// Skill s = (Skill)
	// manageService.getSkillById(ps.getSkillId()).getResult();
	// // 填充大项
	// for (PersonSkillBigItem pb : ps.getPersonBigItems()) {
	// SkillBigItem bigItem = s.getBigItems().stream().filter(x -> x.getId() ==
	// pb.getBigId()).findFirst().get();
	// // 填充小项目
	// for (PersonSkillSmallItem pss : pb.getPersonSmallItems()) {
	// SkillSmallItem smallItem = bigItem.getSmallItems().stream().filter(x ->
	// x.getId() == pss.getSmallId())
	// .findFirst().get();
	// pss.setName(smallItem.getName());
	// pss.setScore(smallItem.getScore());
	// }
	// pb.setName(bigItem.getName());
	// pb.setType(bigItem.getType());
	// pb.setSkillId(bigItem.getSkillId());
	// pb.setSmallItemNum(bigItem.getSmallItemNum());
	// pb.setTotalScore(bigItem.getTotalScore());
	// Integer mytotal =
	// pb.getPersonSmallItems().stream().collect(Collectors.summingInt(x ->
	// x.getMyScore()));
	// pb.setMyTotalScore(mytotal);
	// }
	// ps.setVersion(s.getVersion());
	// ps.setCreatePerson(s.getCreatePerson());
	// ps.setUseTimes(s.getUseTimes());
	// ps.setStatus(s.getStatus());
	// ps.setPostId(s.getPostId());
	// ps.setSmallItemNum(s.getSmallItemNum());
	// ps.setBigItemNum(s.getBigItemNum());
	// ps.setTotalScore(s.getTotalScore());
	// ps.setPostName(s.getPostName());
	// Integer total =
	// ps.getPersonBigItems().stream().collect(Collectors.summingInt(x ->
	// x.getMyTotalScore()));
	// ps.setMyTotalScore(total);
	// return ps;
	// }

	// 填充skill信息
	private PersonSkill fillSkill(PersonSkill ps) {
		// 填充大项
		for (PersonSkillBigItem pb : ps.getPersonBigItems()) {
			pb.setMyTotalScore(pb.getPersonSmallItems().stream().collect(Collectors.summingInt(x -> x.getMyScore())));
			pb.setTotalScore(pb.getPersonSmallItems().stream().collect(Collectors.summingInt(x -> x.getScore())));
			pb.setSmallItemNum(pb.getPersonSmallItems().size());
		}
		ps.setMyTotalScore(ps.getPersonBigItems().stream().collect(Collectors.summingInt(x -> x.getMyTotalScore())));
		ps.setTotalScore(ps.getPersonBigItems().stream().collect(Collectors.summingInt(x -> x.getTotalScore())));
		ps.setSmallItemNum(ps.getPersonBigItems().stream().collect(Collectors.summingInt(x -> x.getSmallItemNum())));
		ps.setBigItemNum(ps.getPersonBigItems().size());
		return ps;
	}

	// 按ID查询
	public PersonSkill findPersonSkillById(Integer id) {
		PersonSkill skill;
		// 加个同步锁
		synchronized (this) {
			skill = personSkillList.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
			if (skill == null) {
				skill = skillMapper.findPersonSkillById(id);
				skill = fillSkill(skill);
				personSkillList.add(skill);
			}
		}
		// System.out.println(skill);
		return skill;
	}

	// 新增personskill
	public CyberResultInfo addPersonSkill(String name, Integer postId) {
		Integer skillId = skillMapper.findSkillIdByPostId(postId);
		// System.out.println(skillId);
		if (skillId == null) {
			return new CyberResultInfo(-1, "该岗位下暂无启用的评分表!", null);
		}
		Skill s = (Skill) manageService.getSkillById(skillId).getResult();
		addPersonSkill(s, name);
		skillMapper.updateUseTimes(s.getId(), s.getUseTimes() + 1);
		// System.out.println(s);
		// 移除缓存
		personSkillList.removeIf(x -> x.getName().equals(name));
		return CyberResultInfo.ok();
	}

	// 添加辅助类
	private void addPersonSkill(Skill s, String name) {
		PersonSkill p = new PersonSkill();
		p.setId(null);
		p.setCreateTime(new Date());
		p.setUpdateTime(new Date());
		p.setName(name);
		p.setSkillId(s.getId());
		skillMapper.addPersonSkill(p);
		for (SkillBigItem sb : s.getBigItems()) {
			PersonSkillBigItem pb = new PersonSkillBigItem();
			pb.setId(null);
			pb.setBigId(sb.getId());
			pb.setPersonId(p.getId());
			skillMapper.addPersonSkillBig(pb);
			for (SkillSmallItem ss : sb.getSmallItems()) {
				PersonSkillSmallItem ps = new PersonSkillSmallItem();
				ps.setId(null);
				ps.setMyScore(0);
				ps.setPbigId(pb.getId());
				ps.setSmallId(ss.getId());
				skillMapper.addPersonSkillSmall(ps);
			}
		}
	}

	// 加载统计表
	public CyberResultInfo loadTotal(Integer id) {
		PersonSkill personSkill = findPersonSkillById(id);
		Object[][] data = new Object[4][4];
		data[0][0] = "人际沟通";
		data[0][1] = personSkill.getPersonBigItems().stream().filter(x -> x.getType() == 1)
				.collect(Collectors.summingInt(x -> x.getSmallItemNum()));
		data[0][2] = personSkill.getPersonBigItems().stream().filter(x -> x.getType() == 1)
				.collect(Collectors.summingInt(x -> x.getMyTotalScore()));
		data[0][3] = personSkill.getPersonBigItems().stream().filter(x -> x.getType() == 1)
				.collect(Collectors.summingInt(x -> x.getTotalScore()));
		data[1][0] = "基础技能";
		data[1][1] = personSkill.getPersonBigItems().stream().filter(x -> x.getType() == 2)
				.collect(Collectors.summingInt(x -> x.getSmallItemNum()));
		data[1][2] = personSkill.getPersonBigItems().stream().filter(x -> x.getType() == 2)
				.collect(Collectors.summingInt(x -> x.getMyTotalScore()));
		data[1][3] = personSkill.getPersonBigItems().stream().filter(x -> x.getType() == 2)
				.collect(Collectors.summingInt(x -> x.getTotalScore()));
		data[2][0] = "高级技能";
		data[2][1] = personSkill.getPersonBigItems().stream().filter(x -> x.getType() == 3)
				.collect(Collectors.summingInt(x -> x.getSmallItemNum()));
		data[2][2] = personSkill.getPersonBigItems().stream().filter(x -> x.getType() == 3)
				.collect(Collectors.summingInt(x -> x.getMyTotalScore()));
		data[2][3] = personSkill.getPersonBigItems().stream().filter(x -> x.getType() == 3)
				.collect(Collectors.summingInt(x -> x.getTotalScore()));
		data[3][0] = "汇总";
		data[3][1] = personSkill.getPersonBigItems().stream().collect(Collectors.summingInt(x -> x.getSmallItemNum()));
		data[3][2] = personSkill.getPersonBigItems().stream().collect(Collectors.summingInt(x -> x.getMyTotalScore()));
		data[3][3] = personSkill.getPersonBigItems().stream().collect(Collectors.summingInt(x -> x.getTotalScore()));
		return new CyberResultInfo(1, personSkill.getName(), data);
	}

	// 加载大项表
	public CyberResultInfo loadBigTable(Integer id, Integer skillType) {
		PersonSkill personSkill = findPersonSkillById(id);
		List<PersonSkillBigItem> bigItems = personSkill.getPersonBigItems().stream()
				.filter(x -> x.getType() == skillType).collect(Collectors.toList());
		return CyberResultInfo.ok(bigItems);
	}

	// 加载小项表
	public CyberResultInfo loadSmallTable(Integer id, Integer pbigId) {
		PersonSkill personSkill = findPersonSkillById(id);
		List<PersonSkillSmallItem> smallItems = personSkill.getPersonBigItems().stream()
				.filter(x -> x.getId() == pbigId).findFirst().get().getPersonSmallItems();
		return CyberResultInfo.ok(smallItems);
	}

	// 修改分数
	public CyberResultInfo updateMyScore(Integer pskillId, Integer smallId, Integer myScore) {
		if (myScore == null)
			skillMapper.updateMyScore(smallId, 0);
		else
			skillMapper.updateMyScore(smallId, myScore);
		skillMapper.updateUpdateTime(pskillId, new Date());
		// 清除缓存
		personSkillList.removeIf(x -> x.getId() == pskillId);
		return CyberResultInfo.ok();
	}

	// 按按姓名与时间查询
	public CyberResultInfo findPersonSkillByNameWithDate(String name, String startTime, String endTime) {
		List<PersonSkill> list = skillMapper.findPersonSkillByNameWithDate(name, startTime, endTime);
		if (list.isEmpty()) {
			return new CyberResultInfo(-1, "该成员暂无评分信息!", null);
		}
		list = fillPersonSkillList(list);
		List<String> postNames = list.stream().map(x -> x.getPostName()).distinct().collect(Collectors.toList());
		List<Map<String, Object>> datas = new ArrayList<>();
		for (String n : postNames) {
			Map<String, Object> map = new HashMap<>();
			map.put("postName", n);
			map.put("data",
					fillDatas(list.stream().filter(x -> x.getPostName().equals(n)).collect(Collectors.toList())));
			datas.add(map);
		}
		return CyberResultInfo.ok(datas);
	}

	// 填充datas
	private Object[][] fillDatas(List<PersonSkill> list) {
		Object[][] data = new Object[2][list.size()];
		for (int i = 0; i < data[0].length; i++) {
			data[0][i] = FormatUtil.formateDate(list.get(i).getUpdateTime());
		}
		for (int i = 0; i < data[0].length; i++) {
			data[1][i] = list.get(i).getMyTotalScore();
		}
		return data;
	}

}
