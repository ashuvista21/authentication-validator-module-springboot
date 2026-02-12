package validator.exceptions;

import validator.contract.enums.JwtErrorCode;

public class InvalidPublicKeyURL extends JwtValidationException {

	private static final long serialVersionUID = 1L ;
	
	public InvalidPublicKeyURL(String message) {
        super(message, JwtErrorCode.MALFORMED) ;
    }

    public InvalidPublicKeyURL(String message, Throwable cause) {
        super(message, JwtErrorCode.MALFORMED , cause) ;
    }

}
