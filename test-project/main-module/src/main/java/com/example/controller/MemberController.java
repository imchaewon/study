package com.example.controller;

import com.example.model.mybatis.Member;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/member")
@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/hello")
	public void hello(Model model){
		System.out.println("hello?");
		model.addAttribute("data", "this id data");
	}

	@PostMapping(value = "/map")
	public void member(Model model, @RequestBody HashMap<String,Object> map){
		System.out.println(map);
//		System.out.println("memberMap");
		List<Map<String, Object>> list = memberService.getAllUsersMap();
//		System.out.println(list.get(0).get("USR_ID"));
//		System.out.println(list);
		model.addAttribute("data", list);
	}

	@PostMapping(value="/dto")
	public void member2(Model model, @RequestBody Member member){
		System.out.println(member);
//		System.out.println("memberDto");
		List<Member> list = memberService.getAllUsersDto();
//		System.out.println(list);
		model.addAttribute("data", list);
	}

	@PostMapping("asdf")
	public void asdf(){
		System.out.println("aaaaaaaa");
	}

	@GetMapping("zxcv")
	public void zxcv(){
		System.out.println("bbbbbbbb");
	}

}
