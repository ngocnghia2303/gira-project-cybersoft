package cybersoft.javabackend.java16giranghia.role.service;

import java.util.List;

import cybersoft.javabackend.java16giranghia.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giranghia.role.dto.GiraGroupWithRoleDTO;

public interface GiraGroupService {
	List<GiraGroupDTO> findAllDto();
	
	GiraGroupDTO createNewGroup(GiraGroupDTO dto);

	GiraGroupWithRoleDTO addRole(String groupId, String roleId);

	GiraGroupWithRoleDTO removeRole(String groupId, String roleId);

	GiraGroupWithRoleDTO findById(String groupId);
}
