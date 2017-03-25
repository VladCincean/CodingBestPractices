import domain.Actor;
import domain.Movie;
import domain.MovieActor;
import domain.validator.ActorValidator;
import domain.validator.LinkValidator;
import domain.validator.MovieValidator;
import repository.ActorCsvRepository;
import repository.LinkCsvRepository;
import repository.MovieCsvRepository;
import repository.Repository;
import service.ActorService;
import service.LinkService;
import service.MovieService;
import ui.Console;

/**
 * Created by vlad on 24.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        String actorsFile = "src/main/resources/actors.csv";
        String moviesFile = "src/main/resources/movies.csv";
        String linkFile = "src/main/resources/links.csv";

        Repository<Long, Actor> actorRepository = new ActorCsvRepository(new ActorValidator(), actorsFile);
        Repository<Long, Movie> movieRepository = new MovieCsvRepository(new MovieValidator(), moviesFile);
        Repository<Long, MovieActor> linkRepository = new LinkCsvRepository(new LinkValidator(), linkFile);

        MovieService movieService = new MovieService(movieRepository, linkRepository);
        ActorService actorService = new ActorService(actorRepository, linkRepository);
        LinkService linkService = new LinkService(linkRepository);

        Console console = new Console(actorService, movieService, linkService);

        console.run();
    }
}
