package study.datajpa.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class TestTransactionalService {
	private final MemberRepository memberRepository;

//	@Transactional
	public void m1() {
//		m2();
//		throw new RuntimeException("");
		new C1().m1();
		throw new RuntimeException("");
	}

//	@Transactional
	public void m2() {
		memberRepository.save(new Member("xxx"));
	}

	class C1{
		public void m1(){
			memberRepository.save(new Member("xxx"));
		}
	}
}