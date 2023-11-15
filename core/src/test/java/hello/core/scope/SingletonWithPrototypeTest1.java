package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {
	@Test
	void prototypeFind(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

		PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
		bean1.addCnt();

		assertThat(bean1.getCnt()).isEqualTo(1);

		PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
		bean2.addCnt();

		assertThat(bean2.getCnt()).isEqualTo(1);
	}

	@Test
	void singletonClientUsePrototype(){
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

		ClientBean bean1 = ac.getBean(ClientBean.class);
		int cnt1 = bean1.logic();
		assertThat(cnt1).isEqualTo(1);

		ClientBean bean2 = ac.getBean(ClientBean.class);
		int cnt2 = bean2.logic();
		assertThat(cnt2).isEqualTo(1);
	}

	@Scope("singleton")
	@RequiredArgsConstructor
	static class ClientBean{
		private final Provider<PrototypeBean> prototypeBeanProvider;

		public int logic(){
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
			prototypeBean.addCnt();
			return prototypeBean.getCnt();
		}
	}

	@Scope("prototype")
	static class PrototypeBean{
		private int cnt;

		void addCnt(){
			cnt++;
		}

		int getCnt(){
			return cnt;
		}

		@PostConstruct
		void init(){
			System.out.println("PrototypeBean.init " + this);
		}

		@PreDestroy
		void destroy(){
			System.out.println("PrototypeBean.destroy");
		}
	}
}
