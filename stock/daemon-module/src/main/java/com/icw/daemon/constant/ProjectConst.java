package com.icw.daemon.constant;

public class ProjectConst {
	public static final String DATASOURCE = " stockSource";

	public class JPA {
		public static final String ENTITY_MANAGER_FACTORY_REF = " portalJpaEntityManagerFactory";
		public static final String TRANSACTION_MANAGER_REF = " portalTransactionManager";
	}

	public class EnvPath {
		//JPA
		private static final String basePackage = "com.icw.daemon";
		public static final String REPOSITORY_PACKAGE = basePackage + ".repository";
		public static final String ENTITY_PACKAGE = "com.icw.common.entity";
	}

	public class ApplicationConf {
		public static final String DATASOURCE = "spring.stock.datasource";
	}
}

