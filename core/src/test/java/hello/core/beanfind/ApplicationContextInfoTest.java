package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;

class ApplicationContextInfoTest {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("모든 빈 출력하기")
	void findAllBean(){
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames)
			System.out.println("key: " + beanDefinitionName + ", val: " + ac.getBean(beanDefinitionName));
	}

	@Test
	@DisplayName("애플리케이션 빈 출력하기")
	void findApplicationBean(){
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION)
				System.out.println("key: " + beanDefinitionName + ", val: " + ac.getBean(beanDefinitionName));
		}
	}
}
