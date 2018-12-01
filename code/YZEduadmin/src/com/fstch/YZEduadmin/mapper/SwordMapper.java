package com.fstch.YZEduadmin.mapper;

import java.util.List;

import com.fstch.YZEduadmin.models.Sword;

public interface SwordMapper {
	List<Sword> findAll();
	Sword findById(int sensitive_word_id);
	void addSword(Sword sword);
	void delSword(int sensitive_word_id);
	void modify(Sword sword);
}
