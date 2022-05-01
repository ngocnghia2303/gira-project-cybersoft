package cybersoft.javabackend.java16giranghia.role.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import cybersoft.javabackend.java16giranghia.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giranghia.role.model.GiraRole;

public interface GiraRoleService {
	// find all Role & save role
	List<GiraRole> findAllEntity();

	GiraRole save(GiraRoleDTO dto);

	GiraRole findById(String id);

	GiraRole update(UUID id, @Valid GiraRoleDTO dto);
}
