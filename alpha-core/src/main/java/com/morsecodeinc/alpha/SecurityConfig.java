package com.morsecodeinc.alpha;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by morsecode on 7/18/2017.
 */
@Configuration
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/", "/js/**", "/css/**", "/img/**", "/favicon.ico").permitAll()
            // for now
            .antMatchers(".*").permitAll()
                //.and()
                //.csrf();
        ;
        /* future:
            // white list of public pages
            .antMatchers("/signup", "/terms", "/privacy").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .permitAll()
            .and()
            .csrf();
        */
    }


    @Bean
    public CsrfConfigurer csrfConfig() {
        return new CsrfConfigurer(getApplicationContext());
    }
}
