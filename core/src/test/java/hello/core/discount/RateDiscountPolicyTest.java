package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
	RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
	AppConfig appConfig = new AppConfig();
	MemberService memberService = appConfig.memberService();
	OrderService orderService = appConfig.orderService();

	@Test
	@DisplayName("VIP는 10% 할인이 적용되어야 한다")
	void vip_o() {
		//given(어떤 환경이 주어졌을때)
		Member member = new Member(1L, "mem1", Grade.VIP);
		memberService.join(member);

		//when(어떻게 하면)
		int discount = discountPolicy.discount(member, 10000);

		//then(이렇게 되어야 한다)
		assertThat(discount).isEqualTo(1500);
	}

	@Test
	@DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
	void vip_x() {
		//given(어떤 환경이 주어졌을때)
		Member member = new Member(1L, "mem1", Grade.BASIC);
		memberService.join(member);

		//when(어떻게 하면)
		int discount = discountPolicy.discount(member, 10000);

		//then(이렇게 되어야 한다)
		assertThat(discount).isEqualTo(0);
	}
}