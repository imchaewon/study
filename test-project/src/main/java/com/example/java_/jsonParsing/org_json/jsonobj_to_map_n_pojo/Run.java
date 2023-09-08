package com.example.java_.jsonParsing.org_json.jsonobj_to_map_n_pojo;

import com.example.java_.testData.Human;
import com.example.java_.testData.HumanData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Run {
	public static void main(String[] args) {

		JSONObject obj = HumanData.getData();
		JSONArray arr = (JSONArray) obj.get("data");

//		List<Object> list = arr.toList();


		// <List<Map>> 으로 변환
		List<Map<String,Object>> list = new ArrayList<>();

		for (Object o : arr) {
			list.add(((JSONObject) o).toMap());
		}

		System.out.println(list);
		System.out.println(list.get(0).get("name"));
		System.out.println();


		// <List<VO>>로 변환
		List<Human> list2 = new ArrayList<>();

		Class<Human> clazz = Human.class;
		Field[] fields = clazz.getDeclaredFields();
		System.out.println(Arrays.toString(fields));
		System.out.println(fields.length);

		System.out.println();

		Human human = new Human();
		Arrays.stream(fields).forEach(f->{ // 필드수만큼 반복. 필드 가져오기
			list.forEach(i->{ // Map(json객체) 가져오기. 개체만큼 반복
				if(i.containsKey(f.getName()) && i.get(f.getName()) != null){ // json 객체에 필드명 key가 존재하는지 확인
					String type = f.getType().toString();
					try {
						switch (type){
							case "class java.lang.Integer" :
								f.set(human, Integer.parseInt(i.get(f.getName()).toString())); // 필드에 값 삽입
								break;
							case "class java.lang.Double" :
								f.set(human, Double.parseDouble(i.get(f.getName()).toString())); // 필드에 값 삽입
								break;
							default:
								f.set(human, i.get(f.getName())); // 필드에 값 삽입
						}
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			});

			list2.add(human);

		});

		System.out.println(list2);
		System.out.println(list2.get(0).getName());




	}



}
