package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class NameValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String name = (String) target;
        if(name.contains("master")){
            errors.rejectValue("master은 안됩니다.","notAllowed.master");
        }
        if(name.length() > 10){
            errors.rejectValue("ten","maxLength");
        }
    }
}
