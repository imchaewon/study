package hello.core.xml;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContext {

	@Test
	void xmlAppContext(){
		ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
		Assertions.assertThat(ac.getBean("discountPolicy", DiscountPolicy.class)).isInstanceOf(FixDiscountPolicy.class);
//		System.out.println(ac.getBeansOfType(MemberService.class));
//		System.out.println(ac.getBeansOfType(MemberRepository.class));
//		Assertions.assertThat(ac.getBean("dis", DiscountPolicy.class)).isInstanceOf(FixDiscountPolicy.class);
//		Assertions.assertThat(ac.getBean("discountPolicy", DiscountPolicy.class)).isInstanceOf(RateDiscountPolicy.class);
	}
}
