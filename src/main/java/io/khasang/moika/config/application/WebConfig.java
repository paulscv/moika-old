package io.khasang.moika.config.application;

import io.khasang.moika.annotation.AddMenuPathAnnotationBeanPostProcessor;
import io.khasang.moika.util.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan({
        "io.khasang.moika.controller",
        "io.khasang.moika.config",
        "io.khasang.moika.dao",
        "io.khasang.moika.service",
        "io.khasang.moika.model",
        "io.khasang.moika.validator",
        "io.khasang.moika.util"
})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    javax.validation.Validator validator;

    public WebConfig() {

    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setContentType("text/html; charset=utf-8");
        return viewResolver;
    }

    @Bean
    public AddMenuPathAnnotationBeanPostProcessor addMenuPathAnnotationBeanPostProcessor(){
        return new AddMenuPathAnnotationBeanPostProcessor();
    }

    @Bean
    public MenuMapper menuMapper(){
        return new MenuMapper();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/views/fonts/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/views/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/views/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/views/images/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/views/resources/");
    }


    /**
     * DRS Это переопределение приведёт к тому, что бин mvcValidator, при помощи которого все MVC-контроллеры
     * по-умолчанию валидируют объекты, передаваемые через параметры методов маппинга, аннотированные как @Valid,
     * будет указывать на наш кастомный валидатор, настроенный в конфигурации приложения (см. класс AppConfig),
     * а не на экземпляр по-умолчанию, который в противном случае автоматически создаётся в недрах Spring'а.
     * @return
     */
    @Override
    public Validator getValidator() {
        return (Validator) validator;
    }
}