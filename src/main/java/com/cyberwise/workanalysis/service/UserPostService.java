package com.cyberwise.workanalysis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberwise.workanalysis.bean.Post;
import com.cyberwise.workanalysis.bean.PostProfessional;
import com.cyberwise.workanalysis.bean.PostUser;
import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.common.User;
import com.cyberwise.workanalysis.mapper.UserPostMapper;

@Service
public class UserPostService {
	@Autowired
	private UserPostMapper postMapper;

	// 按tpye查询岗位信息
	public List<PostProfessional> findPostProfessional(Integer type) {
		return postMapper.findPostProfessionalByType(type);
	}

	// 增加岗位信息
	public CyberResultInfo addPostProfessional(PostProfessional p) {
		p.setId(null);
		postMapper.addPostProfessional(p);
		return CyberResultInfo.ok();
	}

	// 修改岗位信息
	public CyberResultInfo updatePostProfessional(PostProfessional p) {
		postMapper.updatePostProfessional(p);
		return CyberResultInfo.ok();

	}

	// 删除岗位信息
	public CyberResultInfo deletePostProfessional(Integer id) {
		Integer count = postMapper.findPostCountByProid(id);
		if (count > 0) {
			return new CyberResultInfo(-1, "请先删除该岗位下的子类!", null);
		}
		postMapper.deletePostProfessional(id);
		return CyberResultInfo.ok();
	}

	// 根据proid查询post
	public List<Post> findPost(Integer proid) {
		return postMapper.findPostByProfessionalId(proid);
	}

	// 添加post
	public CyberResultInfo addPost(Post post) {
		// System.out.println(post);
		Integer count = postMapper.findPostNameCount(post.getProfessionalId(), post.getPostName());
		if (count > 0) {
			return new CyberResultInfo(-1, "当前岗位下已经存在" + post.getPostName() + "了!", null);
		}
		// 这儿也可以用postLevel=3代替PostName，防止写死,不过前台就得传个postlevel过来
		if (post.getPostName().equals("工程师")) {
			Integer parentId = postMapper.findPostIdByProId(post.getProfessionalId());
			if (parentId == null) {
				return new CyberResultInfo(-1, "请先添加当前岗位的组长!", null);
			}
			post.setParentPostId(parentId);
			post.setPostLevel(3);
		} else {
			String name = getPostName(post.getPostType());
			Integer parentId = postMapper.findPostIdByName(name);
			post.setParentPostId(parentId);
			post.setPostLevel(2);
		}
		post.setId(null);
		post.setRemoveFlag(0);
		post.setDisableForStati(0);
		postMapper.addPost(post);
		return CyberResultInfo.ok();
	}

	private String getPostName(Integer type) {
		String name = null;
		switch (type) {
		case 1:
			name = "开发";
			break;
		case 2:
			name = "产品";
			break;
		case 3:
			name = "测试";
			break;
		case 4:
			name = "项目";
			break;

		}
		return name;
	}

	// 更新post表
	public CyberResultInfo updatePost(Integer stati, Integer id) {
		postMapper.updatePost(stati, id);
		return CyberResultInfo.ok();
	}

	// 删除post
	public CyberResultInfo deletePost(Integer id) {
		postMapper.deletePost(id);
		return CyberResultInfo.ok();
	}

	// 查询所有没被分配岗位的user
	public List<User> findUserList() {
		return postMapper.findUserList();
	}

	// 查询所有用户与岗位信息
	public List<PostUser> findPostUserList() {
		return postMapper.findPostUserList();
	}

	// 按分类查询岗位信息
	public List<PostUser> findAllPostNameByType(Integer type) {
		return postMapper.findAllPostNameByType(type);
	}

	// 添加用户与岗位信息
	public CyberResultInfo addPostUser(PostUser postUser) {
		postUser.setId(null);
		postUser.setJoinToStati(0);
		postMapper.addPostUser(postUser);
		return CyberResultInfo.ok();
	}

	// 修改用户与岗位信息
	public CyberResultInfo updatePostUser(PostUser postUser) {
		postMapper.updatePostUser(postUser);
		return CyberResultInfo.ok();
	}

	// 修改用户岗位的岗位信息
	public CyberResultInfo updatePostUserPost(PostUser postUser) {
		postMapper.updatePostUserPost(postUser);
		return CyberResultInfo.ok();
	}

	// 修改用户岗位的均线信息
	public CyberResultInfo updatePostUserStati(PostUser postUser) {
		postMapper.updatePostUserStati(postUser);
		return CyberResultInfo.ok();
	}

	// 删除用户与岗位信息
	public CyberResultInfo deletePostUser(Integer id) {
		postMapper.deletePostUser(id);
		return CyberResultInfo.ok();
	}
}
