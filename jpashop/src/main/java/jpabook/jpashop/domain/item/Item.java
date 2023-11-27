package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {
	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;

	private String name;
	private Integer price;
	private int stockQuantity;

	@OneToMany(mappedBy = "item")
	private List<OrderItem> orderItems = new ArrayList<>();

	@ManyToMany
	@JoinTable(
			name = "category_item",
			joinColumns = @JoinColumn(name = "item_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private List<Category> categories = new ArrayList<>();

	//==비즈니스 로직==//
	/**
	 * stock 증가
	 */
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}

	/**
	 * stock 감
	 */
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if (restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}
}
























