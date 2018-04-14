package inc.morsecode.centari;

import inc.morsecode.web.data.FormStore;
import inc.morsecode.web.resource.FileFormStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormConfiguration {

    @Autowired
    private FileFormStore formStore;

    @Bean
    public FormStore formStore() {
        return formStore;
    }

}
