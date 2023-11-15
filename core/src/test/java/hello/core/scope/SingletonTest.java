package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
	@Test
	void singletonBeanFind(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean1.class);
		AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext(SingletonBean2.class);

		System.out.println("find singletonBean1-1");
		SingletonBean1 singletonBean1 = ac.getBean(SingletonBean1.class);
		System.out.println("find singletonBean1-2");
		SingletonBean1 singletonBean2 = ac.getBean(SingletonBean1.class);

		System.out.println("find singletonBean2-1");
		SingletonBean2 singletonBean3 = ac2.getBean(SingletonBean2.class);
		System.out.println("find singletonBean2-2");
		SingletonBean2 singletonBean4 = ac2.getBean(SingletonBean2.class);

		System.out.println("singletonBean1 = " + singletonBean1);
		System.out.println("singletonBean2 = " + singletonBean2);
		assertThat(singletonBean1).isSameAs(singletonBean2);
		ac.close();
	}

	@Scope("singleton")
	static class SingletonBean1{
		@PostConstruct
		public void init(){
			System.out.println("SingletonBean1.init");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("SingletonBean.destroy");
		}
	}

	@Scope("singleton")
	static class SingletonBean2{
		@PostConstruct
		public void init(){
			System.out.println("SingletonBean2.init");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("SingletonBean.destroy");
		}
	}
}