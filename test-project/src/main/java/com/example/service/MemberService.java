package com.example.service;

import com.example.model.mybatis.Member;
import com.example.repository.mybatis.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

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
