package com.learn.springBootLdapOverview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize) -> authorize.anyRequest().fullyAuthenticated())
				.formLogin(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(contextSource());
	}

	@Bean
	public LdapContextSource contextSource() {
		LdapContextSource ldapContextSource = new LdapContextSource();
		ldapContextSource.setUrl("ldap://localhost:10389");
		ldapContextSource.setUserDn("uid=admin,ou=system");
		ldapContextSource.setPassword("secret");
		return ldapContextSource;
	}

	@Bean
	AuthenticationManager authManager(BaseLdapPathContextSource source) {
		LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(source);
		factory.setUserDnPatterns("cn={0},ou=users,ou=system");
		return factory.createAuthenticationManager();
	}
}
