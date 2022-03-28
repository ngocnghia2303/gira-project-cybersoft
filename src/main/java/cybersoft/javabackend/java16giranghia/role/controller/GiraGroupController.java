package cybersoft.javabackend.java16giranghia.role.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.tools.sjavac.Log;

import cybersoft.javabackend.java16giranghia.common.util.ErrorHelper;
import cybersoft.javabackend.java16giranghia.common.util.ResponseHelper;
import cybersoft.javabackend.java16giranghia.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giranghia.role.dto.GiraGroupWithRoleDTO;
import cybersoft.javabackend.java16giranghia.role.service.GiraGroupService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("groups")
public class GiraGroupController {
	@Autowired
	private GiraGroupService services;

	@GetMapping
	public Object findAllGroup() {
		// threat pool
		
		// imperative & reactive - callback hell - async await
		
		// reactive for java coding & reactor & cloud native frameword

		log.info("Find all Gira group...");
		log.debug("Calling Gira GroupService.findAllDto()");
		List<GiraGroupDTO> groups = services.findAllDto();
		log.debug("result: {}", groups);
		log.info("Find all Group STOPPED");
		return ResponseHelper.getResponse(groups, HttpStatus.OK);
	}

	@PostMapping
	public Object createNewGroup(@Valid @RequestBody GiraGroupDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseHelper.getErrosResponse(result, HttpStatus.BAD_GATEWAY);
		}

		GiraGroupDTO newGroup = services.createNewGroup(dto);
		return ResponseHelper.getResponse(newGroup, HttpStatus.CREATED);
	}

	@PostMapping("add-role/{group-id}/{role-id}")
	public Object addRole(@PathVariable(name = "group-id") String groupId,
			@PathVariable(name = "role-id") String roleId) {
		GiraGroupWithRoleDTO modifiedGroup = services.addRole(groupId, roleId);

		if (modifiedGroup == null) {
			return ResponseHelper.getErrosResponse("Group or Role is not existing.", HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(modifiedGroup, HttpStatus.OK);
	}

//	remove-role
	@DeleteMapping("remove-role/{group-id}/{role-id}")
	public Object removeRole(@PathVariable(name = "group-id") String groupId,
			@PathVariable(name = "role-id") String roleId) {
		GiraGroupWithRoleDTO removedGroup = services.removeRole(groupId, roleId);

		if (removedGroup == null) {
			return ResponseHelper.getErrosResponse("Group or Role is not existing.", HttpStatus.BAD_REQUEST);
		}

		return ResponseHelper.getResponse(removedGroup, HttpStatus.OK);
	}
}
