package com.example.spring.t2;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class C3 {

	private final SqlSession sqlSession;

	public JSONObject m1(){
		List<Map<String,Object>> list = sqlSession.selectList("com.example.testProject.mapper.MemberMapper.selectAllMap");
		JSONObject obj = new JSONObject();
		obj.put("result",list);
		return obj;
	}

}
