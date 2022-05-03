package cybersoft.javabackend.java16giranghia.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.javabackend.java16giranghia.common.util.ResponseHelper;
import cybersoft.javabackend.java16giranghia.user.dto.GiraUserDTO;
import cybersoft.javabackend.java16giranghia.user.dto.GiraUserRolesDTO;
import cybersoft.javabackend.java16giranghia.user.dto.GiraUserWithRolesDTO;
import cybersoft.javabackend.java16giranghia.user.service.GiraUserService;

@RestController
@RequestMapping("api/v1/users")
public class GiraUserController {
	@Autowired
	private GiraUserService service;
	
	@GetMapping("/{user-name}/roles")
	public Object findUserRolesByUsername(@PathVariable("user-name") String username) {
		List<GiraUserRolesDTO> userRoles = service.findUserWithRolesByUsername(username);
		if(userRoles == null){
			return ResponseHelper.getErrosResponse(username, HttpStatus.BAD_REQUEST);
		}
		
		return ResponseHelper.getResponse(userRoles, HttpStatus.OK);
	}
	
	@GetMapping("{user-name}/roles-by-join-query")
	public Object findRolesByUsernameUsingJoin(@PathVariable("user-name") String username) {
		GiraUserWithRolesDTO user = service.findUserWithRolesByUsernameUsingJoin(username);
		
		if(user == null) {
			return ResponseHelper.getErrosResponse("Username is not existed.", HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(user, HttpStatus.OK);
	}
	
	@PostMapping
	public Object createNewUser(@RequestBody @Valid GiraUserDTO dto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return ResponseHelper.getErrosResponse(bindingResult, HttpStatus.BAD_REQUEST);
		}
		
		GiraUserDTO newUser = service.createNewUser(dto);
		return ResponseHelper.getResponse(newUser, HttpStatus.CREATED);
	}

}
