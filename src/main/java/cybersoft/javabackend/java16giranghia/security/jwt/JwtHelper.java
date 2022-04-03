package cybersoft.javabackend.java16giranghia.security.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtHelper {
	public String generateJwtToken(Authentication authentication) {
		if(authentication == null) {
			return null;
		}
		
		// what is the authentication ?
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Date currentDate = new Date();
		
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(currentDate)
				.setExpiration(new Date(currentDate.getTime() + 86400)) // thoi gian token ton tai trong 1 ngay
				.signWith(SignatureAlgorithm.HS512, "whatissecret")
				.compact();
	}
}
