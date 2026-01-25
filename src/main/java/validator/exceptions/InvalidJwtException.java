package validator.exceptions;

/**
 * Thrown when JWT is invalid, malformed, or signature verification fails.
 */
public class InvalidJwtException extends JwtValidationException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidJwtException(String message) {
        super(message);
    }

    public InvalidJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
