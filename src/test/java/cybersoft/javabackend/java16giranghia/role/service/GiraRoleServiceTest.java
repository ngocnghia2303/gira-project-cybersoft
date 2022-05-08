package cybersoft.javabackend.java16giranghia.role.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import cybersoft.javabackend.java16giranghia.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giranghia.role.mapper.GiraRoleMapper;
import cybersoft.javabackend.java16giranghia.role.model.GiraRole;
import cybersoft.javabackend.java16giranghia.role.repository.GiraRoleRepository;

@DisplayName("Gira Role Service")
@SpringBootTest
public class GiraRoleServiceTest {
	@Mock
	private GiraRoleRepository repository;
	
	@Mock
	private GiraRoleMapper mapper;
	
	@InjectMocks
	private GiraRoleService service = new GiraRoleServiceImpl();
	
	@Test
	public void shouldMockCorrectly() {
		UUID roleId = UUID.randomUUID();
		GiraRole role = GiraRole.builder()
				.id(roleId).code("TEST_ROLE").description("Role for test").build();
		
		when(repository.findById(roleId)).thenReturn(Optional.ofNullable(role));
		
		GiraRole actualRole = repository.findById(roleId).get();
		
		assertEquals(roleId, actualRole.getId());
		assertEquals("TEST_ROLE", actualRole.getCode());
		assertEquals("Role for test", actualRole.getDescription());
	}
	
	// return chinh xac DTO khi tim thay 1 entity
	@DisplayName("should return correct DTO when there is only one entity be found")
	@Test
	public void shouldReturnCorrectDTOWhenOneEntityFound() {
		UUID roleId = UUID.randomUUID();
		GiraRole role = GiraRole.builder()
				.id(roleId)
				.code("TEST_ROLE")
				.description("Role for test")
				.build();
		
		GiraRoleDTO dto = GiraRoleDTO.builder()
				.id(roleId)
				.code("TEST_ROLE")
				.description("Role for test")
				.build();
		
		when(repository.findAll()).thenReturn(List.of(role));
		when(mapper.toGiraRoleDTO(role)).thenReturn(dto);
		
		List<GiraRoleDTO> actualRoles = service.findAllEntity();
		
		assertEquals(1, actualRoles.size());
		
		// get the first element of the Array List GiraRoleDTO
		GiraRoleDTO actualRole = actualRoles.get(0);
		
		assertEquals(roleId, actualRole.getId());
		assertEquals("TEST_ROLE", actualRole.getCode());
		assertEquals("Role for test", actualRole.getDescription());
	}
	
	@DisplayName("should return empty list when no entity be found")
	@Test
	public void shouldReturnAllEntityCorrectyWhenNoEntityFound() {
		when(repository.findAll()).thenReturn(List.of());
		
		List<GiraRoleDTO> roles = service.findAllEntity();
		
		assertEquals(0, roles.size());
	}
	
	
}
