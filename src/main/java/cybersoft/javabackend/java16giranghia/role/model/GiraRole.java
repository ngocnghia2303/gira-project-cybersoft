package cybersoft.javabackend.java16giranghia.role.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cybersoft.javabackend.java16giranghia.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "gira_role")
public class GiraRole extends BaseEntity {
	// Validate constraint
	// Attribute

	// 1. Code (min = 5, max = 100)
	@Size(min = 5, max = 100)
	private String code;
	// 2. Description
	@NotBlank
	private String description;

	// Reference many - many
	@ManyToMany(mappedBy = "roles")
	private Set<GiraGroup> groups = new LinkedHashSet<>(); // write LinkedHashSet truoc roi viet anotation sau

}
