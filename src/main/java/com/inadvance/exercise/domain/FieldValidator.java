package com.inadvance.exercise.domain;

import com.inadvance.exercise.domain.config.ValidationConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FieldValidator {

    private ValidationConfig validationConfig;

    public Boolean validatePassword(String password) {
        String passwordRegex = this.validationConfig.getPasswordRegex();
        return password.matches(passwordRegex);
    }
}
