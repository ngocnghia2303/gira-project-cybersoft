package cybersoft.javabackend.java16giranghia.role.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import cybersoft.javabackend.java16giranghia.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giranghia.role.model.GiraRole;
import cybersoft.javabackend.java16giranghia.role.service.GiraRoleService;

@SpringBootTest
@AutoConfigureMockMvc
public class GiraRoleControllerTest {
	@MockBean
	private GiraRoleService giraRolesServices;
	
	@Autowired
	private MockMvc mockMvc;
	

	@BeforeEach
	public void mockSecurity() {

	}

	@Test
	public void shouldReturn403WhenNoUser() throws Exception {
		mockMvc.perform(get("/api/v1/roles"))
		.andDo(print())
		.andExpect(status(
				).isForbidden());
	}

	@WithMockUser("lindahtv")
	@Test
	public void shouldReturnEmptyWhenNoUser() throws Exception {
		when(giraRolesServices.findAllEntity())
				.thenReturn(List.of(GiraRoleDTO.builder()
						.id(UUID.fromString("3c927bbe-1574-4bd5-aa2d-0e0c1ce1fe1d"))
						.code("INTER").description("Internship")
						.build()));
		String returnPayload = "[{\"id\":\"3c927bbe-1574-4bd5-aa2d-0e0c1ce1fe1d\",\"code\":\"INTER\",\"description\":\"Internship\"}]";
		mockMvc.perform(get("/api/v1/roles"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(returnPayload));
	}
	
	// usecase: tim dc role thanh cong cua 1 user voi findById of the role
	@WithMockUser
	@Test
	public void shouldFindRoleSuccessfullyWithExistedId() throws Exception {
		String roleId = "3c09fb3d-650d-4ba5-8bf4-a9ff8255d8f4";
		
		GiraRole role = GiraRole.builder()
				.id(UUID.fromString(roleId))
				.code("FRESH")
				.description("FRESHER & INTERNSHIP")
				.build();
		
		when(giraRolesServices.findById(roleId)).thenReturn(role);
		
		String roleJson = "\"description\":\"FRESHER & INTERNSHIP\"";
		
		mockMvc.perform(get("/api/v1/roles/" + roleId))
		.andDo(print())
		.andExpect(status().isOk())
//		.andExpect(content().string(containsString(roleJson)));
		.andExpect(jsonPath("$.content.description").value("FRESHER & INTERNSHIP"));
	}
	
	@WithMockUser
	@Test
	public void shouldNotFoundRoleWithUnexistedId() throws Exception {
		
		String roleId = UUID.randomUUID().toString();
		when(giraRolesServices.findById(roleId)).thenReturn(null);
		mockMvc.perform(get("/api/v1/roles/" + roleId))
		.andDo(print())
		.andExpect(status().is4xxClientError())
//		.andExpect(jsonPath("$.errors").value("Role is not existing."));
		.andExpect(content().string(containsString("Role is not existing.")));
	}


}
