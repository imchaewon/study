package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
	MemberService memberService;
	MemoryMemberRepository memberRepository;

	@BeforeEach
	void beforeEach(){
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@AfterEach
	void afterEach(){
		memberRepository.clearStore();
	}


	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("aaa");

		//when
		Long saveId = memberService.join(member);

		//then
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertEquals(member.getName(), findMember.getName());
	}

	@Test
	void 중복회원예외(){
		//given
		Member member = new Member();
		member.setName("aaa");

		Member member2 = new Member();
		member2.setName("aaa");

		//when
		memberService.join(member);
		IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		Assertions.assertEquals("이미 존재하는 회원입니다", illegalStateException.getMessage());
//		try {
//			memberService.join(member2);
//			Assertions.fail("예외가 발생해야 합니다");
//		} catch (Exception e) {
//			Assertions.assertEquals(e.getMessage(), "이미 존재하는 회원입니다");
//		}
	}

	@Test
	void findMembers() {
	}

	@Test
	void findOne() {
	}
}