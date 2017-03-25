package service;

import domain.Movie;
import domain.MovieActor;
import repository.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by vlad on 24.03.2017.
 */
public class MovieService {
    private Repository<Long, Movie> repository;
    private Repository<Long, MovieActor> linkRepository;

    public MovieService(Repository<Long, Movie> repository, Repository<Long, MovieActor> linkRepository) {
        this.repository = repository;
        this.linkRepository = linkRepository;
    }

    /**
     * Provides all movies from the repository.
     *
     * @return the set of all actors.
     */
    public Set<Movie> getAllMovies() {
        Iterable<Movie> movies = repository.findAll();
        return StreamSupport.stream(movies.spliterator(), false)
                .collect(Collectors.toSet());
    }

    /**
     * Find the {@code Movie} with the given {@code id}.
     *
     * @param id
     *            must be not null.
     * @return an {@code Optional} encapsulating the {@code Movie} with the given id.
     *          null if there is no one.
     */
    public Movie findOne(Long id) {
        Optional<Movie> optional = repository.findOne(id);
        return optional.orElse(null);
    }

    /**
     * Provides all movie(s) with the given title;
     *
     * @param title
     *              must be not null.
     * @return a set of actor(s) having the given title.
     */
    public Set<Movie> filterMovies(String title) {
        Iterable<Movie> movies = repository.findAll();
        return StreamSupport.stream(movies.spliterator(), false)
                .filter(m -> m.getTitle().contains(title))
                .collect(Collectors.toSet());
    }

    /**
     * Deletes a movie.
     *
     * @param id
     *       must be valid and not null.
     * @throws IllegalArgumentException
     *       if the id is not valid
     */
    public void deleteMovie(Long id) throws IllegalArgumentException {
        Iterable<MovieActor> links = linkRepository.findAll();
        StreamSupport.stream(links.spliterator(), false)
                .filter(l -> l.getMovieId().equals(id))
                .map(MovieActor::getId)
                .forEach(linkRepository::delete);

        repository.delete(id);
    }
}
