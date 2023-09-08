package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;
import java.util.Comparator;

//겹치는 선분의 길이
//선분 3개가 평행하게 놓여 있습니다.
//세 선분의 시작과 끝 좌표가 [[start, end], [start, end], [start, end]] 형태로 들어있는 2차원 배열 lines가 매개변수로 주어질 때,
//두 개 이상의 선분이 겹치는 부분의 길이를 return 하도록 solution 함수를 완성해보세요.
//lines가 [[0, 2], [-3, -1], [-2, 1]]일 때 그림으로 나타내면 다음과 같습니다.
//line_2.png
//선분이 두 개 이상 겹친 곳은 [-2, -1], [0, 1]로 길이 2만큼 겹쳐있습니다.
public class Solution103 {
	public static void main(String[] args) {
		Solution103 s = new Solution103();
		System.out.println(s.solution(new int[][]{{0,2},{-3,-1},{-2,1}})); // 2
		System.out.println(s.solution(new int[][]{{0,2},{-3,-1},{-2,-1}})); // 1
		System.out.println(s.solution(new int[][]{{3,4},{6,7},{3,7}})); // 2
		System.out.println(s.solution(new int[][]{{1,3},{3,9},{-1,1}})); // 0
		System.out.println(s.solution(new int[][]{{1,3},{2,4},{3,5}})); // 2
		System.out.println(s.solution(new int[][]{{-3,1},{-2,-1},{0,2}})); // 2
		System.out.println(s.solution(new int[][]{{0,1},{2,4},{3,5}})); // 1
		System.out.println(s.solution(new int[][]{{5,7},{6,8},{8,10}})); // 1
		System.out.println(s.solution(new int[][]{{5,7},{6,8},{9,10}})); // 1
		System.out.println(s.solution(new int[][]{{1,3},{2,5},{4,6}})); // 2
		System.out.println(s.solution(new int[][]{{-3,-2},{-1,0},{1,2}})); // 0
		System.out.println(s.solution(new int[][]{{-1,0},{1,2},{-1,0}})); // 1
		System.out.println(s.solution(new int[][]{{-1,0},{-3,-2},{0,2}})); // 0
		System.out.println(s.solution(new int[][]{{-1,0},{0,1},{-2,0}})); // 1
		System.out.println(s.solution(new int[][]{{1,8},{0,6},{3,4}})); // 5
		System.out.println(s.solution(new int[][]{{5,6},{3,4},{1,2}})); // 0
		System.out.println(s.solution(new int[][]{{5,6},{4,5},{3,4}})); // 0
		System.out.println(s.solution(new int[][]{{5,6},{4,5},{3,5}})); // 1
		System.out.println(s.solution(new int[][]{{5,6},{4,5},{3,6}})); // 2
		System.out.println(s.solution(new int[][]{{5,6},{4,5},{3,7}})); // 2
		System.out.println(s.solution(new int[][]{{-1,0},{3,4},{-2,2}})); // 1
		System.out.println(s.solution(new int[][]{{-1,0},{2,3},{-2,2}})); // 1
		System.out.println(s.solution(new int[][]{{-1,0},{2,3},{-2,4}})); // 2 ★4
		System.out.println(s.solution(new int[][]{{6,7},{8,9},{7,10}})); // 1
		System.out.println(s.solution(new int[][]{{-1,0},{0,2},{-1,4}})); // 3
		System.out.println(s.solution(new int[][]{{5,6},{6,7},{7,8}})); // 0
		System.out.println(s.solution(new int[][]{{0,5},{3,9},{1,10}})); // 8
		System.out.println(s.solution(new int[][]{{-1,1},{1,3},{3,9}})); // 0
		System.out.println(s.solution(new int[][]{{-1,1},{1,3},{4,9}})); // 0
		System.out.println(s.solution(new int[][]{{2,4},{5,7},{3,6}})); // 2
	}

	public int solution(int[][] lines) {
		/*Set<Integer> set = new HashSet<>();
		System.out.println(Arrays.deepToString(Arrays.stream(lines).map(arr -> IntStream.rangeClosed(arr[0], arr[1]).toArray()).toArray()));

//		System.out.println(Arrays.toString(Arrays.stream(lines).flatMapToInt(arr -> IntStream.rangeClosed(arr[0] + 1, arr[1] - 1)).toArray()));
//		return (int) Arrays.stream(lines).flatMapToInt(arr -> IntStream.rangeClosed(arr[0], arr[1])).filter(n -> !set.add(n)).count();
		System.out.println(Arrays.toString(Arrays.stream(lines).flatMap(arr -> IntStream.rangeClosed(arr[0], arr[1]).boxed()).toArray()));
		System.out.println(Arrays.stream(lines).flatMap(arr -> IntStream.rangeClosed(arr[0], arr[1]).boxed()).collect(Collectors.groupingBy(n -> n)).entrySet());
		System.out.println(Arrays.toString(Arrays.stream(lines).flatMap(arr -> IntStream.rangeClosed(arr[0], arr[1]).boxed()).collect(Collectors.groupingBy(n -> n)).values().stream().map(List::size).toArray()));
		int c = (int) (Arrays.stream(lines).flatMap(arr -> IntStream.rangeClosed(arr[0], arr[1]).boxed()).collect(Collectors.groupingBy(n -> n)).values().stream().map(List::size).filter(n -> n > 1).count() - 1);
		c = c == 0 ? 1 : c;
//		return Arrays.stream(lines[0]).max().orElse(0) == Arrays.stream(lines[1]).min().orElse(0) && Arrays.stream(lines[1]).max().orElse(0) == Arrays.stream(lines[2]).min().orElse(0) || Arrays.stream(lines[1]).max().orElse(0) == Arrays.stream(lines[2]).min().orElse(0) && Arrays.stream(lines[2]).max().orElse(0) == Arrays.stream(lines[0]).min().orElse(0) || Arrays.stream(lines[2]).max().orElse(0) == Arrays.stream(lines[0]).min().orElse(0) && Arrays.stream(lines[0]).max().orElse(0) == Arrays.stream(lines[1]).min().orElse(0) ? 0 : (int) (Arrays.stream(lines).flatMap(arr -> IntStream.rangeClosed(arr[0], arr[1]).boxed()).collect(Collectors.groupingBy(n -> n)).values().stream().map(List::size).filter(n -> n > 1).count() - 1);
//		int[][] ints = Arrays.stream(lines).flatMap(arr -> IntStream.rangeClosed(arr[0], arr[1]).boxed()).collect(Collectors.groupingBy(n -> n)).entrySet().stream().map(arr -> new int[]{arr.getKey(), arr.getValue().size()}).toArray(int[][]::new);
		System.out.println(Arrays.stream(lines[0]).max().orElse(0) == Arrays.stream(lines[1]).min().orElse(0) &&
				Arrays.stream(lines[1]).max().orElse(0) == Arrays.stream(lines[2]).min().orElse(0) ||
				Arrays.stream(lines[1]).max().orElse(0) == Arrays.stream(lines[2]).min().orElse(0) &&
				Arrays.stream(lines[2]).max().orElse(0) == Arrays.stream(lines[0]).min().orElse(0) ||
				Arrays.stream(lines[2]).max().orElse(0) == Arrays.stream(lines[0]).min().orElse(0) &&
				Arrays.stream(lines[0]).max().orElse(0) == Arrays.stream(lines[1]).min().orElse(0));
//		return (int)(Arrays.stream(lines).flatMapToInt(arr -> IntStream.rangeClosed(arr[0], arr[1])).count() - Arrays.stream(lines).flatMapToInt(arr -> IntStream.rangeClosed(arr[0], arr[1])).distinct().count());
		int r = Arrays.stream(lines).flatMap(arr -> IntStream.rangeClosed(arr[0], arr[1]).boxed()).collect(Collectors.groupingBy(n -> n)).values().stream().map(List::size).noneMatch(n -> n > 1) ? 0 : (int) (Arrays.stream(lines).flatMap(arr -> IntStream.rangeClosed(arr[0], arr[1]).boxed()).collect(Collectors.groupingBy(n -> n)).values().stream().map(List::size).filter(n -> n > 1).count() - 1);
		System.out.println(Arrays.deepToString(Arrays.stream(lines).map(a -> Arrays.stream(lines).filter(a2 -> a[1] == a2[0]).toArray()).toArray()));
		System.out.println(Arrays.toString(Arrays.stream(lines).map(a -> Arrays.stream(lines).filter(a2 -> a[1] == a2[0]).count()).toArray()));
		System.out.println(Arrays.stream(lines).map(a -> Arrays.stream(lines).filter(a2 -> a[1] == a2[0]).count()).filter(i -> i == 1).count());
		return Arrays.stream(lines).flatMap(arr -> IntStream.rangeClosed(arr[0], arr[1]).boxed()).collect(Collectors.groupingBy(n -> n)).values().stream().map(List::size).noneMatch(n -> n > 1) ? 0 : (int) (Arrays.stream(lines).flatMap(arr -> IntStream.rangeClosed(arr[0], arr[1]).boxed()).collect(Collectors.groupingBy(n -> n)).values().stream().map(List::size).filter(n -> n > 1).count() - 1 - (Arrays.stream(lines).map(a -> Arrays.stream(lines).filter(a2 -> a[1] == a2[0]).count()).anyMatch(i -> i == 1) ? Arrays.stream(lines).map(a -> Arrays.stream(lines).filter(a2 -> a[1] == a2[0]).count()).filter(i -> i == 1).count() - 1 : 0));
		System.out.println(Arrays.deepToString(lines));
		int sum = (lines[0][1] - lines[0][0]) + (lines[1][1] - lines[1][0]) + (lines[2][1] - lines[2][0]);
		System.out.println(Arrays.deepToString(Arrays.stream(lines).map(arr -> IntStream.range(arr[0], arr[1]).toArray()).toArray()));
		System.out.println(Arrays.toString(Arrays.stream(lines).flatMapToInt(arr -> IntStream.range(arr[0], arr[1])).toArray()));
//		System.out.println(Arrays.stream(lines).flatMapToInt(arr -> IntStream.range(arr[0], arr[1])).count() - Arrays.stream(lines).flatMapToInt(arr -> IntStream.range(arr[0], arr[1])).distinct().count() + 1);
		System.out.println(Arrays.stream(lines).flatMap(arr -> IntStream.range(arr[0], arr[1]).boxed()).collect(Collectors.groupingBy(n -> n)).values().stream().map(List::size).filter(n -> n > 1).reduce(Integer::sum).orElse(0));
//		System.out.println(Arrays.stream(lines).flatMapToInt(arr -> IntStream.range(arr[0], arr[1])).map(n -> Arrays.stream(n));
		System.out.println(sum);

//		lines[0][1] - lines[0][0] + lines[1][1] - lines[1][0] + lines[2][1] - lines[2][0];

		System.out.println();

//		2번째 B - 2번쨰 A
//		-
//		A > B && !(A > 자신이 아닌 모든 B) → A - B
//		-
//		2번째 A > 1번째B → 3번째A - 2번째A
//		-
//		2번째 B < 3번째A → 2번째B - 1번째B
//		(음수가 나올경우 0으로 치환)
//		-
//		3번째 A == 2번째 B || 1번째 B == 2번째 A → 3번째 A - 1번째 B → X?
//		3번째 A < 2번째 B || 1번째 B > 2번째 A → 3번째 A - 1번째 B?
*/
		int[][] ints = Arrays.stream(lines).sorted(Comparator.comparingInt(o -> o[1])).toArray(int[][]::new);
		int[][] ints1 = Arrays.stream(lines).sorted(Comparator.comparingInt(o -> o[0])).toArray(int[][]::new);
//		System.out.println("ints: "+ Arrays.deepToString(ints));
//		System.out.println("ints1: "+ Arrays.deepToString(ints1));
		int mid = ints[1][1] - ints1[1][0];
//		System.out.println("mid: "+mid);
		int[][] arrs = Arrays.stream(lines).flatMap(arr -> Arrays.stream(lines).filter(arr2 -> arr[0] != arr2[0]).filter(arr2 -> arr[0] > arr2[1])).toArray(int[][]::new);
//		System.out.println("arrs: "+Arrays.deepToString(arrs));
		int cnt = arrs.length;
//		System.out.println("cnt: "+cnt);
//		System.out.println("cnt<2:"+(cnt < 2));
		int maxA = Arrays.stream(lines).mapToInt(arr -> arr[0]).max().orElse(0);
//		System.out.println("maxA: "+maxA);
		int forSubB = arrs.length == 0 ? 0 : arrs[0][1];
//		System.out.println("forSubB: "+forSubB);
		int sub = forSubB != 0 ? maxA - forSubB : 0;
//		System.out.println("sub: "+sub);
		int resultSub = cnt < 2 ? sub : 0;
//		System.out.println("resultSub:" + resultSub);
		int subC1A = ints1[1][0] > ints[0][1] ? ints1[2][0] - ints1[1][0] : 0;
//		System.out.println("subC1A: " + subC1A);
		int subC1B = ints[1][1] < ints1[2][0] ? ints[1][1] - ints[0][1] : 0;
//		System.out.println("subC1B: " + subC1B);
		int subC2 = ints1[2][0] == ints[1][1] || ints[0][1] == ints1[1][0] ? ints1[2][0] - ints[0][1] : 0;
//		System.out.println("subC2: " + subC2);

		return Math.max(mid - resultSub - subC1A - subC1B - subC2, 0);
	}

}


