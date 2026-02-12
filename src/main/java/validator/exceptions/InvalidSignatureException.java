package validator.exceptions;

import validator.contract.enums.JwtErrorCode;

public class InvalidSignatureException extends JwtValidationException {
	
	private static final long serialVersionUID = 1L ;
	
	public InvalidSignatureException(String message) {
        super(message, JwtErrorCode.INVALID_SIGNATURE) ;
    }

    public InvalidSignatureException(String message, Throwable cause) {
        super(message, JwtErrorCode.INVALID_SIGNATURE, cause) ;
    }

}
