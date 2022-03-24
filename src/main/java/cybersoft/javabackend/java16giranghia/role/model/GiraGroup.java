package cybersoft.javabackend.java16giranghia.role.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
@Table(name = "gira_group")
public class GiraGroup extends BaseEntity {

	// id, code, description, roles
	private String code;
	private String description;

	// Group is a boss of the roles :))
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }) // Persist == bo nho cua hibernate
	@JoinTable(
			// table secondary is "gira_group_role" in the many to many reference
			name = "gira_group_role",
			joinColumns = @JoinColumn(name = "group_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<GiraRole> roles = new LinkedHashSet();
	
	// function => addRole
	public void addRole(GiraRole role) {
		roles.add(role);
		role.getGroups().add(this);
	}
	
	// function => removeRole
	public void removeRole(GiraRole role) {
		roles.remove(role);
		role.getGroups().remove(this);
	}
	
	// function => clearRole
	public void clearRole() {
		this.roles.clear();
	}
}
