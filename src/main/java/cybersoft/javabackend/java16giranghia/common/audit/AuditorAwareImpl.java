package cybersoft.javabackend.java16giranghia.common.audit;

import java.util.Optional;


import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		// get current auditor (user is logging)
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// you need check getAuthentication function have return null, right ?
		// ex: the Authentication or null if no authenticationinformation is available
		if(auth == null) {
			// have not user login
			return Optional.ofNullable("");
		}
		
//		// Obtains the currently authenticated principal, or an authentication request token.
		if (auth.getPrincipal() instanceof String) {
			String principal = (String) auth.getPrincipal();
			return Optional.ofNullable(principal);
		}
		
		UserDetails currentAuditor = (UserDetails) auth.getPrincipal();
		return Optional.ofNullable(currentAuditor.getUsername());
	}

}
