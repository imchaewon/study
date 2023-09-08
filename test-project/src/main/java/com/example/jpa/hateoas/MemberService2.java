package com.example.jpa.hateoas;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService2 {

	private final MemberRepository memberRepository;

	public Member2 addMember(String name, Integer age, Grade grade) {

//		System.out.println(memberRepository.findByAge(age));

		Member2 findMember = memberRepository.findByName123(name);

		System.out.println("(indMember::::::"+findMember);
		if(findMember == null){

			Member2 member = Member2.builder()
					.name123(name)
					.age(age)
					.grade(grade)
					.build();

			return memberRepository.save(member);
		}

		return findMember;
	}
}
