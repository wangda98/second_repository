package com.cyberwise.workanalysis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cyberwise.workanalysis.service.PersonSkillService;

/**
 * 测试dao层的工作
 * 
 * 推荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
 * 
 * 1、导入SpringTest模块
 * 
 * 2、@ContextConfiguration指定Spring配置文件的位置
 * 
 * 3、直接autowired要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class MapperTest {

	@Autowired
	private PersonSkillService skillService;

	@Test
	public void test1() {
		// weekWorkLoadService.getWeekCapacity("2017-09-11");
		// weekWorkLoadService.getWeeksOfAnalysis();
		// weekWorkLoadService.getPersonalWorkAnalysis("2017-09-16",
		// "2017-10-22", 7, "刘娟,吴莎,冯梦棋", 0);
		// weekWorkLoadService.getOperatingPost(null, null);
		// weekWorkLoadService.getUserPostInfo();
		// weekWorkLoadService.getPersonalWorkCapacityInProject("2017-07-30",
		// "2017-10-30", "应歧林,熊良凡");
		// weekWorkLoadService.getGroupWeekCapacity("2017-09-11");
		// weekWorkLoadService.getGroupWorkAnalysis("2017-09-16", "2017-10-22", 7, "1,2", 0);
		// List<String> list=work.findObject();
		// System.out.println(list.size());
		// manageService.getAllPostProfessional();
		// manageService.getSkillById(3);
		// CyberResultInfo info = manageService.deleteSkill(3);
		// System.out.println(info.getMsg());
		// manageService.copySkill(3);
		// manageService.updateSkillStatus(1, 2);
		// manageService.updateStatusAndUseTimes(2, 1, 3);
		// skillService.findPersonSkillById(1);
		// skillService.addPersonSkill("雷神平", 1);
		skillService.findPersonSkillByNameWithDate("雷神平", "2017-11-20", "2017-11-24");
	}
}
