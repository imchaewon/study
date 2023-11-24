package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
	private String city;
	private String street;
	private String zipcode;
}
