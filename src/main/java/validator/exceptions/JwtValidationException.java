package validator.exceptions;

/**
 * Base exception for all JWT validation errors.
 */
public class JwtValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
    public JwtValidationException(String message) {
        super(message);
    }

    public JwtValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
