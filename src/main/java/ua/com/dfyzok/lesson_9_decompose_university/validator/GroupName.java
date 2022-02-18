package ua.com.dfyzok.lesson_9_decompose_university.validator;

import java.lang.annotation.*;
import javax.validation.*;

@Documented
@Constraint(validatedBy = GroupNameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface GroupName {

    String message() default "Group name must be in \"AB-12\" format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
