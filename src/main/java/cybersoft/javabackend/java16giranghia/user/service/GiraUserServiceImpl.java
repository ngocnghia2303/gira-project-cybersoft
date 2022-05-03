package cybersoft.javabackend.java16giranghia.user.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cybersoft.javabackend.java16giranghia.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giranghia.role.mapper.GiraRoleMapper;
import cybersoft.javabackend.java16giranghia.role.model.GiraGroup;
import cybersoft.javabackend.java16giranghia.role.model.GiraRole;
import cybersoft.javabackend.java16giranghia.user.dto.GiraUserDTO;
import cybersoft.javabackend.java16giranghia.user.dto.GiraUserRolesDTO;
import cybersoft.javabackend.java16giranghia.user.dto.GiraUserWithRolesDTO;
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

	@Override
	public List<GiraUserRolesDTO> findUserWithRolesByUsername(String username) {
		return repository.findUserWithRolesByUsername(username);
	}

	@Override
	public GiraUserWithRolesDTO findUserWithRolesByUsernameUsingJoin(String username) {
		GiraUser user = repository.findUserWithRolesByUsernameEntityGraph(username);
		
		if(user == null) {
			return null;
		}
		
		return GiraUserWithRolesDTO.builder()
				.id(user.getId())
				.username(user.getUsername())
				.displayName(user.getDisplayName())
				.email(user.getEmail())
				.roles(getRolesFromUser(user)) // create getRolesFromUser method
				.build();
	}
	
	protected List<GiraRoleDTO> getRolesFromUser(GiraUser user) {
		List<GiraRoleDTO> roles = new ArrayList<GiraRoleDTO>();
		
		for (GiraGroup group : user.getGroups()) {
			group.getRoles().forEach((role) -> {
				if(isRoleExisted(roles, role)) {
					return;
				}
				
				roles.add(GiraRoleMapper.INSTANCE.toGiraRoleDTO(role));
				
			});
		}
		return roles;
	}

	protected boolean isRoleExisted(List<GiraRoleDTO> roles, GiraRole role) {
		for (GiraRoleDTO giraRoleDTO : roles) {
			if(giraRoleDTO.getCode().equals(role.getCode())) {
				return true;
			}
		}
		return false;
	}
}
