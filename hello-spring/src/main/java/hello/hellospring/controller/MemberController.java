package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController {
	private MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members/new")
	public String createForm(){
		return "members/createMemberForm";
	}

	@GetMapping("join")
	public String join(@RequestParam("name") String name, Model model){
		Member member = new Member();
		member.setName(name);

		memberService.join(member);

		List<Member> members = memberService.getMemberRepository().findAll();
		System.out.println(members);

		List<Map<String,Object>> mlist = new ArrayList<>();
		members.forEach(e->{
			Map<String, Object> map = new HashMap<>();
			map.put("id", e.getId());
			map.put("name", e.getName());
			mlist.add(map);
		});

		model.addAttribute("members", mlist);

		return "join";
	}

	@PostMapping("members/new")
	public String create(MemberForm form){
		Member member = new Member();
		member.setName(form.getName());

		memberService.join(member);

		return "redirect:/";
	}

	@GetMapping("members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
}













