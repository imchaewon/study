package com.example.spring.t1.controller;

import com.example.spring.t1.model.member.entity.Member;
import com.example.spring.t1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/rest/member")
@RequiredArgsConstructor
@RestController
public class MemberRestController {

	private final MemberService memberService;

	@GetMapping("/map")
	public ResponseEntity<Map<String,Object>> member(){
		System.out.println("memberMap");
		List<Map<String, Object>> list = memberService.getAllUsersMap();
		System.out.println(list.get(0).get("USR_ID"));
		System.out.println(list);

		Map<String,Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("data", list);
		map.put("message", "성공");

		return ResponseEntity.ok(map);
	}

	@PostMapping("/mapCri")
	public ResponseEntity<Map<String,Object>> member(@RequestBody Map<String, Object> cri){
		System.out.println("memberMap");
		System.out.println(cri);
		List<Map<String, Object>> list = memberService.getAllUsersMap(cri);
		System.out.println(list);

		Map<String,Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("data", list);
		map.put("message", "성공");

		return ResponseEntity.ok(map);
	}

//	@GetMapping("/map")
//	public ResponseEntity<Map<String,Object>> member(Criteria cri){
//		System.out.println("memberMap");
//		List<Map<String, Object>> list = memberService.getAllUsersMap(cri);
//		System.out.println(list.get(0).get("USR_ID"));
//		System.out.println(list);
//
//		Map<String,Object> map = new HashMap<>();
//		map.put("code", 200);
//		map.put("data", list);
//		map.put("message", "성공");
//
//		return ResponseEntity.ok(map);
//	}

	@GetMapping("/dto")
	public ResponseEntity<Map<String,Object>> member2(){
		System.out.println("memberDto");
		List<Member> list = memberService.getAllUsersDto();
		System.out.println(list.get(0).getUsrId());
		System.out.println(list);

		Map<String,Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("data", list);
		map.put("message", "성공");

		return ResponseEntity.ok(map);
	}

}
