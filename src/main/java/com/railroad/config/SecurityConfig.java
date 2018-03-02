package com.railroad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final
    DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new ShaPasswordEncoder(256))
                .usersByUsernameQuery(
                        "select login username, password, enabled from user where login=?")
                .authoritiesByUsernameQuery(
                        "select u.login username, r.role_name role from user_role u left outer join role r on u.role_id = r.role_id  where  u.login = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/station").access("hasRole('admin')")
                .and().formLogin() //login configuration
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/")
                .and()
                .logout().logoutSuccessUrl("/login?logout") //logout configuration
                .logoutRequestMatcher(new AntPathRequestMatcher("/j_spring_security_logout"))
                .and()
                .exceptionHandling().accessDeniedPage("/login")
                .and();
        http.csrf();
        http.httpBasic();
    }

}
