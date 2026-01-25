package validator.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt-val")
class JwtProperties {
	/**
     * JWKS endpoint URL, e.g. https://auth.mycompany.com/.well-known/jwks.json
     */
    private String jwksUrl;
    private String algorithm;

    public String getJwksUrl() {
        return jwksUrl;
    }

    public void setJwksUrl(String jwksUrl) {
        this.jwksUrl = jwksUrl;
    }

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
    
    
}
