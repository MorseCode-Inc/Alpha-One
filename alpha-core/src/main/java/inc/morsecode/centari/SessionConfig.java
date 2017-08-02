package inc.morsecode.centari;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;

@Configuration
@EnableAutoConfiguration
public class SessionConfig {

    private SessionRepository<ExpiringSession> sessions= new MapSessionRepository();

    @Primary
    @Bean
    public SessionRepository<ExpiringSession> sessionRepository() {
        return sessions;
    }

}
