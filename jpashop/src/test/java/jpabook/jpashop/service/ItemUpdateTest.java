package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class ItemUpdateTest {
	@Autowired
	EntityManager em;
	@Autowired
	MemberRepository memberRepository;

	@Test
	@Rollback(false)
	@Transactional
	void 업데이트_변경감지() throws Exception{
		//TX
		Book book = em.find(Book.class, 3L);

		book.setName("jpa44");
		//TX commit
		//변경감지 == dirty checking
	}

	@Test
	@Rollback(false)
	@Transactional
	void 업데이트_병합() throws Exception{
		//TX
		Book book = new Book();

		book.setId(3L);
		book.setName("jpa33");

		Book book2 = em.merge(book);
		//TX commit
	}
}
