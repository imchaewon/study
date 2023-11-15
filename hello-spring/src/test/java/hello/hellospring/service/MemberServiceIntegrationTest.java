package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("spring321");

		//when
		Long saveId = memberService.join(member);
//		System.out.println(saveId);
//		System.out.println(memberService.findMembers());

		//then
		Member findMember = memberService.findOne(saveId).get();
//		System.out.println(member);
//		System.out.println(findMember);
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
		memberService.findMembers();
	}

	@Test
	void findOne() {
		Member member = new Member();
		member.setName("aaaaa");
		Long saveId = memberService.join(member);
		System.out.println(saveId);
		System.out.println(memberService.findOne(saveId));
	}

	@Test
	void m1() {
		memberService.m1();
		memberService.m2(null);
	}
}