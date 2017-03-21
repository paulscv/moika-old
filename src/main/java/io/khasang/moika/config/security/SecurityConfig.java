package io.khasang.moika.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/client").permitAll()
                .antMatchers("/company/**").permitAll()
                //добавлен для тестирования validator'ов
                //.antMatchers("/client/**").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/users/create*").permitAll()

                .antMatchers("/create*").access("hasAnyRole('ADMIN')")
                .antMatchers("/admin/**").access("hasAnyRole('ADMIN','USER')")
                .antMatchers("/db/**").access("hasRole('DB')")
                //.antMatchers("/**").fullyAuthenticated()
                .and().csrf().disable().formLogin().defaultSuccessUrl("/", false)
                //http://www.mkyong.com/spring-security/customize-http-403-access-denied-page-in-spring-security/
                .and().exceptionHandling().accessDeniedPage("/accessDenied");
    }
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}