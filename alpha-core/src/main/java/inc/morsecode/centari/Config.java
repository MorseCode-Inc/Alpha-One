package inc.morsecode.centari;

import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.spring.view.JadeViewResolver;
import inc.morsecode.web.resource.FileFormStore;
import inc.morsecode.web.security.CsrfToken;
import de.neuland.jade4j.spring.template.SpringTemplateLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.FileStore;
import java.util.EnumSet;

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
//public class Config extends WebMvcConfigurerAdapter implements WebApplicationInitializer {
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


    /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**.jade")
            .addResourceLocations("/templates/**")
            .addResourceLocations("/**")
        ;
    }
    */

    // WebApplicationInitializer

    /*
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.getEnvironment().setDefaultProfiles("production");
        rootContext.scan("com.example.config");

        container.addListener(new ContextLoaderListener(rootContext));

        ServletRegistration.Dynamic servlet = container.addServlet("DispatcherServlet", DispatcherServlet.class);
        servlet.setInitParameter("contextConfigLocation", "");
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        FilterRegistration charEncodingfilterReg = container.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
        charEncodingfilterReg.setInitParameter("encoding", "UTF-8");
        charEncodingfilterReg.setInitParameter("forceEncoding", "true");
        charEncodingfilterReg.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy("wroFilter");
        delegatingFilterProxy.setTargetFilterLifecycle(true);

        FilterRegistration wroFilterReg = container.addFilter("WebResourceOptimizer", delegatingFilterProxy);
        wroFilterReg.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/resources/opt/*");
    }
    */

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver);
    }

}
