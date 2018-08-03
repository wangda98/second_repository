package com.cyberwise.workanalysis.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyberwise.workanalysis.bean.AllowedUser;
import com.cyberwise.workanalysis.bean.AllowedUserExample;
import com.cyberwise.workanalysis.bean.Group;
import com.cyberwise.workanalysis.bean.GroupExample;
import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.mapper.AllowedUserMapper;
import com.cyberwise.workanalysis.mapper.GroupMapper;
@Service
public class GroupService {
	@Resource
	private	 GroupMapper gm;  
	public List<Group> queryAll( ) {
		GroupExample example=new GroupExample();
		List<Group> list =gm.queryAll();
		return list;
	}
	public int  insert(String s1,Integer s2){
		Group group =new Group();
		group.setCreatedTime(new Date());
		group.setLastMofidiedTime(new Date());
		group.setGroupName(s1);
		group.setGroupType(s2);
		group.setRemovedFlag(0);
	return	gm.insert(group);
	}
	public int  update(String s1,Integer s2,Integer s3,Integer id){
		Group group =new Group();
		GroupExample example=new GroupExample();
		example.createCriteria().andIdEqualTo(id);
		group.setLastMofidiedTime(new Date());
		group.setGroupName(s1);
		group.setGroupType(s2);
		group.setRemovedFlag(s3);
	int n =	gm.updateByExampleSelective(group, example);
		return	 n;
	}
	public int  delete(Integer s3){
	return	gm.deleteByPrimaryKey(s3);
	}
}
