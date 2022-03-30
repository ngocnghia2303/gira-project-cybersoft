package cybersoft.javabackend.java16giranghia.user.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import cybersoft.javabackend.java16giranghia.common.model.BaseEntity;
import cybersoft.javabackend.java16giranghia.role.model.GiraGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="gira_user")
public class GiraUser extends BaseEntity{
	
	@ManyToMany(mappedBy = "users")
	private Set<GiraGroup> groups = new LinkedHashSet<GiraGroup>();
	
	@Column(name="username", unique=true, nullable = false, length = 100)
	private String username;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="display_name", nullable = false)
	private String displayName;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email", length=100 , unique=true, nullable = false) // unique === co cho trung value khong?
	private String email;
	
	@Column(name="avatar")
	private String avatar;
	
	@Enumerated(EnumType.STRING) // gia tri mac dinh saved enum co kieu String
	@Column(name="status", nullable = false)
	private UserStatus status;
	
	@Column(name="department")
	private String department;
	
	@Column(name="major")
	private String major;
	
	@Column(name="hobbies")
	private String hobbies;
	
	@Column(name="facebook")
	private String facebook;
	
	
}
