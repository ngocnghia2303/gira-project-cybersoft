package cybersoft.javabackend.java16giranghia.security.service;

import cybersoft.javabackend.java16giranghia.security.dto.LoginDTO;

public interface AuthService {
	String login(LoginDTO dto);
}
