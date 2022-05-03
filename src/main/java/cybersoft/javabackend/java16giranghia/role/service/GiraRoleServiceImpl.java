package cybersoft.javabackend.java16giranghia.role.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.javabackend.java16giranghia.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giranghia.role.mapper.GiraRoleMapper;
import cybersoft.javabackend.java16giranghia.role.model.GiraRole;
import cybersoft.javabackend.java16giranghia.role.repository.GiraRoleRepository;

@Service
public class GiraRoleServiceImpl implements GiraRoleService {
	@Autowired
	private GiraRoleRepository repository;

	@Override
	public List<GiraRoleDTO> findAllEntity() {
		List<GiraRole> roles = repository.findAll();
		//convert model -> data transfer object (dto)
		List<GiraRoleDTO> dtos = roles.stream().map(t -> GiraRoleMapper.INSTANCE
				.toGiraRoleDTO(t))
				.collect(Collectors.toList());
		
		return dtos;
	}

	@Override
	public GiraRole save(GiraRoleDTO dto) {
		// Map dto to entity
		GiraRole role = GiraRoleMapper.INSTANCE.mapToEntity(dto);
		return repository.save(role);
	}

	@Override
	public GiraRole findById(String id) {
		
		Optional<GiraRole> roleOtp = repository.findById(UUID.fromString(id));
		// return result if be found. Also, return null if not find
		return roleOtp.orElse(null);
	}

	@Override
	public GiraRole update(UUID id, @Valid GiraRoleDTO dto) {
		Optional<GiraRole> roleOtp = repository.findById(id);
		
		if(roleOtp.isEmpty()) {
			return null;
		}
		
		GiraRole currentRole = roleOtp.get();
		// check dto's content is changed
		if(!currentRole.getCode().equals(dto.getCode())) {
			// check role code is used
			Optional<GiraRole> existedRole = repository.findByCode(dto.getCode());
			if(existedRole.isPresent()) {
				return null;
			}
			
			currentRole.setCode(dto.getCode());
		}
		
		currentRole.setDescription(dto.getDescription());
		
		return repository.save(currentRole);
	}
	
	

}
