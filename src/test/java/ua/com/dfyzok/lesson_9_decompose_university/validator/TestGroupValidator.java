package ua.com.dfyzok.lesson_9_decompose_university.validator;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ua.com.dfyzok.lesson_9_decompose_university.entity.Group;

@RunWith(MockitoJUnitRunner.class)
public class TestGroupValidator {

    private static Validator validator;
    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Test
    public void validatesSizeMustBeZero() {
        final Group group = new Group();
        group.setGroupName("NR-43");
        group.setGroupId(0L);
        group.setCapacity(23);

        Set<ConstraintViolation<Group>> violations = validator.validate(group);
        assertTrue(violations.isEmpty());
    }

}
