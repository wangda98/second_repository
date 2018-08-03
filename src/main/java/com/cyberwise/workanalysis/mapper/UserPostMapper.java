package com.cyberwise.workanalysis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyberwise.workanalysis.bean.Post;
import com.cyberwise.workanalysis.bean.PostProfessional;
import com.cyberwise.workanalysis.bean.PostUser;
import com.cyberwise.workanalysis.common.User;

public interface UserPostMapper {
	List<PostProfessional> findPostProfessionalByType(Integer type);

	void addPostProfessional(PostProfessional p);

	void updatePostProfessional(PostProfessional p);

	void deletePostProfessional(Integer id);

	Integer findPostCountByProid(Integer proid);

	List<Post> findPostByProfessionalId(Integer proid);

	Integer findPostIdByProId(Integer proid);

	Integer findPostIdByName(String name);

	Integer findPostNameCount(@Param("proid") Integer proid, @Param("postName") String postName);

	void addPost(Post post);

	void updatePost(@Param("stati") Integer stati, @Param("id") Integer id);

	void deletePost(Integer id);

	List<User> findUserList();

	List<PostUser> findPostUserList();

	List<PostUser> findAllPostNameByType(Integer type);

	void addPostUser(PostUser postUser);

	void updatePostUser(PostUser postUser);

	void updatePostUserPost(PostUser postUser);

	void updatePostUserStati(PostUser postUser);

	void deletePostUser(Integer id);

}
