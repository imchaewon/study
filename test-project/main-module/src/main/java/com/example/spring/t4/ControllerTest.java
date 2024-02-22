package com.example.spring.t4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

@Controller
public class ControllerTest {

	@PostMapping("filetest")
	public String m1(MultipartHttpServletRequest files) {
		Iterator<String> iter = files.getFileNames();
		MultipartFile file = null;
		String fieldName = "";
		while (iter.hasNext()) {
			fieldName = iter.next();
			file = files.getFile(fieldName);
			System.out.println(Objects.requireNonNull(file).getOriginalFilename());
			System.out.println(file.getSize());
			System.out.println(file.getContentType());
		}
		return "success";
	}

	@GetMapping("t1page")
	String t1page() {
		System.out.println("aaaaa");
		return "jsonTest";
	}

	@GetMapping("t1/1")
	@ResponseBody
	String t1(String s1) {
		System.out.println(s1);
		return s1 + 123;
	}

	@GetMapping("t1/2")
	@ResponseBody
	Map<String, Object> t1_2(String s1) {
		System.out.println(s1);
		Map<String,Object> map = new HashMap<>();
		map.put("a", "aa");
		map.put("b", "bb");
		return map;
	}

}
