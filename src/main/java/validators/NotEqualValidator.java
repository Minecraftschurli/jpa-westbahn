package validators;

import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

public class NotEqualValidator implements ConstraintValidator<NotEqual, Object> {
    private String[] fields;

    @SneakyThrows
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object last = null;
        boolean first = true;
        final Class<?> valueClass = value.getClass();
        for (String fieldName : fields) {
            final Field field = valueClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object val = field.get(value);
            if (!first && Objects.equals(val, last)) {
                return false;
            }
            last = val;
            first = false;
        }
        return true;
    }

    @Override
    public void initialize(NotEqual constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.fields = constraintAnnotation.fields();
        if (this.fields.length < 2) {
            throw new IllegalArgumentException("The NotEqual Constraint needs at least 2 field names!");
        }
    }
}
