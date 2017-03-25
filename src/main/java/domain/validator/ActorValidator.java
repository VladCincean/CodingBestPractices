package domain.validator;

import domain.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 23.03.2017.
 *
 * Implementation of {@code Validator} for validating {@code Actor} instances
 */
public class ActorValidator implements Validator<Actor> {
    private List<String> errors;

    public ActorValidator() {
        this.errors = new ArrayList<>();
    }

    @Override
    public void validate(Actor actor) throws ValidatorException {
        if (!actor.getBirthDate().matches(
                     "(^(((0[1-9]|1[0-9]|2[0-8])" +
                        "[-]" +
                        "(0[1-9]|1[012]))|((29|30|31)" +
                        "[-]" +
                        "(0[13578]|1[02]))|((29|30)" +
                        "[-]" +
                        "(0[469]|11)))" +
                        "[-]" +
                        "(19|[2-9][0-9])\\d\\d$)" +
                        "|" +
                        "(^29[-]02[-](19|[2-9][0-9])" +
                        "(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)"
        )) {
            errors.add("birth-date is invalid");
        }

        if (!actor.getCity().matches("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$")) {
            errors.add("city is invalid");
        }

        if (!actor.getHeight().matches("^[0-2]+(\\.\\d{1,2})?m$")) {
            errors.add("height is invalid");
        }

        if (!actor.getWeight().matches("^[1-9]+\\d{1,2}kg$")) {
            errors.add("weight is invalid");
        }

        if (!actor.getEmail().matches("^([\\w\\\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})$")) {
            errors.add("email is invalid");
        }

        if (!actor.getPhoneNumber().matches("^0+[23789]+[0-9]{8}$")) {
            errors.add("phone number is invalid");
        }

        errors.stream()
                .reduce((acc, it) -> acc + "\n" + it)
                .ifPresent(opt -> {
                    errors.clear();
                    throw new ValidatorException(opt);
                });

    }
}
