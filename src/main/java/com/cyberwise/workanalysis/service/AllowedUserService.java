package com.cyberwise.workanalysis.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyberwise.workanalysis.bean.AllowedUser;
import com.cyberwise.workanalysis.bean.AllowedUserExample;
import com.cyberwise.workanalysis.mapper.AllowedUserMapper;
@Service
public class AllowedUserService {
	@Resource
	private	AllowedUserMapper aum;  
	public String findAllLoad(String value) {
		AllowedUserExample example=new AllowedUserExample();
		example.createCriteria().andUsernameEqualTo(value);
		List<AllowedUser> list = aum.selectByExample(example);
		if (list!=null&&!list.isEmpty()) {
			AllowedUser aUser=list.get(0);
			return aUser.getRemoveFlag().toString();
		}
		return null;
		
	}
}
