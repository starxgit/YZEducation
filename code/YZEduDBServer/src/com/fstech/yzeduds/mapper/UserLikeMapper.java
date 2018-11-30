package com.fstech.yzeduds.mapper;

import java.util.List;

import com.fstech.yzeduds.model.ClassificationBean;
import com.fstech.yzeduds.model.LearnLike;

public interface UserLikeMapper {
	public List<ClassificationBean> LikesList(); 
	public void delMyLike(String user_id);
	public void addMyLike(LearnLike learnLike);
	public String mylike(String user_id);
	
}
