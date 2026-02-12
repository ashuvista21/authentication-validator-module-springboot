package validator.exceptions;

import validator.contract.enums.JwtErrorCode;

/**
 * Base exception for all JWT validation errors.
 */
public class JwtValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L ;
	private final JwtErrorCode errorCode ;
	
    public JwtValidationException(String message, JwtErrorCode errorCode) {
        super(message) ;
        this.errorCode = errorCode ;
    }

    public JwtValidationException(String message, JwtErrorCode errorCode, Throwable cause) {
        super(message, cause) ;
        this.errorCode = errorCode ;
    }
    
    public JwtErrorCode getErrorCode() {
        return errorCode;
    }
}
