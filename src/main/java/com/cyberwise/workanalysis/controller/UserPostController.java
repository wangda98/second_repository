package com.cyberwise.workanalysis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyberwise.workanalysis.bean.Post;
import com.cyberwise.workanalysis.bean.PostProfessional;
import com.cyberwise.workanalysis.bean.PostUser;
import com.cyberwise.workanalysis.common.CyberResultInfo;
import com.cyberwise.workanalysis.common.User;
import com.cyberwise.workanalysis.service.UserPostService;

/**
 * 用户岗位接口
 */
@Controller
@RequestMapping("/userpost")
public class UserPostController {
	@Autowired
	private UserPostService postService;

	// 按tpye查询岗位信息
	@RequestMapping(value = "/findPostProfessional")
	@ResponseBody
	public CyberResultInfo findPostProfessional(Integer type) {
		List<PostProfessional> list = postService.findPostProfessional(type);
		return CyberResultInfo.ok(list);
	}

	// 增加岗位信息
	@RequestMapping(value = "/addPostProfessional")
	@ResponseBody
	public CyberResultInfo addPostProfessional(PostProfessional p) {
		return postService.addPostProfessional(p);
	}

	// 修改岗位信息
	@RequestMapping(value = "/updatePostProfessional")
	@ResponseBody
	public CyberResultInfo updatePostProfessional(PostProfessional p) {
		return postService.updatePostProfessional(p);

	}

	// 删除岗位信息
	@RequestMapping(value = "/deletePostProfessional")
	@ResponseBody
	public CyberResultInfo deletePostProfessional(Integer id) {
		return postService.deletePostProfessional(id);
	}

	// 根据proid查询post
	@RequestMapping(value = "/findPost")
	@ResponseBody
	public CyberResultInfo findPost(Integer proid) {
		List<Post> list = postService.findPost(proid);
		return CyberResultInfo.ok(list);
	}

	// 添加post
	@RequestMapping(value = "/addPost")
	@ResponseBody
	public CyberResultInfo addPost(Post post) {
		return postService.addPost(post);
	}

	// 更新post表
	@RequestMapping(value = "/updatePost")
	@ResponseBody
	public CyberResultInfo updatePost(Integer stati, Integer id) {
		return postService.updatePost(stati, id);
	}

	// 删除post
	@RequestMapping(value = "/deletePost")
	@ResponseBody
	public CyberResultInfo deletePost(Integer id) {
		return postService.deletePost(id);
	}

	// 查询所有没被分配岗位的user
	@RequestMapping(value = "/findUserList")
	@ResponseBody
	public CyberResultInfo findUserList() {
		List<User> list = postService.findUserList();
		return CyberResultInfo.ok(list);
	}

	// 查询所有用户与岗位信息
	@RequestMapping(value = "/findPostUserList")
	@ResponseBody
	public CyberResultInfo findPostUserList() {
		List<PostUser> list = postService.findPostUserList();
		return CyberResultInfo.ok(list);
	}

	// 按分类查询岗位信息
	@RequestMapping(value = "/findPostName")
	@ResponseBody
	public CyberResultInfo findAllPostNameByType(Integer type) {
		List<PostUser> list = postService.findAllPostNameByType(type);
		return CyberResultInfo.ok(list);
	}

	// 添加用户与岗位信息
	@RequestMapping(value = "/addPostUser")
	@ResponseBody
	public CyberResultInfo addPostUser(PostUser postUser) {
		return postService.addPostUser(postUser);
	}

	// 修改用户与岗位信息
	@RequestMapping(value = "/updatePostUser")
	@ResponseBody
	public CyberResultInfo updatePostUser(PostUser postUser) {
		return postService.updatePostUser(postUser);
	}

	// 修改用户岗位的岗位信息
	@RequestMapping(value = "/updatePostUserPost")
	@ResponseBody
	public CyberResultInfo updatePostUserPost(PostUser postUser) {
		return postService.updatePostUserPost(postUser);
	}

	// 修改用户岗位的均线信息
	@RequestMapping(value = "/updatePostUserStati")
	@ResponseBody
	public CyberResultInfo updatePostUserStati(PostUser postUser) {
		return postService.updatePostUserStati(postUser);
	}

	// 删除用户与岗位信息
	@RequestMapping(value = "/deletePostUser")
	@ResponseBody
	public CyberResultInfo deletePostUser(Integer id) {
		return postService.deletePostUser(id);
	}
}
