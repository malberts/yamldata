package models.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {ProcessThreeValidator.class})
@Documented
public @interface ValidProcessThree {

    //    String message() default "{org.hibernate.validator.referenceguide.chapter06.classlevel." +
//            "ValidProcessThree.message}";
    String message() default "Invalid Process Three";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
