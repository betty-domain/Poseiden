package com.poseiden.springboot.domain;

import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for Password validation
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {

    /**
     * Check if password is valid
     * @param password password to validate
     * @param constraintValidatorContext context of validation
     * @return true if password is valid
     */
    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext constraintValidatorContext) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new UppercaseCharacterRule(1),
                new LengthRule(8,125),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1)
        ));

        if (password == null)
        {
            return false;
        }

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid())
        {
            return true;
        }

        List<String> messages = validator.getMessages(result);
        String messageTemplate = messages.stream().collect(Collectors.joining(","));
        constraintValidatorContext.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
