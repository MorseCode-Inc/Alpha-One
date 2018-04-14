package inc.morsecode.web.security;

import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsrfGrantingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        CsrfToken csrf = (CsrfToken) servletRequest.getAttribute(CsrfToken.class.getName());
        String token = csrf.getToken();
        if (token != null && isAuthenticating(servletRequest)) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            Cookie cookie = new Cookie("CSRF-TOKEN", token);
            cookie.setPath("/");
            cookie.setDomain(servletRequest.getServerName());
            response.addCookie(cookie);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isAuthenticating(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        return request.getRequestURI().equals("/welcome-login");
    }

    @Override
    public void destroy() {}
}

