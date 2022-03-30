package cybersoft.javabackend.java16giranghia.user.dto;

import java.util.Set;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import cybersoft.javabackend.java16giranghia.common.model.BaseEntity;
import cybersoft.javabackend.java16giranghia.role.model.GiraGroup;
import cybersoft.javabackend.java16giranghia.user.model.GiraUser;
import cybersoft.javabackend.java16giranghia.user.model.UserStatus;
import cybersoft.javabackend.java16giranghia.user.validation.annotation.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class GiraUserDTO {
	// mandatory (thuoc tinh bat buoc)
	@Size(min=3, max=100, message="{user.username.size}")
	@UniqueUsername(message="{user.username.existed}")
	private String username;
	
	private String password;
	
	private String displayName;
	
	private String email;
	
	private UserStatus status;
}
