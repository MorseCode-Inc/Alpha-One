package inc.morsecode.centari;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.SessionRepositoryFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.EnumSet;

@WebListener
public class Initializer implements ServletContextListener {

    @Autowired
    SessionRepository<ExpiringSession> sessions;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        sce.getServletContext().addFilter("springSessionFilter",
                new SessionRepositoryFilter<>(sessions))
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}
