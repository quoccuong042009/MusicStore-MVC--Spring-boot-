package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import com.example.demo.domain.User;
 
 
@Component
public class UserValidator {
	 
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object target, Errors errors) {
        User user = (User)target;

        String password = user.getPassword();
        String confPassword = user.getPasswordConfirmation();
        
        //Business validation
        if(!password.equals(confPassword)){
            errors.rejectValue("Password", "customer.password.missMatch", "password and confirm password do not match");
        }
    
    }
}
