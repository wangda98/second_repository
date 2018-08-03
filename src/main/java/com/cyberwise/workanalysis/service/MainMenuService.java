package com.cyberwise.workanalysis.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyberwise.workanalysis.bean.MainMenu;
import com.cyberwise.workanalysis.bean.MainMenuExample;
import com.cyberwise.workanalysis.mapper.MainMenuMapper;
@Service
public class MainMenuService {
	@Resource
	private	 MainMenuMapper mm ;  
	MainMenuExample example=new MainMenuExample();
	public List<MainMenu> queryAll(){
		List<MainMenu> list = mm.selectByExample(example);
		return list;
	}
	public List<MainMenu> queryByParentId(Integer value) {
		example.createCriteria().andParentIdEqualTo(value);
		List<MainMenu> list = mm.selectByExample(example);
		return list;
	}
}
