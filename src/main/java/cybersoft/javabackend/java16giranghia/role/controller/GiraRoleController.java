package cybersoft.javabackend.java16giranghia.role.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.javabackend.java16giranghia.common.util.ResponseHelper;
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
		List<GiraRoleDTO> roles = service.findAllEntity();
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

	
	@PutMapping("/{role-id}")
	public Object update(@PathVariable("role-id") String id, 
			@RequestBody @Valid GiraRoleDTO dto,
			BindingResult bindingResult) {
		
		// neu request co roi
		if(bindingResult.hasErrors()) {
				return	ResponseHelper.getErrosResponse(bindingResult, HttpStatus.BAD_REQUEST);
		}
		
		GiraRole updatedRole = service.update(UUID.fromString(id), dto);
		// neu body null => content da dc dung
		if(updatedRole == null) {
			return ResponseHelper.getErrosResponse("Role code is used.", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseHelper.getResponse(updatedRole, HttpStatus.OK);
	}
	
	@GetMapping("/{role-id}")
	public Object findRoleById(@PathVariable("role-id") String id) {
		GiraRole role = service.findById(id);
		
		if(role == null) {
			return ResponseHelper.getErrosResponse("Role is not existing.", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseHelper.getResponse(role, HttpStatus.OK);
	}
}
