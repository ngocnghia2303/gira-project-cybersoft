package cybersoft.javabackend.java16giranghia.role.dto;

import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiraGroupWithRoleDTO {
	private UUID id;
	
	private String code;
	
	private String description;
	
	Set<GiraRoleDTO> roles;
}
