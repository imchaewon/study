package study.datajpa.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.test.TestSaveService;
import study.datajpa.test.TestTransactionalService;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {
	private final MemberRepository memberRepository;
	private final TestTransactionalService testTransactionalService;
	private final TestSaveService testSaveService;

	@GetMapping("/members/{id}")
	public String findMember(@PathVariable("id") Long id) {
		Member member = memberRepository.findById(id).get();
		return member.getUsername();
	}

	@GetMapping("/members2/{id}")
	public String findMember2(@PathVariable("id") Member member) {
		return member.getUsername();
	}

	@GetMapping("/members")
	public Page<MemberDto> list(@PageableDefault(size = 5, sort = "username") Pageable pageable) {
		return memberRepository.findAll(pageable).map(MemberDto::new);
	}

	@GetMapping("test1")
	public void m1() throws Exception {
		testTransactionalService.m1();
	}

	@GetMapping("test2")
	public void m2() throws Exception {
		testSaveService.m1();
	}

//	@PostConstruct
	public void init() throws Exception {
		for (int i = 0; i < 100; i++) {
			memberRepository.save(new Member("user" + i, i));
		}
	}


}





