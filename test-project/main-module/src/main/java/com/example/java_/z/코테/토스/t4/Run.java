package com.example.java_.z.코테.토스.t4;

//정확성 시간 제한 / 메모리 제한
//10초/ 2GB

//나만의 L4 만들기
//여러 대의 서버로 부하를 분산하는 로드밸런서를 만들고자 합니다. 해당 로드밸런서는 기본적으로는 라운드 로빈 방식으로 요청을 서버로 분배합니다.
//다만 요청의 sticky 옵션이 true 인 경우 이전에 분배된 서버로 요청이 분배되어야 합니다. 이러한 동작을 수행하는 함수를 구현해보세요.
//• 라운드 로빈 방식 : 1번 서버부터 시작, 1, 2, ... N, 그리고 다시 1, 2, ... 순서입니다.

//예시1
//2개의 서버에 라운드 로빈 방식으로 요청을 분배한다면 아래와 같이 동작해야 합니다.
//int servers = 2;
//boolean sticky = false;
//int[] requests = new int[]{1,2,3,4};
//int[][]
//result = solution(servers, sticky, requests);
//println(result); // [1,3], [2,4]
//출력 결과인 [1,3], [2,4] 가 의미하는 바는 1번 서버에 1번과 3번 요청이 전달되었으며, 2번 서 버에 2번과 4번 요청이 전달되었다는 의미입니다.

//예시2
//2개의 서버에 sticky 옵션을 true 로 요청한 경우에는 아래와 같이 동작해야 합니다.
//int servers = 2;
//boolean sticky = true;
//int[] requests = new int[]{1,1,2,2};
//int[I] result = solution(servers, sticky, requests);
//println(result); // [1,1], [2,2]
//출력 결과인 [1,1], [2,2] 가 의미하는 바는 첫 번째 서버에 1번 요청이 2개 전달되었으며, 두 번 째 서버에 2번 요청이 2개 전달되었다는 의미입니다.

import java.util.*;

//예시3
//약간 더 복잡한 사례를 살펴보겠습니다. 2개의 서버에 sticky 옵션을 true 로 요청한 경우에는 아 래와 같이 동작해야 합니다.
//int servers = 2;
//boolean sticky = true;
//int[] requests = new int[]{1,2,2,3,4,1};
//int[ll] result = solution(servers, sticky, requests);
//println(result); // [1,3,1], [2,2,4]
//출력 결과인 [1,3,1], [2,2,4] 가 의미하는 바는 첫 번째 서버에 1번, 3번, 1번 요청이 전달되었 으며, 두 번째 서버에 2번, 2번, 4번 요청이 전달되었다는 의미입니다.
public class Run {
	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(new Solution().solution(2, false, new int[]{1, 2})));
	}
}

class Solution {
	public int[][] solution(int servers, boolean sticky, int[] requests) {
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, Integer> stickyMap = new HashMap<>();
		List<Integer>[] serverRequests = new List[servers];

		for (int i = 0; i < servers; i++) {
			serverRequests[i] = new ArrayList<>();
		}

		for (int request : requests) {
			int index;
			if (sticky && stickyMap.containsKey(request)) {
				index = stickyMap.get(request);
			} else {
				index = result.size() % servers;
				if (!serverRequests[index].isEmpty()) {
					stickyMap.remove(serverRequests[index].get(0));
					serverRequests[index].clear();
				}
				result.add(new ArrayList<>());
			}
			serverRequests[index].add(request);
			if (sticky) {
				stickyMap.put(request, index);
			}
			result.get(index).add(request);
		}

		int[][] resArray = new int[result.size()][];
		for (int i = 0; i < result.size(); i++) {
			List<Integer> list = result.get(i);
			int[] arr = new int[list.size()];
			for (int j = 0; j < list.size(); j++) {
				arr[j] = list.get(j);
			}
			resArray[i] = arr;
		}

		return resArray;
	}
}