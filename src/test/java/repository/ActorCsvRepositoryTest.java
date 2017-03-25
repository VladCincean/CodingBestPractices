package repository;

import domain.Actor;
import domain.validator.ActorValidator;
import org.junit.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

/**
 * Created by vlad on 25.03.2017.
 */
public class ActorCsvRepositoryTest {
    private Repository<Long, Actor> repository;
    private final String fileName = "src/test/resources/actors.csv";
    private final Path path = Paths.get(fileName);
    private Actor bob;
    private Actor alice;

    @Before
    public void setUp() throws Exception {
        bob = new Actor(
                1L,
                "Bob",
                "Smith",
                "22-02-1994",
                "american",
                "New York",
                "1.75m",
                "78kg",
                "john.smith@example.com",
                "0745254896"
        );

        alice = new Actor(
                2L,
                "Alice",
                "Smith",
                "17-09-1995",
                "american",
                "Los Angeles",
                "1.70m",
                "64kg",
                "alice.barbie@example.com",
                "0725658400"
        );

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {
            Files.write(path, "".getBytes());
            bufferedWriter.write(
                    "1,Bob,Smith,22-02-1994,american,New York,1.75m,78kg,john.smith@example.com,0745254896\n" +
                    "2,Alice,Smith,17-09-1995,american,Los Angeles,1.70m,64kg,alice.barbie@example.com,0725658400\n"
            );
        } catch (IOException e) {
            System.err.println("StudentFileRepository::setUp() failed: " + e.getMessage());
        }

        repository = new ActorCsvRepository(new ActorValidator(), fileName);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void save() throws Exception {
        assertTrue(repository.save(alice).get().equals(alice));

        Actor a = new Actor(
                3L,
                "Ion",
                "Popescu",
                "02-01-1995",
                "romanian",
                "Brasov",
                "1.70m",
                "68kg",
                "ionpopescuvasilian@example.com",
                "0721059407"
        );
        assertNull(repository.save(a).orElse(null));
        assertTrue(Files.lines(path).count() == 3);
        assertTrue(Files
                .lines(path)
                .reduce("", (acc, it) -> acc + it + "\n")
                .equals("1,Bob,Smith,22-02-1994,american,New York,1.75m,78kg,john.smith@example.com,0745254896\n" +
                        "2,Alice,Smith,17-09-1995,american,Los Angeles,1.70m,64kg,alice.barbie@example.com,0725658400\n" +
                        "3,Ion,Popescu,02-01-1995,romanian,Brasov,1.70m,68kg,ionpopescuvasilian@example.com,0721059407\n")
        );
    }

    @Test
    public void delete() throws Exception {
        assertNull(repository.delete(42L).orElse(null));
        assertTrue(repository.delete(2L).get().equals(alice));
        assertTrue(Files
                .lines(path)
                .reduce("", (acc, it) -> acc + it + "\n")
                .equals("1,Bob,Smith,22-02-1994,american,New York,1.75m,78kg,john.smith@example.com,0745254896\n")
        );
    }

    @Test
    public void findOne() throws Exception {
        assertTrue(repository.findOne(1L).get().equals(bob));
        assertTrue(repository.findOne(2L).get().equals(alice));
        assertNull(repository.findOne(3L).orElse(null));
    }

    @Test
    public void findAll() throws Exception {
        assertTrue(StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toSet())
                .size() == 2);
        assertTrue(StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toSet())
                .contains(alice));
        assertTrue(StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toSet())
                .contains(bob));
    }

}