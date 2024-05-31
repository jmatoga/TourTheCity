package jkd.tourthecity.exception.internal.data.inconsistency;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Class<?> clazz, String criteriaField, String criteriaValue) {
        super(String.format("Can't find resource of type %s, by criteria {%s} = {%s}",
                clazz.getSimpleName(),
                criteriaField,
                criteriaValue));
    }

    public ResourceNotFoundException(Class<?> clazz, String idValue) {
        this(clazz, "id", idValue);
    }
}
