package jpabook.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
	@Id
	private String id;

	private String name;

	private int price;

	@OneToMany(mappedBy = "item")
	private List<OrderItem> orderItems = new ArrayList<>();

}
