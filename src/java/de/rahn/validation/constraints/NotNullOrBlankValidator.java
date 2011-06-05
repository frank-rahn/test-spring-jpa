package de.rahn.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import de.rahn.validation.NotNullOrBlank;

/**
 * Der Validator zur Annotation.
 * @author Frank W. Rahn
 */
public class NotNullOrBlankValidator implements ConstraintValidator<NotNullOrBlank, String> {

	/**
	 * {@inheritDoc}
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(NotNullOrBlank constraintAnnotation) {
		// Leer
	}

	/**
	 * {@inheritDoc}
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && value.trim().length() >= 0;
	}

}