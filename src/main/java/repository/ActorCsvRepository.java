package repository;

import domain.Actor;
import domain.validator.Validator;
import domain.validator.ValidatorException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vlad on 23.03.2017.
 *
 * Extension of {@code FileRepository} for CRUD operations on a repository for type {@code Actor}
 *      while maintaining CSV persistence
 */
public class ActorCsvRepository extends FileRepository<Long, Actor> {

    public ActorCsvRepository(Validator<Actor> validator, String fileName) {
        super(validator, fileName);

        loadData();
    }

    /**
     * Loads the data from the CSV file into memory.
     */
    @Override
    protected void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path, StandardCharsets.UTF_8).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                String firstName = items.get(1);
                String lastName = items.get(2);
                String birthDate = items.get(3);
                String nationality = items.get(4);
                String city = items.get(5);
                String height = items.get(6);
                String weight = items.get(7);
                String email = items.get(8);
                String phoneNumber = items.get(9);

                Actor actor = new Actor(
                        id,
                        firstName,
                        lastName,
                        birthDate,
                        nationality,
                        city,
                        height,
                        weight,
                        email,
                        phoneNumber
                );

                try {
                    super.saveInMemory(actor);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends the given entity to the CSV file.
     *
     * @param entity
     *             must not be null.
     */
    @Override
    protected void saveToFile(Actor entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(entity.getId() + "," +
                    entity.getFirstName() + "," +
                    entity.getLastName() + "," +
                    entity.getBirthDate() + "," +
                    entity.getNationality() + "," +
                    entity.getCity() + "," +
                    entity.getHeight() + "," +
                    entity.getWeight() + "," +
                    entity.getEmail() + "," +
                    entity.getPhoneNumber()
            );
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
