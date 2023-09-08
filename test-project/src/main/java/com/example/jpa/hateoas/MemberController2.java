package com.example.jpa.hateoas;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class MemberController2 {

	private final MemberService2 memberService;
	private final MemberRepository memberRepository;

	@GetMapping("/api")
	public EntityModel<Member2> insertMember(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age, @RequestParam(value = "grade") Grade grade){

		System.out.println(name);
		System.out.println(age);
		System.out.println(grade);

		Member2 member = memberService.addMember(name, age, grade);

		EntityModel<Member2> memberEntityModel = EntityModel.of(member);
		memberEntityModel.add(linkTo(methodOn(MemberController2.class).insertMember(name, age, grade)).withSelfRel());

		return memberEntityModel;
	}
}