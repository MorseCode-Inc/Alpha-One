package inc.morsecode.centari;

import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.filter.CoffeeScriptFilter;
import de.neuland.jade4j.spring.template.SpringTemplateLoader;
import de.neuland.jade4j.spring.view.JadeViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ViewResolver;

@Configuration
public class JadeConfig {

    @Primary
    @Bean
    public SpringTemplateLoader templateLoader() {
        SpringTemplateLoader templateLoader = new SpringTemplateLoader();
        templateLoader.setBasePath("/templates/");
        templateLoader.setResourceLoader(new ResourceLoader() {
            @Override
            public Resource getResource(String s) {
                return new ClassPathResource(s);
            }

            @Override
            public ClassLoader getClassLoader() {
               return this.getClassLoader();
            }

        });
        templateLoader.setEncoding("UTF-8");
        templateLoader.setSuffix(".jade");
        return templateLoader;
    }

    @Bean
    public JadeConfiguration jadeConfiguration() {
        JadeConfiguration configuration = new JadeConfiguration();
        configuration.setCaching(false);
        configuration.setTemplateLoader(templateLoader());
        // this adds the coffescript fitler
        configuration.setFilter("coffeescript", new CoffeeScriptFilter());
        return configuration;
    }

    @Bean
    public ViewResolver viewResolver() {
        JadeViewResolver viewResolver = new JadeViewResolver();
        viewResolver.setConfiguration(jadeConfiguration());
        return viewResolver;
    }

}
