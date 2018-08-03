package com.cyberwise.workanalysis.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cyberwise.workanalysis.bean.AllowedUser;
import com.cyberwise.workanalysis.bean.AllowedUserExample;
import com.cyberwise.workanalysis.bean.Group;
import com.cyberwise.workanalysis.bean.GroupAndUser;
import com.cyberwise.workanalysis.bean.GroupAndUserExample;
import com.cyberwise.workanalysis.bean.GroupAndUserValue;
import com.cyberwise.workanalysis.bean.GroupExample;
import com.cyberwise.workanalysis.bean.ZtUser;
import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.mapper.AllowedUserMapper;
import com.cyberwise.workanalysis.mapper.GroupAndUserMapper;
import com.cyberwise.workanalysis.mapper.GroupMapper;
@Service
public class GroupAndUserService {
	@Resource
	private	 GroupAndUserMapper gum;
	public List<GroupAndUserValue> queryAllMember( ) {
		List<GroupAndUserValue> list =gum.queryAllMember();
		return list;
	}
	public List<GroupAndUserValue> queryByGroupId(Integer id ) {
		List<GroupAndUserValue> list =gum.queryByGroupId(id);
		return list;
	}
	public List<ZtUser> likequery(String s2 ) {
		List<ZtUser> list =gum.likequery(s2);
		return list;
	}
	 
	public int  insert(Integer s1,Integer s2,Integer s3,Date date) {
		GroupAndUser  g=new GroupAndUser();
		 g.setCreateTime(date);
		 g.setGroupId(s1);
		 g.setIsLeader(s3);
		 g.setRemovedFlag(0);
		 g.setUserId(s2);
		 return	 gum.insert(g);
	}
	public int  delete(Integer id) {
		 return	 gum.deleteByPrimaryKey(id);
	}
	public int  deleteByGroupId(Integer groupId) {
		GroupAndUserExample example=new GroupAndUserExample();
		example.createCriteria().andGroupIdEqualTo(groupId);
		return gum.deleteByExample(example);
	}
	public int  update(Integer s1,Integer s2,Integer s3,Integer s4,Integer id,String date,String isChange) throws ParseException{
		 GroupAndUser g=new GroupAndUser();
		 if (date!=null&&!date.isEmpty()) {
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date leaveTime= formatter.parse(date); //time为你前台得到的值
			g.setLeaveTime(leaveTime);
		}
		 //如果isChange的值改变了就说明改变了项目组,此时需要更新加入时间
		 if("false".equals(isChange)){
			 Date createTime=new Date();
			 g.setCreateTime(createTime);
		 }
		 g.setGroupId(s1);
		 g.setUserId(s2);
		 g.setIsLeader(s3);
		 g.setRemovedFlag(s4);
		 GroupAndUserExample example=new GroupAndUserExample();
		 example.createCriteria().andIdEqualTo(id);
	int n =	gum.updateByExampleSelective(g, example);
		return	 n;
	}
}
