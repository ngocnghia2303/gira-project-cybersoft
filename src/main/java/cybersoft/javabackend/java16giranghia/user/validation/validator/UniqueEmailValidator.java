package cybersoft.javabackend.java16giranghia.user.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.javabackend.java16giranghia.user.model.GiraUser;
import cybersoft.javabackend.java16giranghia.user.repository.GiraUserRepository;
import cybersoft.javabackend.java16giranghia.user.validation.annotation.UniqueEmail;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
	
	// check email input existed in database
	@Autowired
	private GiraUserRepository repository;
	private String message;
	
	@Override
	public void initialize(UniqueEmail uniqueEmail) {
		message = uniqueEmail.message();
	}
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(email == null) {
			return false;
		}
		
		Optional<GiraUser> userOpt = repository.findByEmail(email);
		if(userOpt.isEmpty()) {
			return true;
		}
		
		context.buildConstraintViolationWithTemplate(message)
		.addConstraintViolation()
		.disableDefaultConstraintViolation();
		return false;
	}
}
