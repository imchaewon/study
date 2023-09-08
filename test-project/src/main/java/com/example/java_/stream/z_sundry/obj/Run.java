package com.example.java_.stream.z_sundry.obj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Run {
    public static void main(String[] args) {
        m1();
//        impossibleNewStream();
    }

	static void m1() {
        /* java8 */
        List<SampleDTO> SampleDTOList = new ArrayList<>();
		SampleDTOList.add(new SampleDTO(2, "aaa", "male"));
		SampleDTOList.add(new SampleDTO(5,"bbb","female"));
        SampleDTOList.add(new SampleDTO(7,"ccc","male"));
        SampleDTOList.add(new SampleDTO(8,"ddd","female"));
        SampleDTOList.add(new SampleDTO(10,"eee","male"));
        SampleDTOList.add(new SampleDTO(20,"fff","female"));

        List<String> list = SampleDTOList.stream()
                /** 람다를 인수로 받아, 스트림에서 특정 요소를 제외시킨다. 아래는 idx가 10 이상인 데이터를 선택한다. */
                .filter(a -> a.getIdx() < 10) // idx가 10보다 작은 데이터 선택
                .sorted(Comparator.comparing(SampleDTO::getIdx)) // idx 순서로 정렬
                /** 람다를 이용해서 한 요소를 다른 요소로 변환하거나 정보를 추출한다. */
                .map(SampleDTO::getName) // 이름 추출
                .limit(3) // 선착순 3개만 선택
                .collect(Collectors.toList()); // 리스트로 저장

        /* 병렬 */
        List<String> parallelList = SampleDTOList.parallelStream()
        		.filter(a -> a.getIdx() < 10) // idx가 10보다 작은 데이터 선택
        		.sorted(Comparator.comparing(SampleDTO::getIdx)) // idx 순서로 정렬
        		.map(SampleDTO::getName) // 이름 추출
        		.collect(Collectors.toList()); // 리스트로 저장

        list.forEach(System.out::println);
        parallelList.forEach(System.out::println);
        System.out.println(list);
        list.forEach(s -> System.out.println(s));

        SampleDTOList.stream().forEach((a) -> System.out.println(a));
        SampleDTOList.stream().forEach((a) -> System.out.println(a));

        Stream<SampleDTO> s1 = SampleDTOList.stream();
        s1.collect(Collectors.toList());
        s1.collect(Collectors.toList());
    }

    /**
     * 스트림은 단 한번만 소비 가능하다.
     */
    static void impossibleNewStream() {
        List<String> title = Arrays.asList("A", "B", "C");
        Stream<String> s = title.stream();

        s.forEach(System.out::println); // A, B, C 출력
        /** java.lang.IllegalStateException:스트림이 이미 소비되었거나 닫힘 에러 발생 */
        s.forEach(System.out::println);


    }
}











