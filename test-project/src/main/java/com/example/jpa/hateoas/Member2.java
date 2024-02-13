package com.example.jpa.hateoas;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MEMBER2")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(of = "id")
public class Member2 {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	private String name123;

	private Integer age;

	@Enumerated(EnumType.STRING)
	private Grade grade;

	private Member2(String name, Integer age, Grade grade){
		this.name123 = name;
		this.age = age;
		this.grade = grade;
	}
}
