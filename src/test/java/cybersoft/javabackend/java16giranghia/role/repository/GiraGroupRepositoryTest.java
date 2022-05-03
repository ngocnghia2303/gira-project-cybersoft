package cybersoft.javabackend.java16giranghia.role.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class GiraGroupRepositoryTest {
	
	@Autowired
	private GiraGroupRepository repository;
	
	@BeforeAll
	public void setup() {
		
	}
	
	@Test
	public void shouldFetchGroupWithRoles() {
		assertDoesNotThrow(()-> repository.findGroupWithRolesByGroupId(UUID.fromString("f4e9cf12-18b3-4c9b-91a4-e8054ffdcd3c")));
	}
	
}
