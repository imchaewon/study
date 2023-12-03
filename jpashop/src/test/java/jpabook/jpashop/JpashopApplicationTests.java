package jpabook.jpashop;

import jpabook.jpashop.domain.Child;
import jpabook.jpashop.domain.Parent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class JpashopApplicationTests {
	@Autowired EntityManager em;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("cascade 이용해 부모에서 자식 삽입")
	void cascade1(){
		Parent parent = makeParent("parent1");
//		System.out.println("parentid1: "+parent.getId());
		em.persist(parent);
//		System.out.println("parentid2: "+parent.getId());

//		System.out.println("start..");
//		System.out.println(em.createQuery("select p from Parent p where p.id=123", Parent.class).getSingleResult());

		Child child1 = makeChild("child1");
		child1.setParent(parent);
		Child child2 = makeChild("child2");
		child2.setParent(parent);

		ArrayList<Child> childs = new ArrayList<>();
		childs.add(child1);
		childs.add(child2);
		parent.setChilds(childs);
	}

	@Test
	@DisplayName("cascade와 변경감지 이용해 부모변경")
	void cascade2(){
		Parent parent1 = makeParent("parent1");
		Parent parent2 = makeParent("parent2");
		em.persist(parent1);
		em.persist(parent2);

		Child child1 = makeChild("child1");
		child1.setParent(parent1);
		Child child2 = makeChild("child2");
		child2.setParent(parent1);

		ArrayList<Child> childs = new ArrayList<>();
		childs.add(child1);
		childs.add(child2);
		parent1.setChilds(childs);

		child1.changeParent(parent2);
	}

	@Test
	@DisplayName("자식 직접 삭제")
	void cascade3(){
		Parent parent1 = makeParent("parent1");
		em.persist(parent1);

		Child child1 = makeChild("child1");
		child1.setParent(parent1);
		Child child2 = makeChild("child2");
		child2.setParent(parent1);

		ArrayList<Child> childs = new ArrayList<>();
		childs.add(child1);
		childs.add(child2);
		parent1.setChilds(childs);

		em.flush();
		em.clear();

		Child findChild = em.find(Child.class, child1.getId());
		em.remove(findChild);
	}

	@Test
	@DisplayName("부모에서 자식 삭제")
	void cascade4(){
		Parent parent1 = makeParent("parent1");

		Child child1 = makeChild("child1");
		child1.setParent(parent1);
		Child child2 = makeChild("child2");
		child2.setParent(parent1);

		ArrayList<Child> childs = new ArrayList<>();
		childs.add(child1);
		childs.add(child2);
		parent1.setChilds(childs);

		em.persist(parent1);

		assertThat(em.contains(parent1)).isTrue();
		assertThat(em.contains(child1)).isTrue();
		assertThat(em.contains(child2)).isTrue();

//		em.flush();
//		em.clear();
//
//		Parent findParent = em.find(Parent.class, parent1.getId());
//
//		findParent.getChilds().remove(0);
//		System.out.println("findParent = " + findParent);
	}

	Parent makeParent(String name) {
		Parent parent = new Parent();
		parent.setName(name);
		return parent;
	}

	Child makeChild(String name) {
		Child child = new Child();
		child.setName(name);
		return child;
	}
}
