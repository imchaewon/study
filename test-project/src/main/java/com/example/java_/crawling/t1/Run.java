package com.example.java_.crawling.t1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Run {
	public static void main(String[] args) {

		try {
			String URL = "https://www.mma.go.kr/hall/listsearch.do";
			Document doc = Jsoup.connect(URL).get();

//			System.out.println(doc.text());    // HTML에서 텍스트만 불러옴 (자바스크립트 코드는 생략된다.)
//			System.out.println(doc.html());    // HTML 자체를 가져옴

//			Elements imageUrlElements = doc.getElementsByClass("sp_list");
//			for (Element element : imageUrlElements) {
//				System.out.println(element);
//			}

			Element areaListElement = doc.getElementById("areaList");
			System.out.println(areaListElement);
		} catch (Exception e) {
			System.out.println("Run.main()");
			e.printStackTrace();
		}

	}
}
