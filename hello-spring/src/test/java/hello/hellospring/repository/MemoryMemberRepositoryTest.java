package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {
	MemoryMemberRepository repository = new MemoryMemberRepository();

	@AfterEach
	public void afterEach(){
		repository.clearStore();
	}
	@Test
	public void save(){
		Member member = new Member();
		member.setName("spring");

		repository.save(member);

		Member result = repository.findById(member.getId()).get();
		System.out.println(result);
		System.out.println(member == result);
		System.out.println(member.equals(result));
		Assertions.assertEquals(member, result);
		org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
	}

	@Test
	public void findByName(){
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);

		Assertions.assertEquals(member2, repository.findByName("spring2").get());
	}

	@Test
	public void findAll(){
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);

		Assertions.assertEquals(2, repository.findAll().size());
//		Assertions.assertEquals(3, repository.findAll().size());
	}
}















