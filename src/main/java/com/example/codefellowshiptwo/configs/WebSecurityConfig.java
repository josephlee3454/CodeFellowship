package com.example.codefellowshiptwo.configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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
//pasword encoder
    @Bean  // reusable component that loads once, reusable passwordEncoder
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

// conficuring for auth
    @Override
    public void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

// configuring for http access
@Override // overloads the `configure method`
public void configure(HttpSecurity httpSecurity) throws Exception {
//        We do all the work to decide how users can access the site
    httpSecurity
            .cors().disable()
            .csrf().disable() // cross site resource forgery (heroku needs this)

            .authorizeRequests()
            .antMatchers("/").permitAll() // allow access here
            .antMatchers("/codeUser", "/login").permitAll()
            .antMatchers(HttpMethod.GET, "/codeUser/*").permitAll()
            .anyRequest().authenticated()

            .and()
            .formLogin()
            .defaultSuccessUrl("/")
            .loginPage("/login") // any unauthorised request goes here

            .and()
            .logout()
            .logoutSuccessUrl("/codeUser"); // allows you to visit /logout and be logged out

}
}
