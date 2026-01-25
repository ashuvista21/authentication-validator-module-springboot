package validator.configs;

import java.net.MalformedURLException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import validator.service.AuthJwtService;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnProperty(prefix = "jwt-val", name = "enabled", havingValue = "true", matchIfMissing = true)
public class AuthValidatorAutoConfiguration {
	 @Bean
	 @ConditionalOnMissingBean
	 AuthJwtService authJwtService(JwtProperties properties) throws MalformedURLException {
		 try {
		        AuthKeyResolver resolver = new AuthKeyResolver(properties);
		        return new AuthJwtService(resolver.getJwtProcessor());
		    } catch (MalformedURLException e) {
		        throw new IllegalStateException("Invalid JWKS URL in jwt-val properties", e);
		    }
	 }
}
