package com.av.assignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Value("${oauth2.token-url:http://localhost:8080/oauth/token}")
	private String tokenUrl;

	@Value("${oauth2.client-id:travel-api-client}")
	private String clientId;

	@Value("${oauth2.client-secret:psw}")
	private String clientSecret;

	@Value("${oauth2.grant-type:client_credentials}")
	private String grantType;

	
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception { http
	 * .csrf().disable() .authorizeRequests().anyRequest().authenticated() .and()
	 * .httpBasic(); }
	 * 
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { auth.inMemoryAuthentication() .withUser("admin")
	 * .password("password") .roles("USER"); }
	 */
	
	@Bean(name = "oAuth2RestTemplate")
	public OAuth2RestTemplate oauth2RestTemplate() {
		return new OAuth2RestTemplate(resource());
	}
	
	public OAuth2ProtectedResourceDetails  resource() {
		ClientCredentialsResourceDetails   clientCredentialsResourceDetails = new ClientCredentialsResourceDetails ();
        clientCredentialsResourceDetails.setAccessTokenUri(tokenUrl);
        clientCredentialsResourceDetails.setClientId(clientId);
       	clientCredentialsResourceDetails.setClientSecret(clientSecret);
        
        return clientCredentialsResourceDetails;
    }
   
}
