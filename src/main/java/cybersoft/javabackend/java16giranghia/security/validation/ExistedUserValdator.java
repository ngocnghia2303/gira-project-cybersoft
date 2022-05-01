package cybersoft.javabackend.java16giranghia.security.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.javabackend.java16giranghia.user.model.GiraUser;
import cybersoft.javabackend.java16giranghia.user.repository.GiraUserRepository;

public class ExistedUserValdator implements ConstraintValidator<ExistedUser, String> {
	private String message;
	
	@Autowired
	private GiraUserRepository repository;
	
	@Override
	public void initialize(ExistedUser existedUser) {
		message = existedUser.message();
	}
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(username == null)
			return false;
		
		Optional<GiraUser> userOpt = repository.findByUsername(username);
		
		if(userOpt.isPresent()) {
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