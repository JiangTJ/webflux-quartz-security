package com.example.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapUserDetailsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

@EnableScheduling
@EnableWebFluxSecurity
@SpringBootApplication
public class QuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}

	@Bean
	SecurityWebFilterChain springWebFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeExchange()
				.pathMatchers("/admin/**").hasRole("ADMIN")
				.pathMatchers("/users/{user}/**").access(this::currentUserMatchesPath)
				.anyExchange().authenticated()
				.and()
				.build();
	}

	private Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication, AuthorizationContext context) {
		return authentication
				.map( a -> context.getVariables().get("user").equals(a.getName()))
				.map(AuthorizationDecision::new);
	}

	@Bean
	public MapUserDetailsRepository userDetailsRepository() {
		UserDetails rob = User.withUsername("rob").password("rob").roles("USER").build();
		UserDetails admin = User.withUsername("admin").password("admin").roles("USER","ADMIN").build();
		return new MapUserDetailsRepository(rob, admin);
	}


}
