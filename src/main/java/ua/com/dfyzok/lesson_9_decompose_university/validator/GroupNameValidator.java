package ua.com.dfyzok.lesson_9_decompose_university.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GroupNameValidator implements ConstraintValidator<GroupName, String> {

    @Override
    public void initialize(GroupName groupNameConstrain) {
    }

    @Override
    public boolean isValid(String groupName, ConstraintValidatorContext constraintValidatorContext) {
        return groupName != null && groupName.length() == 5 && groupName.substring(0, 2).matches("[A-Z]+")
                && groupName.substring(2, 3).matches("-") && groupName.substring(3).matches("[0-9]+");
    }
}
