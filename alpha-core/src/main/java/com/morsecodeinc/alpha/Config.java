package com.morsecodeinc.alpha;

import com.morsecodeinc.web.security.CsrfToken;
import de.neuland.jade4j.spring.template.SpringTemplateLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by morsecode on 7/16/2017.
 */
@ComponentScan(
{"com.morsecodeinc.alpha"
,"com.morsecodeinc.web.resource"
,"com.morsecodeinc.web.service"
,"com.morsecodeinc.web.control"
,"com.morsecodeinc.web.security"
,"com.morsecodeinc.web.helper"
})
@Configuration
@EnableAutoConfiguration
public class Config extends WebMvcConfigurerAdapter {

    private static final Logger LOG= LoggerFactory.getLogger(Config.class);

    @Autowired
    SpringTemplateLoader jade;

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
