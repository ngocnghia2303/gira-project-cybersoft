package cybersoft.javabackend.java16giranghia.user.dto;

import java.util.List;
import java.util.UUID;

import cybersoft.javabackend.java16giranghia.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giranghia.user.model.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class GiraUserWithRolesDTO {
	private UUID id;
	private String username;
	private String displayName;
	private String email;
	private List<GiraRoleDTO> roles;
}
