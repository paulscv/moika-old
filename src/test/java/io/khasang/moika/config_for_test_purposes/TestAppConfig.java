package io.khasang.moika.config_for_test_purposes;

import io.khasang.moika.config.AppConfig;

import io.khasang.moika.service.impl.RostislavDataAccessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Rostislav Dublin
 * @since Конфигурация для юнит-тестирования, не подключающая ничего лишнего.
 */
@Configuration
//@EnableCaching
@ComponentScan(
        basePackages = {
                "io.khasang.moika.config",
                "io.khasang.moika.dao",
                "io.khasang.moika.service",
                "io.khasang.moika.validator.**",
                "io.khasang.moika.validator.user",
                "io.khasang.moika.util"
        },
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "io.khasang.moika.config.application.*"),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                        RostislavDataAccessServiceImpl.class,
                       // AKovalevDataAccessServiceImpl.class,
                        AppConfig.class})
        })
//@EnableCaching
@PropertySource(value = {"classpath:util.properties", "classpath:auth.properties"})
public class TestAppConfig {
    final private Environment environment;

    @Autowired
    public TestAppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Environment getEnvironment() {
        return environment;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.postgresql.driverClass"));
        dataSource.setUrl(environment.getProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getProperty("jdbc.postgresql.username"));
        dataSource.setPassword(environment.getProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    /**
     * DRS JSR380(JSR303) Validator setup
     *
     * @return validator
     */
    @Bean(name="validator")
    @Primary
    public javax.validation.Validator localValidatorFactoryBean() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    /**
     * DRS JSR380(JSR303) Validator message sources location  setup
     *
     * @return message sources location
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setBasename("messages/messages");
        return messageSource;
    }
}
