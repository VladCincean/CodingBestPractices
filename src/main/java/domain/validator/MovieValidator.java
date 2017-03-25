package domain.validator;

import domain.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 24.03.2017.
 *
 * Implementation of {@code Validator} for validating {@code Movie} instances
 */
public class MovieValidator implements Validator<Movie> {
    private List<String> errors;

    public MovieValidator() {
        this.errors = new ArrayList<>();
    }

    @Override
    public void validate(Movie movie) throws ValidatorException {
        if (!movie.getYear().matches("^(19|20)+[0-9]{2}$")) {
            errors.add("movie year is invalid");
        }

        if (!movie.getWebsite().matches(
                "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9][a-zA-Z0-9-]*\\.)+[a-z0-9][a-z0-9\\-]*$"
        )) {
            errors.add("website is invalid");
        }

        errors.stream()
                .reduce((acc, it) -> acc + "\n" + it)
                .ifPresent(opt -> {
                    errors.clear();
                    throw new ValidatorException(opt);
                });
    }
}
