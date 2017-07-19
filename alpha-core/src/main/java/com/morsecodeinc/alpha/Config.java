package com.morsecodeinc.alpha;

import com.morsecodeinc.web.security.CsrfToken;
import de.neuland.jade4j.spring.template.SpringTemplateLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by morsecode on 7/16/2017.
 */
@Configuration
@EnableAutoConfiguration
public class Config extends WebMvcConfigurerAdapter {

    private static final Logger LOG= LoggerFactory.getLogger(Config.class);

    @Autowired
    SpringTemplateLoader jade;
    public Config() {
        LOG.info("Loading Configuration");
    }

    @Bean(name="jade4jTemplateLoader")
    @Primary
    public SpringTemplateLoader jade4jTemplateLoader() {
        SpringTemplateLoader jade= new de.neuland.jade4j.spring.template.SpringTemplateLoader() {

            @Override
            public Reader getReader(String name) throws IOException {
                try {
                    return super.getReader(name);
                } catch (IOException iox) {
                    LOG.error("Failed to get reader for template: "+ iox.getMessage(), iox);
                    throw iox;
                }
            }

        };

        jade.setBasePath("src/main/resources/templates");
        return jade;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(csrfTokenAddingInterceptor());
    }

    @Bean
    public HandlerInterceptor csrfTokenAddingInterceptor() {
        return new HandlerInterceptorAdapter() {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView view) {
                CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

                if (token != null) {
                    view.addObject("csrftoken", token);
                }
            }
        };
    }

}
