package com.example.spring.t1.service;

import com.example.spring.t2.mapper.MemberMapper;
import com.example.spring.t1.model.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService{

	@Autowired
	protected SqlSession sqlSession;

	private final MemberMapper memberMapper;

//	public List<Map<String, Object>> getAllUsersMap() {
//		return memberMapper.selectAllMap();
//	}

	public List<Map<String, Object>> getAllUsersMap() {
		return sqlSession.selectList("com.example.testProject.mapper.MemberMapper.selectAllMap");
	}

	public List<Map<String, Object>> getAllUsersMap(Map<String,Object> map) {
		return sqlSession.selectList("com.example.testProject.mapper.MemberMapper.selectCriteria", map);
	}

	public List<Member> getAllUsersDto() {
		return memberMapper.selectAllDto();
	}


}
