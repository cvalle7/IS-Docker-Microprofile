/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.backendf1.model.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Carlos
 */
public class RefValidator implements ConstraintValidator<Ref, String> {
    
    @Override
    public void initialize(Ref constraintAnnotation) {
        
    }
    
    @Override
    public boolean isValid(String ref, ConstraintValidatorContext context) {
        
        Pattern pattern = Pattern.compile("^[0-9]{4}[A-Z]{1}$");
        Matcher matcher = pattern.matcher(ref);
        
        if(matcher.matches()){
            return true;
        }
        
        return false;
    }
}
