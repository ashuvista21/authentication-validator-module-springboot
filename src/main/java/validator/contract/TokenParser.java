package validator.contract;

import java.util.function.Function;

import com.nimbusds.jwt.JWTClaimsSet;

public interface TokenParser {
	public <T> T extractClaim(String token, Function<JWTClaimsSet, T> claimsResolver) ;
	public String extractUsername(String token) ;
}
