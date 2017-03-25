package repository;

import domain.MovieActor;
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
 * Created by vlad on 24.03.2017.
 */
public class LinkCsvRepository extends FileRepository<Long, MovieActor> {

    public LinkCsvRepository(Validator<MovieActor> validator, String filename) {
        super(validator, filename);

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
                Long movieId = Long.valueOf(items.get(1));
                Long actorId = Long.valueOf(items.get(2));

                MovieActor link = new MovieActor(
                        id,
                        actorId,
                        movieId
                );

                try {
                    super.saveInMemory(link);
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
    protected void saveToFile(MovieActor entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(entity.getId() + "," +
                    entity.getMovieId() + "," +
                    entity.getActorId()
            );
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
