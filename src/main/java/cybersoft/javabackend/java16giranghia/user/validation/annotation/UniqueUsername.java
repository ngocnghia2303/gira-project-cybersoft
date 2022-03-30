package cybersoft.javabackend.java16giranghia.user.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.javabackend.java16giranghia.user.validation.validator.UniqueUsernameValidator;

@Constraint(validatedBy = UniqueUsernameValidator.class) // link this annotation & class
@Retention(RetentionPolicy.RUNTIME) // When will this annotation be called ? {RUNTIME, SOURCE, CLASS}
@Target({ElementType.FIELD}) // Define this annotation will be used by Feild or class ...
public @interface UniqueUsername {
	String message() default "Username already used";
	
	// Overlap annotations
	// i do not know it will be any class and i will give it to 1 array, default is a dynamic object
	// annotation always have groups() and payload(). For payload, it will extends Payload validation.
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
