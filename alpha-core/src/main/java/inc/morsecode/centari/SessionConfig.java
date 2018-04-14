package inc.morsecode.centari;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.SessionTrackingMode;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class SessionConfig extends AbstractHttpSessionApplicationInitializer{

    private SessionRepository<ExpiringSession> sessions= new MapSessionRepository();

    @Primary
    @Bean
    public SessionRepository<ExpiringSession> sessionRepository() {
        return sessions;
    }

    public Set<SessionTrackingMode> getTrackingModes() {
        Set<SessionTrackingMode> modes= new HashSet<>();
        // modes.add(SessionTrackingMode.SSL);
        modes.add(SessionTrackingMode.COOKIE);
        return modes;
    }

    @Override
    protected void beforeSessionRepositoryFilter(ServletContext servletContext) {
        System.out.println(" == session_filter == "+ servletContext);
        servletContext.setSessionTrackingModes(getTrackingModes());
    }

    @Override
    protected void afterSessionRepositoryFilter(ServletContext servletContext) {
        System.out.println(" == /session_filter == "+ servletContext);
    }

}
