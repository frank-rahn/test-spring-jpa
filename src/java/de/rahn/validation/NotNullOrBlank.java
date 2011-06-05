package de.rahn.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import de.rahn.validation.constraints.NotNullOrBlankValidator;

/**
 * Validiere String Felder, die nicht <code>null</code> oder aus einem Leerstring bestehen.
 * @author Frank W. Rahn
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { NotNullOrBlankValidator.class })
public @interface NotNullOrBlank {
	String message() default "{de.rahn.validation.NotNullOrBlank.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}