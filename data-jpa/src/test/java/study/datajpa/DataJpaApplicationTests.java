package study.datajpa;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

@SpringBootTest
@Transactional
class DataJpaApplicationTests {
	@Autowired private EntityManager em;

	@Test
	@Rollback(false)
	void sequencetest(){
		Member mem = new Member();
		System.out.println("mem id-1 = " + mem.getId());
		em.persist(mem);
		System.out.println("mem id-2 = " + mem.getId());
//		Member member = em.find(Member.class, 1);
//		System.out.println("member = " + member.getId());
	}

}
