package cybersoft.javabackend.java16giranghia.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cybersoft.javabackend.java16giranghia.security.jwt.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthorizationFilter jwtAuthFilter;
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(); // research Bcrypt
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(getPasswordEncoder());
	}
	
	@Override
	
	protected void configure(HttpSecurity http) throws Exception {
		// config Cors
		http.cors();
		// config session (dont use session)
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// config CSRF (disable csrf)
		http.csrf().disable();
		
		// config jwt filter
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		// config specified api authentication (management api)
		http.antMatcher("/api/v1/**").authorizeRequests()
		.antMatchers("/api/v1/auth/login").permitAll()
		.antMatchers("/api/v1/users").permitAll()
		.antMatchers("/api/v1/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated();
	}
	
}
