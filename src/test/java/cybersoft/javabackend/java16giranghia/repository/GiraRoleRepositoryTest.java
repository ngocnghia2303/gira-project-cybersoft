package cybersoft.javabackend.java16giranghia.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import cybersoft.javabackend.java16giranghia.role.model.GiraRole;
import cybersoft.javabackend.java16giranghia.role.repository.GiraRoleRepository;

@SpringBootTest
// de ko insert & access to database. You can use @Transactional annotation (cua spring)
@Transactional
public class GiraRoleRepositoryTest {
	@Autowired
	GiraRoleRepository repository;

	@Test
	public void shouldNotInsertRole() {
		GiraRole role = GiraRole.builder().code("FOUH").description("Nghia dep trai").build();

		assertDoesNotThrow(() -> repository.save(role));
	}
}
