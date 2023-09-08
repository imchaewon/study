//package com.example.testProject.test;
//
//import com.example.testProject.mapper.MemberMapper;
//import lombok.Setter;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class T1 {
//
//	@Setter(onMethod_ = {@Autowired})
//	protected SqlSession sqlSession;
//
//	@Setter(onMethod_ = {@Autowired})
//	private MemberMapper memberMapper;
//
//	@Test
//	public void m1(){
//		System.out.println("asddd");
////		System.out.println(memberMapper.selectAllDto());
//		System.out.println(sqlSession.selectList("com.example.testProject.mapper.MemberMapper.selectAllMap"));
//	}
//
//}
