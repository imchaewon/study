package com.example.constant;

public class PortalConfigConst {
    public static final String DATASOURCE = " portalSource";

    public class JPA {
        public static final String ENTITY_MANAGER_FACTORY_REF = " portalJpaEntityManagerFactory";
        public static final String TRANSACTION_MANAGER_REF = " portalTransactionManager";
    }

    public class Mybatis {
        public static final String SESSION_FACTORY = " exampleSqlSessionFactory";
        public static final String SESSION_TEMPLATE = " exampleSqlSessionTemplate";
    }

    public class EnvPath {
        //JPA
        private static final String basePackage = "com.example";
        public static final String REPOSITORY_PACKAGE = basePackage + ".repository.portal.jpa";
        public static final String ENTITY_PACKAGE = "com.okestro.vista.common.entity.portal";

        //Mybatis
        public static final String MYBATIS_BASE_PACKAGE = basePackage + ".repository.mybatis";
        public static final String MYBATIS_CONFIG = "classpath:mybatis/mybatis-config.xml";
        public static final String MYBATIS_RESOURCE = "classpath:example/*.xml";

    }

    public class ApplicationConf {
        public static final String DATASOURCE = "spring.example.datasource";
    }
}
