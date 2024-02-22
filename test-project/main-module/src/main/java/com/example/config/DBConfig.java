package com.example.config;

import com.example.constant.PortalConfigConst;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//@EnableJpaRepositories(
//		basePackages = PortalConfigConst.EnvPath.REPOSITORY_PACKAGE,
//		entityManagerFactoryRef = PortalConfigConst.JPA.ENTITY_MANAGER_FACTORY_REF,
//		transactionManagerRef = PortalConfigConst.JPA.TRANSACTION_MANAGER_REF
//)
@MapperScan(
		value = {PortalConfigConst.EnvPath.MYBATIS_BASE_PACKAGE,},
		sqlSessionFactoryRef = PortalConfigConst.Mybatis.SESSION_FACTORY
) //mybatis settings
@EnableTransactionManagement
@Configuration
public class DBConfig {
	@Primary
	@Bean(name = PortalConfigConst.DATASOURCE)
	@ConfigurationProperties(prefix = PortalConfigConst.ApplicationConf.DATASOURCE)
	public DataSource vistaDataSource() {
		return DataSourceBuilder.create().build();
	}

//	//JPA Settings Start
//	@Primary
//	@Bean(name = PortalConfigConst.JPA.ENTITY_MANAGER_FACTORY_REF)
//	public LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory(
//			EntityManagerFactoryBuilder builder,
//			@Qualifier(PortalConfigConst.DATASOURCE) DataSource dataSource
//	) {
//		return builder.dataSource(dataSource).packages(PortalConfigConst.EnvPath.ENTITY_PACKAGE).build();
//	}
//
//	@Primary
//	@Bean(name = PortalConfigConst.JPA.TRANSACTION_MANAGER_REF)
//	public JpaTransactionManager transactionManager(
//			@Qualifier(PortalConfigConst.JPA.ENTITY_MANAGER_FACTORY_REF) LocalContainerEntityManagerFactoryBean mfBean
//	) {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(mfBean.getObject());
//		return transactionManager;
//	}
//	//JPA Settings End

	//Mybatis Start
	@Primary
	@Bean(name = PortalConfigConst.Mybatis.SESSION_FACTORY)
	public SqlSessionFactory exampleSqlSessionFactory(
			@Qualifier(PortalConfigConst.DATASOURCE) DataSource vistaDataSource,
			ApplicationContext applicationContext
	) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(vistaDataSource);
		sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(PortalConfigConst.EnvPath.MYBATIS_CONFIG));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(PortalConfigConst.EnvPath.MYBATIS_RESOURCE));
		return sqlSessionFactoryBean.getObject();
	}

	@Primary
	@Bean(name = PortalConfigConst.Mybatis.SESSION_TEMPLATE)
	public SqlSessionTemplate apiSqlSessionTemplate(SqlSessionFactory exampleSqlSessionFactory) {
		return new SqlSessionTemplate(exampleSqlSessionFactory);
	}
	//Mybatis End

}
