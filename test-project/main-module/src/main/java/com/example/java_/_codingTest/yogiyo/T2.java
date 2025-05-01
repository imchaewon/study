package com.example.java_._codingTest.yogiyo;

import lombok.AllArgsConstructor;

import java.util.*;

// 1. 3개 구매 시, 1개 공짜
// 2. 동일한 피자 5개 구매 시 100원
// 3. Large 1개당 동일한 Small 1개 무료
// 4. Large 3개를 Medium 3개 가격으로 구매
public class T2 {
	@AllArgsConstructor
	public static class Pizza {
		public String name;
		public int price_S;
		public int price_M;
		public int price_L;
	}

	@AllArgsConstructor
	public static class OrderItem {
		public String name;
		public String size;
		public int quantity;
	}

	public static void main(String[] args) {
		Pizza[] menu = new Pizza[]{
			new Pizza("greek", 7, 5, 10),
			new Pizza("texax", 8, 9, 13),
			new Pizza("european", 5, 10, 13)
		};
		OrderItem[] order = new OrderItem[]{
			new OrderItem("greek", "Medium", 1),
			new OrderItem("european", "Small", 2)
		};
		System.out.println(new T2().solution(menu, order));
	}

	public int solution(Pizza[] menu, OrderItem[] order) {
		Map<String, Pizza> menuMap = new HashMap<>();
		for (Pizza p : menu) {
			menuMap.put(p.name, p);
		}

		List<Integer> allPrices = new ArrayList<>();
		int totalCost = 0;
		Map<String, Integer> pizzaCount = new HashMap<>();
		Map<String, Integer> largeCount = new HashMap<>();
		Map<String, Integer> smallCount = new HashMap<>();

		for (OrderItem item : order) {
			Pizza p = menuMap.get(item.name);
			int price = 0;
			if (item.size.equals("Small")) {
				price = p.price_S;
				smallCount.put(item.name, smallCount.getOrDefault(item.name, 0) + item.quantity);
			} else if (item.size.equals("Medium")) {
				price = p.price_M;
			} else if (item.size.equals("Large")) {
				price = p.price_L;
				largeCount.put(item.name, largeCount.getOrDefault(item.name, 0) + item.quantity);
			}
			totalCost += price * item.quantity;
			for (int i = 0; i < item.quantity; i++) {
				allPrices.add(price);
			}
			pizzaCount.put(item.name, pizzaCount.getOrDefault(item.name, 0) + item.quantity);
		}

		int minCost = totalCost;

		// 할인 1: 3개 이상 주문 시 가장 저렴한 것 무료
		if (allPrices.size() >= 3) {
			Collections.sort(allPrices);
			minCost = Math.min(minCost, totalCost - allPrices.get(0));
		}

		// 할인 2: 동일한 피자 5개 구매 시 100원 (가장 비싼 피자를 우선적으로 적용)
		for (Map.Entry<String, Integer> entry : pizzaCount.entrySet()) {
			if (entry.getValue() >= 5) {
				Pizza p = menuMap.get(entry.getKey());
				int originalCost = p.price_S * entry.getValue();
				int discountBundles = entry.getValue() / 5;
				int remainingPizzas = entry.getValue() % 5;
				int discountCost = (discountBundles * 100) + (remainingPizzas * p.price_S);
				minCost = Math.min(minCost, totalCost - originalCost + discountCost);
			}
		}

		// 할인 3: Large 1개당 동일한 Small 1개 무료
		int largeDiscount = 0;
		for (String name : largeCount.keySet()) {
			int freeSmall = Math.min(largeCount.get(name), smallCount.getOrDefault(name, 0));
			if (freeSmall > 0) {
				Pizza p = menuMap.get(name);
				largeDiscount += freeSmall * p.price_S;
			}
		}
		minCost = Math.min(minCost, totalCost - largeDiscount);

		// 할인 4: Large 3개를 Medium 3개 가격으로 구매
		int largeToMediumDiscount = 0;
		for (String name : largeCount.keySet()) {
			int count = largeCount.get(name);
			if (count >= 3) {
				Pizza p = menuMap.get(name);
				int bundles = count / 3;
				int originalCost = p.price_L * bundles * 3;
				int discountCost = p.price_M * bundles * 3;
				largeToMediumDiscount += originalCost - discountCost;
			}
		}
		minCost = Math.min(minCost, totalCost - largeToMediumDiscount);

		return minCost;
	}
}

//	Example test:   ([('greek', 7, 5, 10), ('texas', 8, 9, 13), ('european', 5, 10, 13)], [('texas', 'Medium', 1), ('european', 'Small', 2)])
//	OK
//
//	Example test:   ([('margherita', 90, 80, 100), ('hawaii', 80, 90, 120), ('capricciosa', 50, 70, 130), ('greek', 50, 70, 130)], [('greek', 'Small', 5), ('margherita', 'Small', 4), ('hawaii', 'Large', 1), ('margherita', 'Medium', 2), ('capricciosa', 'Small', 7)])
//	WRONG ANSWER (got 910 expected 900)
//
//	Example test:   ([('margherita', 7, 8, 10), ('hawaii', 8, 9, 12), ('capricciosa', 5, 7, 13)], [('margherita', 'Small', 3), ('capricciosa', 'Large', 2), ('hawaii', 'Large', 3), ('margherita', 'Large', 1), ('hawaii', 'Medium', 1), ('capricciosa', 'Small', 5), ('capricciosa', 'Medium', 1)])
//	OK
//
//	Example test:   ([('boston', 7, 5, 10), ('hawaii', 8, 9, 12), ('newyorker', 8, 9, 130), ('philadelphia', 5, 10, 13)], [('boston', 'Small', 3), ('hawaii', 'Large', 3), ('newyorker', 'Large', 1), ('boston', 'Large', 2), ('philadelphia', 'Large', 2)])
//	WRONG ANSWER (got 219 expected 102)
//
//	Example test:   ([('margherita', 7, 8, 10), ('hawaii', 8, 9, 12), ('capricciosa', 5, 7, 13)], [('margherita', 'Small', 1), ('hawaii', 'Large', 1)])
//	OK