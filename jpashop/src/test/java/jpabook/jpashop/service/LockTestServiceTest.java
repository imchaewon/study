package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.LockTestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@Rollback(false) // 롤백 방지 설정
class LockTestServiceTest {

	@Autowired
	LockTestRepository lockTestRepository;

	@Autowired
	EntityManager em;

	@Test
	@Transactional
	void testLocking() {
		// 첫 번째 트랜잭션
		Member member = new Member();
		member.setName("testUser1");
		member.setAge(20);
		lockTestRepository.save(member);

		// 두 번째 트랜잭션
		Optional<Member> lockedMemberOptional = lockTestRepository.findByName("testUser1");

		assertThat(lockedMemberOptional).isPresent();
		Member lockedMember = lockedMemberOptional.get();

		// 여기서 lockedMember의 데이터를 읽어올 때는 이미 첫 번째 트랜잭션에서 락이 걸려있음
		// 따라서 첫 번째 트랜잭션이 커밋되기 전까지는 두 번째 트랜잭션에서는 락이 풀리지 않음
		// 따라서 두 번째 트랜잭션에서는 해당 회원의 데이터를 읽을 수 없음
		assertThat(lockedMember.getAge()).isEqualTo(20);
	}

	@Test
	@Transactional
	void testUpdate() {
		// 첫 번째 트랜잭션
		Member member = new Member();
		member.setName("testUser1");
		member.setAge(20);
		lockTestRepository.save(member);

		// 두 번째 트랜잭션
		lockTestRepository.updateMember("testUser1", 30);

		em.clear();

		// 첫 번째 트랜잭션이 커밋되면서 락이 풀리고 두 번째 트랜잭션에서 업데이트가 가능
		// 단, 두 번째 트랜잭션에서 updateMember를 호출하는 시점에 이미 첫 번째 트랜잭션이 커밋되었다면
		// 두 번째 트랜잭션에서는 더 이상 락이 걸리지 않음
		Optional<Member> updatedMemberOptional = lockTestRepository.findByName("testUser1");
		assertThat(updatedMemberOptional).isPresent();
		assertThat(updatedMemberOptional.get().getAge()).isEqualTo(30);
	}

	@Test
	@Transactional
	void testNonLocking() {
		// 첫 번째 트랜잭션
		Member member = new Member();
		member.setName("testUser2");
		member.setAge(25);
		lockTestRepository.save(member);

		// 두 번째 트랜잭션 (락 없음)
		Optional<Member> nonLockedMemberOptional = lockTestRepository.findById("testUser2");

		assertThat(nonLockedMemberOptional).isPresent();
		Member nonLockedMember = nonLockedMemberOptional.get();

		// 락이 걸리지 않았기 때문에 데이터를 읽을 수 있음
		assertThat(nonLockedMember.getAge()).isEqualTo(25);
	}
}