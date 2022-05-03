package cybersoft.javabackend.java16giranghia.user.service;

import java.util.List;

import cybersoft.javabackend.java16giranghia.user.dto.GiraUserDTO;
import cybersoft.javabackend.java16giranghia.user.dto.GiraUserRolesDTO;
import cybersoft.javabackend.java16giranghia.user.dto.GiraUserWithRolesDTO;

public interface GiraUserService {
	GiraUserDTO createNewUser(GiraUserDTO dto);

	List<GiraUserRolesDTO> findUserWithRolesByUsername(String username);

	GiraUserWithRolesDTO findUserWithRolesByUsernameUsingJoin(String username);
}
