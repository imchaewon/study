package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
	private final OrderRepository orderRepository;

	@GetMapping("/api/v1/orders")
	public List<Order> ordersV1() {
		List<Order> all = orderRepository.findAllByString(new OrderSearch());
		for (Order order : all) {
			order.getMember().getName();
			order.getDelivery().getAddress();
			List<OrderItem> orderItems = order.getOrderItems();
			orderItems.forEach(e -> e.getItem().getName());
		}
		return all;
	}

	@GetMapping("/api/v2/orders")
	public List<OrderDto> ordersV2() {
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());

		return orders.stream()
				.map(OrderDto::new)
				.collect(Collectors.toList());
	}

	@GetMapping("/api/v3/orders")
	public List<OrderDto> ordersV3() {
		List<Order> orders = orderRepository.findAllWithItem();

		for (Order order : orders) {
			System.out.println("order ref="+order+" id="+order.getId());
		}
		
		return orders.stream()
				.map(OrderDto::new)
				.collect(Collectors.toList());
	}

	@Getter
	static class OrderDto{
		private Long orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;
		private List<OrderItemDto> orderItems;

		public OrderDto(Order o) {
			orderId = o.getId();
			name = o.getMember().getName();
			orderDate = o.getOrderDate();
			orderStatus = o.getStatus();
			address = o.getDelivery().getAddress();
			orderItems = o.getOrderItems().stream()
					.map(OrderItemDto::new)
					.collect(Collectors.toList());
			o.getOrderItems().forEach(e->e.getItem().getName());
		}
	}

	@Getter
	static class OrderItemDto{
		private String itemName; // 상품 명
		private int orderPrice; // 주문 가격
		private int count; // 주문 수량

		public OrderItemDto(OrderItem orderItem) {
			itemName = orderItem.getItem().getName();
			orderPrice = orderItem.getOrderPrice();
			count = orderItem.getCount();
		}
	}
}



















