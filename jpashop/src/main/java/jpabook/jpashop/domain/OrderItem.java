package jpabook.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {
	@Id
	private String id;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
}
