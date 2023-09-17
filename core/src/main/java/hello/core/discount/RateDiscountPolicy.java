package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy{
	private int discountPercent = 15;
	@Override
	public int discount(Member member, int price) {
		if(member.getGrade() == Grade.VIP)
			return price / 100 * discountPercent;
		return 0;
	}
}
