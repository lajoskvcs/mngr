package app.config;

import app.helper.PropertyResolver;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * This class contains the {@link org.springframework.context.annotation.Bean Beans}  for the project.
 * It also configure the Spring MVC.
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "app")
public class MNGRConfiguration extends WebMvcConfigurerAdapter{

    /**
     * This method returns a {@link javax.sql.DataSource DataSource} from the given properties.
     * @return The configured {@link javax.sql.DataSource DataSource}
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties props = PropertyResolver.getProperties("application.properties");

        dataSource.setDriverClassName(props.getProperty("datasource_driver_class_name", "com.mysql.jdbc.Driver"));
        dataSource.setUrl(props.getProperty("datasource_url","jdbc:mysql://localhost:3306/mngr?useSSL=false"));
        dataSource.setUsername(props.getProperty("datasource_username","root"));
        dataSource.setPassword(props.getProperty("datasource_password",""));
        return dataSource;
    }

    /**
     * This method returns a {@link org.springframework.orm.hibernate5.HibernateTransactionManager HibernateTransactionManager}
     * with the configured {@link org.hibernate.SessionFactory SessionFactory}
     *
     * @param sessionFactory the {@link org.hibernate.SessionFactory SessionFactory} what the {@link org.springframework.orm.hibernate5.HibernateTransactionManager HibernateTransactionManager} will use
     * @return The configured {@link org.springframework.orm.hibernate5.HibernateTransactionManager HibernateTransactionManager}
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }

    /**
     * This method sets up the {@link org.springframework.orm.hibernate5.LocalSessionFactoryBean LocalSessionFactoryBean}.
     * With this {@link org.springframework.context.annotation.Bean Bean}, the {@link org.hibernate.SessionFactory SessionFactory} can be {@link org.springframework.beans.factory.annotation.Autowired Autowired} anywhere.
     * @return The configured {@link org.springframework.orm.hibernate5.LocalSessionFactoryBean LocalSessionFactoryBean}
     */
    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        Properties props = PropertyResolver.getProperties("application.properties");
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect",props.getProperty("hibernate_dialect","org.hibernate.dialect.MySQLDialect"));
        properties.setProperty("hibernate.show_sql",props.getProperty("hibernate_show_sql","true"));
        properties.setProperty("hibernate.hbm2ddl.auto","update");

        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("app.model");
        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }
}
