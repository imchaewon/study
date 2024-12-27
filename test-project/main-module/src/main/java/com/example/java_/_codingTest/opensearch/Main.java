package com.example.java_._codingTest.opensearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch._types.query_dsl.BoolQuery;
import org.opensearch.client.opensearch._types.query_dsl.Query;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) throws Exception {

		// OpenSearch 설정
		ApplicationContext ac = new AnnotationConfigApplicationContext(OpenSearchConfig.class);
		OpenSearchClient client = ac.getBean(OpenSearchClient.class);

		// BoolQuery를 사용하여 "service.adapter"가 "network"인 문서를 검색
		BoolQuery.Builder boolQ = new BoolQuery.Builder();
		FieldValue fieldValue = FieldValue.of("network");
		boolQ.must(m ->
			m.term(t -> t.field("service.adapter").value(fieldValue))
		);

		Query query = boolQ.build()._toQuery();

		System.out.println("query = " + query);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = gson.toJson(query);
		System.out.println("prettyJson = " + prettyJson);

		// 검색 요청 생성
		SearchRequest searchRequest = SearchRequest.of(
//				i -> i.index("oke-metric-*")
				i -> i.index("oke-metric-openstack-network")
						.size(0)
						.query(query)
		);

		// 검색 요청 실행
		SearchResponse<Void> osResponse;
		try {
			osResponse = client.search(searchRequest, Void.class);
		} catch (Exception e) {
			System.out.println("Main.main()");
			e.printStackTrace();
			throw new Exception("Failed opensearch query");
		}

		System.out.println("searchResponse = " + osResponse);
		System.out.println("searchResponse.hits() = " + osResponse.hits());
		System.out.println("searchResponse.hits().hits() = " + osResponse.hits().hits());

		// 검색 결과 처리
		for (Object hit : osResponse.hits().hits()) {
			System.out.println("hit = " + hit);
		}




		/*// 검색 결과 처리
		TotalHits total = searchResponse.hits().total();
		boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

		if (isExactResult) {
			System.out.println("There are " + total.value() + " results");
		}
		else {
			System.out.println("There are more than " + total.value() + " results");
		}

		List<Hit<Object>> hits = searchResponse.hits().hits();

		List<FilebeatLogMessageDto> dataList = new ArrayList<>();
		for (Hit<Object> hit : hits) {
			Object obj = hit.source();
			Map<String, Object> map = (Map<String, Object>)obj;

			String timeStamp = (String)map.get("@ctime");
			String message = (String)map.get("message");

			String vmId = (String)map.get("identifier");

//      Map<String, String> fieldsMap = (Map<String, String>)map.get("fields");
//      String vmId = fieldsMap.get("vm_id");

			// Opensearch 내 filebeat 결과 log.file.path
			Map<String, Map<String, String>> logFileMap = (Map<String, Map<String, String>>)map.get("log");
			Map<String, String> fileMap = logFileMap.get("file");
			String path = fileMap.get("path");

			FilebeatLogMessageDto dto = new FilebeatLogMessageDto();
			dto.setMessage(message);
			dto.setLogPath(path);
			dto.setTimeText(timeStamp);
			dto.setTerm(vmId);

			dataList.add(dto);
		}

		System.out.println("dataList = " + dataList);*/


	}

}