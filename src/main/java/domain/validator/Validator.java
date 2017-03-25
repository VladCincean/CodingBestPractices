package domain.validator;

/**
 * Created by vlad on 23.03.2017.
 *
 * Interface for a generic {@code Validator} that validates a specific tyle
 */
public interface Validator<T> {
    /**
     * Validates a {@code T} instance object
     * @param entity - the {@code T} instance object that needs to be validated
     * @throws ValidatorException - if {@code entity} is not valid
     */
    void validate(T entity) throws ValidatorException;
}