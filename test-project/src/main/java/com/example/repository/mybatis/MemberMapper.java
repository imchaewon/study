package com.example.repository.mybatis;

import com.example.model.mybatis.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MemberMapper {
//	@Select("SELECT * FROM STAFF")
//	List<Member> findAll();

	List<Map<String,Object>> selectAllMap();
	List<Member> selectAllDto();
}
