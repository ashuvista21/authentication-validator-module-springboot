package validator.service;

import java.util.Date;
import java.util.function.Function;

import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import validator.exceptions.ExpiredJwtException;
import validator.exceptions.InvalidJwtException;
import validator.exceptions.InvalidSignatureException;
import validator.exceptions.JwtValidationException;
import validator.contract.TokenParser;
import validator.contract.TokenValidator;

public class AuthJwtService implements TokenParser, TokenValidator {
	
	private final DefaultJWTProcessor<SecurityContext> jwtProcessor;

    public AuthJwtService(DefaultJWTProcessor<SecurityContext> jwtProcessor) {
        this.jwtProcessor = jwtProcessor;
    }

    /**
     * Validates token by checking both signature and expiration.
     */
    @Override
    public boolean isTokenValid(String token) {
    	try {
            JWTClaimsSet claims = extractAllClaims(token);
            return !isExpired(claims) && !isNotYetValid(claims);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates only the token signature, without checking claims.
     */
    @Override
    public boolean validateTokenSignature(String token) {
    	try {
            jwtProcessor.process(SignedJWT.parse(token), null) ;
            return true ;
        } catch (Exception e) {
            throw new InvalidSignatureException("JWT signature validation failed", e) ;
        }
    }
    
    private boolean isExpired(JWTClaimsSet claims) {
        return claims.getExpirationTime() != null && claims.getExpirationTime().before(new Date()) ;
    }

    private boolean isNotYetValid(JWTClaimsSet claims) {
        return claims.getNotBeforeTime() != null && claims.getNotBeforeTime().after(new Date()) ;
    }

    /**
     * Strong validation: throws exception instead of returning boolean
     */
    @Override
    public void validateOrThrow(String token) throws JwtValidationException {
        JWTClaimsSet claims = extractAllClaims(token);

        if (isExpired(claims)) {
            throw new ExpiredJwtException("JWT is expired");
        }

        if (isNotYetValid(claims)) {
            throw new InvalidJwtException("JWT not valid yet (nbf claim in future)");
        }
    }

    /**
     * Extract a specific claim using a claims resolver function.
     */
    @Override
    public <T> T extractClaim(String token, Function<JWTClaimsSet, T> claimsResolver) {
        final JWTClaimsSet claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts the "sub" (subject) claim, usually the username or user ID.
     */
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, JWTClaimsSet::getSubject) ;
    }

    /**
     * Extract all claims from the JWT after validating it.
     */
    private JWTClaimsSet extractAllClaims(String token) {
    	try {
            SignedJWT signedJWT = SignedJWT.parse(token) ;
            return jwtProcessor.process(signedJWT, null) ;
        } catch (Exception e) {
            throw new InvalidJwtException("Failed to extract claims from JWT", e) ;
        }
    }

	@Override
	public boolean validateTokenMetadata(String token) {
		// TODO Auto-generated method stub
		return false ;
	}

}
