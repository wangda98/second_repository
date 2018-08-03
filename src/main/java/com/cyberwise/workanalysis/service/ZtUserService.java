package com.cyberwise.workanalysis.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cyberwise.workanalysis.bean.ZtUser;
import com.cyberwise.workanalysis.bean.ZtUserExample;
import com.cyberwise.workanalysis.mapper.ZtUserMapper;
@Service
public class ZtUserService {
	@Resource
	private	 ZtUserMapper zm;
	public Integer  queryByName(String name){
		Integer i = zm.querybyName(name);
		System.out.println("list 集合为"+i);
		return i;
	}
}
