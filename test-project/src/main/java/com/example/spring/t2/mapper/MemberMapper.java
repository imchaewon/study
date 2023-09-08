package com.example.spring.t2.mapper;

import com.example.spring.t1.model.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {

//	@Select("SELECT * FROM STAFF")
//	List<Member> findAll();

	List<Map<String,Object>> selectAllMap();
	List<Member> selectAllDto();



}
