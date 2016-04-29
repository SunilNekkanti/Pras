package config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Java based application context configuration class.
 * <p>
 * Including data source and transaction manager configuration.
 *
 * @author sarath
 */
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:jdbc.properties", "classpath:hibernate.properties" })
@ComponentScan(value = "com.pfchoice")
public class ApplicationContext {
	
	public static final String HIBERNATE_CONNECTION_AUTOCOMMIT = "hibernate.connection.autocommit";

	public static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";

	public static final String HIBERNATE_HBM2DDL_AUTO  = "hibernate.hbm2ddl.auto";

	public static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	public static final String HIBERNATE_CIRRENT_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";

	public static final String HIBERNATE_DIALECT = "hibernate.dialect";

	public static final String PACKAGE_TO_SCAN = "com.pfchoice.core.entity";

	public static final String MAXIMUM_POOL_SIZE = "hibernate.hikari.maximumPoolSize";

	public static final String IDLE_TIMEOUT = "hibernate.hikari.idleTimeout";

	public static final String MAX_LIFE_TIME = "hibernate.hikari.maxLifeTime";

	@Autowired
	private Environment env;

	/**
	 * @return
	 */
	@Bean(destroyMethod = "close")
	@Autowired
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setConnectionTestQuery("SELECT 1;");
		dataSource.setMaximumPoolSize(15);
		dataSource.setAutoCommit(false);
		// ------------------------------
		return dataSource;
	}

	/**
	 * @param datasouce
	 * @return
	 */
	@Bean
	@Autowired
	public LocalSessionFactoryBean sessionFactory(DataSource datasouce) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(datasouce);
		sessionFactory.setPackagesToScan(PACKAGE_TO_SCAN);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	/**
	 * @return
	 */
	private Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
		hibernateProperties.put(HIBERNATE_CIRRENT_SESSION_CONTEXT_CLASS,
				env.getProperty(HIBERNATE_CIRRENT_SESSION_CONTEXT_CLASS));
		hibernateProperties.put(HIBERNATE_CONNECTION_AUTOCOMMIT, env.getProperty(HIBERNATE_CONNECTION_AUTOCOMMIT));
		hibernateProperties.put(HIBERNATE_FORMAT_SQL, env.getProperty(HIBERNATE_FORMAT_SQL));
		hibernateProperties.put(HIBERNATE_HBM2DDL_AUTO, env.getProperty(HIBERNATE_HBM2DDL_AUTO));
		hibernateProperties.put(HIBERNATE_SHOW_SQL, env.getProperty(HIBERNATE_SHOW_SQL));
		hibernateProperties.put(MAXIMUM_POOL_SIZE, env.getProperty(MAXIMUM_POOL_SIZE));
		hibernateProperties.put(IDLE_TIMEOUT, env.getProperty(IDLE_TIMEOUT));
		hibernateProperties.put(MAX_LIFE_TIME, env.getProperty(MAX_LIFE_TIME));

		return hibernateProperties;

	}

	/**
	 * @param sessionFactory
	 * @return
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager(sessionFactory);
		return txManager;
	}

}
