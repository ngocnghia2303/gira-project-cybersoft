package cybersoft.javabackend.java16giranghia.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cybersoft.javabackend.java16giranghia.user.dto.GiraUserDTO;
import cybersoft.javabackend.java16giranghia.user.mapper.GiraUserMapper;
import cybersoft.javabackend.java16giranghia.user.model.GiraUser;
import cybersoft.javabackend.java16giranghia.user.repository.GiraUserRepository;

@Service
public class GiraUserServiceImpl implements GiraUserService {
	@Autowired
	private GiraUserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public GiraUserDTO createNewUser(GiraUserDTO dto) {
		GiraUser user = GiraUserMapper.INSTANCE.toModel(dto);
		
		// Encode password before save
		user.setPassword(encoder.encode(dto.getPassword()));
		
		GiraUser newUser = repository.save(user);
		
		// After save password encrypted then set password == null or "" for return
		newUser.setPassword("");
		
		return GiraUserMapper.INSTANCE.toDTO(newUser);
	}
}
