package study.datajpa.test;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestSaveService {
	private final MemberRepository2 memberRepository;
	private final EntityManager em;

	public void m1() {
//		기존 데이터용 데이터 저장
		memberRepository.save(Member.builder().id(1L).name("aaa").age(123).build());
		em.clear();

//		테스트1 - 기존엔티티 조회 후 저장 → 기존 엔티티와 병합됨(merge됨. 안바꾼 컬럼은 이전 데이터 그대로 유지됨)
//		Member findMember = memberRepository.findById(1L).get();
//		findMember.name = "zzz";
//		memberRepository.save(findMember);

//		테스트2 - 새로운, 같은 ID의 엔티티 빌더로 생성 후 저장 → 기존 데이터가 통으로 덮어씌워짐
//		memberRepository.save(Member.builder().id(1L).name("zzz").build());

//		테스트3 - 새로운, 같은 ID의 엔티티 생성자로 생성 후 저장 → 기존 데이터가 통으로 덮어씌워짐
		memberRepository.save(new Member(1L, "zzz"));

	}

	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@Entity(name = "test_member")
	static class Member {
		@Id
		Long id;
		String name;
		int age;

		public Member(Long id, String name) {
			this.id = id;
			this.name = name;
		}
	}
}