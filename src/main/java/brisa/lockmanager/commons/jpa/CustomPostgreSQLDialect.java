package brisa.lockmanager.commons.jpa;

import org.hibernate.dialect.PostgreSQL95Dialect;

public class CustomPostgreSQLDialect extends PostgreSQL95Dialect {

    public static final String CURRENT_DATE = "SELECT NOW()";
}
