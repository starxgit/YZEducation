package com.fstch.YZEduadmin.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fstch.YZEduadmin.mapper.SwordMapper;
import com.fstch.YZEduadmin.models.Sword;
@Repository
public class SwordDao implements SwordMapper {
	@Autowired
	SwordMapper swordMapper;
	@Override
	public List<Sword> findAll() {
		// TODO Auto-generated method stub
		return swordMapper.findAll();
	}
	@Override
	public Sword findById(int sensitive_word_id) {
		// TODO Auto-generated method stub
		return swordMapper.findById(sensitive_word_id);
	}
	@Override
	public void addSword(Sword sword) {
		// TODO Auto-generated method stub
		swordMapper.addSword(sword);
	}
	@Override
	public void delSword(int sensitive_word_id) {
		// TODO Auto-generated method stub
		swordMapper.delSword(sensitive_word_id);
	}
	@Override
	public void modify(Sword sword) {
		// TODO Auto-generated method stub
		swordMapper.modify(sword);
	}

}
