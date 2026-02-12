package validator.exceptions;

import validator.contract.enums.JwtErrorCode;

/**
 * Thrown when JWT is invalid, malformed, or signature verification fails.
 */
public class InvalidJwtException extends JwtValidationException {
	
	private static final long serialVersionUID = 1L ;
	
	public InvalidJwtException(String message) {
        super(message, JwtErrorCode.NOT_YET_VALID) ;
    }

    public InvalidJwtException(String message, Throwable cause) {
        super(message, JwtErrorCode.NOT_YET_VALID, cause) ;
    }
}
