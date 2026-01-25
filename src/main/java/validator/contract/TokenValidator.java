package validator.contract;

import validator.exceptions.JwtValidationException;

public interface TokenValidator {
	public boolean isTokenValid(String token) ;
	public boolean validateTokenSignature(String token) ;
	public void validateOrThrow(String token) throws JwtValidationException ;
	public boolean validateTokenMetadata(String token) ;
}
