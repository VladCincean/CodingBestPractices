package ui;

import service.ActorService;
import service.LinkService;
import service.MovieService;

import java.util.Scanner;

/**
 * Created by vlad on 24.03.2017.
 */
public class Console {
    private ActorService actorService;
    private MovieService movieService;
    private LinkService linkService;

    private final String menu =
            "1 - print all actors\n" +
            "2 - search an actor\n" +
            "---------------------\n" +
            "3 - print all movies\n" +
            "4 - search a movie\n" +
            "---------------------\n" +
            "0 - exit\n";

    public Console(ActorService actorService, MovieService movieService, LinkService linkService) {
        this.actorService = actorService;
        this.movieService = movieService;
        this.linkService = linkService;
    }

    /**
     * Starts the execution of the user interface.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(menu);
            System.out.print("Your option: ");

            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Wrong command!");
                System.out.print("Your option: ");
            }

            int opt = scanner.nextInt();

            switch (opt) {
                case 1:
                    printAllActors();
                    break;
                case 2:
                    searchActor();
                    break;
                case 3:
                    printAllMovies();
                    break;
                case 4:
                    searchMovie();
                    break;
                case 0:
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong command!");
                    break;
            }
        }
    }

    private void printAllActors() {
        actorService.getAllActors()
                .forEach(a -> {
                    System.out.print(a);
                    System.out.println("Acted in: ");
                    linkService.getActorMovies(a)
                            .forEach(id -> {
                                String title = movieService.findOne(id).getTitle();
                                System.out.println("\t- " + title);
                            });
                    System.out.println("");
                });
    }

    private void searchActor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("first name: ");
        String firstName = scanner.next().trim();
        System.out.print("last name: ");
        String lastName = scanner.next().trim();

        actorService.filterActors(firstName, lastName)
                .forEach(a -> {
                    System.out.println(a);
                    System.out.print("Delete " + a.getFirstName() + " " + a.getLastName() + "? [y/n] ");
                    if (scanner.next().trim().toLowerCase().equals("y")) {
                        actorService.deleteActor(a.getId());
                        System.out.println("Deleted!");
                    }
                });
    }

    private void printAllMovies() {
        movieService.getAllMovies()
                .forEach(m -> {
                    System.out.print(m);
                    System.out.println("Actors: ");
                    linkService.getMovieActors(m)
                            .forEach(id -> {
                                String firstName = actorService.findOne(id).getFirstName();
                                String lastName = actorService.findOne(id).getLastName();
                                System.out.println("\t- " + firstName + " " + lastName);
                            });
                    System.out.println("");
                });
    }

    private void searchMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("title: ");
        String title = scanner.next().trim();

        movieService.filterMovies(title)
                .forEach(m -> {
                    System.out.println(m);
                    System.out.print("Delete " + m.getTitle() + "? [y/n] ");
                    if (scanner.next().trim().toLowerCase().equals("y")) {
                        movieService.deleteMovie(m.getId());
                        System.out.println("Deleted!");
                    }
                });
    }
}
