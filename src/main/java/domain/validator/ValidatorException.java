package domain.validator;

/**
 * Created by vlad on 23.03.2017.
 */
public class ValidatorException extends RuntimeException {
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}