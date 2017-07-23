package inc.morsecode.web.security;

import java.security.SecureRandom;

/**
 * Cross-Site Request Forgery (CSRF) Token
 *
 * Created by morsecode on 7/18/2017.
 */
public class CsrfToken {

    private String token;

    /**
     * Note: this constructor is scoped to the package
     * @param token
     */
    CsrfToken(String token) {
        this.token= token;
    }

    /**
     * Auto-generate a new random token
     */
    public CsrfToken() {
        SecureRandom rnd= new SecureRandom();
        byte[] bytes= new byte[512];
        rnd.nextBytes(bytes);
        this.token= bytes.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CsrfToken) {
            return token.equals(((CsrfToken) obj).token);
        }
        return super.equals(obj);
    }
}
