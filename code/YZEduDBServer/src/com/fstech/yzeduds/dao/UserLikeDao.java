package com.fstech.yzeduds.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstech.yzeduds.mapper.UserLikeMapper;
import com.fstech.yzeduds.model.ClassificationBean;
import com.fstech.yzeduds.model.LearnLike;

@Repository
public class UserLikeDao implements UserLikeMapper{
	@Autowired
	UserLikeMapper userLikeMapper;

	@Override
	public List<ClassificationBean> LikesList() {
		return userLikeMapper.LikesList();
	}

	@Override
	public void delMyLike(String user_id) {
		userLikeMapper.delMyLike(user_id);
	}

	@Override
	public void addMyLike(LearnLike learnLike) {
		userLikeMapper.addMyLike(learnLike);
	}

	@Override
	public String mylike(String user_id) {
		return userLikeMapper.mylike(user_id);
	}
	
}
