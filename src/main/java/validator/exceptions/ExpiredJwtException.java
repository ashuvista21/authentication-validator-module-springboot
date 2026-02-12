package validator.exceptions;

import validator.contract.enums.JwtErrorCode;

/**
 * Thrown when JWT is expired.
 */
public class ExpiredJwtException extends JwtValidationException {
	
	private static final long serialVersionUID = 1L ;
	
    public ExpiredJwtException(String message) {
        super(message, JwtErrorCode.EXPIRED) ;
    }

    public ExpiredJwtException(String message, Throwable cause) {
        super(message, JwtErrorCode.EXPIRED, cause) ;
    }
}
