package inc.morsecode.web.security;

import org.springframework.stereotype.Component;

/**
 * Created by morsecode on 7/18/2017.
 */
@Component
public class TokenManager {

    public CsrfToken createCsrf() {
        return new CsrfToken();
    }

}
