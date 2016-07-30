package paxxa.com.ees.annotation;


import org.springframework.beans.factory.annotation.Autowired;
import paxxa.com.ees.repository.user.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUserName, String> {

	@Autowired
	private UserRepository userRepository;

	public void initialize(UniqueUserName constraintAnnotation) {
	}

	public boolean isValid(String username, ConstraintValidatorContext context) {
		/*
		 * During compilation userRepository is null so it will retrieve nullPointExceptio
		 * To avoid that we give this extra condition
		 */
		if(userRepository == null){
			return true;
		}
		return userRepository.findByName(username)==null;
	}

}

