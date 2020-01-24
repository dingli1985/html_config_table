package exception;

import org.apache.shiro.authc.AuthenticationException;

public class LoginException extends AuthenticationException {

    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
