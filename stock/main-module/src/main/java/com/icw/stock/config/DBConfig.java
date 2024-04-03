package com.icw.stock.config;

import com.icw.stock.constant.ProjectConst;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableJpaRepositories(
		basePackages = ProjectConst.EnvPath.REPOSITORY_PACKAGE,
		entityManagerFactoryRef = ProjectConst.JPA.ENTITY_MANAGER_FACTORY_REF,
		transactionManagerRef = ProjectConst.JPA.TRANSACTION_MANAGER_REF
)
@EnableTransactionManagement
@Configuration
public class DBConfig {
	@Primary
	@Bean(name = ProjectConst.DATASOURCE)
	@ConfigurationProperties(prefix = ProjectConst.ApplicationConf.DATASOURCE)
	public DataSource vistaDataSource() {
		return DataSourceBuilder.create().build();
	}

	//JPA Settings Start
	@Primary
	@Bean(name = ProjectConst.JPA.ENTITY_MANAGER_FACTORY_REF)
	public LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier(ProjectConst.DATASOURCE) DataSource dataSource
	) {
		return builder.dataSource(dataSource).packages(ProjectConst.EnvPath.ENTITY_PACKAGE).build();
	}

	@Primary
	@Bean(name = ProjectConst.JPA.TRANSACTION_MANAGER_REF)
	public JpaTransactionManager transactionManager(
			@Qualifier(ProjectConst.JPA.ENTITY_MANAGER_FACTORY_REF) LocalContainerEntityManagerFactoryBean mfBean
	) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(mfBean.getObject());
		return transactionManager;
	}
	//JPA Settings End
}