package inc.morsecode.centari;

import inc.morsecode.web.security.CsrfGrantingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by morsecode on 7/18/2017.
 */
@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableSpringHttpSession
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/", "/js/**", "/css/**", "/img/**", "/favicon.ico").permitAll()
            // for now
            // .antMatchers("**/**").permitAll()
            // white list of public pages
            .antMatchers("/register", "/terms", "/privacy").permitAll()
            .antMatchers("/goodbye").permitAll()
            .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage("/welcome-login")
                    .usernameParameter("username").passwordParameter("password")
                    .failureForwardUrl("/welcome-invalid-login")
                    .successForwardUrl("/welcome")
                    .permitAll()
            .and()
                .logout()
                    .logoutUrl("/goodbye")
                    .permitAll()
                ;
            //.and().csrf();
                // .addFilterAfter(new CsrfGrantingFilter(), SessionManagementFilter.class)
            //.and().csrf();
    }



    @Bean
    public CsrfConfigurer csrfConfig() {
        return new CsrfConfigurer(getApplicationContext());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("admin").password("_admin").roles("USER").and()
            .withUser("user").password("test").roles("USER");
    }


    @Bean
    public HeaderHttpSessionStrategy sessionStrategy() {

        HeaderHttpSessionStrategy strategy= new HeaderHttpSessionStrategy();

        strategy.setHeaderName("X-CSRF-TOKEN");

        return strategy;
    }

}
