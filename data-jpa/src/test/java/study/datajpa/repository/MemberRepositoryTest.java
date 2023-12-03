package study.datajpa.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {
	@Autowired MemberRepository memberRepository;
	@Autowired TeamRepository teamRepository;

	@Autowired EntityManager em;

	@Test
	public void testMember() {
		Member member = new Member("memberA");
		Member savedMember = memberRepository.save(member);

		Member findMember = memberRepository.findById(savedMember.getId()).get();

		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		assertThat(findMember).isEqualTo(member);
	}

	@Test
	public void basicCRUD() {
		Member member1 = new Member("member1");
		Member member2 = new Member("member2");
		memberRepository.save(member1);
		memberRepository.save(member2);

		// 단건 조회 검증
		Member findMember1 = memberRepository.findById(member1.getId()).get();
		Member findMember2 = memberRepository.findById(member2.getId()).get();
		assertThat(findMember1).isEqualTo(member1);
		assertThat(findMember2).isEqualTo(member2);

//		findMember1.setUsername("member!!!!!");

		// 리스트 조회 검증
		List<Member> all = memberRepository.findAll();
		assertThat(all.size()).isEqualTo(2);

		// 카운트 검증
		long count = memberRepository.count();
		assertThat(count).isEqualTo(2);

		// 삭제 검증
		memberRepository.delete(member1);
		memberRepository.delete(member2);

		long deleteCount = memberRepository.count();
		assertThat(deleteCount).isEqualTo(0);

	}

	@Test
	public void findByUsernameAndAgeGreaterThen() {
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("AAA", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

		assertThat(result.get(0).getUsername()).isEqualTo("AAA");
		assertThat(result.get(0).getAge()).isEqualTo(20);
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void findHelloBy() {
		memberRepository.findTop5HelloBy();
	}

	@Test
	public void customMamedQuery() {
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("BBB", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		List<Member> result = memberRepository.findByUsername("AAA");
		Member findMember = result.get(0);

		assertThat(findMember).isEqualTo(m1);
	}

	@Test
	public void queryAnnotaion() {
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("BBB", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		List<Member> result = memberRepository.findUser("AAA", 10);
		Member findMember = result.get(0);

		assertThat(findMember).isEqualTo(m1);
	}

	@Test
	public void findMembernameList() {
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("BBB", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		List<String> usernameList = memberRepository.findUsernameList();
		System.out.println("usernameList = " + usernameList);
	}

	@Test
	public void findMemberDto() {
		Member m1 = new Member("AAA", 10);
		memberRepository.save(m1);

		Team team = new Team("teamA");
		m1.setTeam(team);
		teamRepository.save(team);

		List<MemberDto> memberDtoList = memberRepository.findMemberDto();
		System.out.println("memberDtoList = " + memberDtoList);
	}

	@Test
	public void findByNames() {
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("BBB", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		List<Member> usernameList = memberRepository.findByNames(new ArrayList<>(Arrays.asList("AAA", "BBB")));
		System.out.println("usernameList = " + usernameList);
	}

	@Test
	public void findByNamesQueryMethod() {
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("BBB", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		List<Member> usernameList = memberRepository.findByUsernameIn(new ArrayList<>(Arrays.asList("AAA", "BBB")));
		System.out.println("usernameList = " + usernameList);
	}

	@Test
	public void returnType() {
		Member m1 = new Member("AAA", 10);
		Member m2 = new Member("BBB", 20);
		memberRepository.save(m1);
		memberRepository.save(m2);

		Member findMember = memberRepository.findMemberByUsername("AAA");
		System.out.println("findMember = " + findMember);
	}

	@Test
	public void paging_page() {
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 10));
		memberRepository.save(new Member("member3", 10));
		memberRepository.save(new Member("member4", 10));
		memberRepository.save(new Member("member5", 10));

		int age = 10;
		PageRequest pageRequest = PageRequest.of(1, 2, Sort.by(Sort.Direction.DESC, "username"));

		Page<Member> page = memberRepository.findByAge(age, pageRequest);

		System.out.println("page.getClass(): "+page.getClass());

		List<Member> content = page.getContent();
		int totalPages = page.getTotalPages();
		long totalCount = page.getTotalElements();

		System.out.println("content = " + content);
		System.out.println("totalPages = " + totalPages);
		System.out.println("totalCount = " + totalCount);

		assertThat(content.size()).isEqualTo(2);
		assertThat(page.getTotalElements()).isEqualTo(5);
		assertThat(page.getNumber()).isEqualTo(1);
		assertThat(page.getTotalPages()).isEqualTo(3);
		assertThat(page.isFirst()).isFalse();
		assertThat(page.hasNext()).isTrue();
	}

	@Test
	public void paging_slice() {
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 10));
		memberRepository.save(new Member("member3", 10));
		memberRepository.save(new Member("member4", 10));
		memberRepository.save(new Member("member5", 10));

		int age = 10;
		PageRequest pageRequest = PageRequest.of(1, 2, Sort.by(Sort.Direction.DESC, "username"));

		Slice<Member> page = memberRepository.findSliceByAge(age, pageRequest);

		System.out.println("page.getClass(): "+page.getClass());

		List<Member> content = page.getContent();

		System.out.println("content = " + content);
		System.out.println("hasNext = " + page.hasNext());
		System.out.println("isFirst = " + page.isFirst());
		System.out.println("getNumber = " + page.getNumber());

	}

	@Test
	public void paging_separation() {
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 10));
		memberRepository.save(new Member("member3", 10));
		memberRepository.save(new Member("member4", 10));
		memberRepository.save(new Member("member5", 10));

		int age = 10;
		PageRequest pageRequest = PageRequest.of(1, 2, Sort.by(Sort.Direction.DESC, "username"));

		Page<Member> page = memberRepository.findPgSepByAge(age, pageRequest);

		Page<MemberDto> map = page.map(m -> new MemberDto(m.getId(), m.getUsername(), null));

		System.out.println("page.getClass(): "+page.getClass());

		List<Member> content = page.getContent();

		System.out.println("content = " + content);
		System.out.println("hasNext = " + page.hasNext());
		System.out.println("isFirst = " + page.isFirst());
		System.out.println("getNumber = " + page.getNumber());

	}

	@Test
	public void bulkAgePlus() {
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 19));
		memberRepository.save(new Member("member3", 20));
		memberRepository.save(new Member("member4", 21));
		memberRepository.save(new Member("member5", 40));

		int resultCount = memberRepository.bulkAgePlus(20);

		System.out.println("member5: "+memberRepository.findByUsername("member5").get(0));

		assertThat(resultCount).isEqualTo(3);
	}

	@Test
	public void findMemberLazy() {
		// member1 → teamA
		// member2 → teamB

		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");

		teamRepository.save(teamA);
		teamRepository.save(teamB);

		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 10, teamB);

		memberRepository.save(member1);
		memberRepository.save(member2);

		em.flush();
		em.clear();

//		List<Member> members = memberRepository.findAll();
//		List<Member> members = memberRepository.findMemberFetchJoin();
		List<Member> members = memberRepository.findMemberEntityGraph();

		for (Member member : members) {
			System.out.println("member.getUsername() = " + member.getUsername());
			System.out.println("member.getTeam().getClass() = " + member.getTeam().getClass());
			System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
			System.out.println("member.getTeam().getClass2() = " + member.getTeam().getClass());
		}
	}

	@Test
	public void queryHint() {
		Member member1 = new Member("member1", 10);
		memberRepository.save(member1);
		em.flush();
		em.clear();

		Member findMember = memberRepository.findReadOnlyByUsername("member1");
		findMember.setUsername("member2");
	}

	@Test
	public void lock() {
		Member member1 = new Member("member1", 10);
		memberRepository.save(member1);
		em.flush();
		em.clear();

		List<Member> result = memberRepository.findLockByUsername("member1");
	}

}












