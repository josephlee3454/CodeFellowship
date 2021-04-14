package com.example.codefellowshiptwo.configs;


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
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImplementation userDetailsService;

    @Bean  // reusable component that loads once, reusable passwordEncoder
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override // overloads the `configure method`
        public void configure(HttpSecurity httpSecurity) throws Exception {
    //        We do all the work to decide how users can access the site
            httpSecurity
                    .cors().disable()
                    .csrf().disable() // cross site resource forgery (heroku needs this)

                    .authorizeRequests()
                    .antMatchers("/").permitAll() // allow access here
                    .antMatchers("/codeUser", "/login").permitAll()
                    .anyRequest().authenticated()

                    .and()
                    .formLogin()
                    .defaultSuccessUrl("/codeUser")
                    .loginPage("/login") // any unauthorised request goes here

                    .and()
                    .logout()
                    .logoutSuccessUrl("/"); // allows you to visit /logout and be logged out

        }
    }


