package com.library;

import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LibraryConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Value("${app.security.admin_role}")
    private String ADMIN_ROLE;

    @Value("${app.security.student_role}")
    private String STUDENT_ROLE;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userService);
    }


    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET,"/book/**").hasAnyAuthority(ADMIN_ROLE,STUDENT_ROLE)
                .antMatchers("/book/**").permitAll()
                .antMatchers(HttpMethod.POST,"/student/**").hasAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.GET,"/student/id/**").hasAuthority(ADMIN_ROLE)
                .antMatchers("/student/**").hasAuthority(STUDENT_ROLE)
                .antMatchers("/issue_book").hasAuthority(STUDENT_ROLE)
                .and()
                .httpBasic();
                //.formLogin();


    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


}
