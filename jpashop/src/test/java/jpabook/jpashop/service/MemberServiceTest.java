package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class MemberServiceTest {

	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	EntityManager em;

	@Test
	void 회원가입() throws Exception{
		//given(어떤 환경이 주어졌을때)
		Member member = new Member();
		member.setName("kim");

		//when(어떻게 하면)
		Long saveId = memberService.join(member);

		//then(이렇게 되어야 한다)
		Assertions.assertThat(member).isEqualTo(memberRepository.findById(saveId).get());

	}

	@Test
	void 중복_회원_예외() throws Exception{
		//given(어떤 환경이 주어졌을때)
		Member member1 = new Member();
		member1.setName("kim");

		Member member2 = new Member();
		member2.setName("kim");
	
		//when(어떻게 하면)
		memberService.join(member1);
		try {
			memberService.join(member2); // 예외가 발생해야함
		} catch (IllegalStateException e) {
			return;
		}
	
		//then(이렇게 되어야 한다)
		Assertions.fail("예외가 발생해야한다.");
	}

}










