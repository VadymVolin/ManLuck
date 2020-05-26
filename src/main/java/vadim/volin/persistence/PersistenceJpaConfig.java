package vadim.volin.persistence;


import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({"vadim.volin.model", "vadim.volin.services"})
@EnableJpaRepositories(basePackages = "vadim.volin.repository",
        entityManagerFactoryRef = "entityManagerFactoryBean",
        transactionManagerRef = "platformTransactionManager")
@PropertySource(value = "classpath:/application.properties")
public class PersistenceJpaConfig {

    @Autowired
    Environment environment;

    @Bean(name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("vadim.volin.model");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        return entityManagerFactoryBean;
    }

    @Bean("platformTransactionManager")
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", this.environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", this.environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show_sql", this.environment.getProperty("spring.jpa.show-sql"));
        properties.put("hibernate.current_session_context_class", this.environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        properties.put("hibernate.jdbc.lob.non_contextual_creation", this.environment.getProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation"));
        properties.put("hibernate.format_sql", this.environment.getProperty("spring.jpa.properties.hibernate.format_sql"));
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", this.environment.getProperty("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults"));
        properties.put("hibernate.batch_versioned_data", "true");
        properties.put("hibernate.enable_lazy_load_no_trans", "true");
        properties.put("hibernate.default_schema", "public");
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(this.environment.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(this.environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(this.environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(this.environment.getProperty("spring.datasource.password"));
        return dataSource;
    }

}
