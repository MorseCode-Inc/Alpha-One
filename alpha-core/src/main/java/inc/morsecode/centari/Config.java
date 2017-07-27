package inc.morsecode.centari;

import inc.morsecode.web.resource.FileFormStore;
import inc.morsecode.web.security.CsrfToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by morsecode on 7/16/2017.
 */
@ComponentScan(
{"inc.morsecode.centari"
,"inc.morsecode.web.resource"
,"inc.morsecode.web.service"
,"inc.morsecode.web.control"
,"inc.morsecode.web.security"
,"inc.morsecode.web.helper"
})
@Configuration
@EnableAutoConfiguration
public class Config extends WebMvcConfigurerAdapter {

    private static final Logger LOG= LoggerFactory.getLogger(Config.class);

    @Autowired
    private FileFormStore formStore;

    @Autowired
    public ViewResolver viewResolver;

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

    @Bean
    public FormStore formStore() {
        return formStore;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver);
    }

}
