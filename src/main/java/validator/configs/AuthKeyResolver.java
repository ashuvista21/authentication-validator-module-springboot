package validator.configs;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.JWKSourceBuilder;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext ;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

class AuthKeyResolver {
	
	private final JwtProperties props;
	private final DefaultJWTProcessor<SecurityContext> jwtProcessor;
	
	public AuthKeyResolver(JwtProperties props) throws MalformedURLException {
        this.props = props;
        this.jwtProcessor = buildProcessor();
    }

	private DefaultJWTProcessor<SecurityContext> buildProcessor() throws MalformedURLException {
		if (props.getJwksUrl() == null || props.getJwksUrl().isBlank()) {
            throw new IllegalStateException("Invalid jwks-url provided");
        }
        if (props.getAlgorithm() == null || props.getAlgorithm().isBlank()) {
            throw new IllegalStateException("Invalid jwks-alg provided");
        }
	   
	   URL url = URI.create(props.getJwksUrl()).toURL();
	   DefaultResourceRetriever retriever = new DefaultResourceRetriever(2000, 2000, 512_000);
	   JWKSource<SecurityContext> keySource = JWKSourceBuilder.create(url, retriever)
			   .cache(5 * 60 * 1000L, 30 * 1000L) // optional: cache TTL 5 min, refresh ahead 30s
	           .retrying(true) // optional: auto retry on network errors
	           .build();
	        
	   // Dynamically pick the algorithm from properties
       JWSAlgorithm alg = JWSAlgorithm.parse(props.getAlgorithm());
	        
	   JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(alg, keySource);
	   DefaultJWTProcessor<SecurityContext> processor = new DefaultJWTProcessor<>();
	   processor.setJWSKeySelector(keySelector);
	   
	   return processor ; 
	}

	public DefaultJWTProcessor<SecurityContext> getJwtProcessor() {
		return jwtProcessor;
	}
}
