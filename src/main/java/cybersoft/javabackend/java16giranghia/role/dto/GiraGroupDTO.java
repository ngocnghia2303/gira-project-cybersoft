package cybersoft.javabackend.java16giranghia.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GiraGroupDTO {
	@Size(min = 5, max = 36, message = "Group Code must be longer than 36 characters.")
	private String code;

	@NotBlank(message = "Description must be not blank.")
	private String description;
}
