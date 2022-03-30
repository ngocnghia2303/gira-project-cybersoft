package cybersoft.javabackend.java16giranghia.user.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import cybersoft.javabackend.java16giranghia.user.model.GiraUser;
import cybersoft.javabackend.java16giranghia.user.repository.GiraUserRepository;
import cybersoft.javabackend.java16giranghia.user.validation.annotation.UniqueUsername;


public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
	private String message;
	
	// connect database of the username
	@Autowired
	private GiraUserRepository repository;
	
	// get message & notification error (keyword = initialize)
	@Override
	public void initialize(UniqueUsername uniqueUsername) {
		message = uniqueUsername.message();
	}
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(username == null) {
			return false;
		}
		
		Optional<GiraUser> userOpt = repository.findByUsername(username);
		// if this user has not yet created in database, then will return true
		if(userOpt.isEmpty()) {
			return true;
		}
		// if not valid, then return error
		// context => this is where be used to save information related to the validator. It includes errors.
		context.buildConstraintViolationWithTemplate(message)
		.addConstraintViolation()
		.disableDefaultConstraintViolation();
		
		return false;
	}
}
