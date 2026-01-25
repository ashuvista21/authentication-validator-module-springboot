package validator.exceptions;

/**
 * Thrown when JWT is expired.
 */
public class ExpiredJwtException extends JwtValidationException {
	
	private static final long serialVersionUID = 1L;
	
    public ExpiredJwtException(String message) {
        super(message);
    }

    public ExpiredJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
