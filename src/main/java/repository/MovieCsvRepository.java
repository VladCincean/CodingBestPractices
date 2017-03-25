package repository;

import domain.Movie;
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
 * Extension of {@code FileRepository} for CRUD operations on a repository for type {@code Movie}
 *      while maintaining CSV persistence
 */
public class MovieCsvRepository extends FileRepository<Long, Movie> {

    public MovieCsvRepository(Validator<Movie> validator, String fileName) {
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
                String title = items.get(1);
                String year = items.get(2);
                String website = items.get(3);

                Movie movie = new Movie(
                        id,
                        title,
                        year,
                        website
                );

                try {
                    super.saveInMemory(movie);
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
    protected void saveToFile(Movie entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(entity.getId() + "," +
                    entity.getTitle() + "," +
                    entity.getYear() + "," +
                    entity.getWebsite()
            );
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
