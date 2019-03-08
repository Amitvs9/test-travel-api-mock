package com.av.assignment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Value("${oauth2.token-url}")
	private String tokenUrl;

	@Value("${oauth2.client-id}")
	private String clientId;

	@Value("${oauth2.client-secret}")
	private String clientSecret;

	@Value("${oauth2.grant-type}")
	private String grantType;

	 @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
	    }
	
	@Bean(name = "oAuth2RestTemplate")
	public OAuth2RestTemplate oauth2RestTemplate() {
		return new OAuth2RestTemplate(resource());
	}
	
	 @Bean
	    public PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }
	
	public OAuth2ProtectedResourceDetails  resource() {
		ClientCredentialsResourceDetails   clientCredentialsResourceDetails = new ClientCredentialsResourceDetails ();
        clientCredentialsResourceDetails.setAccessTokenUri(tokenUrl);
        clientCredentialsResourceDetails.setClientId(clientId);
       	clientCredentialsResourceDetails.setClientSecret(clientSecret);
        
        return clientCredentialsResourceDetails;
    }
   
}
