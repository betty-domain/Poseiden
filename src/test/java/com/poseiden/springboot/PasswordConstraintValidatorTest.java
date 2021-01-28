package com.poseiden.springboot;

import com.poseiden.springboot.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PasswordConstraintValidatorTest {

    private final static String TOOSHORT_MESSAGE = "TOO_SHORT";
    private final static String UPPERCASE_MESSAGE = "INSUFFICIENT_UPPERCASE";
    private final static String DIGIT_MESSAGE = "INSUFFICIENT_DIGIT";
    private final static String SPECIALCHAR_MESSAGE = "INSUFFICIENT_SPECIAL";

    private static Validator validator;

    @BeforeAll
    static void setUp()
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testInvalidPassword_AllRulesViolated()
    {
        User user = new User("username","pass","fullname","USER");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        ConstraintViolation<User> userConstraintViolation = constraintViolations.stream().findFirst().get();

        assertThat(userConstraintViolation.getMessage()).contains(TOOSHORT_MESSAGE);
        assertThat(userConstraintViolation.getMessage()).contains(UPPERCASE_MESSAGE);
        assertThat(userConstraintViolation.getMessage()).contains(SPECIALCHAR_MESSAGE);
        assertThat(userConstraintViolation.getMessage()).contains(DIGIT_MESSAGE);
    }


    @Test
    void testInvalidPassword_LengthRuleViolated()
    {
        User user = new User("username","Pass1@","fullname","USER");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        ConstraintViolation<User> userConstraintViolation = constraintViolations.stream().findFirst().get();
        assertThat(userConstraintViolation.getMessage()).contains(TOOSHORT_MESSAGE);
    }

    @Test
    void testInvalidPassword_SpecialCharViolated()
    {
        User user = new User("username","Pass1word","fullname","USER");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        ConstraintViolation<User> userConstraintViolation = constraintViolations.stream().findFirst().get();

        assertThat(userConstraintViolation.getMessage()).contains(SPECIALCHAR_MESSAGE);
    }

    @Test
    void testInvalidPassword_UppercaseRuleViolated()
    {
        User user = new User("username","pass1word","fullname","USER");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        ConstraintViolation<User> userConstraintViolation = constraintViolations.stream().findFirst().get();

        assertThat(userConstraintViolation.getMessage()).contains(UPPERCASE_MESSAGE);
    }

    @Test
    void testInvalidPassword_DigitRuleViolated()
    {
        User user = new User("username","Pass@word","fullname","USER");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        ConstraintViolation<User> userConstraintViolation = constraintViolations.stream().findFirst().get();

        assertThat(userConstraintViolation.getMessage()).contains(DIGIT_MESSAGE);
    }

    @Test
    void testValidPassword()
    {
        User user = new User("username","Pass1word@","fullname","USER");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.size()).isEqualTo(0);
    }
}
