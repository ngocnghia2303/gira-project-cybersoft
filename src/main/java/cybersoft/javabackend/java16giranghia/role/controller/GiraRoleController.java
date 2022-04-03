package cybersoft.javabackend.java16giranghia.role.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.javabackend.java16giranghia.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giranghia.role.model.GiraRole;
import cybersoft.javabackend.java16giranghia.role.service.GiraRoleService;

@RestController
@RequestMapping("api/v1/roles")
public class GiraRoleController {
	@Autowired
	private GiraRoleService service;

	@GetMapping
	public Object findAllRoles() {
		List<GiraRole> roles = service.findAllEntity();
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}

	@PostMapping
	public Object createNewRole(@Valid @RequestBody GiraRoleDTO dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(
					bindingResult.getAllErrors().stream().map(t -> t.getDefaultMessage()).collect(Collectors.toList()),
					HttpStatus.BAD_REQUEST);
		}

		GiraRole role = service.save(dto);
		System.out.println(role);
		return new ResponseEntity<>(role, HttpStatus.CREATED);
	}

//	@PostMapping
//	public Object createNewRole(@RequestBody GiraRoleDTO dto) {
//		try {
//			System.out.println("LOAD dc Post NE");
//			System.out.println(dto);
//			GiraRole role = service.save(dto);
//			System.out.println(role);
//			return new ResponseEntity<>(role, HttpStatus.CREATED);
//		} catch (Exception e) {
//			System.out.println(e);
//			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
//		}
//	}
}
