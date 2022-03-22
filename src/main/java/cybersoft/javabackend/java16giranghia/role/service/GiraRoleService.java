package cybersoft.javabackend.java16giranghia.role.service;

import java.util.List;

import cybersoft.javabackend.java16giranghia.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giranghia.role.model.GiraRole;

public interface GiraRoleService {
	// find all Role & save role
	List<GiraRole> findAllEntity();

	GiraRole save(GiraRoleDTO dto);
}
